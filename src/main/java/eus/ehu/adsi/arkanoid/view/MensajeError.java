package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

public class MensajeError extends JFrame {

    /**
     * (Constructora) Crear ventana de aviso con el mensaje
     * @param mensaje mensaje a mostrar en la ventana de aviso
     */
    public MensajeError(String mensaje) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}
