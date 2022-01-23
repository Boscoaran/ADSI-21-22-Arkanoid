package eus.ehu.adsi.arkanoid.controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.modelo.DataBase;
import eus.ehu.adsi.arkanoid.modelo.Usuario;

public class GestorUsuarios {
    private static GestorUsuarios mGestorUsuarios = null; 
    private List<Usuario> lUsuarios;

    private GestorUsuarios() {
        lUsuarios = new ArrayList<Usuario>();
    }

    public static GestorUsuarios getGestorUsuarios() {
        if (mGestorUsuarios == null) mGestorUsuarios = new GestorUsuarios();
        return mGestorUsuarios;
    }

    public Usuario buscarUsuario(String nombreUsuario, String contra) {
        Usuario u = null;

        JSONObject j = null;
        try {
            j = DataBase.getmDataBase().buscarUsuario(nombreUsuario, contra);
        } catch (Exception e) {
           System.err.println(e);
        }

        if (j.has("nombreUsuario")) {
            u = new Usuario(j.getString("nombreUsuario"), j.getString("correo"), j.getString("contra"));
            GestorUsuarios.getGestorUsuarios().anadir(u);
            System.out.println("hola");
        }

        return u;
        
    }
    public Usuario buscarUsuarioGestor(String nombreUsuario) {
        for (Usuario u : lUsuarios) {
            if (u.esNombre(nombreUsuario))
                return u;
        }
        return null;
    }

    /**
     * Buscar un usuario dado su correo
     * @param correo correo del usuario en String
     * @return si existe, el objeto Usuario que tenga el correo | si no existe, null
     */
    public Usuario buscarUsuarioCorreo(String correo) {
        for (Usuario u : lUsuarios) {
            if (u.esCorreo(correo))
                return u;
        }
        return null;
    }

    /**
     * Comprobar si la contraseña es del usuario
     * @param U objeto del usuario que se quiere comprobar la contraseña
     * @param contrasena contraseña a comprobar
     * @return true si es su contraseña | false sino
     */
    public boolean esContrasena(Usuario U, String contrasena) {
        return U.esContrasena(contrasena);
    }

    /**
     * Cambiar la contraseña del usuario
     * @param U usuario cuya contraseña se quiere cambiar
     * @param contrasena nueva contraseña del usuario
     */
    public void cambiarContrasena(Usuario U, String contrasena) {
        U.setContrasena(contrasena);
    }

    /**
     * Registrar un nuevo usuario en el sistema
     * @param nombreUsuario nombre del usuario
     * @param correo correo del usuario
     * @param contrasena1 contraseña del usuario
     */
    public void registrarUsuario(String nombreUsuario, String correo, String contrasena1) {
        //Crear el usuario
        Usuario U = new Usuario(nombreUsuario, correo, contrasena1);
        //Añadirlo a la lista
        this.lUsuarios.add(U);
    }

    /*MÉTODOS PARA PRUEBAS*/
    public void anadir(Usuario u) {
        this.lUsuarios.add(u);
    }
    /////
    public JSONObject cargarDatosPersonalizacion(String nombreUsuario) {
        try {
            return DataBase.getmDataBase().cargarDatosPersonalizacion(nombreUsuario);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     * Borra todos los usuarios registrados
     */
    public void borrarUsuarios() {
        this.lUsuarios.clear();
    }
}
