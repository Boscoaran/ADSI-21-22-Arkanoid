package eus.ehu.adsi.arkanoid;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.controlador.CreadorVentaja;
import eus.ehu.adsi.arkanoid.controlador.GestorPartidas;
import eus.ehu.adsi.arkanoid.controlador.GestorUsuarios;
import eus.ehu.adsi.arkanoid.modelo.Partida;
import eus.ehu.adsi.arkanoid.modelo.Usuario;
import eus.ehu.adsi.arkanoid.modelo.Ventaja;
import eus.ehu.adsi.arkanoid.modelo.VentajaBola;
import eus.ehu.adsi.arkanoid.modelo.VentajaLadrillos;
import eus.ehu.adsi.arkanoid.modelo.VentajaPaddle;
import eus.ehu.adsi.arkanoid.modelo.VentajaVidas;
import eus.ehu.adsi.arkanoid.view.game.Arkanoid;
import eus.ehu.adsi.arkanoid.view.game.Ball;
import eus.ehu.adsi.arkanoid.view.game.Brick;
import eus.ehu.adsi.arkanoid.view.game.Config;
import eus.ehu.adsi.arkanoid.view.game.Paddle;
import eus.ehu.adsi.arkanoid.view.game.ScoreBoard;
import eus.ehu.adsi.arkanoid.view.game.core.Game;

public class LadrilloSuerteTests {
	

	Game g = new Game();
	Brick bS = new Brick(0, 0, true, 1);
	Ball bola = new Ball(0, 0, 1);
	ScoreBoard sc = new ScoreBoard();
	int nivel = 1;
	Arkanoid a = new Arkanoid(1, "prueba");
	Paddle p = new Paddle(0,0);
	
	@Test
	public void F4P1() {
		//Descripción: El usuario pulsa el botón de “aceptar” en la interfaz al seleccionar el nivel.
		//Resultado Esperado: Al usuario se le muestra la interfaz de juego. 
		
		//Prueba no automatizable.

		//Resultado obtenido: Se abre la interfaz de la partida.
	}

	@Test
	public void F4P1_1() {
		//Descripción: No hay ladrillo de la suerte.
		//Resultado Esperado: La interfaz no contiene ningún ladrillo de la suerte.

		List<Brick> a = new ArrayList<>();

		a = Game.initializeBricks(a, nivel, 1);
		for (Brick b : a) {
			if (b.getSuerte()) assertEquals(b.getSuerte(), true);
			assertEquals(b.getSuerte(), false);
		}
	
	}

	@Test
	public void F4P1_2() {
		//Descripción: Hay un ladrillo de la suerte.
		//Resultado Esperado: La interfaz contiene ningún ladrillo de la suerte de color amarillo.

		//Prueba no automatizable.

		List<Brick> a = new ArrayList<>();

		a = Game.initializeBricks(a, nivel, 2);
		for (Brick b : a) {
			if (b.getSuerte()) assertEquals(b.getSuerte(), true);
			else assertEquals(b.getSuerte(), false);
		}
	}

	@Test
	public void F4P1_3() {
		//Descripción: Hay más de un ladrillo de la suerte.
		//Resultado Esperado: La interfaz contiene más de un ladrillo de la suerte de color amarillo.

		//Finalmente solo se genera un ladrillo, por lo que esta prueba es imposible de realizar.
	}

	@Test
	public void F4P2() {
		//Descripción: La bola toca en uno de los ladrillos normales.
		//Resultado Esperado: Los ladrillos desaparecen cuando son interceptados por la bola.

		Brick b = new Brick(0, 0, false, 0);
		Brick b2 = new Brick(100,100, false, 0);

		Game.testCollision(b, bola, sc, nivel, a, null, "prueba");
		assertEquals(b.destroyed, true);
		Game.testCollision(b2, bola, sc, nivel, a, null, "prueba");
		assertEquals(b2.destroyed, false);
		
	}

