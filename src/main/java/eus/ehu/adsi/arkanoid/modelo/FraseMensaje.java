package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class FraseMensaje {

	private String texto;

	public FraseMensaje(String pTexto) {
		texto=pTexto;
	}

	public String getFrase() {
		return texto;
	}

	public void asignarValoresAParametros(int numFrase, JSONObject pParametros) {
		if (numFrase==1) {
			String usuario=String.valueOf(pParametros.opt("usuario"));
			if (String.valueOf(pParametros.opt("resultado")).equals("V")) {
				String resultado="vencido";
				texto=usuario + " ha " + resultado + " el nivel ";
			}
			else if (String.valueOf(pParametros.opt("resultado")).equals("D")) {
				String resultado="perdido";
				texto=usuario + " ha " + resultado + " el nivel ";
			}
			int nivel=(Integer)pParametros.opt("nivel");
			if (nivel==1){
				String stringNivel="facil";
				texto=texto + stringNivel + " con una puntuacion de ";
			}
			else if (nivel==2){
				String stringNivel="medio";
				texto=texto + stringNivel + " con una puntuacion de ";
			}
			else if (nivel==3){
				String stringNivel="dificil";
				texto=texto + stringNivel + " con una puntuacion de ";
			}
			String stringPuntuacion=String.valueOf(pParametros.opt("puntuacion"));
			texto=texto + stringPuntuacion + ". ";
		}
		else if (numFrase==2) {
			String usuario=String.valueOf(pParametros.opt("usuario"));
			String maxPuntuacion=String.valueOf(pParametros.opt("maxPunt"));
			texto="La maxima puntuacion de " + usuario + " es " + maxPuntuacion + ". ";
		}
		else if (numFrase==3) {
			if (pParametros.has("lista")){
				JSONArray jArray = (JSONArray) pParametros.opt("lista");
				ArrayList<JSONObject> lista = jAtoAL(jArray);
				Iterator<JSONObject> itr = lista.iterator();
				JSONObject premioActual=null;
				while (itr.hasNext()){
					premioActual = itr.next();
					String nombrePremio = String.valueOf(premioActual.opt("nombre"));
					String descripcionPremio = String.valueOf(premioActual.opt("descripcion"));
					texto= texto + "Se ha conseguido el premio " + nombrePremio + " por " + descripcionPremio + ". ";
				}
			}
			else{
				texto="No se ha obtenido ningun premio. ";
			}
		}
	}

	private ArrayList<JSONObject> jAtoAL(JSONArray jA) {
		ArrayList<JSONObject> AL = new ArrayList<JSONObject>();
		for (int i = 0; i < jA.length(); i++) {
			AL.add(jA.getJSONObject(i));
		}
		return AL;
	}

}
