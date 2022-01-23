package eus.ehu.adsi.arkanoid.view;

import eus.ehu.adsi.arkanoid.view.game.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal18 extends JFrame {

    private String nombreUsuario;

    public MenuPrincipal18(String pNombreUsuario) {

        this.nombreUsuario = pNombreUsuario;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.setTitle("Menú principal");
        drawScene();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void drawScene() {

        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new FlowLayout());

        JLabel textoNombre = new JLabel(nombreUsuario);
        textoNombre.setForeground(Config.FONT_COLOR);
        this.add(textoNombre);

        this.add(botonJugar());
        this.add(botonRanking());
        this.add(botonAjustes());
        this.add(botonCerrar());
        this.add(botonCambiar());
    }


    private JButton botonJugar() {

        JButton jugar = new JButton("JUGAR");
        jugar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new SeleccionarNivel19();
            }
        });
        return jugar;
    }

    private JButton botonRanking() {

        JButton ranking = new JButton("Ranking");
        ranking.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Ranking, añadir fig");
            }
        });
        return ranking;
    }

    private JButton botonAjustes() {

        JButton ajustes = new JButton("Ajustes");
        ajustes.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                new Personalizacion21(nombreUsuario);
            }
        });
        return ajustes;
    }

    private JButton botonCerrar() {

        JButton cerrar = new JButton("Cerrar sesión");
        cerrar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Cerrar sesión, añadir fig");
            }
        });
        return cerrar;
    }

    private JButton botonCambiar() {

        JButton cambiar = new JButton("Cambiar contraseña");
        cambiar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new CambiarContrasena20(nombreUsuario);
            }
        });
        return cambiar;
    }
}
