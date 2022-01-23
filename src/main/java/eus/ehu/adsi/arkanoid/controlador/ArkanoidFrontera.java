package eus.ehu.adsi.arkanoid.controlador;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import javax.swing.SwingUtilities;

import eus.ehu.adsi.arkanoid.view.PremiosConseguidos23;
import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.core.ArkanoidThread;
import eus.ehu.adsi.arkanoid.modelo.Arkanoid;
import eus.ehu.adsi.arkanoid.modelo.DataBase;
import eus.ehu.adsi.arkanoid.modelo.Usuario;
import eus.ehu.adsi.arkanoid.modelo.Partida;
import eus.ehu.adsi.arkanoid.modelo.Premio;

public class ArkanoidFrontera {
    private static ArkanoidFrontera mArkanoidFrontera = null;
    private LocalDateTime fechaHoraInicio;
    private String fechaHoraInicioStr;
    private int lvl;

    private ArkanoidFrontera() {}

    public static ArkanoidFrontera getArkanoidFrontera() {
        if (mArkanoidFrontera == null) mArkanoidFrontera = new ArkanoidFrontera();
        return mArkanoidFrontera;
    }

    public JSONObject darVentaja(String nombreUsuario) {
        Usuario u = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);
        int random = generarNumeroAleatorio(4, 1);
        System.out.println(random);
        return GestorPartidas.getGestorPartidas().crearVentaja(random, u);
    }

    private int generarNumeroAleatorio(int i, int j) {
        Random r = new Random();
        return r.nextInt(i - j + 1) + j;
    }

    public void cambiarAjustes(String colorBola, String colorPadel, String colorLadrillo, String colorFondo, boolean sonido, String nombreUsuario) {
        Usuario u = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);
        u.cambiarAjustes(colorBola, colorPadel, colorLadrillo, colorFondo, sonido);
    }
    public JSONObject comprobarInicio(String nombreUsuario, String contrasena) {

        boolean correcto = false;
        JSONObject resultado = new JSONObject();

        Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);

        if (U != null) {
            correcto = GestorUsuarios.getGestorUsuarios().esContrasena(U, contrasena);

            if (!correcto) {
                resultado.put("mensaje","Contraseña incorrecta.");
            } else {
                resultado.put("mensaje", nombreUsuario);
            }
        } else {
            resultado.put("mensaje", "El usuario no está registrado.");
        }
        resultado.put("estado", correcto);

        return resultado;
    }

    public JSONObject recuperarContrasena(String correo) {

        JSONObject resultado = new JSONObject();

        Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

        if (U != null) {

            resultado.put("mensaje", enviarEmail(correo));

        } else {
            resultado.put("mensaje", "El usuario no está registrado.");
        }

        resultado.put("estado", U != null);

        return resultado;
    }

    public String enviarEmail(String correo) {
        //TODO
        return "CODIGO";
    }

    public JSONObject comprobarCodigo(String correo, String codigo, String codigoIntroducido, String cNueva1, String cNueva2) {

        boolean correcto = false;
        JSONObject resultado = new JSONObject();
        Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

        if (U != null) {

            if (codigo.equals(codigoIntroducido)) {

                if (cNueva1.equals(cNueva2)) {

                    GestorUsuarios.getGestorUsuarios().cambiarContrasena(U, cNueva1);
                    correcto = true;

                } else {
                    resultado.put("mensaje", "Las contraseñas no coinciden.");
                }
            } else {
                resultado.put("mensaje", "Código incorrecto.");
            }
        } else {
            resultado.put("mensaje", "El usuario no está registrado.");
        }

        resultado.put("estado", correcto);
        return resultado;
    }

    public JSONObject comprobarRegistro(String nombreUsuario, String correo, String contrasena1, String contrasena2) {

        boolean correcto = false;
        JSONObject resultado = new JSONObject();
        Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);

        if (U == null) {

            U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

            if (U == null) {

                if (contrasena1.equals(contrasena2)) {

                    GestorUsuarios.getGestorUsuarios().registrarUsuario(nombreUsuario, correo, contrasena1);
                    correcto = true;
                    resultado.put("mensaje", nombreUsuario);
                } else {
                    resultado.put("mensaje", "Las contraseñas no coinciden.");
                }
            } else {
                resultado.put("mensaje", "Correo no disponible.");
            }
        } else {
            resultado.put("mensaje", "Nombre de usuario no disponible.");
        }
        resultado.put("estado", correcto);

        return resultado;
    }

    public JSONObject comprobarCambio(String nombreUsuario, String cAnterior, String cNueva1, String cNueva2) {

        boolean correcto = false;
        JSONObject resultado = new JSONObject();
        Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);

        if (GestorUsuarios.getGestorUsuarios().esContrasena(U, cAnterior)) {

            if (cNueva1.equals(cNueva2)) {
                GestorUsuarios.getGestorUsuarios().cambiarContrasena(U, cNueva1);
                correcto = true;
                resultado.put("mensaje", nombreUsuario);
            } else {
                resultado.put("mensaje", "Las contraseñas no coinciden.");
            }
        } else {
            resultado.put("mensaje", "Contraseña incorrecta.");
        }
        resultado.put("estado", correcto);

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

    public void comenzarPartida(int nivel){
        //DataBase.getmDataBase().crearPartida(nivel, nombreJugador);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime fechaHoraInicio = LocalDateTime.now();
        fechaHoraInicioStr = dtf.format(fechaHoraInicio);
        lvl = nivel;
        ArkanoidThread arkanoidThread = new ArkanoidThread();
        arkanoidThread.start();
    }

    public void terminarPartida(Partida p){ //TODO: EN VEZ DE UN PARÁMETRO IGUAL TIENE QUE HACER EL NEW PARTIDA
        p.getJugador().actualizarPartida(p);
        comprobarPremios(p);
    }

    public int getLvl(){
        return lvl;
    }

    private void comprobarPremios(Partida p){
        List<Premio> PC = GestorPremios.getGestorPremios().comprobarPremios(p);
        List<Premio> PO = GestorPremios.getGestorPremios().otorgarPremios(p.getJugador(),PC);
        if (PO.size()>0){
            GestorPremios.getGestorPremios().annadirPremios(p, PO);
            JSONObject premios = GestorPremios.getGestorPremios().obtenerNombresDescripciones(PO);
            new PremiosConseguidos23(premios);
        }
        else{
            //TODO: ABRIR PANTALLA COMPARTIR RESULTADOS
        }

    }
}
