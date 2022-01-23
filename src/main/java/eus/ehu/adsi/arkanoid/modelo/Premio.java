package eus.ehu.adsi.arkanoid.modelo;

public class Premio {
    private String nombre;
    private String descripcion;
    private Personalizable recompensa;

    public Premio(String pNombre, String pDescripcion){
        nombre = pNombre;
        descripcion = pDescripcion;
    }

    public Premio(String pNombre, String pDescripcion, Personalizable pRecompensa){
        nombre = pNombre;
        descripcion = pDescripcion;
        recompensa = pRecompensa;
    }

    public void setRecompensa(Personalizable pRecompensa) {
        recompensa = pRecompensa;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Personalizable getRecompensa() {
        return recompensa;
    }
}
