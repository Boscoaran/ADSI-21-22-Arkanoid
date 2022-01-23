package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CorreoRecuperacion17b extends JFrame {

    private JTextField correo;
    private JButton cancelar;
    private JButton enviar;
    private Font impact = AddFont.createFont();

    public CorreoRecuperacion17b() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.setTitle("Recuperar Contraseña");
        drawScene();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    //Método para dibujar el contenido de la pantalla
    private void drawScene() {

        this.getContentPane().setBackground(Config.BACKGROUND_COLOR);
        this.setLayout(new GridLayout(3,0));

        JLabel textoCorreo = new JLabel("Introduce el correo asociado a tu cuenta para recuperar la contraseña:");
        textoCorreo.setForeground(Config.FONT_COLOR);
        textoCorreo.setFont(impact.deriveFont(20.0f));
        JPanel texto = new JPanel();
        texto.setLayout(new GridBagLayout());
        texto.setBackground(Config.BACKGROUND_COLOR);
        texto.add(textoCorreo);
        this.add(texto);

        correo = new JTextField("", 20);
        JPanel campo = new JPanel();
        campo.setBackground(Config.BACKGROUND_COLOR);
        campo.add(correo);
        this.add(campo);

        JPanel botones = new JPanel();
        botones.setBackground(Config.BACKGROUND_COLOR);
        botones.add(botonCancelar());
        botones.add(botonEnviar());
        this.add(botones);
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
                    new Inicio16();
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

    //Método para crear el botón de enviar, que mandará a comprobar los datos
    private JButton botonEnviar() {
        if (enviar == null) {
            enviar = new JButton("Enviar correo");
            enviar.setFont(impact.deriveFont(20.0f));
            enviar.setBorderPainted(false);
            enviar.setFocusPainted(false);
            enviar.setContentAreaFilled(false);
            enviar.setForeground(Color.WHITE);

            enviar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //Intentar recuperar contraseña con los datos proporcionados
                    JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().recuperarContrasena(correo.getText());
                    //Comprobar el estado de la recuperación
                    if (!resultado.getBoolean("estado")) {
                        //Si ha sido incorrecto mostrar mensaje de error, con el mensaje que corresponda
                        new MensajeError((String) resultado.get("mensaje"));

                    } else {
                        //Si ha sido exitoso, cerrar pantalla actual
                        cerrarActual();
                        //Abrir pantalla de Recuperar contraseña con el correo del usuario, y pasando el código que se ha mandado al email
                        new RecuperarContrasena17c(correo.getText(), (String) resultado.get("mensaje"));
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    enviar.setForeground(Color.RED);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    enviar.setForeground(Color.WHITE);
                }
            });
        }
        return enviar;
    }

    //Método para poder cerrar la pantalla actual, útil cuando se trata con clases anónimas
    private void cerrarActual() {
        this.dispose();
    }
}
