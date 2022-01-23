package eus.ehu.adsi.arkanoid.view;

import eus.ehu.adsi.arkanoid.view.game.Config;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Inicio16 extends JFrame {

    private JFrame frame;
    private JPanel panel;
    private JPanel panelSup;
    private JPanel panelInf;
<<<<<<< HEAD
	private JButton btnRegistrase;
    private JButton btnIniciarSesion;
	private JLabel lblTitulo;
    private JLabel lblGrupo;
    private JLabel lblNombres;
    private Font impact = AddFont.createFont();
    

    public Inicio16() {
        initialize();
    }

    private void initialize() {
		frame = new JFrame();
		frame.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		frame.setTitle("ARKANOID");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getPanel());
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.BLACK);
		frame.setVisible(true);
	}

    private JPanel getPanel(){
        if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
            panel.setLayout(new GridLayout(2, 0, 0, 0));
            panel.add(getPanelSup());
            panel.add(getPanelInf());
        }
        return panel;
    }
	
	private JPanel getPanelSup() {
		if (panelSup == null) {
			panelSup = new JPanel();
			GridBagLayout gbl_panelCentro = new GridBagLayout();
			gbl_panelCentro.columnWidths = new int[]{30,200,30};
			gbl_panelCentro.rowHeights = new int[]{5, 150, 150, 100};
			gbl_panelCentro.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panelSup.setLayout(gbl_panelCentro);

			//LBL TITULO
			GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
			gbc_lblTitulo.anchor = GridBagConstraints.CENTER;
			gbc_lblTitulo.insets = new Insets(0, 0, 0, 0);
			gbc_lblTitulo.gridx = 1;
			gbc_lblTitulo.gridy = 1;
			panelSup.add(getLblTitulo(), gbc_lblTitulo);

            //LBL GRUPO
			GridBagConstraints gbc_lblGrupo = new GridBagConstraints();
			gbc_lblGrupo.anchor = GridBagConstraints.CENTER;
			gbc_lblGrupo.insets = new Insets(0, 0, 0, 0);
			gbc_lblGrupo.gridx = 1;
			gbc_lblGrupo.gridy = 2;
			panelSup.add(getLblGrupo(), gbc_lblGrupo);

            //LBL NOMBRES
            GridBagConstraints gbc_lblNombres = new GridBagConstraints();
			gbc_lblNombres.anchor = GridBagConstraints.CENTER;
			gbc_lblNombres.insets = new Insets(0, 0, 0, 0);
			gbc_lblNombres.gridx = 1;
			gbc_lblNombres.gridy = 3;
			panelSup.add(getLblNombres(), gbc_lblNombres);

            panelSup.setBackground(Color.BLACK);
          	}
		return panelSup;
	}
	
    private JPanel getPanelInf() {
		if (panelInf == null) {
			panelInf = new JPanel();
			panelInf.setBounds(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
			panelInf.setLayout(new GridLayout(0, 2, 0, 0));
            panelInf.add(getBtnIniciarSesion());
            panelInf.add(getBtnRegistrarse());
            panelInf.setBackground(Color.BLACK);
        }
        return panelInf;
    }

	private JButton getBtnIniciarSesion() {
		if (btnIniciarSesion == null) {
			btnIniciarSesion = new JButton("Iniciar sesión");
			btnIniciarSesion.setBorderPainted(false);
			btnIniciarSesion.setFocusPainted(false);
			btnIniciarSesion.setContentAreaFilled(false);
			btnIniciarSesion.setForeground(Color.WHITE);
			btnIniciarSesion.setFont(impact.deriveFont(30.0f));
			btnIniciarSesion.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
                    frame.dispose();
                    new IniciarSesion17a();
				}
				@Override
				public void mouseEntered(MouseEvent e){
					btnIniciarSesion.setForeground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e){
					btnIniciarSesion.setForeground(Color.WHITE);
				}
			});
		}
		return btnIniciarSesion;
	}

    private JButton getBtnRegistrarse() {
		if (btnRegistrase == null) {
			btnRegistrase = new JButton("Registrarse");
			btnRegistrase.setBorderPainted(false);
			btnRegistrase.setFocusPainted(false);
			btnRegistrase.setContentAreaFilled(false);
			btnRegistrase.setForeground(Color.WHITE);
			btnRegistrase.setFont(impact.deriveFont(30.0f));
			btnRegistrase.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
                    new CrearCuenta17d();
				}
				@Override
				public void mouseEntered(MouseEvent e){
					btnRegistrase.setForeground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent e){
					btnRegistrase.setForeground(Color.WHITE);
				}
			});
		}
		return btnRegistrase;
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("ARKANOID", JLabel.CENTER);
			lblTitulo.setFont(impact.deriveFont(80.0f));
			lblTitulo.setForeground(Color.WHITE);
			}
		return lblTitulo;
	}
	private JLabel getLblGrupo() {
		if (lblGrupo == null) {
			lblGrupo = new JLabel("CROCS", JLabel.CENTER);
			lblGrupo.setFont(impact.deriveFont(50.0f));
			lblGrupo.setForeground(Color.WHITE);
			}
		return lblGrupo;
	}

    private JLabel getLblNombres() {
		if (lblNombres == null) {
			lblNombres = new JLabel("Joseba Rodríguez - Diego Marta - Jon Ander Lopez de Ahumada - Jon García - Joel Bra - Bosco Aranguren", JLabel.CENTER);
			lblNombres.setFont(impact.deriveFont(18.0f));
			lblNombres.setForeground(Color.WHITE);
			}
		return lblNombres;
	}
