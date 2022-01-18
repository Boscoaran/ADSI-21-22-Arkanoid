package eus.ehu.adsi.arkanoid.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import eus.ehu.adsi.arkanoid.view.game.Config;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ranking22 {

	private JFrame frame;
    private JPanel panelTitulo;
	private JPanel panelMedio;
	private JLabel lblGlobal;
	private JLabel lblNombre;
	private JLabel lblIndividual;
	private JComboBox comboBoxNiveles;
	private JPanel panelRankings;
	private JPanel panelGlobal;
	private JPanel panelIndividual;
	private JLabel lblRanking;
	private JPanel panelVolver;
	private JButton btnVolver;
	private JLabel lblJugador;
	private JPanel panelSeparador;
    private int nJugadoresGlobal;
    private int nJugadoresIndividual;
    private String jugador;
    private Font impact = AddFont.createFont();

	
	public Ranking22(String nombre) {
		jugador = nombre;
        initialize();
	}

	private void initialize() {
		frame = new JFrame();
        frame.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        frame.setTitle("Ranking");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[] {36/131*Config.SCREEN_WIDTH, 68/131*Config.SCREEN_WIDTH, 0, 17/131*Config.SCREEN_WIDTH, 10/131*Config.SCREEN_WIDTH};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		GridBagConstraints gbc_panelTitulo = new GridBagConstraints();
		gbc_panelTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitulo.fill = GridBagConstraints.BOTH;
		gbc_panelTitulo.gridx = 0;
		gbc_panelTitulo.gridy = 0;
		frame.getContentPane().add(getPanelTitulo(), gbc_panelTitulo);
		GridBagConstraints gbc_panelMedio = new GridBagConstraints();
		gbc_panelMedio.insets = new Insets(0, 0, 5, 0);
		gbc_panelMedio.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelMedio.gridx = 0;
		gbc_panelMedio.gridy = 1;
		frame.getContentPane().add(getPanelMedio(), gbc_panelMedio);
		GridBagConstraints gbc_panelRankings = new GridBagConstraints();
		gbc_panelRankings.insets = new Insets(0, 0, 5, 0);
		gbc_panelRankings.fill = GridBagConstraints.BOTH;
		gbc_panelRankings.gridx = 0;
		gbc_panelRankings.gridy = 2;
		frame.getContentPane().add(getPanelRankings(), gbc_panelRankings);
		GridBagConstraints gbc_panelVolver = new GridBagConstraints();
		gbc_panelVolver.fill = GridBagConstraints.BOTH;
		gbc_panelVolver.gridx = 0;
		gbc_panelVolver.gridy = 3;
		frame.getContentPane().add(getPanelVolver(), gbc_panelVolver);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
	}

    //////////////////////////TITULO////////////////////////////////
    private JPanel getPanelTitulo() {
		if (panelTitulo == null) {
			panelTitulo = new JPanel();
			panelTitulo.setBackground(Color.BLACK);
			panelTitulo.add(getLblRanking());
		}
		return panelTitulo;
	}

    private JLabel getLblRanking() {
		if (lblRanking == null) {
			lblRanking = new JLabel("Ranking");
			lblRanking.setForeground(Color.WHITE);
            lblRanking.setBackground(Color.BLACK);
			lblRanking.setFont(impact.deriveFont(60.0f));
            }
		return lblRanking;
	}

    ///////////////////////////////////PANEL MEDIO/////////////////////
	private JPanel getPanelMedio() {
		if (panelMedio == null) {
			panelMedio = new JPanel();
			panelMedio.setBackground(Color.BLACK);
			GridBagLayout gbl_panelMedio = new GridBagLayout();
			gbl_panelMedio.columnWidths = new int[] {100, 100, 100, 0};
			gbl_panelMedio.rowHeights = new int[]{42, 0, 0};
			gbl_panelMedio.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panelMedio.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panelMedio.setLayout(gbl_panelMedio);
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.insets = new Insets(0, 30, 5, 5);
			gbc_lblNombre.gridx = 1;
			gbc_lblNombre.gridy = 0;
			panelMedio.add(getLblNombre(), gbc_lblNombre);
			GridBagConstraints gbc_lblGlobal = new GridBagConstraints();
			gbc_lblGlobal.insets = new Insets(0, 50, 0, 0);
			gbc_lblGlobal.gridx = 0;
			gbc_lblGlobal.gridy = 1;
			panelMedio.add(getLblGlobal(), gbc_lblGlobal);
			GridBagConstraints gbc_comboBoxNiveles = new GridBagConstraints();
			gbc_comboBoxNiveles.insets = new Insets(0, 30, 0, 5);
			//gbc_comboBoxNiveles.fill = GridBagConstraints.HORIZONTAL
			gbc_comboBoxNiveles.gridx = 1;
			gbc_comboBoxNiveles.gridy = 1;
			panelMedio.add(getComboBoxNiveles(), gbc_comboBoxNiveles);
			GridBagConstraints gbc_lblIndividual = new GridBagConstraints();
            gbc_lblIndividual.insets = new Insets(0, 0, 0, 50);
			gbc_lblIndividual.gridx = 2;
			gbc_lblIndividual.gridy = 1;
			panelMedio.add(getLblIndividual(), gbc_lblIndividual);
		}
		return panelMedio;
	}

	private JLabel getLblGlobal() {
		if (lblGlobal == null) {
			lblGlobal = new JLabel("Global");
			lblGlobal.setForeground(Color.WHITE);
			lblGlobal.setFont(impact.deriveFont(40.0f));
		}
		return lblGlobal;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel(jugador);
			lblNombre.setForeground(Color.RED);
			lblNombre.setFont(impact.deriveFont(40.0f));
		}
		return lblNombre;
	}

	private JLabel getLblIndividual() {
		if (lblIndividual == null) {
			lblIndividual = new JLabel("Individual");
			lblIndividual.setForeground(Color.WHITE);
			lblIndividual.setFont(impact.deriveFont(40.0f));
		}
		return lblIndividual;
	}

	private JComboBox getComboBoxNiveles() {
		if (comboBoxNiveles == null) {
			comboBoxNiveles = new JComboBox();
			comboBoxNiveles.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Fácil", "Medio", "Difícil"}));
            comboBoxNiveles.setFont(impact.deriveFont(30.0f));
            comboBoxNiveles.setForeground(Color.BLACK);
		}
		return comboBoxNiveles;
	}

    /////////////////////////////RANKINGS//////////////////////////////////////////////////
	private JPanel getPanelRankings() {
		if (panelRankings == null) {
			panelRankings = new JPanel();
			panelRankings.setBackground(Color.BLACK);
			panelRankings.setForeground(Color.BLACK);
			GridBagLayout gbl_panelRankings = new GridBagLayout();
			gbl_panelRankings.columnWidths = new int[] {30, 270, 5, 270, 30, 0};
			gbl_panelRankings.rowHeights = new int[]{217, 0};
			gbl_panelRankings.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panelRankings.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			panelRankings.setLayout(gbl_panelRankings);
			GridBagConstraints gbc_panelGlobal = new GridBagConstraints();
			gbc_panelGlobal.fill = GridBagConstraints.BOTH;
			gbc_panelGlobal.insets = new Insets(0, 0, 0, 35);
			gbc_panelGlobal.gridx = 1;
			gbc_panelGlobal.gridy = 0;
			panelRankings.add(getPanelGlobal(), gbc_panelGlobal);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 10, 0, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 2;
			gbc_panel.gridy = 0;
			panelRankings.add(getPanelSeparador(), gbc_panel);
			GridBagConstraints gbc_panelIndividual = new GridBagConstraints();
			gbc_panelIndividual.insets = new Insets(0, 60, 0, 5);
			gbc_panelIndividual.fill = GridBagConstraints.BOTH;
			gbc_panelIndividual.gridx = 3;
			gbc_panelIndividual.gridy = 0;
			panelRankings.add(getPanelIndividual(), gbc_panelIndividual);
            panelRankings.setBackground(Color.BLACK);
		}
		return panelRankings;
	}

	private JPanel getPanelGlobal() {
		if (panelGlobal == null) {
			panelGlobal = new JPanel();
			panelGlobal.setBackground(Color.BLACK);
			panelGlobal.setLayout(new GridLayout(10, 0, 0, 0));
            //nJugadoresGlobal = llamada a BD para ver cuantos jugadores hay guardados (necesario si hay menos de 10)
            for (int i=0; i<nJugadoresGlobal || i<10; i++){
                //llamada a BD para obtener el nombre y puntuación de jugador en posición i
                String nombre = "Bosco";
                int puntos = 100;
			    panelGlobal.add(getLblJugador(nombre, puntos));
            }
		}
		return panelGlobal;
	}

	private JPanel getPanelIndividual() {
		if (panelIndividual == null) {
			panelIndividual = new JPanel();
			panelIndividual.setBackground(Color.BLACK);
			panelIndividual.setLayout(new GridLayout(10, 0, 0, 0));
            for (int i=0; i<nJugadoresGlobal || i<10; i++){
                //llamada a BD para obtener el nombre y puntuación de jugador en posición i y nivel x
                String nombre = "Bosco";
                int puntos = 100;
			    panelIndividual.add(getLblJugador(nombre, puntos));
            }
		}
		return panelIndividual;
	}

    private JLabel getLblJugador(String nombre, int puntos) {
            String p = ".";
            String separacion = p.repeat(60 - nombre.length()- Integer.toString(puntos).length());
            lblJugador = new JLabel(nombre + separacion + puntos);
			lblJugador.setForeground(Color.WHITE);
            lblJugador.setFont(impact.deriveFont(20.0f));
		return lblJugador;
	}

	private JPanel getPanelSeparador() {
		if (panelSeparador == null) {
			panelSeparador = new JPanel();
            panelSeparador.setBackground(Color.WHITE);
		}
		return panelSeparador;
	}
	
    ///////////////////////////////VOLVER//////////////////////////////
	private JPanel getPanelVolver() {
		if (panelVolver == null) {
			panelVolver = new JPanel();
			panelVolver.setBackground(Color.BLACK);
			panelVolver.add(getBtnVolver());
		}
		return panelVolver;
	}

	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
            btnVolver.setBorderPainted(false);
            btnVolver.setFocusPainted(false);
            btnVolver.setContentAreaFilled(false);
            btnVolver.setForeground(Color.WHITE);
            btnVolver.setFont(impact.deriveFont(30.0f));
            btnVolver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
				}
				@Override
				public void mouseEntered(MouseEvent e){
					btnVolver.setForeground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e){
					btnVolver.setForeground(Color.WHITE);
				}
			});
		}
		return btnVolver;
	}
}