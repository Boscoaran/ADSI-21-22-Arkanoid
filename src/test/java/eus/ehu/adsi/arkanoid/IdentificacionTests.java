package eus.ehu.adsi.arkanoid;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.controlador.GestorUsuarios;
import eus.ehu.adsi.arkanoid.modelo.Usuario;
import org.json.JSONObject;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import javax.mail.internet.AddressException;

public class IdentificacionTests {

	@After
	public void tearDown() {
		//Borrar todos los usuarios registrados
		// Antes de cada prueba se registrará un usuario con los datos que interesen
		ArkanoidFrontera.getArkanoidFrontera().borrarUsuarios();
	}

	//PRUEBAS DE UNIDAD DE CAJA NEGRA (definidas en el plan de pruebas por Jon Ander López de Ahumada)

	//Caso de uso Iniciar Sesión
	// Antes de empezar las pruebas nos encontramos en la página de inicio de sesión.

	@Test
	public void F2P1() {
		//Descripción: Nombre de usuario correcto y contraseña correcta, se pulsa “Iniciar sesión”
		//Resultado Esperado: Inicio de sesión correcto, se accede al Menú Principal

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar iniciar sesión con datos correctos y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarInicio("usuario", "contrasena");
		//Comprobar que el resultado es correcto (se ha iniciado sesión)
		assertTrue(resultado.getBoolean("estado"));
	}

	@Test
	public void F2P2() {
		//Descripción: Nombre de usuario correcto y contraseña incorrecta o vacía, se pulsa “Iniciar sesión”
		//Resultado Esperado: Mensaje de error

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar iniciar sesión con la contraseña incorrecta y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarInicio("usuario", "incorrecto");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Contraseña incorrecta.", resultado.get("mensaje"));
	}

	@Test
	public void F2P3() {
		//Descripción: Nombre de usuario incorrecto o vacío y contraseña correcta, se pulsa “Iniciar sesión”
		//Resultado Esperado: Mensaje de error

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar iniciar sesión con un nombre de usuario incorrecto y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarInicio("incorrecto", "contrasena");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("El usuario no está registrado.", resultado.get("mensaje"));
	}

	@Test
	public void F2P4() {
		//Descripción: Nombre de usuario incorrecto o vacío y contraseña incorrecta o vacía, se pulsa “Iniciar sesión”
		//Resultado Esperado: Mensaje de error

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar iniciar sesión con un nombre de usuario incorrecto y una contraseña incorrecta y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarInicio("incorrecto", "incorrecto");
		//Podrían salir 2 mensajes, "El usuario no está registrado." o "Contraseña incorrecta."
		// Pero como ambos estarían bien, simplemente se comprueba que el inicio haya sido incorrecto
		assertFalse(resultado.getBoolean("estado"));
	}

	@Test
	public void F2P5() {
		//Descripción: Se pulsa “Cancelar”
		//Resultado Esperado: Se vuelve a la página de Inicio

		//Prueba no automatizable, comprobar manualmente
	}

	@Test
	public void F2P6() {
		//Descripción: Se pulsa “He olvidado mi contraseña”
		//Resultado Esperado: Se pasa a la página para introducir el correo de recuperación

		//Prueba no automatizable, comprobar manualmente
	}

	//Subcaso de uso Recuperar Contraseña
	// Antes de empezar las pruebas nos encontramos en la página para introducir el correo de recuperación.

	@Test
	public void F2P7() {
		//Descripción: En la página para introducir el correo se pulsa “Cancelar”
		//Resultado Esperado: Se vuelve a la página de inicio de sesión

		//Prueba no automatizable, comprobar manualmente
	}

	@Test
	public void F2P8() {
		//Descripción: En la página para introducir el correo se pulsa “Enviar correo” sin haber puesto ningún correo
		//Resultado Esperado: Mensaje de error

		//Intentar recuperar contraseña sin introducir correo y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().recuperarContrasena("");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Introduce un correo.", resultado.get("mensaje"));
	}