	@Test
	public void F4P2_1() {
		//Descripción: La bola puede tocar varios ladrillos en la misma tirada.
		//Resultado Esperado: Se eliminan tantos ladrillos como la bola haya alcanzado.

		Brick b = new Brick(0, 0, false, 0);
		Brick b2 = new Brick(100,100, false, 0);
		Brick b3 = new Brick(0,1,false,0);

		Game.testCollision(b, bola, sc, nivel, a, null, "prueba");
		assertEquals(b.destroyed, true);
		Game.testCollision(b2, bola, sc, nivel, a, null, "prueba");
		assertEquals(b2.destroyed, false);
		Game.testCollision(b3, bola, sc, nivel, a, null, "prueba");
		assertEquals(b.destroyed, true);
	}

	@Test
	public void F4P2_2() {
		//Descripción: El usuario golpea el ladrillo especial.
		//Resultado Esperado: El jugador obtiene ventajas especiales tras golpear el ladrillo especial.

		JSONObject j = new JSONObject();
		j.put("vidas", 2);
		j.put("descrip", "Se han agregado 2 vidas");
		Game.testCollision(bS, bola, sc, nivel, a, j, "prueba");
		assertEquals(sc.lives, 7);
	}

	@Test
	public void F4P3() {
		//Descripción: El usuario golpea más de un ladrillo especial.
		//Resultado Esperado: Al jugador se le aplican tanto grupo de ventajas como ladrillos especiales haya golpeado.

		//Finalmente solo se genera un ladrillo, por lo que esta prueba es imposible de realizar.
	}

	@Test
	public void F4P3_1() {
		//Descripción: Las ventajas son las que deberían ser.
		//Resultado Esperado: Se aplican las ventajas correctas al usuario que ha golpeado el ladrillo especial.

		//Finalmente solo se genera un ladrillo, por lo que esta prueba es imposible de realizar.
	}

	@Test
	public void F4P3_2() {
		//Descripción: Se aplica la primera ventaja relacionada con las vidas.
		//Resultado Esperado: Se agregan las vidas correctamente al contador de vidas tras golpear un ladrillo de la suerte.

		JSONObject j = new JSONObject();
		j.put("vidas", 2);
		j.put("descrip", "Se han agregado 2 vidas");
		Game.testCollision(bS, bola, sc, nivel, a, j, "prueba");
		assertEquals(sc.lives, 7);
	}

	@Test
	public void F4P3_2_1() {
		//Descripción: Se añade la vida o varias vidas.
		//Resultado Esperado: El sistema decide si se añaden una o varias vidas al almacén de vidas.

		JSONObject j = new JSONObject();
		j.put("vidas", 2);
		j.put("descrip", "Se han agregado 2 vidas");
		Game.testCollision(bS, bola, sc, nivel, a, j, "prueba");
		assertEquals(sc.lives, 7);
	}

	@Test
	public void F4P3_2_2() {
		//Descripción: Se añade el número de vidas correcto.
		//Resultado Esperado: El sistema realiza la suma de las vidas, correctamente en el almacén de vidas. 
		//La suma de las vidas que el usuario ya tenía más las conseguidas con el ladrillo especial.

		JSONObject j = new JSONObject();
		j.put("vidas", 2);
		j.put("descrip", "Se han agregado 2 vidas");
		Game.testCollision(bS, bola, sc, nivel, a, j, "prueba");
		assertEquals(sc.lives, 7);

		sc = new ScoreBoard();

		j = new JSONObject();
		j.put("vidas", 4);
		j.put("descrip", "Se han agregado 4 vidas");
		Game.testCollision(bS, bola, sc, nivel, a, j, "prueba");
		assertEquals(sc.lives, 9);
	}

	@Test
	public void F4P3_2_3() {
		//Descripción: El usuario pierde vidas.
		//Resultado Esperado: Cuando el usuario no alcanza la bola con el paddle, se perderá una de las vidas aplicadas de la ventaja.

		JSONObject j = new JSONObject();
		j.put("vidas", 2);
		j.put("descrip", "Se han agregado 2 vidas");
		Game.testCollision(bS, bola, sc, nivel, a, j, "prueba");
		assertEquals(sc.lives, 7);
		
		bola.y = Config.SCREEN_HEIGHT+100;
		bola.update(sc, p, nivel, a);
		assertEquals(sc.lives, 6);
	}

