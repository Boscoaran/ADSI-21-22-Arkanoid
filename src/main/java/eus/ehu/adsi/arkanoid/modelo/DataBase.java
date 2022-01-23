package eus.ehu.adsi.arkanoid.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
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
        ResultSet rs;
        if (nivel == 0){
            rs = s.executeQuery("SELECT nombreUsuario, puntuacion FROM partida ORDER BY puntuacion DESC, fechaFin ASC LIMIT 1 OFFSET " + (p-1));
        }else{
            rs = s.executeQuery("SELECT NombreUsuario, Puntuacion FROM Partida WHERE NombreNivel=\""+ nivel + "\" ORDER BY Puntuacion DESC, FechaFin ASC LIMIT 1 OFFSET " + (p-1));
        }
        Boolean b = rs.next();
        if (b){
            jugadorPuntuacion.put("nombre", rs.getString(1));
            jugadorPuntuacion.put("puntos", rs.getInt(2));
        }
        System.out.println(jugadorPuntuacion.get("nombre"));
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
            rs1 = s.executeQuery("SELECT Puntuacion FROM Partida WHERE NombreUsuario=\""+ nombre +"\" ORDER BY Puntuacion DESC, FechaFin ASC LIMIT 1 OFFSET " + (p-1));
        }else{
            rs1 = s.executeQuery("SELECT Puntuacion FROM Partida WHERE NombreUsuario=\""+ nombre +"\" AND Nivel=\""+ nivel +"\" ORDER BY Puntuacion DESC, FechaFin ASC LIMIT 1 OFFSET " + (p-1));
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
            rs1 = s.executeQuery("SELECT count(*) FROM Partida WHERE Victoria=1 AND NombreNivel=\""+nivel+"\"");
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
            rs1 = s.executeQuery("SELECT count(*) FROM Partida WHERE Victoria=1 AND NombreUsuario=\"" + nombre + "\" AND NombreNivel=\""+ nivel +"\"");
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

    public void registrarUsuario(String nombreUsuario, String correo, String contrasena1) throws SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al registrar el driver de MySQL:" + e);
        }
        Statement s = con.createStatement();
        s.executeUpdate("INSERT INTO usuario(nombreUsuario, correo, contraseña) VALUES (\""+nombreUsuario+"\",\""+correo+"\",\""+contrasena1+"\")");
        con.close();
        return;
    }

    public void cambiarContrasena(String nombreUsuario, String contrasena) throws SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al registrar el driver de MySQL:" + e);
        }
        Statement s = con.createStatement();
        s.executeUpdate("UPDATE usuario SET contraseña = \""+contrasena+"\"WHERE nombreUsuario = \""+nombreUsuario+"\"");
        con.close();
        return;
    }

    public void borrarUsuarios() throws SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al registrar el driver de MySQL:" + e);
        }
        ResultSet rs;
        Statement s = con.createStatement();
        s.executeUpdate("DELETE FROM usuario");
        con.close();
        return;
    }

    public JSONObject buscarUsuario(String nombreUsuario) throws SQLException {

        JSONObject j = new JSONObject();
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el driver de MySQL:" + e);
		}
        ResultSet rs;
        Statement s = con.createStatement();
        rs = s.executeQuery("SELECT * FROM usuario WHERE nombreUsuario = \""+nombreUsuario+"\"");
        boolean b = rs.next();
        if (b) {
            j.put("nombreUsuario", rs.getString(1));
            j.put("correo", rs.getString(2));
            j.put("contra", rs.getString(3));
        }
        con.close();
        return j;
    }

    public JSONObject buscarUsuarioCorreo(String correo) throws SQLException {

        JSONObject j = new JSONObject();
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al registrar el driver de MySQL:" + e);
        }
        ResultSet rs;
        Statement s = con.createStatement();
        rs = s.executeQuery("SELECT * FROM usuario WHERE correo = \""+correo+"\"");
        boolean b = rs.next();
        if (b) {
            j.put("nombreUsuario", rs.getString(1));
            j.put("correo", rs.getString(2));
            j.put("contra", rs.getString(3));
        }
        con.close();
        return j;
    }

    public void guardarPartida(int puntuacion, int ladrillosNormalesDestruidos, int ladrillosEspecialesDestruidos,
            boolean victoria, String nombre, LocalDateTime fechaFin, int numVidas, int lvl) throws SQLException {
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
		}
        String fecha = fechaFin.toString();
        Statement s = con.createStatement();
        int v = 0;
        if (victoria) v = 1;
        s.execute("INSERT INTO Partida VALUES (\"" + nombre + "\", \""+ "null" + "\", " + lvl + ", , \""+ "null" + "\", \""+ fecha + "\", " + puntuacion + ", " + ladrillosNormalesDestruidos + ", " + ladrillosEspecialesDestruidos + ", " + v + ", " + numVidas + ")");       
    }
    
    public int getMaxPunt(String nombre) throws SQLException {
        int maxPunt=0;
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
            return -1;
		}
        Statement s = con.createStatement();
        ResultSet rs;
        rs = s.executeQuery("SELECT MAX(Puntuacion) FROM Partida WHERE nombreUsuario=\"" + nombre +"\"");
        boolean b = rs.next();
        if (b) {
            maxPunt=rs.getInt(1);
        }
        return maxPunt;
    }

    public void annadirDesbloquable(String nombreUsuario, String nTabla) throws Exception {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql4466495","NKihfwtwiR");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al registrar el dirver de MySQL:" + e);
        }
        Statement s = con.createStatement();
        s.executeUpdate("UPDATE usuario SET\""+nTabla+"\"=TRUE WHERE nombreUsuario =\""+nombreUsuario+"\"");
    }
}