=======
    private JButton btnRegistrase;
    private JButton btnIniciarSesion;
    private JLabel lblTitulo;
    private JLabel lblGrupo;
    private JLabel lblNombres;
    private Font impact = AddFont.createFont();


    public Inicio16() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        frame.setTitle("ARKANOID");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(getPanel());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
    }

    private JPanel getPanel(){
        if (panel == null) {
            panel = new JPanel();
            panel.setBounds(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
            panel.setLayout(new GridLayout(2, 0, 0, 0));
            panel.add(getPanelSup());
            panel.add(getPanelInf());
        }
        return panel;
    }

    private JPanel getPanelSup() {
        if (panelSup == null) {
            panelSup = new JPanel();
            GridBagLayout gbl_panelCentro = new GridBagLayout();
            gbl_panelCentro.columnWidths = new int[]{30,200,30};
            gbl_panelCentro.rowHeights = new int[]{5, 150, 150, 100};
            gbl_panelCentro.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
            gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
            panelSup.setLayout(gbl_panelCentro);

            //LBL TITULO
            GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
            gbc_lblTitulo.anchor = GridBagConstraints.CENTER;
            gbc_lblTitulo.insets = new Insets(0, 0, 0, 0);
            gbc_lblTitulo.gridx = 1;
            gbc_lblTitulo.gridy = 1;
            panelSup.add(getLblTitulo(), gbc_lblTitulo);

            //LBL GRUPO
            GridBagConstraints gbc_lblGrupo = new GridBagConstraints();
            gbc_lblGrupo.anchor = GridBagConstraints.CENTER;
            gbc_lblGrupo.insets = new Insets(0, 0, 0, 0);
            gbc_lblGrupo.gridx = 1;
            gbc_lblGrupo.gridy = 2;
            panelSup.add(getLblGrupo(), gbc_lblGrupo);

            //LBL NOMBRES
            GridBagConstraints gbc_lblNombres = new GridBagConstraints();
            gbc_lblNombres.anchor = GridBagConstraints.CENTER;
            gbc_lblNombres.insets = new Insets(0, 0, 0, 0);
            gbc_lblNombres.gridx = 1;
            gbc_lblNombres.gridy = 3;
            panelSup.add(getLblNombres(), gbc_lblNombres);

            panelSup.setBackground(Color.BLACK);
        }
        return panelSup;
    }

    private JPanel getPanelInf() {
        if (panelInf == null) {
            panelInf = new JPanel();
            panelInf.setBounds(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
            panelInf.setLayout(new GridLayout(0, 2, 0, 0));
            panelInf.add(getBtnIniciarSesion());
            panelInf.add(getBtnRegistrarse());
            panelInf.setBackground(Color.BLACK);
        }
        return panelInf;
    }

    private JButton getBtnIniciarSesion() {
        if (btnIniciarSesion == null) {
            btnIniciarSesion = new JButton("Iniciar sesión");
            btnIniciarSesion.setBorderPainted(false);
            btnIniciarSesion.setFocusPainted(false);
            btnIniciarSesion.setContentAreaFilled(false);
            btnIniciarSesion.setForeground(Color.WHITE);
            btnIniciarSesion.setFont(impact.deriveFont(30.0f));
            btnIniciarSesion.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.dispose();
                    new IniciarSesion17a();
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    btnIniciarSesion.setForeground(Color.RED);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    btnIniciarSesion.setForeground(Color.WHITE);
                }
            });
        }
        return btnIniciarSesion;
    }

    private JButton getBtnRegistrarse() {
        if (btnRegistrase == null) {
            btnRegistrase = new JButton("Registrarse");
            btnRegistrase.setBorderPainted(false);
            btnRegistrase.setFocusPainted(false);
            btnRegistrase.setContentAreaFilled(false);
            btnRegistrase.setForeground(Color.WHITE);
            btnRegistrase.setFont(impact.deriveFont(30.0f));
            btnRegistrase.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.dispose();
                    new CrearCuenta17d();
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    btnRegistrase.setForeground(Color.RED);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    btnRegistrase.setForeground(Color.WHITE);
                }
            });
        }
        return btnRegistrase;
    }

    private JLabel getLblTitulo() {
        if (lblTitulo == null) {
            lblTitulo = new JLabel("ARKANOID", JLabel.CENTER);
            lblTitulo.setFont(impact.deriveFont(80.0f));
            lblTitulo.setForeground(Color.WHITE);
        }
        return lblTitulo;
    }
    private JLabel getLblGrupo() {
        if (lblGrupo == null) {
            lblGrupo = new JLabel("CROCS", JLabel.CENTER);
            lblGrupo.setFont(impact.deriveFont(50.0f));
            lblGrupo.setForeground(Color.WHITE);
        }
        return lblGrupo;
    }

    private JLabel getLblNombres() {
        if (lblNombres == null) {
            lblNombres = new JLabel("Joseba Rodríguez - Diego Marta - Jon Ander Lopez de Ahumada - Jon García - Joel Bra - Bosco Aranguren", JLabel.CENTER);
            lblNombres.setFont(impact.deriveFont(18.0f));
            lblNombres.setForeground(Color.WHITE);
        }
        return lblNombres;
    }
>>>>>>> origin/feature-premios
}