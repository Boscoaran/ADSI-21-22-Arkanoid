package eus.ehu.adsi.arkanoid.view;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JLabel;

import javax.swing.JPanel;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class SeleccionarNivel19 {

	private JFrame frame;
	private JPanel panelCentro;
	private JButton btnComenzar;
	private JButton btnCancelar;
	private JLabel lblNivel;
	private JComboBox<String> comboBoxNivel;
	private Font impact = AddFont.createFont();
	

	public SeleccionarNivel19() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		frame.setTitle("Seleccionar Nivel");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getPanelCentro());
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.BLACK);
		frame.setVisible(true);
	}

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setBounds(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
			GridBagLayout gbl_panelCentro = new GridBagLayout();
			gbl_panelCentro.columnWidths = new int[]{150, 150, 150, 150, 150, 150};
			gbl_panelCentro.rowHeights = new int[]{200, 50, 50, 100, 50, 200};
			gbl_panelCentro.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelCentro.setLayout(gbl_panelCentro);
			GridBagConstraints gbc_lblNivel = new GridBagConstraints();
			gbc_lblNivel.anchor = GridBagConstraints.EAST;
			gbc_lblNivel.insets = new Insets(0, 0, 0, 0);
			gbc_lblNivel.gridx = 2;
			gbc_lblNivel.gridy = 1;
			panelCentro.add(getLblNivel(), gbc_lblNivel);
			GridBagConstraints gbc_comboBoxNivel = new GridBagConstraints();
			gbc_comboBoxNivel.insets = new Insets(0, 0, 0, 0);
			gbc_comboBoxNivel.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxNivel.gridx = 2;
			gbc_comboBoxNivel.gridy = 2;
			panelCentro.add(getComboBoxNivel(), gbc_comboBoxNivel);
			GridBagConstraints gbc_btnComenzar = new GridBagConstraints();
			gbc_btnComenzar.insets = new Insets(0, 0, 0, 0);
			gbc_btnComenzar.gridx = 3;
			gbc_btnComenzar.gridy = 4;
			panelCentro.add(getBtnComenzar(), gbc_btnComenzar);
			GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
			gbc_btnCancelar.insets = new Insets(0, 0, 0, 0);
			gbc_btnCancelar.gridx = 1;
			gbc_btnCancelar.gridy = 4;
			panelCentro.add(getBtnCancelar(), gbc_btnCancelar);
			panelCentro.setBackground(Color.BLACK);
		}
		return panelCentro;
	}

	private JButton getBtnComenzar() {
		if (btnComenzar == null) {
			btnComenzar = new JButton("Comenzar");
			btnComenzar.setBorderPainted(false);
			btnComenzar.setFocusPainted(false);
			btnComenzar.setContentAreaFilled(false);
			btnComenzar.setForeground(Color.WHITE);
			btnComenzar.setFont(impact.deriveFont(30.0f));
			btnComenzar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					ArkanoidFrontera.getArkanoidFrontera().comenzarPartida(comboBoxNivel.getSelectedIndex()+1, Config.getSonido());
				}
				@Override
				public void mouseEntered(MouseEvent e){
					btnComenzar.setForeground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e){
					btnComenzar.setForeground(Color.WHITE);
				}
			});
		}
		return btnComenzar;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBorderPainted(false);
			btnCancelar.setFocusPainted(false);
			btnCancelar.setContentAreaFilled(false);
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setFont(impact.deriveFont(30.0f));
			btnCancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
				}
				@Override
				public void mouseEntered(MouseEvent e){
					btnCancelar.setForeground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e){
					btnCancelar.setForeground(Color.WHITE);
				}
			});
		}
		return btnCancelar;
	}
	private JLabel getLblNivel() {
		if (lblNivel == null) {
			lblNivel = new JLabel("Escoge un nivel", JLabel.CENTER);
			lblNivel.setFont(impact.deriveFont(60.0f));
			lblNivel.setForeground(Color.WHITE);
		}
		return lblNivel;
	}

	private JComboBox<String> getComboBoxNivel() {
		if (comboBoxNivel == null) {
			comboBoxNivel = new JComboBox<String>();
			comboBoxNivel.setModel(new DefaultComboBoxModel<String>(new String[] {"F\u00E1cil", "Medio", "Dif\u00EDcil"}));
			comboBoxNivel.setForeground(Color.BLACK);
			comboBoxNivel.setFont(impact.deriveFont(30.0f));
		}
		return comboBoxNivel;
	}
}