	@Test
	public void F2P9() {
		//Descripción: En la página para introducir el correo se pulsa “Enviar correo” habiendo puesto un correo no válido
		//Resultado Esperado: Mensaje de error

		//Intentar recuperar contraseña con un correo de formato inválido y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().recuperarContrasena("correo");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Correo no válido.", resultado.get("mensaje"));
	}

	@Test
	public void F2P10() {
		//Descripción: En la página para introducir el correo se pulsa “Enviar correo” habiendo puesto un correo válido
		//Resultado Esperado: Se envía al correo un código de recuperación y se pasa a la página de recuperación de
		// contraseña

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar recuperar contraseña con el correo del usuario registrado y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().recuperarContrasena("correo@ehu.eus");
		//Comprobar que el resultado es correcto (se ha avanzado al siguiente paso)
		assertTrue(resultado.getBoolean("estado"));
	}

	@Test
	public void F2P10_1() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se
		// pulsa “Cancelar”
		//Resultado Esperado: Se vuelve a la página para introducir el correo de recuperación y el usuario puede
		// introducir otro correo

		//Prueba no automatizable, comprobar manualmente
	}

	@Test
	public void F2P10_2() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se pulsa
		// “Volver a enviar”
		//Resultado Esperado: Se envía al correo un nuevo código de recuperación

		//Prueba no automatizable, comprobar manualmente
	}

	@Test
	public void F2P10_3() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se
		// introduce un código incorrecto, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar cambiar la contraseña con un código incorrecto y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo("correo@ehu.eus", "123456", "000000", "contrasena", "contrasena");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Código incorrecto.", resultado.get("mensaje"));
	}

	@Test
	public void F2P10_4() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se
		// introduce el código correcto y falta la contraseña en uno o dos de los campos, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar cambiar la contraseña con un campo de contraseña vacío y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo("correo@ehu.eus", "123456", "123456", "", "contrasena");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Rellena todos los campos.", resultado.get("mensaje"));
	}

	@Test
	public void F2P10_5() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se
		// introduce el código correcto y los dos campos de contraseña están completados, pero la contraseña de cada
		// uno es distinta, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar cambiar la contraseña con contraseñas distintas y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo("correo@ehu.eus", "123456", "123456", "contrasena1", "contrasena2");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Las contraseñas no coinciden.", resultado.get("mensaje"));
	}

	@Test
	public void F2P10_6() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se
		// introduce el código correcto y los dos campos de contraseña están completados y con la misma contraseña,
		// pero el formato de la contraseña no es válido, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar cambiar la contraseña con un formato de contraseña incorrecto y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo("correo@ehu.eus", "123456", "123456", "contrasenaMasDe20Caracteres", "contrasenaMasDe20Caracteres");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Formato de contraseña no válido (longitud máxima 20 caracteres).", resultado.get("mensaje"));
	}

	@Test
	public void F2P10_7() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se
		// introduce el código correcto y los dos campos de contraseña están completados y con la misma contraseña,
		// pero es igual a la contraseña anterior del usuario, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar cambiar la contraseña con la misma contraseña que tenía y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo("correo@ehu.eus", "123456", "123456", "contrasena", "contrasena");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("La contraseña nueva no puede ser igual a la anterior.", resultado.get("mensaje"));
	}

	@Test
	public void F2P10_8() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se
		// introduce el código correcto y los dos campos de contraseña están completados y con la misma contraseña, con
		// formato adecuado, se pulsa “Aceptar”
		//Resultado Esperado: Se almacena la nueva contraseña del usuario. Se vuelve a la página de inicio de sesión

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar cambiar la contraseña con los campos correctos y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo("correo@ehu.eus", "123456", "123456", "contrasenaNueva", "contrasenaNueva");
		//Comprobar que el resultado es correcto (se cambia la contraseña y pasa al inicio de sesión)
		assertTrue(resultado.getBoolean("estado"));
	}

	//Caso de uso Registrarse
	// Antes de empezar las pruebas nos encontramos en la página de creación de cuenta.

	@Test
	public void F2P11() {
		//Descripción: Se pulsa “Cancelar”
		//Resultado Esperado: Se vuelve a la página de Inicio

		//Prueba no automatizable, comprobar manualmente
	}

	@Test
	public void F2P12() {
		//Descripción: Hay algún campo sin completar, se pulsa “Crear cuenta”
		//Resultado Esperado: Mensaje de error

		//Intentar crear una cuenta con algún campo vacío y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("", "correo@ehu.eus", "contrasena", "contrasena");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Rellena todos los campos.", resultado.get("mensaje"));
	}

	@Test
	public void F2P13() {
		//Descripción: Todos los campos están completados, nombre de usuario y correo válidos, pero los dos campos de
		// contraseña no coinciden, se pulsa “Crear cuenta”
		//Resultado Esperado: Mensaje de error

		//Intentar crear una cuenta con contraseñas distintas y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena1", "contrasena2");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Las contraseñas no coinciden.", resultado.get("mensaje"));
	}

	@Test
	public void F2P14() {
		//Descripción: Todos los campos están completados, nombre de usuario válido y los dos campos de contraseña
		// coinciden, pero el correo no es válido, se pulsa “Crear cuenta”
		//Resultado Esperado: Mensaje de error

		//Intentar crear una cuenta con un correo inválido y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo", "contrasena", "contrasena");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Correo no válido.", resultado.get("mensaje"));
	}

	@Test
	public void F2P15() {
		//Descripción: Todos los campos están completados, correo válido y los dos campos de contraseña coinciden, pero
		// el nombre de usuario no tiene un formato válido, se pulsa “Crear cuenta”
		//Resultado Esperado: Mensaje de error

		//Intentar crear una cuenta con un nombre de usuario de formato inválido y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuarioDeMasDe20Caracteres", "correo@ehu.eus", "contrasena", "contrasena");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Formato de nombre de usuario no válido (longitud máxima 20 caracteres).", resultado.get("mensaje"));
	}

	@Test
	public void F2P16() {
		//Descripción: Todos los campos están completados, correo válido y los dos campos de contraseña coinciden, pero
		// el nombre de usuario ya está registrado, se pulsa “Crear cuenta”
		//Resultado Esperado: Mensaje de error

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo1@ehu.eus", "contrasena1", "contrasena1");
		//Intentar crear una cuenta con un nombre de usuario que ya existe y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo2@ehu.eus", "contrasena2", "contrasena2");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Nombre de usuario no disponible.", resultado.get("mensaje"));
	}

	@Test
	public void F2P17() {
		//Descripción: Todos los campos están completados, correo válido y los dos campos de contraseña coinciden, pero
		// el nombre de usuario no tiene un formato válido, se pulsa “Crear cuenta”
		//Resultado Esperado: Mensaje de error

		//Ya se ha contemplado en F2P15
	}

	@Test
	public void F2P18() {
		//Descripción: Todos los campos están completados, nombre de usuario y correo válidos y los dos campos de
		// contraseña coinciden, pero el formato de la contraseña no es válido, se pulsa “Crear cuenta”
		//Resultado Esperado: Mensaje de error

		//Intentar crear una cuenta con una contraseña de formato inválido y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasenaMasDe20Caracteres", "contrasenaMasDe20Caracteres");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Formato de contraseña no válido (longitud máxima 20 caracteres).", resultado.get("mensaje"));
	}

	@Test
	public void F2P19() {
		//Descripción: Todos los campos están completados, nombre de usuario y correo válidos y los dos campos de
		// contraseña coinciden y su formato es válido, se pulsa “Crear cuenta”
		//Resultado Esperado: Se crea la cuenta y se almacena su información. El usuario pasa al Menú Principal

		//Intentar crear una cuenta con todos los campos correctos y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Comprobar que el resultado es correcto (se registra e inicia al usuario)
		assertTrue(resultado.getBoolean("estado"));
	}

	//Caso de uso Cambiar Contraseña

	@Test
	public void F2P20() {
		//Descripción: Se pulsa “Cancelar”
		//Resultado Esperado: Se vuelve al Menú Principal

		//Prueba no automatizable, comprobar manualmente
	}

	@Test
	public void F2P21() {
		//Descripción: Hay algún campo sin completar, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error

		//Intentar cambiar la contraseña con algún campo vacío y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio("", "contrasena", "contrasenaNueva", "contrasenaNueva");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Rellena todos los campos.", resultado.get("mensaje"));
	}

	@Test
	public void F2P22() {
		//Descripción: Todos los campos están completados, pero la contraseña actual no es correcta, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar cambiar la contraseña con una contraseña incorrecta y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio("usuario", "incorrecto", "contrasenaNueva", "contrasenaNueva");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Contraseña incorrecta.", resultado.get("mensaje"));
	}

	@Test
	public void F2P23() {
		//Descripción: Todos los campos están completados, la contraseña actual es correcta, pero los dos campos de la
		// nueva contraseña no coinciden, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar cambiar la contraseña con contraseñas distintas y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio("usuario", "contrasena", "contrasenaNueva1", "contrasenaNueva2");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Las contraseñas no coinciden.", resultado.get("mensaje"));
	}

	@Test
	public void F2P24() {
		//Descripción: Todos los campos están completados, la contraseña actual es correcta, los dos campos de la nueva
		// contraseña coinciden, pero su formato no es válido, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar cambiar la contraseña con una contraseña de formato inválido y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio("usuario", "contrasena", "contrasenaMasDe20Caracteres", "contrasenaMasDe20Caracteres");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("Formato de contraseña no válido (longitud máxima 20 caracteres).", resultado.get("mensaje"));
	}

	@Test
	public void F2P25() {
		//Descripción: Todos los campos están completados, la contraseña actual es correcta, los dos campos de la nueva
		// contraseña coinciden, pero la nueva contraseña es igual a la actual, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar cambiar la contraseña con una contraseña igual a la anterior y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio("usuario", "contrasena", "contrasena", "contrasena");
		//Comprobar que el mensaje de error mostrado es el que corresponde
		assertEquals("La contraseña nueva no puede ser igual a la anterior.", resultado.get("mensaje"));
	}

	@Test
	public void F2P26() {
		//Descripción: Todos los campos están completados, la contraseña actual es correcta, los dos campos de la nueva
		// contraseña coinciden y su formato es válido, se pulsa “Aceptar”
		//Resultado Esperado: Se cambia y se almacena la nueva contraseña del usuario. El usuario vuelve al Menú
		// Principal

		//Registrar un usuario de prueba
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Intentar cambiar la contraseña con todos los campos correctos y recoger el resultado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio("usuario", "contrasena", "contrasenaNueva", "contrasenaNueva");
		//Comprobar que el resultado es correcto (se cambia la contraseña)
		assertTrue(resultado.getBoolean("estado"));
	}

	//PRUEBAS DE UNIDAD DE CAJA BLANCA

	//Clase Usuario

	//Método esCorreo(String pCorreo)

	@Test
	public void esCorreoCorrecto() {
		//Crear un usuario de prueba
		Usuario u = new Usuario("usuario", "correo@ehu.eus", "contrasena");
		//Comprobar que el mismo correo devuelve True
		assertTrue(u.esCorreo("correo@ehu.eus"));
	}

	@Test
	public void esCorreoIncorrecto() {
		//Crear un usuario de prueba
		Usuario u = new Usuario("usuario", "correo@ehu.eus", "contrasena");
		//Comprobar que un correo incorrecto devuelve False
		assertFalse(u.esCorreo("incorrecto"));
	}

	//Método esContrasena(String pContrasena)

	@Test
	public void esContrasenaCorrecta() {
		//Crear un usuario de prueba
		Usuario u = new Usuario("usuario", "correo@ehu.eus", "contrasena");
		//Comprobar que la misma contraseña devuelve True
		assertTrue(u.esContrasena("contrasena"));
	}

	@Test
	public void esContrasenaIncorrecta() {
		//Crear un usuario de prueba
		Usuario u = new Usuario("usuario", "correo@ehu.eus", "contrasena");
		//Comprobar que una contraseña incorrecta devuelve False
		assertFalse(u.esContrasena("incorrecto"));
	}

	//Método setContrasena(String pContrasena)

	@Test
	public void setContrasena() {
		//Crear un usuario de prueba
		Usuario u = new Usuario("usuario", "correo@ehu.eus", "incorrecto");
		//Cambiar su contraseña
		u.setContrasena("contrasena");
		//Comprobar que la contraseña es la definida con el cambio
		assertTrue(u.esContrasena("contrasena"));
	}

	//Clase GestorUsuarios

	//Método buscarUsuarioCorreo(String correo)

	@Test
	public void buscarUsuarioCorreoExiste() {
		//Crear un usuario de prueba
		Usuario u = new Usuario("usuario", "correo@ehu.eus", "contrasena");
		//Añadir el usuario a la lista
		GestorUsuarios.getGestorUsuarios().anadir(u);
		//Comprobar que se ha encontrado el Usuario (el método no ha devuelto NULL)
		assertNotNull(GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo("correo@ehu.eus"));
	}

	@Test
	public void buscarUsuarioCorreoNoExiste() {
		//Crear un usuario de prueba
		Usuario u = new Usuario("usuario", "correo@ehu.eus", "contrasena");
		//Añadir el usuario a la lista
		GestorUsuarios.getGestorUsuarios().anadir(u);
		//Comprobar que no se ha encontrado el Usuario (el método ha devuelto NULL)
		assertNull(GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo("incorrecto"));
	}

	@Test
	public void buscarUsuarioCorreoListaVacia() {
		//Comprobar que no se ha encontrado el Usuario (el método ha devuelto NULL)
		assertNull(GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo("incorrecto"));
	}

	//Método esContrasena(Usuario U, String contrasena)

	@Test
	public void GUesContrasenaCorrecta() {
		//Crear un usuario de prueba
		Usuario u = new Usuario("usuario", "correo@ehu.eus", "contrasena");
		//Añadir el usuario a la lista
		GestorUsuarios.getGestorUsuarios().anadir(u);
		//Comprobar que la misma contraseña devuelve True
		assertTrue(GestorUsuarios.getGestorUsuarios().esContrasena(u, "contrasena"));
	}

	@Test
	public void GUesContrasenaIncorrecta() {
		//Crear un usuario de prueba
		Usuario u = new Usuario("usuario", "correo@ehu.eus", "contrasena");
		//Añadir el usuario a la lista
		GestorUsuarios.getGestorUsuarios().anadir(u);
		//Comprobar que una contraseña incorrecta devuelve False
		assertFalse(GestorUsuarios.getGestorUsuarios().esContrasena(u,"incorrecto"));
	}

	//Método cambiarContrasena(Usuario U, String contrasena)

	@Test
	public void cambiarContrasena() {
		//Crear un usuario de prueba
		Usuario u = new Usuario("usuario", "correo@ehu.eus", "incorrecto");
		//Añadir el usuario a la lista
		GestorUsuarios.getGestorUsuarios().anadir(u);
		//Cambiar su contraseña
		GestorUsuarios.getGestorUsuarios().cambiarContrasena(u, "contrasena");
		//Comprobar que la contraseña es la definida con el cambio
		assertTrue(u.esContrasena("contrasena"));
	}

	//Método registrarUsuario(String nombreUsuario, String correo, String contrasena1)

	@Test
	public void registrarUsuario() {
		//Registrar un usuario
		GestorUsuarios.getGestorUsuarios().registrarUsuario("usuario", "correo@ehu.eus", "contrasena");
		//Comprobar que esté en la lista
		assertNotNull(GestorUsuarios.getGestorUsuarios().buscarUsuario("usuario"));
	}

	//Método borrarUsuarios()

	@Test
	public void borrarUsuarios() {
		//Crear un usuario de prueba
		Usuario u = new Usuario("usuario", "correo@ehu.eus", "incorrecto");
		//Añadir el usuario a la lista
		GestorUsuarios.getGestorUsuarios().anadir(u);
		//Borrar los usuarios de la lista
		GestorUsuarios.getGestorUsuarios().borrarUsuarios();
		//Comprobar que no esté en la lista
		assertNull(GestorUsuarios.getGestorUsuarios().buscarUsuario("usuario"));
	}

	//Clase ArkanoidFrontera

	//Método comprobarInicio(String nombreUsuario, String contrasena)
	// Todas las posibilidades de ejecución se consideran en el plan de pruebas (F2P1 - F2P4)

	//Método recuperarContrasena(String correo)
	// Todas las demás posibilidades de ejecución se consideran en el plan de pruebas (F2P8 - F2P10)

	@Test
	public void recuperarContrasenaUsuarioNoRegistrado() {
		//Intentar recuperar contraseña de un usuario no registrado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().recuperarContrasena("correo@ehu.eus");
		//Comprobar que el mensaje es el que corresponde
		assertEquals("El usuario no está registrado.", resultado.get("mensaje"));
	}

	//Método emailValido(String correo)
	// Considerado para plan de pruebas donde se comprueba el formato del correo (F2P14)

	//Método enviarEmail(String correo)

	@Test
	public void emailValidoCorrecto() {
		//Intentar enviar email a un correo válido Y comprobar que se devuelve un String
		assertTrue(ArkanoidFrontera.getArkanoidFrontera().enviarEmail("correo@ehu.eus") instanceof String);
	}

	//Las excepciones AddressException y MessagingException nunca se van a dar porque los parámetros clave como
	// los que tienen que ver con las propiedades (Ej. "mail.smtp.host") y los de transporte se han definido en el
	// código y no dependen de lo que introduzca el usuario

	//Método comprobarCodigo(String correo, String codigo, String codigoIntroducido, String cNueva1, String cNueva2)
	// Todas las demás posibilidades de ejecución se consideran en el plan de pruebas (F2P10.3 - F2P10.8)

	@Test
	public void comprobarCodigoUsuarioNoRegistrado() {
		//Intentar recuperar contraseña de un usuario no registrado
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo("correo@ehu.eus", "000000", "000000", "contrasena", "contrasena");
		//Comprobar que el mensaje es el que corresponde
		assertEquals("El usuario no está registrado.", resultado.get("mensaje"));
	}

	//Método comprobarRegistro(String nombreUsuario, String correo, String contrasena1, String contrasena2)
	// Todas las demás posibilidades de ejecución se consideran en el plan de pruebas (F2P12 - F2P19)

	@Test
	public void comprobarRegistroCorreoNoDisponible() {
		//Crear un usuario de prueba
		Usuario u = new Usuario("usuario", "correo@ehu.eus", "contrasena");
		//Añadir el usuario a la lista
		GestorUsuarios.getGestorUsuarios().anadir(u);
		//Intentar registrarse con un correo que no está disponible
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("otroUsuario", "correo@ehu.eus", "contrasena", "contrasena");
		//Comprobar que el mensaje es el que corresponde
		assertEquals("Correo no disponible.", resultado.get("mensaje"));
	}

	//Método comprobarCambio(String nombreUsuario, String cAnterior, String cNueva1, String cNueva2)
	// Todas las posibilidades de ejecución se consideran en el plan de pruebas (F2P21 - F2P26)


	//Método contrasenaValida(String contrasena)
	// Posibilidades de ejecución consideradas en el plan de pruebas (F2P18 (incorrecto) - F2P19 (correcto))

	//Método borrarUsuarios()

	@Test
	public void borrarUsuariosAF() {
		//Crear un usuario de prueba
		Usuario u = new Usuario("usuario", "correo@ehu.eus", "incorrecto");
		//Añadir el usuario a la lista
		GestorUsuarios.getGestorUsuarios().anadir(u);
		//Borrar los usuarios de la lista
		ArkanoidFrontera.getArkanoidFrontera().borrarUsuarios();
		//Comprobar que no esté en la lista
		assertNull(GestorUsuarios.getGestorUsuarios().buscarUsuario("usuario"));
	}
}
