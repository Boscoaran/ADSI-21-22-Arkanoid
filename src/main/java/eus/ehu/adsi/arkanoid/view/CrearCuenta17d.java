package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearCuenta17d extends JFrame {

    private JTextField nombre;
    private JTextField correo;
    private JPasswordField contrasena1;
    private JPasswordField contrasena2;

    public CrearCuenta17d() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.setTitle("Registrarse");
        drawScene();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    //Método para dibujar el contenido de la pantalla
    private void drawScene() {
        this.setLayout(new BorderLayout());

        JPanel campos = new JPanel();
        campos.setBackground(Config.BACKGROUND_COLOR);
        campos.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel textoNombre = new JLabel("Nombre:");
        textoNombre.setForeground(Config.FONT_COLOR);
        c.gridx = 0;
        c.gridy = 0;
        campos.add(textoNombre, c);
        nombre = new JTextField("", 20);
        c.gridx = 1;
        c.gridy = 0;
        campos.add(nombre, c);

        JLabel textoCorreo = new JLabel("Correo:");
        textoCorreo.setForeground(Config.FONT_COLOR);
        c.gridx = 0;
        c.gridy = 1;
        campos.add(textoCorreo, c);
        correo = new JTextField("", 20);
        c.gridx = 1;
        c.gridy = 1;
        campos.add(correo, c);

        JLabel textoContrasena1 = new JLabel("Contraseña:");
        textoContrasena1.setForeground(Config.FONT_COLOR);
        c.gridx = 0;
        c.gridy = 2;
        campos.add(textoContrasena1, c);
        contrasena1 = new JPasswordField("", 20);
        c.gridx = 1;
        c.gridy = 2;
        campos.add(contrasena1, c);

        JLabel textoContrasena2 = new JLabel("Confirmar contraseña:");
        textoContrasena2.setForeground(Config.FONT_COLOR);
        c.gridx = 0;
        c.gridy = 3;
        campos.add(textoContrasena2, c);
        contrasena2 = new JPasswordField("", 20);
        c.gridx = 1;
        c.gridy = 3;
        campos.add(contrasena2, c);

        this.add(campos, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.setBackground(Config.BACKGROUND_COLOR);
        botones.add(botonCancelar());
        botones.add(botonCrear());
        this.add(botones, BorderLayout.PAGE_END);
    }

    //Método para crear el botón de cancelar, que abrirá la pantalla de Inicio
    private JButton botonCancelar() {

        JButton cancelar = new JButton("Cancelar");
        cancelar.setFont(Font.getFont(Config.FONT));
        cancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cerrarActual();
                new Fig16();
            }
        });
        return cancelar;
    }

    //Método para crear el botón de crear cuenta, que mandará a comprobar los datos
    private JButton botonCrear() {

        JButton crear = new JButton("Crear cuenta");
        crear.setFont(Font.getFont(Config.FONT));
        crear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro(nombre.getText(), correo.getText(), String.valueOf(contrasena1.getPassword()), String.valueOf(contrasena2.getPassword()));
//                * Definición de JSON:
//                { : boolean, : String }
//                    Si es True, String = nombreUsuario
//                    Si es False, String = mensaje de error correspondiente

                //Comprobar el estado del registro
                if (!resultado.getBoolean("estado")) {
                    //Si ha sido incorrecto mostrar mensaje de error, con el mensaje que corresponda
                    new MensajeError((String) resultado.get("mensaje"), false);

                } else {
                    //Si ha sido exitoso, cerrar pantalla actual
                    cerrarActual();
                    //Abrir pantalla de Menú Principal con el nombre de usuario del jugador
                    new Fig18((String) resultado.get("mensaje"));
                }
            }
        });
        return crear;
    }

    //Método para poder cerrar la pantalla actual, útil cuando se trata con clases anónimas
    private void cerrarActual() {
        this.dispose();
    }
}
