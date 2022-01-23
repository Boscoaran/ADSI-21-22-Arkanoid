package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrearCuenta17d extends JFrame {

    private JTextField nombre;
    private JTextField correo;
    private JPasswordField contrasena1;
    private JPasswordField contrasena2;
    private JButton cancelar;
    private JButton crear;
    private Font impact = AddFont.createFont();

    public CrearCuenta17d() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.setTitle("Registrarse");
        drawScene();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.BLACK);
    }

    //Método para dibujar el contenido de la pantalla
    private void drawScene() {
        this.setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setLayout(new GridLayout(2, 0));
        north.setBackground(Color.BLACK);
        north.setSize(new Dimension(Config.SCREEN_HEIGHT/3, Config.SCREEN_WIDTH));
        JLabel titulo = new JLabel("Introduce tus datos:", JLabel.CENTER);
        titulo.setFont(impact.deriveFont(50.0f));
        titulo.setForeground(Color.WHITE);
        JLabel titulo2 = new JLabel("Introduce tus datos:");
        titulo2.setFont(impact.deriveFont(30.0f));
        titulo2.setForeground(Color.BLACK);
        north.add(titulo);
        north.add(titulo2);
        this.add(north, BorderLayout.NORTH);
        JPanel campos = new JPanel();
        campos.setBackground(Color.BLACK);
        campos.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel textoNombre = new JLabel("Nombre:");
        textoNombre.setForeground(Color.WHITE);
        textoNombre.setFont(impact.deriveFont(30.0f));
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 0;
        campos.add(textoNombre, c);
        nombre = new JTextField("", 30);
        c.gridx = 1;
        c.gridy = 0;
        campos.add(nombre, c);

        JLabel textoCorreo = new JLabel("Correo:");
        textoCorreo.setForeground(Color.WHITE);
        textoCorreo.setFont(impact.deriveFont(30.0f));
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 1;
        campos.add(textoCorreo, c);
        correo = new JTextField("", 30);
        c.gridx = 1;
        c.gridy = 1;
        campos.add(correo, c);

        JLabel textoContrasena1 = new JLabel("Contraseña:");
        textoContrasena1.setForeground(Color.WHITE);
        textoContrasena1.setFont(impact.deriveFont(30.0f));
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 2;
        campos.add(textoContrasena1, c);
        contrasena1 = new JPasswordField("", 30);
        c.gridx = 1;
        c.gridy = 2;
        campos.add(contrasena1, c);

        JLabel textoContrasena2 = new JLabel("Confirmar contraseña:");
        textoContrasena2.setForeground(Color.WHITE);
        textoContrasena2.setFont(impact.deriveFont(30.0f));
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 3;
        campos.add(textoContrasena2, c);
        contrasena2 = new JPasswordField("", 30);
        c.gridx = 1;
        c.gridy = 3;
        campos.add(contrasena2, c);

        this.add(campos, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.setBackground(Color.BLACK);
        botones.add(botonCancelar());
        botones.add(botonCrear());
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

    //Método para crear el botón de crear cuenta, que mandará a comprobar los datos
    private JButton botonCrear() {
        if (crear == null) {
            crear = new JButton("Crear cuenta");
            crear.setFont(impact.deriveFont(20.0f));
            crear.setBorderPainted(false);
            crear.setFocusPainted(false);
            crear.setContentAreaFilled(false);
            crear.setForeground(Color.WHITE);

            crear.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //Intentar crear una cuenta con los datos proporcionados
                    JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro(nombre.getText(), correo.getText(), String.valueOf(contrasena1.getPassword()), String.valueOf(contrasena2.getPassword()));
                    //Comprobar el estado del registro
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
                    crear.setForeground(Color.RED);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    crear.setForeground(Color.WHITE);
                }
            });
        }
        return crear;
    }

    //Método para poder cerrar la pantalla actual, útil cuando se trata con clases anónimas
    private void cerrarActual() {
        this.dispose();
    }
}
