package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Partida {
    private int puntuacion;
    private int ladrillosNormalesDestruidos;
    private int ladrillosEspecialesDestruidos;
    private boolean victoria;
    private Date fechaFin;
    private Usuario jugador;
    private List<Ventaja> listaVentajas = new ArrayList<Ventaja>();
    private int numVidas;
    private int nivel;
    private List<Premio> listaPremios = new ArrayList<Premio>();
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

    public void annadirPremios(List<Premio> premios){
        listaPremios.addAll(premios);
    }

    public boolean isVictoria() {
        return victoria;
    }

    public int getLadrillosNormalesDestruidos() {
        return ladrillosNormalesDestruidos;
    }

    public int getLadrillosEspecialesDestruidos() {
        return ladrillosEspecialesDestruidos;
    }

    public int getNivel() {
        return nivel;
    }

    public Usuario getJugador() {
        return jugador;
    }
}
