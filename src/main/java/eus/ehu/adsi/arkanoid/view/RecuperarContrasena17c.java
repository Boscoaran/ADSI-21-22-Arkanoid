package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecuperarContrasena17c extends JFrame {

    private String correo;
    private String codigo;
    private JTextField codigoIntroducido;
    private JPasswordField contrasena1;
    private JPasswordField contrasena2;
    private Font impact = AddFont.createFont();

    public RecuperarContrasena17c(String pCorreo, String pCodigo) {

        this.correo = pCorreo;
        this.codigo = pCodigo;
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

        this.setLayout(new BorderLayout());

        JLabel textoCorreo = new JLabel("Se ha enviado un correo con el código para cambiar la contraseña");
        textoCorreo.setForeground(Config.FONT_COLOR);
        textoCorreo.setFont(impact.deriveFont(20.0f));
        JPanel titulo = new JPanel();
        titulo.setBackground(Config.BACKGROUND_COLOR);
        titulo.add(textoCorreo);
        this.add(titulo, BorderLayout.PAGE_START);

        JPanel campos = new JPanel();
        campos.setBackground(Config.BACKGROUND_COLOR);
        campos.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel textoCodigo = new JLabel("Código:");
        textoCodigo.setForeground(Config.FONT_COLOR);
        textoCodigo.setFont(impact.deriveFont(20.0f));
        c.gridx = 0;
        c.gridy = 0;
        campos.add(textoCodigo, c);
        codigoIntroducido = new JTextField("", 20);
        c.gridx = 1;
        c.gridy = 0;
        campos.add(codigoIntroducido, c);

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

        JLabel textoVolver = new JLabel("¿No te ha llegado el código?:");
        textoVolver.setForeground(Config.FONT_COLOR);
        textoVolver.setFont(impact.deriveFont(20.0f));
        JPanel enviar = new JPanel();
        enviar.setBackground(Config.BACKGROUND_COLOR);
        enviar.add(textoVolver);
        enviar.add(botonVolver());
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        campos.add(enviar, c);

        this.add(campos, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.setBackground(Config.BACKGROUND_COLOR);
        botones.add(botonAceptar());
        botones.add(botonCancelar());
        this.add(botones, BorderLayout.PAGE_END);
    }

    //Método para crear el botón de volver a enviar, que mandará un nuevo correo
    private JButton botonVolver() {

        JButton volver = new JButton("Volver a enviar");
        volver.setFont(impact.deriveFont(20.0f));
        volver.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String codigo = ArkanoidFrontera.getArkanoidFrontera().enviarEmail(correo);
                cerrarActual();
                new RecuperarContrasena17c(correo, codigo);
            }
        });
        return volver;
    }

    //Método para crear el botón de aceptar, que mandará a comprobar los datos
    private JButton botonAceptar() {

        JButton aceptar = new JButton("Aceptar");
        aceptar.setFont(impact.deriveFont(20.0f));
        aceptar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Intentar recuperar contraseña con los datos proporcionados
                JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo(correo, codigo, codigoIntroducido.getText(), String.valueOf(contrasena1.getPassword()), String.valueOf(contrasena2.getPassword()));
                //Comprobar el estado de la recuperación
                if (!resultado.getBoolean("estado")) {
                    //Si ha sido incorrecto mostrar mensaje de error, con el mensaje que corresponda
                    new MensajeError((String) resultado.get("mensaje"));

                } else {
                    //Si ha sido exitoso, cerrar pantalla actual
                    cerrarActual();
                    //Abrir pantalla de Inicio de Sesión
                    new IniciarSesion17a();
                }
            }
        });
        return aceptar;
    }

    //Método para crear el botón de cancelar, que abrirá la pantalla de Recuperación
    private JButton botonCancelar() {

        JButton cancelar = new JButton("Cancelar");
        cancelar.setFont(impact.deriveFont(20.0f));
        cancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cerrarActual();
                new CorreoRecuperacion17b();
            }
        });
        return cancelar;
    }

    //Método para poder cerrar la pantalla actual, útil cuando se trata con clases anónimas
    private void cerrarActual() {
        this.dispose();
    }
}
