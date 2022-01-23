package eus.ehu.adsi.arkanoid.controlador;

import eus.ehu.adsi.arkanoid.modelo.Partida;
import eus.ehu.adsi.arkanoid.modelo.Premio;
import eus.ehu.adsi.arkanoid.modelo.Usuario;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestorPremios {
    private List<Premio> listaPremios;
    private static GestorPremios mGestorPremios = null;

    private GestorPremios() {
        listaPremios = new ArrayList<Premio>();
    }

    public static GestorPremios getGestorPremios() {
        if (mGestorPremios == null) mGestorPremios = new GestorPremios();
        return mGestorPremios;
    }

    public void generarPremios(){
        listaPremios.add(new Premio("Fácil I", "Ganar 1 partida en el nivel de dificultad fácil"));
        listaPremios.add(new Premio("Fácil II", "Ganar 5 partida en el nivel de dificultad fácil"));
        listaPremios.add(new Premio("Fácil III", "Ganar 10 partida en el nivel de dificultad fácil"));
        listaPremios.add(new Premio("Medio I", "Ganar 1 partida en el nivel de dificultad Medio"));
        listaPremios.add(new Premio("Medio II", "Ganar 5 partida en el nivel de dificultad Medio"));
        listaPremios.add(new Premio("Medio III", "Ganar 10 partida en el nivel de dificultad Medio"));
        listaPremios.add(new Premio("Difícil I", "Ganar 1 partida en el nivel de dificultad Difícil"));
        listaPremios.add(new Premio("Difícil II", "Ganar 5 partida en el nivel de dificultad Difícil"));
        listaPremios.add(new Premio("Difícil III", "Ganar 10 partida en el nivel de dificultad Difícil"));
        listaPremios.add(new Premio("En racha I", "Ganar 2 partidas seguidas (en cualquier dificultad)"));
        listaPremios.add(new Premio("En racha II", "Ganar 5 partidas seguidas (en cualquier dificultad)"));
        listaPremios.add(new Premio("En racha III", "Ganar 10 partidas seguidas (en cualquier dificultad)"));
        listaPremios.add(new Premio("Demoledor I", "Romper 100 ladrillos (en total)"));
        listaPremios.add(new Premio("Demoledor II", "Romper 500 ladrillos (en total)"));
        listaPremios.add(new Premio("Demoledor III", "Romper 1000 ladrillos (en total)"));
        listaPremios.add(new Premio("Suertudo I", "Romper 1 ladrillo especial (en total)"));
        listaPremios.add(new Premio("Suertudo II", "Romper 5 ladrillo especiales (en total)"));
        listaPremios.add(new Premio("Suertudo III", "Romper 10 ladrillo especiales (en total)"));
    }

    public void generarPremiosBD(){ //TODO
        listaPremios.add(new Premio("Fácil I", "Ganar 1 partida en el nivel de dificultad fácil"));
        listaPremios.add(new Premio("Fácil II", "Ganar 5 partida en el nivel de dificultad fácil"));
        listaPremios.add(new Premio("Fácil III", "Ganar 10 partida en el nivel de dificultad fácil"));
        listaPremios.add(new Premio("Medio I", "Ganar 1 partida en el nivel de dificultad Medio"));
        listaPremios.add(new Premio("Medio II", "Ganar 5 partida en el nivel de dificultad Medio"));
        listaPremios.add(new Premio("Medio III", "Ganar 10 partida en el nivel de dificultad Medio"));
        listaPremios.add(new Premio("Difícil I", "Ganar 1 partida en el nivel de dificultad Difícil"));
        listaPremios.add(new Premio("Difícil II", "Ganar 5 partida en el nivel de dificultad Difícil"));
        listaPremios.add(new Premio("Difícil III", "Ganar 10 partida en el nivel de dificultad Difícil"));
        listaPremios.add(new Premio("En racha I", "Ganar 2 partidas seguidas (en cualquier dificultad)"));
        listaPremios.add(new Premio("En racha II", "Ganar 5 partidas seguidas (en cualquier dificultad)"));
        listaPremios.add(new Premio("En racha III", "Ganar 10 partidas seguidas (en cualquier dificultad)"));
        listaPremios.add(new Premio("Demoledor I", "Romper 100 ladrillos (en total)"));
        listaPremios.add(new Premio("Demoledor II", "Romper 500 ladrillos (en total)"));
        listaPremios.add(new Premio("Demoledor III", "Romper 1000 ladrillos (en total)"));
        listaPremios.add(new Premio("Suertudo I", "Romper 1 ladrillo especial (en total)"));
        listaPremios.add(new Premio("Suertudo II", "Romper 5 ladrillo especiales (en total)"));
        listaPremios.add(new Premio("Suertudo III", "Romper 10 ladrillo especiales (en total)"));
    }

    public Premio getPremio(String pNombre){
        return listaPremios.stream().filter(p -> pNombre.equals(p.getNombre())).findAny().get();
    }

    public List<Premio> comprobarPremios(Partida p) {
        List<Premio> PConseguidos = new ArrayList<Premio>();
        int nivel = p.getNivel();
        if (p.isVictoria()) {
            if (nivel == 1) {
                if (p.getJugador().getnVFacil() == 1) {
                    PConseguidos.add(getPremio("Fácil I"));
                } else if (p.getJugador().getnVFacil() == 5) {
                    PConseguidos.add(getPremio("Fácil II"));
                } else if (p.getJugador().getnVFacil() == 10) {
                    PConseguidos.add(getPremio("Fácil III"));
                }
            } else if (nivel == 2) {
                if (p.getJugador().getnVMedio() == 1) {
                    PConseguidos.add(getPremio("Medio I"));
                } else if (p.getJugador().getnVMedio() == 5) {
                    PConseguidos.add(getPremio("Medio II"));
                } else if (p.getJugador().getnVMedio() == 10) {
                    PConseguidos.add(getPremio("Medio III"));
                }
            } else if (nivel == 3) {
                if (p.getJugador().getnVDificil() == 1) {
                    PConseguidos.add(getPremio("Difícil I"));
                } else if (p.getJugador().getnVDificil() == 5) {
                    PConseguidos.add(getPremio("Difícil II"));
                } else if (p.getJugador().getnVDificil() == 10) {
                    PConseguidos.add(getPremio("Difícil III"));
                }
            }
            if (p.getJugador().getnSeguidas() == 2) {
                PConseguidos.add(getPremio("En racha I"));
            } else if (p.getJugador().getnSeguidas() == 5) {
                PConseguidos.add(getPremio("En racha II"));
            } else if (p.getJugador().getnSeguidas() == 10) {
                PConseguidos.add(getPremio("En racha III"));
            }
        }
        if (p.getJugador().getnLadrillosN() >= 100) {
            PConseguidos.add(getPremio("Demoledor I"));
        } else if (p.getJugador().getnLadrillosN() >= 500) {
            PConseguidos.add(getPremio("Demoledor II"));
        } else if (p.getJugador().getnLadrillosN() >= 1000) {
            PConseguidos.add(getPremio("Demoledor III"));
        }
        if (p.getJugador().getnLadrillosE() >= 1) {
            PConseguidos.add(getPremio("Suertudo I"));
        } else if (p.getJugador().getnLadrillosE() >= 5) {
            PConseguidos.add(getPremio("Suertudo II"));
        } else if (p.getJugador().getnLadrillosE() >= 10) {
            PConseguidos.add(getPremio("Suertudo III"));
        }
        return PConseguidos;
    }

    public List<Premio> otorgarPremios(Usuario u, List<Premio> premios){
        return u.otorgarPremiosBD(premios);
    }

    public void annadirPremios(Partida p, List<Premio> premios){
        p.annadirPremios(premios);
    }

    public JSONObject obtenerNombresDescripciones(List<Premio> premios, JSONObject joLista){
        List<JSONObject> listaPremios = new ArrayList<JSONObject>();
        Premio PActual;
        Iterator<Premio> itr = premios.iterator();
        while(itr.hasNext()){
            PActual = itr.next();
            JSONObject joPremio = new JSONObject();
            joPremio.put("nombre", PActual.getNombre());
            joPremio.put("descripcion", PActual.getDescripcion());
            listaPremios.add(joPremio);
        }
        joLista.put("lista", listaPremios);
        return joLista;
    }

    public JSONObject generarJSONPremios(){ //Solo se utiliza para probar, genera un JSON de premios de una partida en la que se han conseguido el número máximo de premios (8)
        JSONObject jo = new JSONObject();
        ArrayList<JSONObject> lista = new ArrayList<JSONObject>();
        JSONObject premio1 = new JSONObject();
        premio1.put("nombre","Premio 1");
        premio1.put("descripcion", "des 1");
        lista.add(premio1);
        JSONObject premio2 = new JSONObject();
        premio2.put("nombre","Premio 2");
        premio2.put("descripcion", "des 2");
        lista.add(premio2);
        JSONObject premio3 = new JSONObject();
        premio3.put("nombre","Premio 3");
        premio3.put("descripcion", "des 3");
        lista.add(premio3);
        JSONObject premio4 = new JSONObject();
        premio4.put("nombre","Premio 4");
        premio4.put("descripcion", "des 4");
        lista.add(premio4);
        JSONObject premio5 = new JSONObject();
        premio5.put("nombre","Premio 5");
        premio5.put("descripcion", "des 5");
        lista.add(premio5);
        JSONObject premio6 = new JSONObject();
        premio6.put("nombre","Premio 6");
        premio6.put("descripcion", "des 6");
        lista.add(premio6);
        JSONObject premio7 = new JSONObject();
        premio7.put("nombre","Premio 7");
        premio7.put("descripcion", "des 7");
        lista.add(premio7);
        JSONObject premio8 = new JSONObject();
        premio8.put("nombre","Premio 8");
        premio8.put("descripcion", "des 8");
        lista.add(premio8);
        jo.put("lista",lista);
        return jo;
    }
}
