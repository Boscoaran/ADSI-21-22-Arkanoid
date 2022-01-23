package eus.ehu.adsi.arkanoid.controlador;

import java.sql.SQLException;
import java.sql.Time;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.xml.crypto.Data;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.view.game.core.*;
import eus.ehu.adsi.arkanoid.modelo.DataBase;
import eus.ehu.adsi.arkanoid.modelo.FraseMensaje;
import eus.ehu.adsi.arkanoid.modelo.Partida;
import eus.ehu.adsi.arkanoid.modelo.Usuario;
import eus.ehu.adsi.arkanoid.view.game.Config;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ArkanoidFrontera {

    private static ArkanoidFrontera mArkanoidFrontera = null;
    private LocalDateTime fechaHoraInicio;
    private String fechaHoraInicioStr;
    private int lvl;
    private String nombreUsuario;
    private Properties propiedad = null;
    private Session sesion = null;
    //Datos para JavaxMail
    private String correoArkanoid = "arkanoidrecovery@gmail.com";
    private String contrasenaArkanoid = "ARKpassword";

    private ArkanoidFrontera() {}

    public static ArkanoidFrontera getArkanoidFrontera() {
        if (mArkanoidFrontera == null) mArkanoidFrontera = new ArkanoidFrontera();
        return mArkanoidFrontera;
    }

    public String getNombre(){
        return nombreUsuario;
    }

    public JSONObject darVentaja(String nombreUsuario) {
        Usuario u = GestorUsuarios.getGestorUsuarios().buscarUsuarioGestor(nombreUsuario);
        int random = generarNumeroAleatorio(4, 1);
        return GestorPartidas.getGestorPartidas().crearVentaja(random, u);
    }

    public int generarNumeroAleatorio(int max, int min) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    public void cambiarAjustes(String colorBola, String colorPadel, String colorLadrillo, String colorFondo, Boolean sonido, String nombreUsuario) {
        try {
            DataBase.getmDataBase().cambiarAjustes(nombreUsuario, colorBola, colorPadel, colorLadrillo, colorFondo, sonido);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Config.setBackgroundColor(getColor("Fondo", nombreUsuario));
		Config.setLadrilloColor(getColor("Ladrillo", nombreUsuario));
		Config.setBolaColor(getColor("Bola", nombreUsuario));
		Config.setPaddleColor(getColor("Paddle",nombreUsuario));
        Config.setSonido(getSonido(nombreUsuario));
    }
    /**
     * Verificar el estado del inicio de sesión (Vista de Iniciar Sesión)
     * @param nombreUsuario el usuario que intenta iniciar sesión
     * @param contrasena contraseña con la intenta iniciar sesión
     * @return un JSON con la forma:
     *  { estado : boolean, mensaje : String }
     *  Si es True, String = nombreUsuario
     *  Si es False, String = mensaje de error correspondiente
     */
    public JSONObject comprobarInicio(String nombreUsuario, String contrasena) {

        //Inicializar variables
        //Estado del inicio (por defecto false)
        boolean correcto = false;
        //JSON a devolver
        JSONObject resultado = new JSONObject();

        //Buscar el usuario por su nombre de usuario
        Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario, contrasena);

        //Comprobar si el usuario existe
        if (U != null) {

            //Comprobar si la contraseña que ha introducido es la correcta para su cuenta
            correcto = GestorUsuarios.getGestorUsuarios().esContrasena(U, contrasena);

            if (!correcto) {
                //Es incorrecta, guardar un mensaje indicando la situación
                resultado.put("mensaje","Contraseña incorrecta.");
            } else {
                //Es correcta, inicio correcto, guardar el nombre de usuario
                resultado.put("mensaje", nombreUsuario);
            }
        } else {
            //No existe, guardar un mensaje indicando la situación
            resultado.put("mensaje", "El usuario no está registrado.");
        }
        //Poner el estado del inicio en el JSON
        resultado.put("estado", correcto);
        //Devolver JSON
        return resultado;
    }

    /**
     * Verificar recuperación de contraseña (Vista de Recuperar Contraseña | 1ª pantalla)
     * @param correo correo de la cuenta que se quiere recuperar la contraseña
     * @return un JSON con la forma:
     *  { estado : boolean, mensaje : String }
     *  Si es True, String = código que se ha enviado al correo
     *  Si es False, String = mensaje de error correspondiente
     */
    public JSONObject recuperarContrasena(String correo) {

        //Inicializar variables
        //Estado del inicio (por defecto false)
        boolean correcto = false;
        //JSON a devolver
        JSONObject resultado = new JSONObject();

        //Comprobar que el campo no esté vacío
        if (!correo.equals("")) {

            //Comprobar que el email tenga un formato válido
            if (correoValido(correo)) {

                //Buscar el usuario por su correo
                Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

                //Comprobar si el usuario existe
                if (U != null) {

                    //El usuario existe, mandar el código de recuperación al correo y guardar el código
                    resultado.put("mensaje", enviarEmail(correo));
                    //Indicar estado de la recuperación
                    correcto = true;

                } else {
                    //No existe, guardar un mensaje indicando la situación
                    resultado.put("mensaje", "El usuario no está registrado.");
                }
            } else {
                //No lo tiene, guardar un mensaje indicando la situación
                resultado.put("mensaje", "Correo no válido.");
            }
        } else {
            //Está vacío, guardar un mensaje indicando la situación
            resultado.put("mensaje", "Introduce un correo.");
        }
        //Poner el estado del inicio en el JSON
        resultado.put("estado", correcto);
        //Devolver JSON
        return resultado;
    }

    /**
     * Verificar el formato del correo
     * @param correo correo al que verificar formato
     * @return True si válido | False sino
     */
    private boolean correoValido(String correo) {

        //Expresión regular del correo electrónico
        String regex = "^(.+)@(.+)$";
        //Compilar el patrón que se busca (expresión regular)
        Pattern pattern = Pattern.compile(regex);
        //Comparar patrón con el correo
        Matcher matcher = pattern.matcher(correo);

        //Devolver si cumple o no
        return matcher.matches();
    }

    /**
     * Enviar un email con un código de recuperación de 6 dígitos
     * @param correo correo destino
     * @return código que se ha enviado
     */
    public String enviarEmail(String correo) {

        //Fuente: https://www.youtube.com/watch?v=Dj1t53SH7nk&t=703s

        //Comprobar que se haya inicializado la variable de propiedades
        if (propiedad == null) {
            //No se ha hecho, crear nuevo objeto
            propiedad = new Properties();

            //Añadir propiedades necesarias
            propiedad.put("mail.smtp.host", "smtp.gmail.com");
            propiedad.put("mail.smtp.starttls.enable", "true");
            propiedad.put("mail.smtp.port", "587");
            propiedad.put("mail.smtp.auth", "true");

            //Iniciar la sesión
            sesion = Session.getDefaultInstance(propiedad);
        }

        //Iniciar un nuevo mensaje en la sesión
        MimeMessage mail = new MimeMessage(sesion);
        //Inicializar la variable del código
        String codigo = "";

        //Intentar enviar email
        try {
            //Definir elementos del email
            //Emisor
            mail.setFrom(new InternetAddress(contrasenaArkanoid));
            //Receptor
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            //Sujeto
            mail.setSubject("[Arkanoid] Recuperar Contraseña");
            //Obtener código
            codigo = String.format("%06d", generarNumeroAleatorio(999999,100000));
            //Cuerpo (texto)
            mail.setText("El código de recuperación es: " + codigo);

            //Iniciar transporte SMTP
            Transport transporte = sesion.getTransport("smtp");
            //Conectar con cuenta de Arkanoid
            transporte.connect(correoArkanoid, contrasenaArkanoid);
            //Mandar mensaje
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            //Cerrar transporte
            transporte.close();

        //Capturar excepciones
        } catch (AddressException e) {
            return "";
        } catch (MessagingException e) {
            return "";
        }
        return codigo;
    }

    /**
     * Verificar recuperación de contraseña (Vista de Recuperar Contraseña | 2ª pantalla)
     * @param correo correo de la cuenta que quiere recuperar la contraseña
     * @param codigo código que se ha enviado a su correo (CORRECTO)
     * @param codigoIntroducido código que ha introducido el usuario
     * @param cNueva1 contraseña nueva que quiere el usuario
     * @param cNueva2 confirmación de la contraseña
     * @return un JSON con la forma:
     *  { estado : boolean, mensaje : String }
     *  Si es True, String = vacío
     *  Si es False, String = mensaje de error correspondiente
     */
    public JSONObject comprobarCodigo(String correo, String codigo, String codigoIntroducido, String cNueva1, String cNueva2) {

        //Inicializar variables
        //Estado del inicio (por defecto false)
        boolean correcto = false;
        //JSON a devolver
        JSONObject resultado = new JSONObject();

        //Comprobar que todos los campos estén rellenos
        if (!(correo.equals("") || codigo.equals("") || codigoIntroducido.equals("") || cNueva1.equals("") || cNueva2.equals(""))) {
            //Buscar el usuario por su correo
            Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

            //Comprobar si el usuario existe
            if (U != null) {

                //Comprobar si el código enviado y el introducido conciden
                if (codigo.equals(codigoIntroducido)) {

                    //Comprobar si las dos contraseñas conciden
                    if (cNueva1.equals(cNueva2)) {

                        //Comprobar que la contraseña tenga un formato válido
                        if (contrasenaValida(cNueva1)) {

                            //Comprobar que la contraseña que quiere usar no es la misma que la que tenía
                            if (!GestorUsuarios.getGestorUsuarios().esContrasena(U, cNueva1)) {
                                //Cambiar la contraseña
                                GestorUsuarios.getGestorUsuarios().cambiarContrasena(U, cNueva1);
                                //Indicar estado de la recuperación
                                correcto = true;
                            } else {
                                //Es la misma, guardar un mensaje indicando la situación
                                resultado.put("mensaje", "La contraseña nueva no puede ser igual a la anterior.");
                            }
                        } else {
                            //No lo tiene, guardar un mensaje indicando la situación
                            resultado.put("mensaje", "Formato de contraseña no válido (longitud máxima 20 caracteres).");
                        }
                    } else {
                        //Son distintas, guardar un mensaje indicando la situación
                        resultado.put("mensaje", "Las contraseñas no coinciden.");
                    }
                } else {
                    //Son distintos, guardar un mensaje indicando la situación
                    resultado.put("mensaje", "Código incorrecto.");
                }
            } else {
                //No existe, guardar un mensaje indicando la situación
                resultado.put("mensaje", "El usuario no está registrado.");
            }
        } else {
            //Hay alguno vacío, guardar un mensaje indicando la situación
            resultado.put("mensaje", "Rellena todos los campos.");
        }
        //Poner el estado del inicio en el JSON
        resultado.put("estado", correcto);
        //Devolver JSON
        return resultado;
    }

    /**
     * Verificar el registro de una cuenta usuario
     * @param nombreUsuario nombre del usuario
     * @param correo correo electrónico
     * @param contrasena1 contraseña
     * @param contrasena2 confirmación de la contraseña
     * @return un JSON con la forma:
     *  { estado : boolean, mensaje : String }
     *  Si es True, String = nombreUsuario
     *  Si es False, String = mensaje de error correspondiente
     */
    public JSONObject comprobarRegistro(String nombreUsuario, String correo, String contrasena1, String contrasena2) {

        //Inicializar variables
        //Estado del inicio (por defecto false)
        boolean correcto = false;
        //JSON a devolver
        JSONObject resultado = new JSONObject();

        //Comprobar que todos los campos estén rellenos
        if (!(nombreUsuario.equals("") || correo.equals("") || contrasena1.equals("") || contrasena2.equals(""))) {
            //Buscar el usuario por su nombre de usuario
            Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuarioGestor(nombreUsuario);

            //Comprobar que el usuario no exista
            if (U == null) {
                //Buscar el usuario por su correo
                U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

                //Comprobar que el usuario no exista
                if (U == null) {

                    //Comprobar que el correo tenga un formato válido
                    if (correoValido(correo)) {

                        //Comprobar que el nombre de usuario tenga un formato válido
                        if (nombreUsuario.length() <= 20) {

                            //Comprobar si las dos contraseñas conciden
                            if (contrasena1.equals(contrasena2)) {

                                //Comprobar que la contraseña tenga un formato válido
                                if (contrasenaValida(contrasena1)) {
                                    //Registrar el usuario
                                    GestorUsuarios.getGestorUsuarios().registrarUsuario(nombreUsuario, correo, contrasena1);
                                    //Indicar estado de la recuperación
                                    correcto = true;
                                    //Guardar el nombre de usuario
                                    resultado.put("mensaje", nombreUsuario);
                                } else {
                                    //No tiene formato válido, guardar un mensaje indicando la situación
                                    resultado.put("mensaje", "Formato de contraseña no válido (longitud máxima 20 caracteres).");
                                }
                            } else {
                                //No coinciden, guardar un mensaje indicando la situación
                                resultado.put("mensaje", "Las contraseñas no coinciden.");
                            }
                        } else {
                            //No tiene formato válido, guardar un mensaje indicando la situación
                            resultado.put("mensaje", "Formato de nombre de usuario no válido (longitud máxima 20 caracteres).");
                        }
                    } else {
                        //No tiene formato válido, guardar un mensaje indicando la situación
                        resultado.put("mensaje", "Correo no válido.");
                    }
                } else {
                    //Sí existe, guardar un mensaje indicando la situación
                    resultado.put("mensaje", "Correo no disponible.");
                }
            } else {
                //Sí existe, guardar un mensaje indicando la situación
                resultado.put("mensaje", "Nombre de usuario no disponible.");
            }
        } else {
            //Hay alguno vacío, guardar un mensaje indicando la situación
            resultado.put("mensaje", "Rellena todos los campos.");
        }
        //Poner el estado del inicio en el JSON
        resultado.put("estado", correcto);
        //Devolver JSON
        return resultado;
    }

    /**
     * Verificar cambio de contraseña
     * @param nombreUsuario nombre de usuario de la cuenta que quiere cambiar de contraseña
     * @param cAnterior contraseña que tiene actualmente la cuenta
     * @param cNueva1 contraseña nueva que se quiere poner
     * @param cNueva2 confirmación de la contraseña
     * @return un JSON con la forma:
     *  { estado : boolean, mensaje : String }
     *  Si es True, String = nombreUsuario
     *  Si es False, String = mensaje de error correspondiente
     */
    public JSONObject comprobarCambio(String nombreUsuario, String cAnterior, String cNueva1, String cNueva2) {

        //Inicializar variables
        //Estado del inicio (por defecto false)
        boolean correcto = false;
        //JSON a devolver
        JSONObject resultado = new JSONObject();

        //Comprobar que todos los campos estén rellenos
        if (!(nombreUsuario.equals("") || cAnterior.equals("") || cNueva1.equals("") || cNueva2.equals(""))) {
            //Buscar el usuario por su nombre de usuario
            Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuarioGestor(nombreUsuario);

            //Comprobar si la contrasena anterior es correcta
            if (GestorUsuarios.getGestorUsuarios().esContrasena(U, cAnterior)) {

                //Comprobar que la contraseña que quiere usar no es la misma que la que tenía
                if (!cAnterior.equals(cNueva1)) {

                    //Comprobar si las dos contraseñas conciden
                    if (cNueva1.equals(cNueva2)) {

                        //Comprobar que la contraseña tenga un formato válido
                        if (contrasenaValida(cNueva1)) {
                            //Cambiar la contraseña
                            GestorUsuarios.getGestorUsuarios().cambiarContrasena(U, cNueva1);
                            //Indicar estado de la recuperación
                            correcto = true;
                            //Guardar el nombre de usuario
                            resultado.put("mensaje", nombreUsuario);
                        } else {
                            //No tiene formato válido, guardar un mensaje indicando la situación
                            resultado.put("mensaje", "Formato de contraseña no válido (longitud máxima 20 caracteres).");
                        }
                    } else {
                        //No coinciden, guardar un mensaje indicando la situación
                        resultado.put("mensaje", "Las contraseñas no coinciden.");
                    }
                } else {
                    //Es la misma, guardar un mensaje indicando la situación
                    resultado.put("mensaje", "La contraseña nueva no puede ser igual a la anterior.");
                }
            } else {
                //No es correcta, guardar un mensaje indicando la situación
                resultado.put("mensaje", "Contraseña incorrecta.");
            }
        } else {
            //Hay alguno vacío, guardar un mensaje indicando la situación
            resultado.put("mensaje", "Rellena todos los campos.");
        }
        //Poner el estado del inicio en el JSON
        resultado.put("estado", correcto);
        //Devolver JSON
        return resultado;
    }

    public JSONObject jugadorPos(int pos, int nivel, String jugador){
        if (jugador == null){
            try {
				return DataBase.getmDataBase().jugadorPosGlobal(pos, nivel);
			} catch (SQLException e) {
                System.err.println(e);
                return null;
            }
        } else {
            try {
                return DataBase.getmDataBase().jugadorPosIndividual(pos, jugador, nivel);
            } catch (SQLException e) {
                System.err.println(e);
                return null;
            }
        }
    }

    public int nPartidas(String jugador, int nivel) {
        if (jugador == null){
            try {
				return DataBase.getmDataBase().nPartidasGlobal(nivel);
			} catch (SQLException e) {
                System.err.println(e);
				return 0;
			}
        }else{
            try {
                return DataBase.getmDataBase().nPartidasIndividual(jugador, nivel);
            } catch (SQLException e) {
                System.err.println(e);
                return 0;
            }
        }
    }

    public void comenzarPartida(int nivel, boolean sonido){
        //DataBase.getmDataBase().crearPartida(nivel, nombreJugador);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime fechaHoraInicio = LocalDateTime.now();
        fechaHoraInicioStr = dtf.format(fechaHoraInicio);
        lvl = nivel;
        if (sonido){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/X2Download.com - Plan B - Si No Le Contesto [Official Video] (64 kbps).wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
           } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.err.println(ex);
           }
        }
        ArkanoidThread arkanoidThread = new ArkanoidThread();
        arkanoidThread.start();
    }

    public int getLvl(){
        return lvl;
    }

    public String generarMensaje(String pNombreUsuario) {
    	FraseMensaje f1= new FraseMensaje("pUsuario ha pResultado con una puntuacion de pPuntuacion. ");
    	FraseMensaje f2= new FraseMensaje("La maxima puntuacion de pUsuario es pMaxPuntuacion. ");
    	FraseMensaje f3= new FraseMensaje("");	//luego se crear� una frase por cada premio conseguido en la partida
    	
    	Usuario usuario=GestorUsuarios.getGestorUsuarios().buscarUsuarioGestor(pNombreUsuario);
    	int maxPunt=GestorPartidas.getGestorPartidas().obtenerMaxPuntuacionHistorica(usuario);
    	Partida partida=GestorPartidas.getGestorPartidas().buscarPartidaActual(usuario);	//revisar si esta parte se puede eliminar
    	//igual a estas alturas ya hay fechaFin en la partida, si no la hay, habr�a que ponerla justo aqu�
    
    	JSONObject datosPartida=GestorPartidas.getGestorPartidas().obtenerDatosPartida(partida,maxPunt);
    	f1.asignarValoresAParametros(1,datosPartida);
    	f2.asignarValoresAParametros(2,datosPartida);
    	f3.asignarValoresAParametros(3,datosPartida);	//si no se ha conseguido ning�n premio, no se har� nada
    	
    	//pasar mensaje a string
    	String mensaje=f1.getFrase()+f2.getFrase()+f3.getFrase();
    	
    	return mensaje;
    }
    
    public JSONObject cargarDatosPersonalizacion(String nombre) {
        try {
            nombreUsuario = nombre;
            return DataBase.getmDataBase().cargarDatosPersonalizacion(nombre);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public JSONObject getColores(String objeto, String nombre){
        JSONObject colores;
        if (objeto == "fondo"){
            try {
                colores = DataBase.getmDataBase().getColoresFondo(nombre);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e);
                return null;
            }
        } else if (objeto == "bola"){
            try {
                colores = DataBase.getmDataBase().getColoresBola(nombre);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e);
                return null;
            }
        }else if (objeto == "paddle"){
            try {
                colores = DataBase.getmDataBase().getColoresPaddle(nombre);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e);
                return null;
            }
        }else {
            try {
                colores = DataBase.getmDataBase().getColoresLadrillos(nombre);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e);
                return null;
            }
        }
        return colores;
    }

    public boolean getSonido(String nombre) {
        boolean b = false;
        try {
            b = DataBase.getmDataBase().getSonido(nombre);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public Color getColor(String obj, String nombre) {
        String colorStr = null;
        try {
            colorStr = DataBase.getmDataBase().getColor(obj, nombre);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        Color color;
        switch (colorStr) {
            case "Rojo":
                color = Color.RED;
                break;
            case "Verde":
                color = Color.GREEN;
                break;
            case "Morado":
                color = Color.MAGENTA;
                break;
            case "Naranja":
                color = Color.ORANGE;
                break;
            case "Cyan":
                color = Color.CYAN;
                break;
            case "Blanco":
                color = Color.WHITE;
                break;
            case "Azul":
                color = Color.BLUE;
                break;
            case "Negro":
                color = Color.BLACK;
                break;
            default:
                color = Color.PINK;
                break;
        }
        return color;
    }
    /**
     * Verificar formatode la contraseña
     * @param contrasena contraseña a verificar
     * @return True si válido | False sino
     */
    private boolean contrasenaValida(String contrasena) {
        return contrasena.length() <= 20;
    }

    // MÉTODOS PARA PRUEBAS

    /**
     * Borrar todos los usuarios
     */
    public void borrarUsuarios() {
        GestorUsuarios.getGestorUsuarios().borrarUsuarios();
    }
}
