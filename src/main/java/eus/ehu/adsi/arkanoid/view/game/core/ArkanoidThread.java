package eus.ehu.adsi.arkanoid.view.game.core;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Arkanoid;

public class ArkanoidThread extends Thread {

    @Override
    public void run(){
        new Arkanoid(ArkanoidFrontera.getArkanoidFrontera().getLvl(), ArkanoidFrontera.getArkanoidFrontera().getNombre()).run();
    }
    public static void main(String[] args) {
        ArkanoidThread thread = new ArkanoidThread();
        thread.start();
<<<<<<< HEAD:src/main/java/eus/ehu/adsi/arkanoid/view/game/core/ArkanoidThread.java
        
=======

>>>>>>> origin/feature-premios:src/main/java/eus/ehu/adsi/arkanoid/core/ArkanoidThread.java
    }
}