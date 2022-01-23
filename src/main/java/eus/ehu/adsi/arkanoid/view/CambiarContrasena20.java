package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CambiarContrasena20 extends JFrame {

    private String nombreUsuario;
    private JPasswordField contrasenaOriginal;
    private JPasswordField contrasena1;
    private JPasswordField contrasena2;
    private JButton cancelar;
    private JButton aceptar;
    private Font impact = AddFont.createFont();

    public CambiarContrasena20(String pNombreUsuario) {

        this.nombreUsuario = pNombreUsuario;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.setTitle("Cambiar contraseña");
        drawScene();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    //Método para dibujar el contenido de la pantalla
    private void drawScene() {

        this.setLayout(new BorderLayout());

        JPanel titulo = new JPanel();
        titulo.setBackground(Config.BACKGROUND_COLOR);
        titulo.setLayout(new GridLayout(2,0));

        JLabel textoCambiarContrasena = new JLabel("Cambiar contraseña");
        textoCambiarContrasena.setForeground(Config.FONT_COLOR);
        textoCambiarContrasena.setFont(impact.deriveFont(20.0f));
        titulo.add(textoCambiarContrasena);

        JLabel textoNombreUsuario = new JLabel(nombreUsuario);
        textoNombreUsuario.setForeground(Color.RED);
        textoNombreUsuario.setFont(impact.deriveFont(20.0f));
        titulo.add(textoNombreUsuario);

        JPanel contenedor = new JPanel();
        contenedor.setBackground(Config.BACKGROUND_COLOR);
        contenedor.setLayout(new GridBagLayout());
        contenedor.add(titulo);

        this.add(contenedor, BorderLayout.PAGE_START);

        JPanel campos = new JPanel();
        campos.setBackground(Config.BACKGROUND_COLOR);
        campos.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel textoContrasenaOriginal = new JLabel("Antigua contraseña:");
        textoContrasenaOriginal.setForeground(Config.FONT_COLOR);
        textoContrasenaOriginal.setFont(impact.deriveFont(20.0f));
        c.gridx = 0;
        c.gridy = 0;
        campos.add(textoContrasenaOriginal, c);
        contrasenaOriginal = new JPasswordField("", 20);
        c.gridx = 1;
        c.gridy = 0;
        campos.add(contrasenaOriginal, c);

        JLabel textoContrasena1 = new JLabel("Nueva contraseña:");
        textoContrasena1.setForeground(Config.FONT_COLOR);
        textoContrasena1.setFont(impact.deriveFont(20.0f));
        c.gridx = 0;
        c.gridy = 1;
        campos.add(textoContrasena1, c);
        contrasena1 = new JPasswordField("", 20);
        c.gridx = 1;
        c.gridy = 1;
        campos.add(contrasena1, c);

        JLabel textoContrasena2 = new JLabel("Confirmar contraseña:");
        textoContrasena2.setForeground(Config.FONT_COLOR);
        textoContrasena2.setFont(impact.deriveFont(20.0f));
        c.gridx = 0;
        c.gridy = 2;
        campos.add(textoContrasena2, c);
        contrasena2 = new JPasswordField("", 20);
        c.gridx = 1;
        c.gridy = 2;
        campos.add(contrasena2, c);

        this.add(campos, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.setBackground(Config.BACKGROUND_COLOR);
        botones.add(botonCancelar());
        botones.add(botonAceptar());
        this.add(botones, BorderLayout.PAGE_END);
    }
    //Método para crear el botón de cancelar, que abrirá la pantalla de Inicio
    private JButton botonCancelar() {
        if (cancelar == null) {
            cancelar = new JButton("Cancelar");
            cancelar.setFont(impact.deriveFont(20.0f));
            cancelar.setBorderPainted(false);
            cancelar.setFocusPainted(false);
            cancelar.setContentAreaFilled(false);
            cancelar.setForeground(Color.WHITE);

            cancelar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cerrarActual();
                    new MenuPrincipal18(nombreUsuario);
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    cancelar.setForeground(Color.RED);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    cancelar.setForeground(Color.WHITE);
                }
            });
        }
        return cancelar;
    }

    //Método para crear el botón de aceptar, que mandará a comprobar los datos
    private JButton botonAceptar() {
        if (aceptar == null) {
            aceptar = new JButton("Aceptar");
            aceptar.setFont(impact.deriveFont(20.0f));
            aceptar.setBorderPainted(false);
            aceptar.setFocusPainted(false);
            aceptar.setContentAreaFilled(false);
            aceptar.setForeground(Color.WHITE);

            aceptar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //Intentar cambiar contraseña cuenta con los datos proporcionados
                    JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio(nombreUsuario, String.valueOf(contrasenaOriginal.getPassword()), String.valueOf(contrasena1.getPassword()), String.valueOf(contrasena2.getPassword()));
                    //Comprobar el estado del cambio de contraseña
                    if (!resultado.getBoolean("estado")) {
                        //Si ha sido incorrecto mostrar mensaje de error, con el mensaje que corresponda
                        new MensajeError((String) resultado.get("mensaje"));

                    } else {
                        //Si ha sido exitoso, cerrar pantalla actual
                        cerrarActual();
                        //Abrir pantalla de Menú Principal con el nombre de usuario del jugador
                        new MenuPrincipal18((String) resultado.get("mensaje"));
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    aceptar.setForeground(Color.RED);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    aceptar.setForeground(Color.WHITE);
                }
            });
        }
        return aceptar;
    }

    //Método para poder cerrar la pantalla actual, útil cuando se trata con clases anónimas
    private void cerrarActual() {
        this.dispose();
    }
}
