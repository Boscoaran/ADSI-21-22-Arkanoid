package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IniciarSesion17a extends JFrame {

    private JTextField nombre;
    private JPasswordField contrasena;
    private JButton olvidar;
    private JButton cancelar;
    private JButton iniciar;
    private Font impact = AddFont.createFont();

    public IniciarSesion17a() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.setTitle("Iniciar sesión");
        drawScene();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    //Método para dibujar el contenido de la pantalla
    private void drawScene() {
        this.setLayout(new BorderLayout());

        JPanel campos = new JPanel();
        campos.setBackground(Color.BLACK);
        campos.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel textoNombre = new JLabel("Nombre:");
        textoNombre.setFont(impact.deriveFont(20.0f));
        textoNombre.setForeground(Config.FONT_COLOR);
        c.gridx = 0;
        c.gridy = 0;
        campos.add(textoNombre, c);
        nombre = new JTextField("", 20);
        c.gridx = 1;
        c.gridy = 0;
        campos.add(nombre,c);

        JLabel textoContrasena = new JLabel("Contraseña:");
        textoContrasena.setFont(impact.deriveFont(20.0f));
        textoContrasena.setForeground(Config.FONT_COLOR);
        c.gridx = 0;
        c.gridy = 1;
        campos.add(textoContrasena, c);
        contrasena = new JPasswordField("", 20);
        c.gridx = 1;
        c.gridy = 1;
        campos.add(contrasena, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        campos.add(botonOlvidar(), c);

        this.add(campos, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.setBackground(Color.BLACK);
        botones.add(botonCancelar());
        botones.add(botonIniciar());
        this.add(botones, BorderLayout.PAGE_END);
    }

    //Método para crear el botón de contrasena olvidada, que abrirá la pantalla de Recuperación
    private JButton botonOlvidar() {
        if (olvidar == null) {
            olvidar = new JButton("He olvidado mi contraseña");
            olvidar.setFont(impact.deriveFont(20.0f));
            olvidar.setBorderPainted(false);
            olvidar.setFocusPainted(false);
            olvidar.setContentAreaFilled(false);
            olvidar.setForeground(Color.WHITE);

            olvidar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cerrarActual();
                    new CorreoRecuperacion17b();
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    olvidar.setForeground(Color.RED);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    olvidar.setForeground(Color.WHITE);
                }
            });
        }
        return olvidar;
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

    //Método para crear el botón de iniciar sesión, que mandará a comprobar los datos
    private JButton botonIniciar() {
        if (iniciar == null) {
            iniciar = new JButton("Iniciar Sesión");
            iniciar.setFont(impact.deriveFont(20.0f));
            iniciar.setBorderPainted(false);
            iniciar.setFocusPainted(false);
            iniciar.setContentAreaFilled(false);
            iniciar.setForeground(Color.WHITE);

            iniciar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //Intentar iniciar sesión con los datos proporcionados
                    JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarInicio(nombre.getText(), String.valueOf(contrasena.getPassword()));
                    //Comprobar el estado del inicio de sesión
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
                    iniciar.setForeground(Color.RED);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    iniciar.setForeground(Color.WHITE);
                }
            });
        }
        return iniciar;
    }

    //Método para poder cerrar la pantalla actual, útil cuando se trata con clases anónimas
    private void cerrarActual() {
        this.dispose();
    }
}