	@Test
	public void F4P3_3() {
		//Descripción: Romper el ladrillo de la suerte divide la bola.
		//Resultado Esperado: Si el jugador ha roto un ladrillo de la suerte y el sistema ha decidido darle la ventaja de que esta se divide en dos.

		JSONObject j = new JSONObject();
		j.put("bola", 2);
		j.put("descrip", "Se ha duplicado la bola");
		Game.testCollision(bS, bola, sc, nivel, a, j, "prueba");
		
		assertNotNull(a.getBola(2));
	}

	@Test
	public void F4P3_3_1() {
		//Descripción: Una de las dos bolas se cae.
		//Resultado Esperado: Si una bola de las dos no rebota en el pádel, se cae pero no se pierde una vida.

		JSONObject j = new JSONObject();
		j.put("bola", 2);
		j.put("descrip", "Se ha duplicado la bola");
		Game.testCollision(bS, bola, sc, nivel, a, j, "prueba");
		
		Ball bola2 = a.getBola(2);
		bola2.y = Config.SCREEN_HEIGHT+100;
		bola2.update(sc, p, nivel, a);

		assertEquals(sc.lives, 5);
	}

	@Test
	public void F4P3_3_2() {
		//Descripción: Las bolas se caen al vacío.
		//Resultado Esperado: Si las dos bolas se caen a la vez se pierde una vida.

		JSONObject j = new JSONObject();
		j.put("bola", 2);
		j.put("descrip", "Se ha duplicado la bola");
		Game.testCollision(bS, bola, sc, nivel, a, j, "prueba");
		
		Ball bola2 = a.getBola(2);
		bola2.y = Config.SCREEN_HEIGHT+100;
		bola2.update(sc, p, nivel, a);
		bola.y = Config.SCREEN_HEIGHT+100;
		bola.update(sc, p, nivel, a);

		assertEquals(sc.lives, 4);
	}

	@Test
	public void F4P3_3_3() {
		//Descripción: Las bolas no se caen.
		//Resultado Esperado: Si las dos bolas rebotan en el paddle, se continúa y ambas destruyen los ladrillos.

		//Prueba no automatizable --> Requiere la ejecución total del juego. Probar de manera manual.

		//Resultado obtenido: Las pelotas rebotan en el paddle como si se tratara de una pelota.
	}

	@Test
	public void F4P3_4() {
		//Descripción: Se aplica la tercera ventaja. El paddle se hace más grande.
		//Resultado Esperado: El sistema detecta que se aplica la ventaja relacionada con la anchura del paddle tras golpear un ladrillo de la suerte.

		JSONObject j = new JSONObject();
		j.put("paddle", 2);
		j.put("descrip", "Se ha aumentado el tamaño del paddle");
		Game.testCollision(bS, bola, sc, nivel, a, j, "prueba");
		
		Paddle p = a.getPaddle();

		assertEquals(p.size(), 100.0, 100.0);
	}

	@Test
	public void F4P3_4_1() {
		//Descripción: El paddle se extiende.
		//Resultado Esperado: El sistema decide la anchura.

		JSONObject j = new JSONObject();
		j.put("paddle", 2);
		j.put("descrip", "Se ha aumentado el tamaño del paddle");
		Game.testCollision(bS, bola, sc, nivel, a, j, "prueba");
		
		Paddle p = a.getPaddle();

		assertEquals(p.size(), 100.0, 100.0);
	}

	@Test
	public void F4P3_4_2() {
		//Descripción: El paddle se ensancha y se mantiene durante la partida.
		//Resultado Esperado: El paddle se ensancha de forma correcta hasta el final de la ronda.

		JSONObject j = new JSONObject();
		j.put("paddle", 2);
		j.put("descrip", "Se ha aumentado el tamaño del paddle");
		Game.testCollision(bS, bola, sc, nivel, a, j, "prueba");
		
		Paddle p = a.getPaddle();

		assertEquals(p.size(), 100.0, 100.0);
	}

