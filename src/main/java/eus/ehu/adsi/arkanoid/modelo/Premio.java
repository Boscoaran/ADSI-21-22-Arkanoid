package eus.ehu.adsi.arkanoid.modelo;

public class Premio {
    private String nombre;
    private String descripcion;
    private Personalizable recompensa;
    private String nTabla;
    private String vColor;

    public Premio(String pNombre, String pDescripcion){
        nombre = pNombre;
        descripcion = pDescripcion;
    }

    public Premio(String pNombre, String pDescripcion, Personalizable pRecompensa){
        nombre = pNombre;
        descripcion = pDescripcion;
        recompensa = pRecompensa;
    }

    public Premio(String pNombre, String pDescripcion, String pNTabla, String pVColor){
        nombre = pNombre;
        descripcion = pDescripcion;
        nTabla = pNTabla;
        vColor = pVColor;
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

    public String getnTabla() {
        return nTabla;
    }

    public String getvColor() {
        return vColor;
    }
}
