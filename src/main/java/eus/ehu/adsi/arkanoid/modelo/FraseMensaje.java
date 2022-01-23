package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Iterator;

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
			texto.replaceFirst("pUsuario", pParametros.getString("usuario"));
			if (pParametros.getBoolean("victoria")) {
				texto.replaceFirst("pResultado", "vencido");
			}
			else {
				texto.replaceFirst("pResultado", "perdido");
			}
			//texto.replaceFirst("pNivel", pParametros.getInt("nivel").toString());
			texto.replaceFirst("pPuntuacion", pParametros.getString("puntuacion"));
		}
		else if (numFrase==2) {
			texto.replaceFirst("pUsuario", pParametros.getString("usuario"));
			texto.replaceFirst("pMaxPuntuacion", pParametros.getInt("maxPunt").toString());
		}
		else if (numFrase==3) {
			ArrayList<Premio> listaPremios=pParametros.get("premios");
			Iterator<Premio> itr=listaPremios.iterator();
			while (itr.hasNext()) {
				Premio premio=itr.next();
				texto= texto + "Se ha conseguido el premio pPremio por pDescripcion. ";
				texto.replaceFirst("pPremio", premio.getNombrePremio());
				texto.replaceFirst("pDescripcion", premio.getMotivoPremio());
			}
		}
	}
	
}
