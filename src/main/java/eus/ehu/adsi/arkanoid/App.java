package eus.ehu.adsi.arkanoid;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import eus.ehu.adsi.arkanoid.view.CrearCuenta17d;
import eus.ehu.adsi.arkanoid.view.IniciarSesion17a;
import eus.ehu.adsi.arkanoid.view.Inicio16;

public class App {

	public static void main(String[] args) {
		try {
			EventQueue.invokeAndWait(new Runnable() {
				public void run(){
					try {
						//new IniciarSesion17a();
						//new Personalizacion21("Bosco");
						new Inicio16();
					} catch (Exception e){
						System.err.println(e);
						e.printStackTrace();
					}
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