	@Test
	public void F4P3_4_3() {
		//Descripción: El usuario pierde una vida.
		//Resultado Esperado: Si la ventaja del paddle es aplicada y 
		//el usuario pierde una vida debido a que no se ha alcanzado la bola y ésta ha caído al vacío, el paddle vuelve a su estado original.

		//Funcionalidad finalmente no implementada
	}

	@Test
	public void F4P3_5() {
		//Descripción: Se produce una explosión.
		//Resultado Esperado: El sistema detecta que se tiene que realizar una explosión tras golpear un ladrillo especial.

		//Prueba no automatizable --> Requiere la ejecución total del juego. Probar de manera manual.

		//Resultado obtenido: En caso de que haya ladrillos suficientes en la lista, se eliminan los ladrillos más cercanos.
		
	}

	@Test
	public void F4P3_5_1() {
		//Descripción: El rango de la explosión es el correcto.
		//Resultado Esperado: Se produce la explosión eliminando los ladrillos contiguos al ladrillo especial que haya interceptado.

		//Prueba no automatizable --> Requiere la ejecución total del juego. Probar de manera manual.

		//Resultado obtenido: En caso de que haya ladrillos suficientes en la lista, se eliminan los ladrillos más cercanos.

	}

	//--------PRUEBAS DE CAJA BLANCA--------//

	@Test
	public void soloSeGeneraSiusuarioExiste() {
		//Descripción: la ventaja se genera si el nombre del usuario está almacenado en objetos y existe una partida actual
		
		//El usuario no existe
		JSONObject j = ArkanoidFrontera.getArkanoidFrontera().darVentaja("null");
		assertNull(j);

		//Existe el usuario pero la partida no
		Usuario u = new Usuario("prueba", "prueba@gmail.com", "prueba");
		GestorUsuarios.getGestorUsuarios().anadir(u);
		j = ArkanoidFrontera.getArkanoidFrontera().darVentaja("prueba");
		assertNull(j);

		//Existe el usuario y la partida
		Partida p = new Partida(0, 0 , 0, false, u, 5, 1);
		GestorPartidas.getGestorPartidas().anadir(p);
		
		j = ArkanoidFrontera.getArkanoidFrontera().darVentaja("prueba");
		assertNotNull(j);
	}

	@Test
	public void buscarPartida() {
		//Descripción: buscar partida almacenada en objetos

		//No hay partidas almacenadas del usuario "prueba"
		Usuario u = new Usuario("prueba", "prueba@gmail.com", "prueba");
		GestorUsuarios.getGestorUsuarios().anadir(u);
		Partida p = GestorPartidas.getGestorPartidas().buscarPartidaActual(u);
		assertNull(p);

		//Hay una partida pero está finalizada
		p = new Partida(0, 0 , 0, false, u, 5, 1);
		p.setFechaFin();
		GestorPartidas.getGestorPartidas().anadir(p);
		Partida par = GestorPartidas.getGestorPartidas().buscarPartidaActual(u);
		assertNull(par);

		//Hay una partida pero no está finalizada
		p = new Partida(0, 0 , 0, false, u, 5, 1);
		GestorPartidas.getGestorPartidas().anadir(p);
		par = GestorPartidas.getGestorPartidas().buscarPartidaActual(u);
		assertNotNull(par);
	}

	@Test
	public void generarVentaja() {
		//Descripción: se genera la ventaja indicada correctamente
		Ventaja v = CreadorVentaja.getCreadorVentaja().crearVentaja(1);
		assertEquals(v.getClass(), VentajaVidas.class);

		v = CreadorVentaja.getCreadorVentaja().crearVentaja(2);
		assertEquals(v.getClass(), VentajaBola.class);

		v = CreadorVentaja.getCreadorVentaja().crearVentaja(3);
		assertEquals(v.getClass(), VentajaPaddle.class);

		v = CreadorVentaja.getCreadorVentaja().crearVentaja(4);
		assertEquals(v.getClass(), VentajaLadrillos.class);

		v = CreadorVentaja.getCreadorVentaja().crearVentaja(5);
		assertEquals(v, null);
	}

}

