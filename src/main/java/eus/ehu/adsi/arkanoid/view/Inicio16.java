package eus.ehu.adsi.arkanoid.view;

import eus.ehu.adsi.arkanoid.view.game.Config;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio16 extends JFrame {

    public Inicio16() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.setTitle("Inicio");
        drawScene();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void drawScene() {

        this.getContentPane().setBackground(Config.BACKGROUND_COLOR);
        this.setLayout(new FlowLayout());

        JLabel textoArkanoid = new JLabel("ARKANOID");
        textoArkanoid.setForeground(Config.FONT_COLOR);
        this.add(textoArkanoid);

        JLabel textoCrocs = new JLabel("CROCS");
        textoCrocs.setForeground(Config.FONT_COLOR);
        this.add(textoCrocs);

        JLabel textoNombres = new JLabel("Diego Marta - Joel Bra - Joseba Rodríguez - Jon Ander López de Ahumada - Galder García - Jon García - Bosco Aranguren");
        textoNombres.setForeground(Config.FONT_COLOR);
        this.add(textoNombres);

        this.add(botonIniciar());
        this.add(botonRegistrar());
    }

    private JButton botonIniciar() {

        JButton iniciar = new JButton("Iniciar sesión");
        iniciar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new IniciarSesion17a();
            }
        });
        return iniciar;
    }

    private JButton botonRegistrar() {

        JButton registrar = new JButton("Registrarse");
        registrar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new CrearCuenta17d();
            }
        });
        return registrar;
    }
}
