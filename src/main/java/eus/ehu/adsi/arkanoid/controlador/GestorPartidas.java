package eus.ehu.adsi.arkanoid.controlador;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.modelo.Partida;
import eus.ehu.adsi.arkanoid.modelo.Usuario;
import eus.ehu.adsi.arkanoid.modelo.Ventaja;

public class GestorPartidas {
    private List<Partida> lPartidas;
    private static GestorPartidas mGestorPartidas = null; 

    private GestorPartidas() {
        lPartidas = new ArrayList<Partida>();
    }

    public static GestorPartidas getGestorPartidas() {
        if (mGestorPartidas == null) mGestorPartidas = new GestorPartidas();
        return mGestorPartidas;
    }

    public Partida buscarPartidaActual(Usuario u) {
        for (Partida p : lPartidas) {
            if (p.esUsuario(u)) //Si se trata del usuario que está jugando ahora
                if (p.noFechaFin()) { //Si la partida no tiene fecha de fin, es la actual
                    return p;
                }
        }
        return null;
    }

    public JSONObject crearVentaja(int random, Usuario u) {
        Partida p = buscarPartidaActual(u); //Bsucamos la partida actual
        if (p != null) {
            Ventaja v = CreadorVentaja.getCreadorVentaja().crearVentaja(random); //Se aplica el patrón Factory en caso de querer añadir nuevas funcionalidades
            p.agregarAListaVentaja(v); //Se agrega a la lista de ventajas de partida actual
            return v.darVentaja(); //Se ejecuta la funcionalidad de la ventaja
        }
        return null;
        
    }

    public void anadir(Partida p) {
        this.lPartidas.add(p);
    }

    public JSONObject obtenerDatosPartida(Partida p, int pMaxPuntUsuario) {
    	return p.obtenerDatos();
    }
}
