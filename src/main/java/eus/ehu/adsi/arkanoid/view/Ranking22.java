package eus.ehu.adsi.arkanoid.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.logging.log4j.core.config.builder.api.Component;
import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.modelo.DataBase;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

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
	private JPanel panelGlobalUpdate;
	private JPanel panelIndividual;
	private JPanel panelIndividualUpdate;
	private JLabel lblRanking;
	private JPanel panelVolver;
	private JButton btnVolver;
	private JLabel lblJugador;
	private JPanel panelSeparador;
	private String jugador;
	private Font impact = AddFont.createFont();
	private GridBagConstraints gbc_panelGlobal = new GridBagConstraints();
	private GridBagConstraints gbc_panelIndividual = new GridBagConstraints();
	private GridBagConstraints gbc_panelSeparador = new GridBagConstraints();


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
			comboBoxNiveles.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					panelRankings.remove(panelGlobal);
					panelRankings.remove(panelIndividual);
					panelRankings.remove(panelSeparador);
					panelRankings.add(getPanelGlobal(), gbc_panelGlobal);
					panelRankings.add(getPanelSeparador(), gbc_panelSeparador);
					panelRankings.add(getPanelIndividual(), gbc_panelIndividual);
					panelRankings.revalidate();
					panelRankings.repaint();
				}

			});
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
			gbc_panelGlobal.fill = GridBagConstraints.BOTH;
			gbc_panelGlobal.insets = new Insets(0, 0, 0, 35);
			gbc_panelGlobal.gridx = 1;
			gbc_panelGlobal.gridy = 0;
			panelRankings.add(getPanelGlobal(), gbc_panelGlobal);
			gbc_panelSeparador.insets = new Insets(0, 10, 0, 5);
			gbc_panelSeparador.fill = GridBagConstraints.BOTH;
			gbc_panelSeparador.gridx = 2;
			gbc_panelSeparador.gridy = 0;
			panelRankings.add(getPanelSeparador(), gbc_panelSeparador);
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
		panelGlobal = new JPanel();
		panelGlobal.setBackground(Color.BLACK);
		panelGlobal.setLayout(new GridLayout(10, 0, 0, 0));
		int nPartidas = ArkanoidFrontera.getArkanoidFrontera().nPartidas(null, comboBoxNiveles.getSelectedIndex());
		for (int i=1; i<=nPartidas &&  i<10; i++){
			JSONObject jugadorPuntuacion = ArkanoidFrontera.getArkanoidFrontera().jugadorPos(i, comboBoxNiveles.getSelectedIndex(), null);
			panelGlobal.add(getLblJugador(jugadorPuntuacion.getString("nombre"), jugadorPuntuacion.getInt("puntos")));
		}
		return panelGlobal;
	}

	private JPanel updatePanelGlobal() {
		panelGlobalUpdate = new JPanel();
		panelGlobalUpdate.setBackground(Color.BLACK);
		panelGlobalUpdate.setLayout(new GridLayout(10, 0, 0, 0));
		int nPartidas = ArkanoidFrontera.getArkanoidFrontera().nPartidas(null, comboBoxNiveles.getSelectedIndex());
		for (int i=1; i<=nPartidas &&  i<10; i++){
			JSONObject jugadorPuntuacion = ArkanoidFrontera.getArkanoidFrontera().jugadorPos(i, comboBoxNiveles.getSelectedIndex(), null);
			panelGlobalUpdate.add(getLblJugador(jugadorPuntuacion.getString("nombre"), jugadorPuntuacion.getInt("puntos")));
		}
		return panelGlobalUpdate;
	}

	private JPanel getPanelIndividual() {
		panelIndividual = new JPanel();
		panelIndividual.setBackground(Color.BLACK);
		panelIndividual.setLayout(new GridLayout(10, 0, 0, 0));
		int nPartidas = ArkanoidFrontera.getArkanoidFrontera().nPartidas(jugador, comboBoxNiveles.getSelectedIndex());
		for (int i=1; i<=nPartidas && i<10; i++){
			JSONObject jugadorPuntuacion = ArkanoidFrontera.getArkanoidFrontera().jugadorPos(i, comboBoxNiveles.getSelectedIndex(), jugador);
			panelIndividual.add(getLblJugador(jugadorPuntuacion.getString("nombre"), jugadorPuntuacion.getInt("puntos")));
		}
		return panelIndividual;
	}

	private JPanel updatePanelIndividual() {
		panelIndividualUpdate = new JPanel();
		panelIndividualUpdate.setBackground(Color.BLACK);
		panelIndividualUpdate.setLayout(new GridLayout(10, 0, 0, 0));
		int nPartidas = ArkanoidFrontera.getArkanoidFrontera().nPartidas(jugador, comboBoxNiveles.getSelectedIndex());
		for (int i=1; i<=nPartidas && i<10; i++){
			JSONObject jugadorPuntuacion = ArkanoidFrontera.getArkanoidFrontera().jugadorPos(i, comboBoxNiveles.getSelectedIndex(), jugador);
			panelIndividualUpdate.add(getLblJugador(jugadorPuntuacion.getString("nombre"), jugadorPuntuacion.getInt("puntos")));
		}
		return panelIndividualUpdate;
	}

<<<<<<< HEAD
    private JLabel getLblJugador(String nombre, Integer puntos) {
            String p = ".";
			String puntosStr = Integer.toString(puntos);
            String separacion = p.repeat(60 - nombre.length()-puntosStr.length()*(puntosStr.length()));
            lblJugador = new JLabel(nombre + separacion + puntos);
			lblJugador.setForeground(Color.WHITE);
            lblJugador.setFont(impact.deriveFont(20.0f));
=======
	private JLabel getLblJugador(String nombre, Integer puntos) {
		String p = ".";
		String puntosStr = Integer.toString(puntos);
		String separacion = p.repeat(60 - nombre.length()-puntosStr.length()*(puntosStr.length()));
		lblJugador = new JLabel(nombre + separacion + puntos);
		lblJugador.setForeground(Color.WHITE);
		lblJugador.setFont(impact.deriveFont(20.0f));
>>>>>>> origin/feature-premios
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
					new MenuPrincipal18(jugador);
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