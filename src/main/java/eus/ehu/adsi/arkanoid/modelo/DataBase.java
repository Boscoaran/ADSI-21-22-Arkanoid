package eus.ehu.adsi.arkanoid.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.json.JSONObject;

public class DataBase {
    
    private static DataBase mDataBase;

    private DataBase(){}

    public static DataBase getmDataBase() {
        if (mDataBase == null){
            mDataBase = new DataBase();
        }
        return mDataBase;
    }

    public JSONObject jugadorPosGlobal(int p, int nivel) throws SQLException{
        Connection con = null;
        JSONObject jugadorPuntuacion = new JSONObject();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
		}
        Statement s = con.createStatement();
        ResultSet rs1;
		if (nivel == 0){
            rs1 = s.executeQuery("SELECT nombreUsuario, puntuacion FROM partida ORDER BY puntuacion DESC, fechaFin ASC LIMIT 1 OFFSET " + (p-1));
        }else{
            rs1 = s.executeQuery("SELECT nombreUsuario, puntuacion FROM partida WHERE nivel=\""+ nivel + "\" ORDER BY puntuacion DESC, fechaFin ASC LIMIT 1 OFFSET " + (p-1));
        }
        rs1.next(); //TO DO: Arreglar esto, que sino peta
        jugadorPuntuacion.put("nombre", rs1.getString(1));
        jugadorPuntuacion.put("puntos", rs1.getInt(2));
        return jugadorPuntuacion;
    }

    public JSONObject jugadorPosIndividual(int p, String nombre, int nivel) throws SQLException{
        JSONObject puntuacion = new JSONObject();
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
		}
        Statement s = con.createStatement();
        ResultSet rs1;
        if (nivel == 0){
            rs1 = s.executeQuery("SELECT puntuacion FROM partida WHERE nombreUsuario=\""+ nombre +"\" ORDER BY puntuacion DESC, fechaFin ASC LIMIT 1 OFFSET " + (p-1));
        }else{
            rs1 = s.executeQuery("SELECT puntuacion FROM partida WHERE nombreUsuario=\""+ nombre +"\" AND nivel=\""+ nivel +"\" ORDER BY puntuacion DESC, fechaFin ASC LIMIT 1 OFFSET " + (p-1));
        }
        rs1.next();
        puntuacion.put("nombre", nombre);
        puntuacion.put("puntos", rs1.getInt(1));
        return puntuacion;
    }

    public int nPartidasGlobal(int nivel) throws SQLException{
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
		}
        Statement s = con.createStatement();
        ResultSet rs1;
        if (nivel == 0){
		    rs1 = s.executeQuery("SELECT count(*) FROM partida WHERE victoria=1");
            rs1.next();
        } else {
            rs1 = s.executeQuery("SELECT count(*) FROM partida WHERE victoria=1 AND nivel=\""+nivel+"\"");
            rs1.next();
        }
        return rs1.getInt(1);
    }

    public int nPartidasIndividual(String nombre, int nivel) throws SQLException{
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
		}
        Statement s = con.createStatement();
        ResultSet rs1;
        if (nivel == 0){
		    rs1 = s.executeQuery("SELECT count(*) FROM partida WHERE victoria=1 AND nombreUsuario=\"" + nombre + "\"");
            rs1.next();
        }else{
            rs1 = s.executeQuery("SELECT count(*) FROM partida WHERE victoria=1 AND nombreUsuario=\"" + nombre + "\" AND nivel=\""+ nivel +"\"");
            rs1.next();
        }
        return rs1.getInt(1);
    }

    public JSONObject cargarDatosPersonalizacion(String nombreUsuario) throws SQLException{
        Connection con = null;
        JSONObject res = new JSONObject();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
		}
        Statement s = con.createStatement();
        ResultSet rs;
        rs = s.executeQuery("SELECT sonidoAct, colorLadrillo, colorBola, colorPaddle, colorFondo FROM usuario WHERE nombreUsuario =\"" + nombreUsuario + "\"");
        boolean h = rs.next();
        if (h) {
            res.put("Sonido", rs.getInt(1));
            res.put("ColorLadrillo", rs.getString(2));
            res.put("ColorBola", rs.getString(3));
            res.put("ColorPaddle", rs.getString(4));
            res.put("ColorFondo", rs.getString(5));
        }

        return res;
    }

    public void actualizarPersonalizacion(String nombreUsuario, String colorBola, String colorPadel, String colorLadrillo,String colorFondo, boolean sonido) throws Exception {
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
		}
        int son;
        Statement s = con.createStatement();
        if (sonido) son = 1;
        else son = 0;
        s.executeUpdate("UPDATE usuario SET sonidoAct=\"" + son + "\", colorLadrillo=\"" + colorLadrillo + "\", colorBola =\"" + colorBola + "\", colorPaddle =\"" + colorPadel + "\" , colorFondo =\"" + colorFondo + "\" WHERE nombreUsuario = \"" + nombreUsuario + "\"");
    }

    public JSONObject getColoresBola(String nombre) throws SQLException {
        JSONObject colores = new JSONObject();
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
            return null;
		}
        Statement s = con.createStatement();
        ResultSet rs;
        rs = s.executeQuery("SELECT bolaNaranja, bolaRojo, bolaBlanco, bolaAzul FROM usuario WHERE nombreUsuario=\"" + nombre +"\"");
        boolean b = rs.next();
        if (b) {
            colores.put("bolaNaranja", rs.getInt(1));
            colores.put("bolaRojo", rs.getInt(2));
            colores.put("bolaBlanco", rs.getInt(3));
            colores.put("bolaAzul", rs.getInt(4));
        }
        return colores;
    }

    public JSONObject getColoresFondo(String nombre) throws SQLException {
        JSONObject colores = new JSONObject();
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
            return null;
		}
        Statement s = con.createStatement();
        ResultSet rs;
        rs = s.executeQuery("SELECT fondoNegro, fondoVerde, fondoMorado, fondoCyan FROM usuario WHERE nombreUsuario=\"" + nombre +"\"");
        boolean b = rs.next();
        if (b) {
            colores.put("fondoNegro", rs.getInt(1));
            colores.put("fondoVerde", rs.getInt(2));
            colores.put("fondoMorado", rs.getInt(3));
            colores.put("fondoCyan", rs.getInt(4));
        }
        return colores;
    }

    public JSONObject getColoresLadrillos(String nombre) throws SQLException {
        JSONObject colores = new JSONObject();
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
            return null;
		}
        Statement s = con.createStatement();
        ResultSet rs;
        rs = s.executeQuery("SELECT ladrilloAzul, ladrilloRojo, ladrilloBlanco, ladrilloNaranja FROM usuario WHERE nombreUsuario=\"" + nombre +"\"");
        boolean b = rs.next();
        if (b) {
            colores.put("ladrilloAzul", rs.getInt(1));
            colores.put("ladrilloRojo", rs.getInt(2));
            colores.put("ladrilloBlanco", rs.getInt(3));
            colores.put("ladrilloNaranja", rs.getInt(4));
        }
        return colores;
    }

    public JSONObject getColoresPaddle(String nombre) throws SQLException {
        JSONObject colores = new JSONObject();
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
            return null;
		}
        Statement s = con.createStatement();
        ResultSet rs;
        rs = s.executeQuery("SELECT paddleRojo, paddleBlanco, paddleNaranja, paddleAzul FROM usuario WHERE nombreUsuario=\"" + nombre +"\"");
        boolean b = rs.next();
        if (b) {
            colores.put("paddleRojo", rs.getInt(1));
            colores.put("paddleBlanco", rs.getInt(2));
            colores.put("paddleNaranja", rs.getInt(3));
            colores.put("paddleAzul", rs.getInt(4));
        }
        return colores;
    }

    public String getColor(String obj, String nombre) throws SQLException{
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
            return null;
		}
        Statement s = con.createStatement();
        ResultSet rs;
        rs = s.executeQuery("SELECT color"+ obj +" FROM usuario WHERE nombreUsuario=\"" + nombre +"\"");
        boolean b = rs.next();
        if (b){
            return rs.getString(1);
        }
        return null;
    }

    public void cambiarAjustes(String nombre, String colorBola, String colorPaddel, String colorLadrillo, String colorFondo, Boolean sonido) throws SQLException{
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
		}
        int audio = 0;
        if (sonido) audio=1;
        Statement s = con.createStatement();
        s.executeUpdate("UPDATE usuario SET colorBola=\""+ colorBola +"\", colorPaddle=\""+ colorPaddel +"\", colorLadrillo=\""+ colorLadrillo +"\", colorFondo=\""+ colorFondo +"\", sonidoAct=\""+ audio +"\" WHERE nombreUsuario=\""+ nombre +"\";");
    }

    public boolean getSonido(String nombre) throws SQLException{
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
		}
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT sonidoAct FROM usuario WHERE nombreUsuario=\""+ nombre +"\"");
        boolean b = rs.next();
        if (b) {
            return rs.getBoolean(1);
        }
        return false;
    }

    public JSONObject buscarUsuario(String nombreUsuario, String contra) throws SQLException {
        System.out.println(nombreUsuario);
        System.out.println(contra);
        JSONObject j = new JSONObject();
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
		}
        ResultSet rs;
        Statement s = con.createStatement();
        rs = s.executeQuery("SELECT * FROM usuario WHERE nombreUsuario = \""+nombreUsuario+"\" AND contraseña = \"" + contra + "\"");
        boolean b = rs.next();
        if (b) {
            System.out.println("hola");
            j.put("nombreUsuario", rs.getString(1));
            j.put("correo", rs.getString(2));
            j.put("contra", rs.getString(3));
        }

        return j;
    }
    
}