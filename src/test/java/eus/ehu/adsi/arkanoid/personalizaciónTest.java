package eus.ehu.adsi.arkanoid;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.modelo.DataBase;
import eus.ehu.adsi.arkanoid.modelo.Usuario;
import eus.ehu.adsi.arkanoid.view.Personalizacion21;

import org.json.JSONObject;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class personalizaciónTest {

    @Test
	public void F3P1() {
		//Descripción: Pulsar el botón “Ajustes” del menú principal. Imagen 18 (Menú principal)
		//Resultado Esperado: Se abre el menú de Ajustes en una nueva ventana.
		
	}

    @Test
	public void F3P2_1() {
		//Descripción: Cargar el menú de opciones. Imagen 21a (Ajustes)
		//Resultado Esperado: Aparecen todas las opciones que se pueden modificar: el fondo, el paddle, la bola.
		
	}

    @Test
	public void F3P2_2() {
		//Descripción: Cargar el menú muestra las opciones establecidas. Imagen 21a (Ajustes)
		//Resultado Esperado: Aparecen todas las opciones que han sido definidas correctamente por el usuario previamente o, en su caso, las predefinidas por el sistema.
		
	}

    @Test
	public void F3P3() throws SQLException {
		//Descripción: Pulsar en el desplegable de los ajustes del fondo.
		//Resultado Esperado: Muestra todos los colores establecidos en la base de datos para el fondo.
	
	}

    @Test
	public void F3P3_1() throws SQLException {
		//Descripción: Se muestran los colores de fondo no desbloqueados.
		//Resultado Esperado: Tras pulsar el desplegable, se observa que los colores no desbloqueados por el usuario tienen un color de letra rojo.
		
	}

	@Test
	public void F3P3_1_1() {
		//Descripción: Se muestra el mensaje con el requisito para desbloquear el color bloqueado del fondo. Imagen 21b (Ajustes opción bloqueada)
		//Resultado Esperado: Tras pasar por encima del color bloqueado se puede ver el mensaje con el requisito para desbloquearlo justo debajo del título “Ajustes”.
	}

	@Test
	public void F3P3_1_2() {
		//Descripción: Quitar el mouse del color bloqueado del paddle elimina el mensaje del requisito de la interfaz.
		//Resultado Esperado: Tras mover el mouse a otra posición en la que no haya un color bloqueado el mensaje con el requisito se deja de mostrar.
	}

	@Test
	public void F3P3_1_3() {
		//Descripción: Poner el mouse sobre otro color, estando previamente en otro.
		//Resultado Esperado: Moviendo el mouse de un color bloqueado a otro que también está bloqueado cambia el mensaje con el requisito de desbloqueo.
	}

	@Test
	public void F3P3_2() {
		//Descripción: Poner el mouse sobre otro color, estando previamente en otro.
		//Resultado Esperado: Moviendo el mouse de un color bloqueado a otro que también está bloqueado cambia el mensaje con el requisito de desbloqueo.
	}

	@Test
	public void F3P3_3() {
		//Descripción: Pulsar un color del fondo en el menú desplegable
		//Resultado Esperado: Si el color está desbloqueado, se marca; si no, no sucede nada.
	}

	@Test
	public void F3P3_4() {
		//Descripción: Pulsar el botón aceptar habiendo seleccionado el color de un fondo.
		//Resultado Esperado: Si el fondo no estaba activado antes, se activa (se muestra a partir de la siguiente partida) y si es el mismo que estaba establecido antes, no se hace nada.
	}

	@Test
	public void F3P4_1() {
		//Descripción: Se muestran los colores de paddle no desbloqueados
		//Resultado Esperado: Tras pulsar el desplegable, se observa que los colores no desbloqueados por el usuario tienen un color de letra rojo.
	}

	@Test
	public void F3P4_1_1() {
		//Descripción: Se muestra el mensaje con el requisito para desbloquear el color bloqueado del paddle.
		//Resultado Esperado: Tras pasar por encima del color bloqueado se puede ver el mensaje con el requisito para desbloquearlo justo debajo del título “Ajustes”.
	}

	@Test
	public void F3P4_1_2() {
		//Descripción: Quitar el mouse del color bloqueado del paddle elimina el mensaje del requisito de la interfaz.
		//Resultado Esperado: Tras mover el mouse a otra posición en la que no haya un color bloqueado el mensaje con el requisito se deja de mostrar.
	}

	@Test
	public void F3P4_1_3() {
		//Descripción: Poner el mouse sobre otro color del paddle, estando previamente en otro.
		//Resultado Esperado: Moviendo el mouse de un color bloqueado a otro que también está bloqueado cambia el mensaje con el requisito de desbloqueo.
	}

	@Test
	public void F3P4_2() {
		//Descripción: Se muestran los colores del paddle desbloqueados
		//Resultado Esperado: Tras pulsar el desplegable, se observa que los colores desbloqueados se muestran con el texto en negro.
	}

	@Test
	public void F3P4_3() {
		//Descripción: Pulsar un color del paddle en el menú desplegable
		//Resultado Esperado: Si el color está desbloqueado, se marca; si no, no sucede nada.
	}

	@Test
	public void F3P4_4() {
		//Descripción: Pulsar el botón aceptar habiendo seleccionado el color de un paddle.
		//Resultado Esperado: Si el color del paddle no estaba activado antes, se activa (se muestra a partir de la siguiente partida) y si es el mismo que estaba establecido antes, no se hace nada.
	}

	@Test
	public void F3P5() {
		//Descripción: Pulsar en el desplegable de los ajustes de la bola
		//Resultado Esperado: Muestra todos los colores establecidos en la base de datos para la bola.
	}

	@Test
	public void F3P5_1() {
		//Descripción: Se muestran los colores de la bola no desbloqueados
		//Resultado Esperado: Tras pulsar el desplegable, se observa que los colores no desbloqueados por el usuario tienen un color de letra rojo.
	}

	@Test
	public void F3P5_1_1() {
		//Descripción: Se muestra el mensaje con el requisito para desbloquear el color bloqueado de la bola.
		//Resultado Esperado: Tras pasar por encima del color bloqueado se puede ver el mensaje con el requisito para desbloquearlo justo debajo del título “Ajustes”.
	}

	@Test
	public void F3P5_1_2() {
		//Descripción: Quitar el mouse del color bloqueado de la bola elimina el mensaje del requisito de la interfaz.
		//Resultado Esperado: Tras mover el mouse a otra posición en la que no haya un color bloqueado el mensaje con el requisito se deja de mostrar.
	}

	@Test
	public void F3P5_1_3() {
		//Descripción: Poner el mouse sobre otro color de la bola, estando previamente en otro.
		//Resultado Esperado: Moviendo el mouse de un color bloqueado a otro que también está bloqueado cambia el mensaje con el requisito de desbloqueo.
	}

	@Test
	public void F3P5_2() {
		//Descripción: Se muestra el mensaje con el requisito para desbloquear el color bloqueado de la bola.
		//Resultado Esperado: Tras pasar por encima del color bloqueado se puede ver el mensaje con el requisito para desbloquearlo justo debajo del título “Ajustes”.
	}

	@Test
	public void F3P5_3() {
		//Descripción: Pulsar un color de la bola en el menú desplegable
		//Resultado Esperado: Si el color está desbloqueado, se marca; si no, no sucede nada.
	}

	@Test
	public void F3P5_4() {
		//Descripción: Pulsar el botón aceptar habiendo seleccionado el color de la bola.
		//Resultado Esperado: Si el color de la bola no estaba activado antes, se activa (se muestra a partir de la siguiente partida) y si es el mismo que estaba establecido antes, no se hace nada.
	}

	@Test
	public void F3P6() {
		//Descripción: Pulsar en el desplegable de los ajustes de los ladrillos
		//Resultado Esperado: Muestra todos los colores establecidos en la base de datos para los ladrillos.
	}

	@Test
	public void F3P6_1() {
		//Descripción: Se muestran los colores de los ladrillos no desbloqueados
		//Resultado Esperado: Tras pulsar el desplegable, se observa que los colores no desbloqueados por el usuario tienen un color de letra rojo.
	}

	@Test
	public void F3P6_1_1() {
		//Descripción: Se muestra el mensaje con el requisito para desbloquear el color bloqueado de los ladrillos.
		//Resultado Esperado: Tras pasar por encima del color bloqueado se puede ver el mensaje con el requisito para desbloquearlo justo debajo del título “Ajustes”.
	}

	@Test
	public void F3P6_1_2() {
		//Descripción: Quitar el mouse del color bloqueado de los ladrillos elimina el mensaje del requisito de la interfaz.
		//Resultado Esperado: Tras mover el mouse a otra posición en la que no haya un color bloqueado el mensaje con el requisito se deja de mostrar.
	}

	@Test
	public void F3P6_1_3() {
		//Descripción: Poner el mouse sobre otro color de los ladrillos, estando previamente en otro.
		//Resultado Esperado: Moviendo el mouse de un color bloqueado a otro que también está bloqueado cambia el mensaje con el requisito de desbloqueo.
	}

	@Test
	public void F3P6_2() {
		//Descripción: Se muestran los colores para ladrillos desbloqueados
		//Resultado Esperado: Tras pulsar el desplegable, se observa que los colores desbloqueados se muestran con el texto en negro.
	}

	@Test
	public void F3P6_3() {
		//Descripción: Pulsar un color de los ladrillos en el menú desplegable
		//Resultado Esperado: Si el color está desbloqueado, se marca; si no, no sucede nada.
	}

	@Test
	public void F3P6_4() {
		//Descripción: Pulsar el botón aceptar habiendo seleccionado el color de los ladrillos.
		//Resultado Esperado: Si el color de los ladrillos no estaba activado antes, se activa (se muestra a partir de la siguiente partida) y si es el mismo que estaba establecido antes, no se hace nada.
	}

	@Test
	public void F3P7() {
		//Descripción: Pulsar en el toggle de la música
		//Resultado Esperado: Si pulsamos en el toggle de la música y esta está desactivada, se marca; y si no, se desmarca.
	}

	@Test
	public void F3P8() {
		//Descripción: Pulsar el botón de cancela
		//Resultado Esperado: Si el usuario pulsa el botón de cancelar:
 			//- Si hay cambios: no se guardan y se vuelve al menú principal.
 			//- Si no hay cambios: se vuelve al menú principal.
	}

	@Test
	public void F3P9() {
		//Descripción: Pulsar el botón aceptar con dos o más opciones cambiadas.
		//Resultado Esperado: Si se han cambiado los colores de dos o más elementos, estos se guardan en la base de datos.
	}

	@Test
	public void F3P10() {
		//Descripción: Pulsar el botón aceptar con todas las opciones cambiadas.
		//Resultado Esperado: Si se han cambiado los colores de todos los elementos, estos se guardan en la base de datos.
	}

}