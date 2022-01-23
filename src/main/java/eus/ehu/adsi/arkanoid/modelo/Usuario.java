package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Usuario {
    //información usuario
    private String nombreUsuario;
    private String correo;
    private String contrasena;
    //ajustes usuario
    private ColorBola colorBola;
    private ColorPadel colorPadel;
    private ColorFondo colorFondo;
    private ColorLadrillos colorLadrillos;
    private AjusteSonido sonido;
    //estadísticas usuario
    private List<Premio> listaPremios;
    private List<Personalizable> listaDesbloqueados;
    private int nVFacil;
    private int nVMedio;
    private int nVDificil;
    private int nSeguidas;
    private int nLadrillosN;
    private int nLadrillosE;

    //Faltan más atributos
    public Usuario(String pNombreUsuario, String pCorreo, String pContrasena) {

        nombreUsuario = pNombreUsuario;
        correo = pCorreo;
        contrasena = pContrasena;
        listaPremios = new ArrayList<Premio>();
        listaDesbloqueados = new ArrayList<Personalizable>();
    }

    public boolean esNombre(String pNombreUsuario) {
        return nombreUsuario.equals(pNombreUsuario);
    }

    /**
     * Comprobar si el correo proporcionado es del usuario
     * @param pCorreo correo a comprobar si es propio
     * @return true si es su correo | false sino
     */
    public boolean esCorreo(String pCorreo) {
        return pCorreo.equals(correo);
    }

    /**
     * Comprobar si la contraseña proporcionada es del usuario
     * @param pContrasena contraseña a comprobar si es la propia
     * @return true si es su contraseña | false sino
     */
    public boolean esContrasena(String pContrasena) {
        return pContrasena.equals(contrasena);
    }

    public void cambiarAjustes(String colorBola, String colorPadel, String colorLadrillo, String colorFondo, boolean sonido) {
        this.colorBola = new ColorBola(colorBola);
        this.colorPadel = new ColorPadel(colorPadel);
        this.colorFondo = new ColorFondo(colorFondo);
        this.colorLadrillos = new ColorLadrillos(colorLadrillo);
        this.sonido = new AjusteSonido(sonido);
    }

    /**
     * Cambiar la contraseña actual
     * @param pContrasena contraseña nueva
     */
    public void setContrasena(String pContrasena) {
        this.contrasena = pContrasena;
    }

    public String getNombre() {
        return nombreUsuario;
    }

    public List<Premio> otorgarPremios(List<Premio> pPremios){
        List<Premio> POtorgados = new ArrayList<Premio>();
        Premio PActual;
        Iterator<Premio> itr = pPremios.iterator();
        while(itr.hasNext()){
            PActual = itr.next();
            if (!listaPremios.contains(PActual)){
                POtorgados.add(PActual);
                listaDesbloqueados.add(PActual.getRecompensa());
            }
        }
        return POtorgados;
    }

    public List<Premio> otorgarPremiosBD(List<Premio> pPremios){
        List<Premio> POtorgados = new ArrayList<Premio>();
        Premio PActual;
        Iterator<Premio> itr = pPremios.iterator();
        while(itr.hasNext()){
            PActual = itr.next();
            if (!listaPremios.contains(PActual)){
                POtorgados.add(PActual);
                try {
                    DataBase.getmDataBase().annadirDesbloquable(nombreUsuario,PActual.getnTabla(),PActual.getvColor());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return POtorgados;
    }

    public void actualizarPartida(Partida p){
        if (p.isVictoria()){
            nSeguidas++;
            switch (p.getNivel()) {
                case 1:
                    nVFacil++;
                case 2:
                    nVMedio++;
                case 3:
                    nVDificil++;
            }
        }
        else{
            nSeguidas = 0;
        }
        nLadrillosN += p.getLadrillosNormalesDestruidos();
        nLadrillosE += p.getLadrillosEspecialesDestruidos();
    }

    public List<Premio> getListaPremios() {
        return listaPremios;
    }

    public List<Personalizable> getListaDesbloqueados() {
        return listaDesbloqueados;
    }

    public int getnVFacil() {
        return nVFacil;
    }

    public int getnVMedio() {
        return nVMedio;
    }

    public int getnVDificil() {
        return nVDificil;
    }

    public int getnSeguidas() {
        return nSeguidas;
    }

    public int getnLadrillosN() {
        return nLadrillosN;
    }

    public int getnLadrillosE() {
        return nLadrillosE;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }


}