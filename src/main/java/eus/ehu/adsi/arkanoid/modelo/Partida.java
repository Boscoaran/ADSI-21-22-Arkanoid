package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

public class Partida {
    private int puntuacion;
    private int ladrillosNormalesDestruidos;
    private int ladrillosEspecialesDestruidos;
    private boolean victoria;
    private Date fechaFin;
    private Usuario jugador;
    private List<Ventaja> listaVentajas = new ArrayList<Ventaja>();
    private int numVidas;

    public Partida(Usuario u) {
        jugador = u;
        fechaFin = null;
    }

    public boolean esUsuario(Usuario u) {
        return jugador.equals(u);
    }

    public boolean noFechaFin() {
        return fechaFin == null;
    }

    public void agregarAListaVentaja(Ventaja v) {
        listaVentajas.add(v);
    }

       public int getPuntuacion() {
    	return puntuacion;
    }
    
    public Usuario getJugador() {
    	return jugador;
    }
    
    public JSONObject obtenerDatos(int pMaxPuntUsuario) {
    	JSONObject json = new JSONObject();
    	json.put("usuario",jugador.getNombre());
    	json.put("victoria",victoria);
    	json.put("puntuacion",puntuacion);
    	json.put("maxPunt",pMaxPuntUsuario);
    	//json.put("nivel", nivel.getNumNivel());
        List<Premio> listaPremios = new ArrayList<>();
    	json.put("premios",listaPremios);	//estar� vac�a si no ha habido premios
    	return json;
    }
}
