package eus.ehu.adsi.arkanoid;

import org.json.JSONObject;
import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;

import eus.ehu.adsi.arkanoid.view.AvisoVentanaExterna24b;
import eus.ehu.adsi.arkanoid.view.PublicarResultados24a;

public class PublicacionResultadosTest{

    //Pruebas realizadas por Jon Ander Lopez de Ahumada
    //Correspondientes a la funcionalidad 7 (Publicación de resultados)

    JSONObject datosMensaje = new JSONObject();

    @After
    public void tearDown(){
        datosMensaje = new JSONObject();
    }

    //Pruebas del plan de pruebas de Galder García
    //Subcaso de uso Publicar Resultados

    //Nota: es posible que algunas pruebas estén bien realizadas pero fallen debido al valor de la máxima puntuación histórica, ya que este parámetro se obtiene de la base de datos y
    //es posible que en el momento de la ejecución de las pruebas la máxima puntuación histórica real de la base de datos no sea la que se ha considerado en las pruebas
    //Las pruebas se han realizado pensando que en la base de datos hay un usuario llamado "Bosco" cuya máxima puntuación histórica es 100.

    //Nota: cuando en el plan de pruebas se habla de "n" premios, aquí se elegirá un número cualquiera distinto de 1

    //Pruebas sobre la generación del mensaje:

