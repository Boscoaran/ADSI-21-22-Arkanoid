package eus.ehu.adsi.arkanoid.view.game.core;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Arkanoid;

public class ArkanoidThread extends Thread {
    
    @Override
    public void run(){
        new Arkanoid(ArkanoidFrontera.getArkanoidFrontera().getLvl()).run();
    }
    public static void main(String[] args) {
        ArkanoidThread thread = new ArkanoidThread();
        thread.start();
        
    }
}
