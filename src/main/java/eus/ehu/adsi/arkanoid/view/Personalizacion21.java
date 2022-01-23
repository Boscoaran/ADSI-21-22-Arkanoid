package eus.ehu.adsi.arkanoid.view;
 
import eus.ehu.adsi.arkanoid.view.game.Config;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;

import javax.swing.JPanel;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.Font;

public class Personalizacion21 {

	private JFrame frame;
	private JPanel panelCentro;
	private JButton btnAceptar;
    private JButton btnCancelar;
    private JButton btnSonido;
	private JLabel lblBackGround;
    private JLabel lblBall;
    private JLabel lblPaddle;
    private JLabel lblLadrillo;
	private JComboBox<String> comboBoxBackGround;
    private JComboBox<String> comboBoxBall;
    private JComboBox<String> comboBoxPaddle;
    private JComboBox<String> comboBoxLadrillo;
	private boolean sonido;
	private String nombreUsuario;
	private JLabel lblTitulo;
	private Font impact = AddFont.createFont();
	
	
	public Personalizacion21(String nombre) {
		nombreUsuario = nombre;
		initialize();
	}
	
	
	private void initialize() {
		JSONObject j = ArkanoidFrontera.getArkanoidFrontera().cargarDatosPersonalizacion(nombreUsuario);
		frame = new JFrame();
		frame.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		frame.setTitle("Personalización");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getPanelCentro(j));
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.BLACK);
		frame.setVisible(true);
	}
	
	private JPanel getPanelCentro(JSONObject j) {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setBounds(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
			GridBagLayout gbl_panelCentro = new GridBagLayout();
			gbl_panelCentro.columnWidths = new int[]{200, 150, 150, 150, 150, 150};
			gbl_panelCentro.rowHeights = new int[]{110, 110, 110, 110, 110, 50};
			gbl_panelCentro.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelCentro.setLayout(gbl_panelCentro);

			//LBL TITULO
			GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
			gbc_lblTitulo.anchor = GridBagConstraints.EAST;
			gbc_lblTitulo.insets = new Insets(0, 0, 0, 0);
			gbc_lblTitulo.gridx = 3;
			gbc_lblTitulo.gridy = 0;
			panelCentro.add(getLblTitulo(), gbc_lblTitulo);

            //LBL BG
			GridBagConstraints gbc_lblBackGround = new GridBagConstraints();
			gbc_lblBackGround.anchor = GridBagConstraints.EAST;
			gbc_lblBackGround.insets = new Insets(0, 0, 0, 0);
			gbc_lblBackGround.gridx = 0;
			gbc_lblBackGround.gridy = 0;
			panelCentro.add(getLblBackGround(), gbc_lblBackGround);

            //LBL BALL
            GridBagConstraints gbc_lblBall = new GridBagConstraints();
			gbc_lblBall.anchor = GridBagConstraints.EAST;
			gbc_lblBall.insets = new Insets(0, 0, 0, 0);
			gbc_lblBall.gridx = 0;
			gbc_lblBall.gridy = 1;
			panelCentro.add(getLblBall(), gbc_lblBall);

            //LBL PADDLE
            GridBagConstraints gbc_lblPaddle = new GridBagConstraints();
			gbc_lblPaddle.anchor = GridBagConstraints.EAST;
			gbc_lblPaddle.insets = new Insets(0, 0, 0, 0);
			gbc_lblPaddle.gridx = 0;
			gbc_lblPaddle.gridy = 2;
			panelCentro.add(getLblPaddle(), gbc_lblPaddle);

            //LBL LADRILLO
            GridBagConstraints gbc_lblLadrillo = new GridBagConstraints();
			gbc_lblLadrillo.anchor = GridBagConstraints.EAST;
			gbc_lblLadrillo.insets = new Insets(0, 0, 0, 0);
			gbc_lblLadrillo.gridx = 0;
			gbc_lblLadrillo.gridy = 3;
			panelCentro.add(getLblLadrillo(), gbc_lblLadrillo);


            //CB BG
			GridBagConstraints gbc_comboBoxBackGround = new GridBagConstraints();
			gbc_comboBoxBackGround.insets = new Insets(0, 10, 0, 0);
			gbc_comboBoxBackGround.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxBackGround.gridx = 1;
			gbc_comboBoxBackGround.gridy = 0;
			panelCentro.add(getComboBoxBackGround(), gbc_comboBoxBackGround);

            //CB BALL
            GridBagConstraints gbc_comboBoxBall = new GridBagConstraints();
			gbc_comboBoxBall.insets = new Insets(0, 10, 0, 0);
			gbc_comboBoxBall.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxBall.gridx = 1;
			gbc_comboBoxBall.gridy = 1;
			panelCentro.add(getComboBoxBall(), gbc_comboBoxBall);


            //CB PADDLE
            GridBagConstraints gbc_comboBoxPaddle = new GridBagConstraints();
			gbc_comboBoxPaddle.insets = new Insets(0, 10, 0, 0);
			gbc_comboBoxPaddle.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxPaddle.gridx = 1;
			gbc_comboBoxPaddle.gridy = 2;
			panelCentro.add(getComboBoxPaddle(), gbc_comboBoxPaddle);
			
			

            //CB LADRILLO
            GridBagConstraints gbc_comboBoxLadrillo = new GridBagConstraints();
			gbc_comboBoxLadrillo.insets = new Insets(0, 10, 0, 0);
			gbc_comboBoxLadrillo.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxLadrillo.gridx = 1;
			gbc_comboBoxLadrillo.gridy = 3;
			panelCentro.add(getComboBoxLadrillo(), gbc_comboBoxLadrillo);

            //BTN SONIDO
			GridBagConstraints gbc_btnSonido= new GridBagConstraints();
			gbc_btnSonido.insets = new Insets(0, 0, 0, 0);
            gbc_btnSonido.fill = GridBagConstraints.BOTH;
			gbc_btnSonido.gridx = 3;
			gbc_btnSonido.gridy = 2;
			panelCentro.add(getBtnSonido(j.getInt("Sonido")), gbc_btnSonido);

            //BTN ACEPTAR
			GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
			gbc_btnAceptar.insets = new Insets(0, 0, 0, 0);
			gbc_btnAceptar.gridx = 3;
			gbc_btnAceptar.gridy = 4;
			panelCentro.add(getBtnAceptar(), gbc_btnAceptar);

            //BTN CANCELAR
            GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
			gbc_btnCancelar.insets = new Insets(0, 0, 0, 0);
			gbc_btnCancelar.gridx = 1;
			gbc_btnCancelar.gridy = 4;
			panelCentro.add(getBtnCancelar(), gbc_btnCancelar);
			panelCentro.setBackground(Color.BLACK);
          	}
		return panelCentro;
	}
	
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.setBorderPainted(false);
			btnAceptar.setFocusPainted(false);
			btnAceptar.setContentAreaFilled(false);
			btnAceptar.setForeground(Color.WHITE);
			btnAceptar.setFont(impact.deriveFont(45.0f));
			btnAceptar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					String colorBola = comboBoxBall.getSelectedItem().toString();
					String colorPadel = comboBoxPaddle.getSelectedItem().toString();
					String colorLadrillo = comboBoxLadrillo.getSelectedItem().toString();
					String colorFondo = comboBoxBackGround.getSelectedItem().toString();
					ArkanoidFrontera.getArkanoidFrontera().cambiarAjustes(colorBola, colorPadel, colorLadrillo, colorFondo, sonido, nombreUsuario);
					frame.dispose();
					new MenuPrincipal18(nombreUsuario);

				}
				@Override
				public void mouseEntered(MouseEvent e){
					btnAceptar.setForeground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e){
					btnAceptar.setForeground(Color.WHITE);
				}
			});
		}
		return btnAceptar;
	}

    private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBorderPainted(false);
			btnCancelar.setFocusPainted(false);
			btnCancelar.setContentAreaFilled(false);
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setFont(impact.deriveFont(45.0f));
			btnCancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					new MenuPrincipal18(nombreUsuario);
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

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Ajustes", JLabel.CENTER);
			lblTitulo.setFont(impact.deriveFont(80.0f));
			lblTitulo.setForeground(Color.WHITE);
			}
		return lblTitulo;
	}
	private JLabel getLblBackGround() {
		if (lblBackGround == null) {
			lblBackGround = new JLabel("Fondo:", JLabel.CENTER);
			lblBackGround.setFont(impact.deriveFont(30.0f));
			lblBackGround.setForeground(Color.WHITE);
			}
		return lblBackGround;
	}

    private JLabel getLblBall() {
		if (lblBall == null) {
			lblBall = new JLabel("Bola:", JLabel.CENTER);
			lblBall.setFont(impact.deriveFont(30.0f));
			lblBall.setForeground(Color.WHITE);
			}
		return lblBall;
	}

    private JLabel getLblPaddle() {
		if (lblPaddle == null) {
			lblPaddle = new JLabel("Paddle:", JLabel.CENTER);
			lblPaddle.setFont(impact.deriveFont(30.0f));
			lblPaddle.setForeground(Color.WHITE);
			}
		return lblPaddle;
	}

    private JLabel getLblLadrillo() {
		if (lblLadrillo == null) {
			lblLadrillo = new JLabel("Ladrillo:", JLabel.CENTER);
			lblLadrillo.setFont(impact.deriveFont(30.0f));
			lblLadrillo.setForeground(Color.WHITE);
			}
		return lblLadrillo;
	}

    private JComboBox<String> getComboBoxBackGround() {
		if (comboBoxBackGround == null) {
			comboBoxBackGround = new JComboBox<String>();
			JSONObject colores = ArkanoidFrontera.getArkanoidFrontera().getColores("fondo", nombreUsuario);
			comboBoxBackGround.setModel(new DefaultComboBoxModel<String>());
			this.añadirColores(comboBoxBackGround, colores);
			comboBoxBackGround.setForeground(Color.BLACK);
			comboBoxBackGround.setFont(impact);
		}
		return comboBoxBackGround;
	}

    private JComboBox<String> getComboBoxBall() {
		if (comboBoxBall == null) {
			comboBoxBall = new JComboBox<String>();
			JSONObject colores = ArkanoidFrontera.getArkanoidFrontera().getColores("bola", nombreUsuario);
			comboBoxBall.setModel(new DefaultComboBoxModel<String>());
			this.añadirColores(comboBoxBall, colores);
			comboBoxBall.setForeground(Color.BLACK);
			comboBoxBall.setFont(impact);
		}
		return comboBoxBall;
	}

    private JComboBox<String> getComboBoxPaddle() {
		if (comboBoxPaddle == null) {
			comboBoxPaddle = new JComboBox<String>();
			JSONObject colores = ArkanoidFrontera.getArkanoidFrontera().getColores("paddle", nombreUsuario);
			comboBoxPaddle.setModel(new DefaultComboBoxModel<String>());
			this.añadirColores(comboBoxPaddle, colores);
			comboBoxPaddle.setForeground(Color.BLACK);
			comboBoxPaddle.setFont(impact);
		}
		return comboBoxPaddle;
	}

    private JComboBox<String> getComboBoxLadrillo() {
		if (comboBoxLadrillo == null) {
			comboBoxLadrillo = new JComboBox<String>();
			JSONObject colores = ArkanoidFrontera.getArkanoidFrontera().getColores("ladrillo", nombreUsuario);
			comboBoxLadrillo.setModel(new DefaultComboBoxModel<String>());
			this.añadirColores(comboBoxLadrillo, colores);
			comboBoxLadrillo.setForeground(Color.BLACK);
			comboBoxLadrillo.setFont(impact);
		}
		return comboBoxLadrillo;
	}

	private void añadirColores(JComboBox comboBox, JSONObject colores){
		Iterator <String> keys = colores.keys();
		while(keys.hasNext()){
			String key = keys.next();
			if ((int)colores.get(key) == 1){
				List<Character> lChar = new ArrayList<>();
				for (char c : key.toCharArray()){
					lChar.add(c);
				}
				boolean primeraPalabra = true;
				List<Character> lChar2 = new ArrayList<>();
				for (Character car : lChar) {
					if (!primeraPalabra || Character.isUpperCase(car)){
						primeraPalabra = false;
						lChar2.add(car);
					}
				}
				StringBuilder sb = new StringBuilder();
				for (Character car : lChar2){
					sb.append(car);
				}
				comboBox.addItem(sb.toString());
			}
		}
		
	}

    private JButton getBtnSonido(int s) {
		if (btnSonido == null) {
			
            btnSonido = new JButton("");
			try {
				ImageIcon imgIcon;
				if (s == 1) {
					sonido = true;
					imgIcon = new ImageIcon("src/main/resources/unmuted.png");
				} else {
					sonido = false;
					imgIcon = new ImageIcon("src/main/resources/muted.png");
				} 
                Image image = imgIcon.getImage();
				Image imageReszie = image.getScaledInstance(100,80, java.awt.Image.SCALE_SMOOTH);
				imgIcon = new ImageIcon(imageReszie);
				btnSonido.setIcon(imgIcon);
            } catch (Exception ex){
                System.out.println(ex);
            }

            btnSonido.setContentAreaFilled(false);
			btnSonido.setFocusPainted(false);
			btnSonido.setBorderPainted(false);
            btnSonido.setBackground(Color.BLACK);
            btnSonido.setBorder(null);
			btnSonido.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (sonido){
						ImageIcon imgIcon = new ImageIcon("src/main/resources/muted.png");
                		Image image = imgIcon.getImage();
						Image imageReszie = image.getScaledInstance(100,80, java.awt.Image.SCALE_SMOOTH);
						imgIcon = new ImageIcon(imageReszie);
						btnSonido.setIcon(imgIcon);
						sonido = false;
					} else {
						ImageIcon imgIcon = new ImageIcon("src/main/resources/unmuted.png");
               			Image image = imgIcon.getImage();
						Image imageReszie = image.getScaledInstance(100,80, java.awt.Image.SCALE_SMOOTH);
						imgIcon = new ImageIcon(imageReszie);
						btnSonido.setIcon(imgIcon);
						sonido = true;
					}
				}
			});
		}
		return btnSonido;
	}

}