    @Test
    public void F7P1(){
        //Descripción: Partida de nivel fácil, derrota, no es máxima puntuación, no hay premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "fácil", puntuación, máxima puntuación histórica
        //No contiene premios ni descripciones de premios
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",1);
        datosMensaje.put("puntuacion",15);
        //La maxima puntuacion de Bosco es 100

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel facil con una puntuacion de 15. La maxima puntuacion de Bosco es 100. No se ha obtenido ningun premio. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P2(){
        //Descripción: Partida de nivel fácil, derrota, no es máxima puntuación, hay un premio
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "fácil", puntuación, máxima puntuación histórica, premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",1);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel facil con una puntuacion de 15. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P3(){
        //Descripción: Partida de nivel fácil, derrota, no es máxima puntuación, hay dos premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "fácil", puntuación, máxima puntuación histórica, 2 premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",1);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        JSONObject premio2=new JSONObject();
        premio2.put("nombre","premio2");
        premio2.put("descripcion","romper 30 ladrillos");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        listaPremios.add(premio2);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel facil con una puntuacion de 15. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. Se ha conseguido el premio premio2 por romper 30 ladrillos. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P4(){
        //Descripción: Partida de nivel fácil, victoria, no es máxima puntuación, no hay premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "fácil", puntuación, máxima puntuación histórica, no hay premios
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",1);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel facil con una puntuacion de 15. La maxima puntuacion de Bosco es 100. No se ha obtenido ningun premio. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P5(){
        //Descripción: Partida de nivel fácil, victoria, no es máxima puntuación, un premio
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "fácil", puntuación, máxima puntuación histórica, premio-descripcion
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",1);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        
        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        
        datosMensaje.put("lista",listaPremios);
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel facil con una puntuacion de 15. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P6(){
        //Descripción: Partida de nivel fácil, victoria, no es máxima puntuación, hay dos premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "fácil", puntuación, máxima puntuación histórica, 2 premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",1);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        JSONObject premio2=new JSONObject();
        premio2.put("nombre","premio2");
        premio2.put("descripcion","romper 30 ladrillos");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        listaPremios.add(premio2);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel facil con una puntuacion de 15. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. Se ha conseguido el premio premio2 por romper 30 ladrillos. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P7(){
        //Descripción: Partida de nivel fácil, derrota, es máxima puntuación, no hay premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "fácil", puntuación, máxima puntuación histórica, no hay premios
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",1);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel facil con una puntuacion de 100. La maxima puntuacion de Bosco es 100. No se ha obtenido ningun premio. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P8(){
        //Descripción: Partida de nivel fácil, derrota, es máxima puntuación, un premio
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "fácil", puntuación, máxima puntuación histórica, premio-descripcion
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",1);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        
        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        
        datosMensaje.put("lista",listaPremios);
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel facil con una puntuacion de 100. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P9(){
        //Descripción: Partida de nivel fácil, derrota, es máxima puntuación, hay dos premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "fácil", puntuación, máxima puntuación histórica, 2 premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",1);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        JSONObject premio2=new JSONObject();
        premio2.put("nombre","premio2");
        premio2.put("descripcion","romper 30 ladrillos");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        listaPremios.add(premio2);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel facil con una puntuacion de 100. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. Se ha conseguido el premio premio2 por romper 30 ladrillos. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P10(){
        //Descripción: Partida de nivel fácil, victoria, es máxima puntuación, no hay premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "fácil", puntuación, máxima puntuación histórica, no hay premios
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",1);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel facil con una puntuacion de 100. La maxima puntuacion de Bosco es 100. No se ha obtenido ningun premio. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P11(){
        //Descripción: Partida de nivel fácil, victoria, es máxima puntuación, un premio
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "fácil", puntuación, máxima puntuación histórica, premio-descripcion
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",1);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        
        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        
        datosMensaje.put("lista",listaPremios);
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel facil con una puntuacion de 100. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P12(){
        //Descripción: Partida de nivel fácil, victoria, es máxima puntuación, hay dos premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "fácil", puntuación, máxima puntuación histórica, 2 premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",1);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        JSONObject premio2=new JSONObject();
        premio2.put("nombre","premio2");
        premio2.put("descripcion","romper 30 ladrillos");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        listaPremios.add(premio2);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel facil con una puntuacion de 100. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. Se ha conseguido el premio premio2 por romper 30 ladrillos. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P13(){
        //Descripción: Partida de nivel medio, derrota, no es máxima puntuación, no hay premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "medio", puntuación, máxima puntuación histórica
        //No contiene premios ni descripciones de premios
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",2);
        datosMensaje.put("puntuacion",15);
        //La maxima puntuacion de Bosco es 100

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel medio con una puntuacion de 15. La maxima puntuacion de Bosco es 100. No se ha obtenido ningun premio. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P14(){
        //Descripción: Partida de nivel medio, derrota, no es máxima puntuación, hay un premio
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "medio", puntuación, máxima puntuación histórica, premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",2);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel medio con una puntuacion de 15. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P15(){
        //Descripción: Partida de nivel medio, derrota, no es máxima puntuación, hay dos premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "medio", puntuación, máxima puntuación histórica, 2 premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",2);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        JSONObject premio2=new JSONObject();
        premio2.put("nombre","premio2");
        premio2.put("descripcion","romper 30 ladrillos");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        listaPremios.add(premio2);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel medio con una puntuacion de 15. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. Se ha conseguido el premio premio2 por romper 30 ladrillos. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P16(){
        //Descripción: Partida de nivel medio, victoria, no es máxima puntuación, no hay premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "medio", puntuación, máxima puntuación histórica, no hay premios
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",2);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel medio con una puntuacion de 15. La maxima puntuacion de Bosco es 100. No se ha obtenido ningun premio. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P17(){
        //Descripción: Partida de nivel medio, victoria, no es máxima puntuación, un premio
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "medio", puntuación, máxima puntuación histórica, premio-descripcion
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",2);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        
        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        
        datosMensaje.put("lista",listaPremios);
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel medio con una puntuacion de 15. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P18(){
        //Descripción: Partida de nivel medio, victoria, no es máxima puntuación, hay dos premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "medio", puntuación, máxima puntuación histórica, 2 premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",2);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        JSONObject premio2=new JSONObject();
        premio2.put("nombre","premio2");
        premio2.put("descripcion","romper 30 ladrillos");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        listaPremios.add(premio2);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel medio con una puntuacion de 15. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. Se ha conseguido el premio premio2 por romper 30 ladrillos. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P19(){
        //Descripción: Partida de nivel medio, derrota, es máxima puntuación, no hay premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "medio", puntuación, máxima puntuación histórica, no hay premios
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",2);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel medio con una puntuacion de 100. La maxima puntuacion de Bosco es 100. No se ha obtenido ningun premio. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P20(){
        //Descripción: Partida de nivel medio, derrota, es máxima puntuación, un premio
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "medio", puntuación, máxima puntuación histórica, premio-descripcion
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",2);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        
        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        
        datosMensaje.put("lista",listaPremios);
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel medio con una puntuacion de 100. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P21(){
        //Descripción: Partida de nivel medio, derrota, es máxima puntuación, hay dos premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "medio", puntuación, máxima puntuación histórica, 2 premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",2);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        JSONObject premio2=new JSONObject();
        premio2.put("nombre","premio2");
        premio2.put("descripcion","romper 30 ladrillos");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        listaPremios.add(premio2);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel medio con una puntuacion de 100. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. Se ha conseguido el premio premio2 por romper 30 ladrillos. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P22(){
        //Descripción: Partida de nivel medio, victoria, es máxima puntuación, no hay premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "medio", puntuación, máxima puntuación histórica, no hay premios
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",2);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel medio con una puntuacion de 100. La maxima puntuacion de Bosco es 100. No se ha obtenido ningun premio. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P23(){
        //Descripción: Partida de nivel medio, victoria, es máxima puntuación, un premio
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "medio", puntuación, máxima puntuación histórica, premio-descripcion
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",2);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        
        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        
        datosMensaje.put("lista",listaPremios);
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel medio con una puntuacion de 100. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P24(){
        //Descripción: Partida de nivel medio, victoria, es máxima puntuación, hay dos premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "medio", puntuación, máxima puntuación histórica, 2 premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",2);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        JSONObject premio2=new JSONObject();
        premio2.put("nombre","premio2");
        premio2.put("descripcion","romper 30 ladrillos");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        listaPremios.add(premio2);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel medio con una puntuacion de 100. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. Se ha conseguido el premio premio2 por romper 30 ladrillos. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P25(){
        //Descripción: Partida de nivel dificil, derrota, no es máxima puntuación, no hay premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "dificil", puntuación, máxima puntuación histórica
        //No contiene premios ni descripciones de premios
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",15);
        //La maxima puntuacion de Bosco es 100

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel dificil con una puntuacion de 15. La maxima puntuacion de Bosco es 100. No se ha obtenido ningun premio. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P26(){
        //Descripción: Partida de nivel dificil, derrota, no es máxima puntuación, hay un premio
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "dificil", puntuación, máxima puntuación histórica, premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel dificil con una puntuacion de 15. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P27(){
        //Descripción: Partida de nivel dificil, derrota, no es máxima puntuación, hay dos premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "dificil", puntuación, máxima puntuación histórica, 2 premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        JSONObject premio2=new JSONObject();
        premio2.put("nombre","premio2");
        premio2.put("descripcion","romper 30 ladrillos");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        listaPremios.add(premio2);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel dificil con una puntuacion de 15. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. Se ha conseguido el premio premio2 por romper 30 ladrillos. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P28(){
        //Descripción: Partida de nivel dificil, victoria, no es máxima puntuación, no hay premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "dificil", puntuación, máxima puntuación histórica, no hay premios
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel dificil con una puntuacion de 15. La maxima puntuacion de Bosco es 100. No se ha obtenido ningun premio. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P29(){
        //Descripción: Partida de nivel dificil, victoria, no es máxima puntuación, un premio
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "dificil", puntuación, máxima puntuación histórica, premio-descripcion
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        
        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        
        datosMensaje.put("lista",listaPremios);
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel dificil con una puntuacion de 15. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P30(){
        //Descripción: Partida de nivel dificil, victoria, no es máxima puntuación, hay dos premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "dificil", puntuación, máxima puntuación histórica, 2 premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",15);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        JSONObject premio2=new JSONObject();
        premio2.put("nombre","premio2");
        premio2.put("descripcion","romper 30 ladrillos");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        listaPremios.add(premio2);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel dificil con una puntuacion de 15. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. Se ha conseguido el premio premio2 por romper 30 ladrillos. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P31(){
        //Descripción: Partida de nivel dificil, derrota, es máxima puntuación, no hay premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "dificil", puntuación, máxima puntuación histórica, no hay premios
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel dificil con una puntuacion de 100. La maxima puntuacion de Bosco es 100. No se ha obtenido ningun premio. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P32(){
        //Descripción: Partida de nivel dificil, derrota, es máxima puntuación, un premio
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "dificil", puntuación, máxima puntuación histórica, premio-descripcion
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        
        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        
        datosMensaje.put("lista",listaPremios);
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel dificil con una puntuacion de 100. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P33(){
        //Descripción: Partida de nivel dificil, derrota, es máxima puntuación, hay dos premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha perdido", "dificil", puntuación, máxima puntuación histórica, 2 premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","D");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        JSONObject premio2=new JSONObject();
        premio2.put("nombre","premio2");
        premio2.put("descripcion","romper 30 ladrillos");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        listaPremios.add(premio2);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha perdido el nivel dificil con una puntuacion de 100. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. Se ha conseguido el premio premio2 por romper 30 ladrillos. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P34(){
        //Descripción: Partida de nivel dificil, victoria, es máxima puntuación, no hay premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "dificil", puntuación, máxima puntuación histórica, no hay premios
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel dificil con una puntuacion de 100. La maxima puntuacion de Bosco es 100. No se ha obtenido ningun premio. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P35(){
        //Descripción: Partida de nivel dificil, victoria, es máxima puntuación, un premio
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "dificil", puntuación, máxima puntuación histórica, premio-descripcion
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        
        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        
        datosMensaje.put("lista",listaPremios);
        
        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel dificil con una puntuacion de 100. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    @Test
    public void F7P36(){
        //Descripción: Partida de nivel dificil, victoria, es máxima puntuación, hay dos premios
        //Resultado esperado: El mensaje contiene en orden correcto:
        //nombre de usuario, "ha vencido", "dificil", puntuación, máxima puntuación histórica, 2 premio-descripción
        
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",100);
         //La maxima puntuacion de Bosco es 100

        JSONObject premio1=new JSONObject();
        premio1.put("nombre","premio1");
        premio1.put("descripcion","superar un nivel");
        JSONObject premio2=new JSONObject();
        premio2.put("nombre","premio2");
        premio2.put("descripcion","romper 30 ladrillos");

        ArrayList<JSONObject> listaPremios=new ArrayList<JSONObject>();
        listaPremios.add(premio1);
        listaPremios.add(premio2);
        datosMensaje.put("lista",listaPremios);

        PublicarResultados24a interfaz = new PublicarResultados24a(datosMensaje);

        String mensajeEsperado="Bosco ha vencido el nivel dificil con una puntuacion de 100. La maxima puntuacion de Bosco es 100. Se ha conseguido el premio premio1 por superar un nivel. Se ha conseguido el premio premio2 por romper 30 ladrillos. ";
        
        //Comprobamos que el resultado es correcto:
        assertEquals(mensajeEsperado,interfaz.getMensaje());
    }

    //Pruebas sobre compartir en redes sociales:

    @Test
    public void F7P37(){
        //Descripción: se selecciona el icono de Instagram, aparece el aviso de que se abrirá una web externa y se pulsa "Aceptar"
        //Resultado esperado: se cierra el aviso y se abre la página web de Instagram. El sistema sigue en la pantalla de finalización de partida (PublicarResultados24a)
    
        //El aviso de web externa se crea con el link de Instagram:

        try {
            URI uri = new URI("https://www.instagram.com/?hl=es");
            AvisoVentanaExterna24b interfaz = new AvisoVentanaExterna24b(uri);
            
            String linkEsperado="https://www.instagram.com/?hl=es";

            //Comprobamos que el link que se abre al pulsar "Aceptar" es el de Instagram:
            assertEquals(linkEsperado,interfaz.getUri().toString());
        
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        } 
        
        //Descomentando y ejecutando las siguientes líneas y pulsando el icono de Instagram se puede probar manualmente que aparece el aviso de web externa, y pulsando "Aceptar" se abre la web de Instagram:
        /*
        JSONObject datosMensaje = new JSONObject();
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",100);
        new PublicarResultados24a(datosMensaje);
        */
    }

    @Test
    public void F7P38(){
        //Descripción: se selecciona el icono de Twitter, aparece el aviso de que se abrirá una web externa y se pulsa "Aceptar"
        //Resultado esperado: se cierra el aviso y se abre la página web de Twitter. El sistema sigue en la pantalla de finalización de partida (PublicarResultados24a)
    
        //El aviso de web externa se crea con el link de Twitter:

        try {
            URI uri = new URI("https://twitter.com/?lang=es");
            AvisoVentanaExterna24b interfaz = new AvisoVentanaExterna24b(uri);
            
            String linkEsperado="https://twitter.com/?lang=es";

            //Comprobamos que el link que se abre al pulsar "Aceptar" es el de Twitter:
            assertEquals(linkEsperado,interfaz.getUri().toString());
        
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }   				

        //Descomentando y ejecutando las siguientes líneas y pulsando el icono de Twitter se puede probar manualmente que aparece el aviso de web externa, y pulsando "Aceptar" se abre la web de Twitter:
        /*
        JSONObject datosMensaje = new JSONObject();
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",100);
        new PublicarResultados24a(datosMensaje);
        */
    }

    @Test
    public void F7P39(){
        //Descripción: se selecciona el icono de Facebook, aparece el aviso de que se abrirá una web externa y se pulsa "Aceptar"
        //Resultado esperado: se cierra el aviso y se abre la página web de Facebook. El sistema sigue en la pantalla de finalización de partida (PublicarResultados24a)
    
        //El aviso de web externa se crea con el link de Facebook:

        try {
            URI uri = new URI("https://es-es.facebook.com/");
            AvisoVentanaExterna24b interfaz = new AvisoVentanaExterna24b(uri);
            
            String linkEsperado="https://es-es.facebook.com/";

            //Comprobamos que el link que se abre al pulsar "Aceptar" es el de Facebook:
            assertEquals(linkEsperado,interfaz.getUri().toString());
        
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }   				

        //Descomentando y ejecutando las siguientes líneas y pulsando el icono de Facebook se puede probar manualmente que aparece el aviso de web externa, y pulsando "Aceptar" se abre la web de Facebook:
        /*
        JSONObject datosMensaje = new JSONObject();
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",100);
        new PublicarResultados24a(datosMensaje);
        */
    }

    //Las siguientes 3 pruebas conviene ejecutarlas manualmente para comprobar su resultado

    @Test
    public void F7P40(){
        //Descripción: se selecciona el icono de Instagram, aparece el aviso de que se abrirá una web externa y se pulsa "Cancelar"
        //Resultado esperado: se cierra el aviso. El sistema sigue en la pantalla de finalización de partida (PublicarResultados24a)
    
        //El aviso de web externa se crea con el link de Instagram:

        try {
            URI uri = new URI("https://www.instagram.com/?hl=es");
            new AvisoVentanaExterna24b(uri);
            
            //Si pulsamos "Cancelar" vemos que se cierra el aviso
        
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        } 
        
        //Descomentando y ejecutando las siguientes líneas y pulsando el icono de Instagram se puede probar manualmente que aparece el aviso de web externa
        //Si pulsamos "Cancelar", vemos que se cierra el aviso y la pantalla PublicarResultados24a sigue abierta
        /*
        JSONObject datosMensaje = new JSONObject();
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",100);
        new PublicarResultados24a(datosMensaje);
        */
    }

    @Test
    public void F7P41(){
        //Descripción: se selecciona el icono de Twitter, aparece el aviso de que se abrirá una web externa y se pulsa "Cancelar"
        //Resultado esperado: se cierra el aviso. El sistema sigue en la pantalla de finalización de partida (PublicarResultados24a)
    
        //El aviso de web externa se crea con el link de Twitter:

        try {
            URI uri = new URI("https://twitter.com/?lang=es");
            new AvisoVentanaExterna24b(uri);
            
            //Si pulsamos "Cancelar" vemos que se cierra el aviso
        
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        } 
        
        //Descomentando y ejecutando las siguientes líneas y pulsando el icono de Twitter se puede probar manualmente que aparece el aviso de web externa
        //Si pulsamos "Cancelar", vemos que se cierra el aviso y la pantalla PublicarResultados24a sigue abierta
        /*
        JSONObject datosMensaje = new JSONObject();
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",100);
        new PublicarResultados24a(datosMensaje);
        */
    }

    @Test
    public void F7P42(){
        //Descripción: se selecciona el icono de Facebook, aparece el aviso de que se abrirá una web externa y se pulsa "Cancelar"
        //Resultado esperado: se cierra el aviso. El sistema sigue en la pantalla de finalización de partida (PublicarResultados24a)
    
        //El aviso de web externa se crea con el link de Facebook:

        try {
            URI uri = new URI("https://es-es.facebook.com/");
            new AvisoVentanaExterna24b(uri);
            
            //Si pulsamos "Cancelar" vemos que se cierra el aviso
        
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        } 
        
        //Descomentando y ejecutando las siguientes líneas y pulsando el icono de Facebook se puede probar manualmente que aparece el aviso de web externa
        //Si pulsamos "Cancelar", vemos que se cierra el aviso y la pantalla PublicarResultados24a sigue abierta
        /*
        JSONObject datosMensaje = new JSONObject();
        datosMensaje.put("usuario","Bosco");
        datosMensaje.put("resultado","V");
        datosMensaje.put("nivel",3);
        datosMensaje.put("puntuacion",100);
        new PublicarResultados24a(datosMensaje);
        */
    }

}