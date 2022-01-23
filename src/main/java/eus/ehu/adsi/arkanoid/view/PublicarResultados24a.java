package eus.ehu.adsi.arkanoid.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.json.JSONObject;
import java.awt.Font;

public class PublicarResultados24a {

	private JFrame frameFigura;
	private JPanel panel;
	private JTextArea labelMensaje;
	private JButton botonInstagram;
	private JButton botonTwitter;
	private JButton botonFacebook;
	private JButton botonTerminar;
	private JSONObject datosPartida;
	private String mensaje;
	private Font impact = AddFont.createFont();

	public PublicarResultados24a(JSONObject jo) {
		datosPartida=jo;
		//Generacion del mensaje:
		mensaje = ArkanoidFrontera.getArkanoidFrontera().generarMensaje(datosPartida);

		initialize();
	}

	private void initialize() {
		frameFigura=new JFrame();
		frameFigura.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		frameFigura.setTitle("Partida terminada");
		frameFigura.setResizable(false);
		frameFigura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameFigura.setBackground(Color.BLACK);
		//frameFigura.getContentPane().setBackground(Config.BACKGROUND_COLOR);
		frameFigura.getContentPane().add(getPanel());
		frameFigura.setVisible(true);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();

			GridLayout layout= new GridLayout(6,1);
			layout.setVgap(20);

			panel.setBackground(Color.BLACK);

			panel.setLayout(layout);
			JLabel label1 = new JLabel("La partida ha terminado, aqui puedes ver tus resultados:");
			label1.setForeground(Color.WHITE);
			label1.setFont(impact.deriveFont(20.0f));
			panel.add(label1);
			panel.add(getLabelMensaje());

			JLabel label2 = new JLabel("Comparte tu resultado en tus redes sociales:");
			label2.setForeground(Color.WHITE);
			label2.setFont(impact.deriveFont(20.0f));

			panel.add(label2);

			FlowLayout layoutRedes = new FlowLayout();
			JPanel panelRedes = new JPanel();

			panelRedes.setBackground(Color.BLACK);

			panelRedes.setLayout(layoutRedes);

			panelRedes.add(getBotonInstagram());
			panelRedes.add(getBotonTwitter());
			panelRedes.add(getBotonFacebook());

			panel.add(panelRedes);
			JLabel label3 = new JLabel("Para volver al menu principal, pulsa aqui:");
			label3.setForeground(Color.WHITE);
			label3.setFont(impact.deriveFont(20.0f));
			panel.add(label3);

			panel.add(getBotonTerminar());
		}
		return panel;
	}

	private JTextArea getLabelMensaje() {
		if (labelMensaje == null) {
			labelMensaje = new JTextArea(mensaje);
			labelMensaje.setForeground(Color.BLACK);
			labelMensaje.setFont(impact.deriveFont(15.0f));
			labelMensaje.setBorder(BorderFactory.createLineBorder(Color.RED));
			labelMensaje.setLineWrap(true);
			labelMensaje.setWrapStyleWord(false);
			labelMensaje.setEditable(false);
		}
		return labelMensaje;
	}

	private JButton getBotonInstagram() {
		if (botonInstagram == null) {
			botonInstagram = new JButton("Instagram");
			botonInstagram.setBorderPainted(false);
			botonInstagram.setFocusPainted(false);
			botonInstagram.setContentAreaFilled(false);
			botonInstagram.setForeground(Color.WHITE);
			botonInstagram.setFont(impact.deriveFont(20.0f));
			botonInstagram.setIcon(new ImageIcon("src/main/resources/instagram-logo.png"));

			botonInstagram.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						URI uri = new URI("https://www.instagram.com/?hl=es");
						new AvisoVentanaExterna24b(uri);
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
					frameFigura.dispose();
				}
				@Override
				public void mouseEntered(MouseEvent e){
					botonInstagram.setForeground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e){
					botonInstagram.setForeground(Color.WHITE);
				}

			});
		}

		return botonInstagram;
	}

	private JButton getBotonTwitter() {
		if (botonTwitter == null) {
			botonTwitter = new JButton("Twitter");
			botonTwitter.setBorderPainted(false);
			botonTwitter.setFocusPainted(false);
			botonTwitter.setContentAreaFilled(false);
			botonTwitter.setForeground(Color.WHITE);
			botonTwitter.setFont(impact.deriveFont(20.0f));
			botonTwitter.setIcon(new ImageIcon("src/main/resources/twitter-logo.png"));

			botonTwitter.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						URI uri = new URI("https://twitter.com/?lang=es");
						new AvisoVentanaExterna24b(uri);
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
					frameFigura.dispose();
				}
				@Override
				public void mouseEntered(MouseEvent e){
					botonTwitter.setForeground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e){
					botonTwitter.setForeground(Color.WHITE);
				}

			});

		}

		return botonTwitter;
	}

	private JButton getBotonFacebook() {
		if (botonFacebook == null) {
			botonFacebook = new JButton("Facebook");
			botonFacebook.setBorderPainted(false);
			botonFacebook.setFocusPainted(false);
			botonFacebook.setContentAreaFilled(false);
			botonFacebook.setForeground(Color.WHITE);
			botonFacebook.setFont(impact.deriveFont(20.0f));
			botonFacebook.setIcon(new ImageIcon("src/main/resources/facebook-logo.png"));

			botonFacebook.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						URI uri = new URI("https://es-es.facebook.com/");
						new AvisoVentanaExterna24b(uri);
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
					frameFigura.dispose();
				}
				@Override
				public void mouseEntered(MouseEvent e){
					botonFacebook.setForeground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e){
					botonFacebook.setForeground(Color.WHITE);
				}

			});

		}

		return botonFacebook;
	}

	private JButton getBotonTerminar() {
		if (botonTerminar == null) {
			botonTerminar = new JButton("Terminar");
			botonTerminar.setBorderPainted(false);
			botonTerminar.setFocusPainted(false);
			botonTerminar.setContentAreaFilled(false);
			botonTerminar.setForeground(Color.WHITE);
			botonTerminar.setFont(impact.deriveFont(25.0f));

			botonTerminar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frameFigura.dispose();
					String nombreUsuario=String.valueOf(datosPartida.opt("usuario"));
					new MenuPrincipal18(nombreUsuario);
				}
				@Override
				public void mouseEntered(MouseEvent e){
					botonTerminar.setForeground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e){
					botonTerminar.setForeground(Color.WHITE);
				}

			});
		}

		return botonTerminar;
	}

	public String getMensaje(){
		return mensaje;
	}
}
