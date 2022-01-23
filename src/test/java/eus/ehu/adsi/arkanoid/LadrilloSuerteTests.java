package eus.ehu.adsi.arkanoid;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import eus.ehu.adsi.arkanoid.view.game.Arkanoid;
import eus.ehu.adsi.arkanoid.view.game.Ball;
import eus.ehu.adsi.arkanoid.view.game.Brick;
import eus.ehu.adsi.arkanoid.view.game.ScoreBoard;
import eus.ehu.adsi.arkanoid.view.game.core.Game;

public class LadrilloSuerteTests {
	
	private static final Logger logger = LogManager.getLogger(GameTests.class);

	Game g = new Game();
	Brick bS = new Brick(1, 1, true, 1);
	Ball bola = new Ball(0, 0, 0);
	ScoreBoard sc = new ScoreBoard();
	int nivel = 1;
	Arkanoid a = new Arkanoid(1);
	

	public void F4P1() {
		//Descripción: El usuario pulsa el botón de “aceptar” en la interfaz al seleccionar el nivel.
		//Resultado Esperado: Al usuario se le muestra la interfaz de juego. 
		
		//Prueba no automatizable
	}

	public void F4P1_1() {
		//Descripción: No hay ladrillo de la suerte.
		//Resultado Esperado: La interfaz no contiene ningún ladrillo de la suerte.

		//Prueba no automatizable
	}

	public void F4P1_2() {
		//Descripción: Hay un ladrillo de la suerte.
		//Resultado Esperado: La interfaz contiene ningún ladrillo de la suerte de color amarillo.

		//Prueba no automatizable
	}

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

		Game.testCollision(b, bola, sc, nivel, a);
		assertEquals(b.destroyed, true);
		Game.testCollision(b2, bola, sc, nivel, a);
		assertEquals(b2.destroyed, false);
		
	}

	@Test
	public void F4P2_1() {
		//Descripción: La bola puede tocar varios ladrillos en la misma tirada.
		//Resultado Esperado: Se eliminan tantos ladrillos como la bola haya alcanzado.

		Brick b = new Brick(0, 0, false, 0);
		Brick b2 = new Brick(100,100, false, 0);
		Brick b3 = new Brick(0,1,false,0);

		Game.testCollision(b, bola, sc, nivel, a);
		assertEquals(b.destroyed, true);
		Game.testCollision(b2, bola, sc, nivel, a);
		assertEquals(b2.destroyed, false);
		Game.testCollision(b3, bola, sc, nivel, a);
		assertEquals(b.destroyed, true);
	}

	public void F4P2_2() {
		//Descripción: El usuario golpea el ladrillo especial.
		//Resultado Esperado: El jugador obtiene ventajas especiales tras golpear el ladrillo especial.

		//Prueba no automatizable
	}

	public void F4P3() {
		//Descripción: El usuario golpea más de un ladrillo especial.
		//Resultado Esperado: Al jugador se le aplican tanto grupo de ventajas como ladrillos especiales haya golpeado.

		//Finalmente solo se genera un ladrillo, por lo que esta prueba es imposible de realizar.
	}

	public void F4P3_1() {
		//Descripción: Las ventajas son las que deberían ser.
		//Resultado Esperado: Se aplican las ventajas correctas al usuario que ha golpeado el ladrillo especial.

		//Finalmente solo se genera un ladrillo, por lo que esta prueba es imposible de realizar.
	}

	public void F4P3_2() {
		//Descripción: Se aplica la primera ventaja relacionada con las vidas.
		//Resultado Esperado: Se agregan las vidas correctamente al contador de vidas tras golpear un ladrillo de la suerte.

		//Prueba no automatizable
	}

	public void F4P3_2_1() {
		//Descripción: Se añade la vida o varias vidas.
		//Resultado Esperado: El sistema decide si se añaden una o varias vidas al almacén de vidas.

		//Prueba no automatizable
	}

	public void F4P3_2_2() {
		//Descripción: Se añade el número de vidas correcto.
		//Resultado Esperado: El sistema realiza la suma de las vidas, correctamente en el almacén de vidas. 
		//La suma de las vidas que el usuario ya tenía más las conseguidas con el ladrillo especial.

		//Prueba no automatizable
	}

	public void F4P3_2_3() {
		//Descripción: El usuario pierde vidas.
		//Resultado Esperado: Cuando el usuario no alcanza la bola con el paddle, se perderá una de las vidas aplicadas de la ventaja.

		//Prueba no automatizable
	}

	public void F4P3_3() {
		//Descripción: Romper el ladrillo de la suerte divide la bola.
		//Resultado Esperado: Si el jugador ha roto un ladrillo de la suerte y el sistema ha decidido darle la ventaja de que esta se divide en dos.

		//Prueba no automatizable
	}

	public void F4P3_3_1() {
		//Descripción: Una de las dos bolas se cae.
		//Resultado Esperado: Si una bola de las dos no rebota en el pádel, se cae pero no se pierde una vida.

		//Prueba no automatizable
	}

	public void F4P3_3_2() {
		//Descripción: Las bolas se caen al vacío.
		//Resultado Esperado: Si las dos bolas se caen a la vez se pierde una vida.

		//Prueba no automatizable
	}

	public void F4P3_3_3() {
		//Descripción: Las bolas no se caen.
		//Resultado Esperado: Si las dos bolas rebotan en el paddle, se continúa y ambas destruyen los ladrillos.

		//Prueba no automatizable
	}

	public void F4P3_4() {
		//Descripción: Se aplica la tercera ventaja. El paddle se hace más grande.
		//Resultado Esperado: El sistema detecta que se aplica la ventaja relacionada con la anchura del paddle tras golpear un ladrillo de la suerte.

		//Prueba no automatizable
	}

	public void F4P3_4_1() {
		//Descripción: El paddle se extiende.
		//Resultado Esperado: El sistema decide la anchura.

		//Prueba no automatizable
	}

	public void F4P3_4_2() {
		//Descripción: El paddle se ensancha y se mantiene durante la partida.
		//Resultado Esperado: El paddle se ensancha de forma correcta hasta el final de la ronda.

		//Prueba no automatizable
	}

	public void F4P3_4_3() {
		//Descripción: El usuario pierde una vida.
		//Resultado Esperado: Si la ventaja del paddle es aplicada y 
		//el usuario pierde una vida debido a que no se ha alcanzado la bola y ésta ha caído al vacío, el paddle vuelve a su estado original.

		//Prueba no automatizable
	}

	public void F4P3_5() {
		//Descripción: Se produce una explosión.
		//Resultado Esperado: El sistema detecta que se tiene que realizar una explosión tras golpear un ladrillo especial.

		//Prueba no automatizable
	}

	public void F4P3_5_1() {
		//Descripción: El rango de la explosión es el correcto.
		//Resultado Esperado: Se produce la explosión eliminando los ladrillos contiguos al ladrillo especial que haya interceptado.

		//Prueba no automatizable

	}

	//--------PRUEBAS DE CAJA BLANCA--------//


}

