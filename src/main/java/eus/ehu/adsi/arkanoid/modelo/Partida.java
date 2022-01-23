package eus.ehu.adsi.arkanoid.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Partida {
    private int puntuacion;
    private int ladrillosNormalesDestruidos;
    private int ladrillosEspecialesDestruidos;
    private boolean victoria;
    private LocalDateTime fechaFin;
    private Usuario jugador;
    private List<Ventaja> listaVentajas = new ArrayList<Ventaja>();
    private int nivel;
    private int numVidas;
    private List<Premio> listaPremios = new ArrayList<Premio>();
<<<<<<< HEAD
=======
    private int numVidas;
>>>>>>> origin/feature-premios

    public Partida(int puntos, int ladNorm, int ladEsp, boolean vic, Usuario j, int numVidas, int lvl) {
        puntuacion = puntos;
        ladrillosNormalesDestruidos = ladNorm;
        ladrillosEspecialesDestruidos = ladEsp;
        victoria = vic;
        jugador = j;
        fechaFin = null;
        this.numVidas = numVidas;
        nivel = lvl;
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

<<<<<<< HEAD
       public int getPuntuacion() {
    	return puntuacion;
    }
    

    public void setHoraFin(LocalDateTime fechaHoraFin) {
        fechaFin = fechaHoraFin;
    }

    public void setBricks(int bSR, int bNR) {
        ladrillosNormalesDestruidos = bNR;
        ladrillosEspecialesDestruidos = bSR;        
    }

    public void setScores(int lives, boolean win, int score) {
        numVidas = lives;
        victoria = win;
        puntuacion = score;

    }

	public void guardarEnBD() {
        try {
            DataBase.getmDataBase().guardarPartida(puntuacion, ladrillosNormalesDestruidos, ladrillosEspecialesDestruidos, victoria, jugador.getNombre(), fechaFin, numVidas, nivel);
        } catch (Exception e) {
            System.err.println(e);
        }
	}
    
    public void setFechaFin() {
        this.fechaFin = LocalDateTime.now();
    }
    
=======
    public int getPuntuacion() {
        return puntuacion;
    }

    public Usuario getJugador() {
        return jugador;
    }

    public void setHoraFin(LocalDateTime fechaHoraFin) {
        fechaFin = fechaHoraFin;
    }

    public void setBricks(int bSR, int bNR) {
        ladrillosNormalesDestruidos = bNR;
        ladrillosEspecialesDestruidos = bSR;
    }

    public void setScores(int lives, boolean win, int score) {
        numVidas = lives;
        victoria = win;
        puntuacion = score;

    }

    public void guardarEnBD() {
        try {
            DataBase.getmDataBase().guardarPartida(puntuacion, ladrillosNormalesDestruidos, ladrillosEspecialesDestruidos, victoria, jugador.getNombre(), fechaFin, numVidas, nivel);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

>>>>>>> origin/feature-premios
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

<<<<<<< HEAD
    public Usuario getJugador() {
        return jugador;
    }
}
=======

}
>>>>>>> origin/feature-premios
