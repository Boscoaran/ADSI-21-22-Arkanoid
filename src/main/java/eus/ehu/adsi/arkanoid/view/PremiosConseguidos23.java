package eus.ehu.adsi.arkanoid.view;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class PremiosConseguidos23 extends JFrame {
    private JFrame frame;
    private JPanel panelTitulo;
    private JPanel panelPremios;
    private JPanel panelSiguiente;
    private JButton btnSiguiente;
    private Font impact = AddFont.createFont();
    private JSONObject datos;

    public PremiosConseguidos23(JSONObject d) {
        datos = d;
        initialize();
    }


    private void initialize() {
        frame = new JFrame();
        frame.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        frame.setTitle("Premios Conseguidos");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(getPanelTitulo(),BorderLayout.NORTH);
        frame.getContentPane().add(getPanelPremios(),BorderLayout.CENTER);
        frame.getContentPane().add(getPanelSiguiente(),BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
    }

    private JPanel getPanelSiguiente(){
        if (panelSiguiente == null){
            panelSiguiente = new JPanel();
            panelSiguiente.setBackground(Color.BLACK);
            panelSiguiente.add(getBtnSiguiente());
        }
        return panelSiguiente;
    }

    private JButton getBtnSiguiente(){
        if (btnSiguiente == null){
            btnSiguiente = new JButton("Siguiente");
            btnSiguiente.setBorderPainted(false);
            btnSiguiente.setFocusPainted(false);
            btnSiguiente.setContentAreaFilled(false);
            btnSiguiente.setForeground(Color.WHITE);
            btnSiguiente.setFont(impact.deriveFont(30.0f));
            btnSiguiente.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.dispose();
                    //TODO: ABRIR PANTALLA COMPARTIR RESULTADOS
                }
            });
        }
        return btnSiguiente;
    }

    private JPanel getPanelTitulo(){
        if (panelTitulo == null) {
            panelTitulo = new JPanel();
            panelTitulo.setBackground(Color.BLACK);
            JLabel lTitulo = new JLabel("Se han conseguido los siguientes premios:");
            lTitulo.setFont(impact.deriveFont(35.0f));
            lTitulo.setForeground(Color.WHITE);
            panelTitulo.add(lTitulo);
        }
        return panelTitulo;
    }

    private JPanel getPanelPremios(){
        if (panelPremios == null){
            panelPremios = new JPanel();
            panelPremios.setBounds(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
            panelPremios.setLayout(new BoxLayout(panelPremios, BoxLayout.PAGE_AXIS));
            panelPremios.setBackground(Color.BLACK);
            annadirPremios(panelPremios);
        }
        return panelPremios;
    }

    private void annadirPremios(JPanel panel){
        JSONArray jArray = (JSONArray) datos.opt("lista");
        System.out.println(datos);
        ArrayList<JSONObject> lista = jAtoAL(jArray);
        Iterator<JSONObject> itr = lista.iterator();
        JSONObject joActual;
        JPanel premio;
        JLabel nombre;
        JLabel descripcion;
        String sNombre;
        String sDescripcion;
        while (itr.hasNext()){
            joActual = itr.next();
            premio = new JPanel();
            premio.setBackground(Color.BLACK);

            sNombre = (String) joActual.optString("nombre") + ":";
            nombre = new JLabel(sNombre);
            nombre.setFont(impact.deriveFont(30.0f));
            nombre.setForeground(Color.RED);

            sDescripcion ="\"" + (String) joActual.optString("descripcion") + "\"";
            descripcion = new JLabel(sDescripcion);
            descripcion.setFont(impact.deriveFont(30.0f));
            descripcion.setForeground(Color.WHITE);

            premio.add(nombre);
            premio.add(descripcion);
            panel.add(premio);
        }

    }

    private ArrayList<JSONObject> jAtoAL(JSONArray jA) {
        ArrayList<JSONObject> AL = new ArrayList<JSONObject>();
        for (int i = 0; i < jA.length(); i++) {
            AL.add(jA.getJSONObject(i));
        }
        return AL;
    }
}
