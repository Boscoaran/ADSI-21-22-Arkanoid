package eus.ehu.adsi.arkanoid.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;

import eus.ehu.adsi.arkanoid.view.game.Config;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class AvisoVentanaExterna24b {

	private JFrame frameFigura;
	private JPanel panel;
	private JButton botonCancelar;
	private JButton botonAceptar;
	private URI linkRedSocial;
	private Font impact = AddFont.createFont();

	public AvisoVentanaExterna24b(URI pLinkRedSocial) {
		linkRedSocial = pLinkRedSocial;
		initialize();
	}

	private void initialize() {
		frameFigura=new JFrame();
		frameFigura.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		frameFigura.setTitle("Aviso");
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

			GridLayout layout= new GridLayout(2,1);
			layout.setVgap(20);

			panel.setLayout(layout);
<<<<<<< HEAD
			
=======

>>>>>>> origin/feature-premios
			JLabel labelTexto =new JLabel("Se va a abrir una pagina web externa al sistema Arkanoid");
			labelTexto.setForeground(Color.WHITE);
			labelTexto.setFont(impact.deriveFont(20.0f));
			panel.add(labelTexto);

			FlowLayout layoutRedes = new FlowLayout();
			JPanel panelBotones = new JPanel();

			panelBotones.setLayout(layoutRedes);

			panelBotones.add(getBotonCancelar());
			panelBotones.add(getBotonAceptar());

			panel.add(panelBotones);

		}
		return panel;
	}

	private JButton getBotonCancelar() {
		if (botonCancelar == null) {
			botonCancelar = new JButton("Cancelar");
			botonCancelar.setBorderPainted(false);
			botonCancelar.setFocusPainted(false);
			botonCancelar.setContentAreaFilled(false);
			botonCancelar.setForeground(Color.WHITE);
			botonCancelar.setFont(impact.deriveFont(20.0f));

			botonCancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frameFigura.dispose();
				}
				@Override
				public void mouseEntered(MouseEvent e){
					botonCancelar.setForeground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e){
					botonCancelar.setForeground(Color.WHITE);
				}

			});

		}

		return botonCancelar;
	}

	private JButton getBotonAceptar() {
		if (botonAceptar == null) {
			botonAceptar = new JButton("Aceptar");
			botonAceptar.setBorderPainted(false);
			botonAceptar.setFocusPainted(false);
			botonAceptar.setContentAreaFilled(false);
			botonAceptar.setForeground(Color.WHITE);
			botonAceptar.setFont(impact.deriveFont(20.0f));

			botonAceptar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						frameFigura.dispose();
<<<<<<< HEAD
                		java.awt.Desktop.getDesktop().browse(linkRedSocial);               		
=======
						java.awt.Desktop.getDesktop().browse(linkRedSocial);
>>>>>>> origin/feature-premios
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				@Override
				public void mouseEntered(MouseEvent e){
					botonAceptar.setForeground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e){
					botonAceptar.setForeground(Color.WHITE);
				}

			});
<<<<<<< HEAD
    		
        }
        
        return botonAceptar;
    }

	public URI getUri(){
        return linkRedSocial;
    }

}
=======

		}

		return botonAceptar;
	}

	public URI getUri(){
		return linkRedSocial;
	}

}
>>>>>>> origin/feature-premios
