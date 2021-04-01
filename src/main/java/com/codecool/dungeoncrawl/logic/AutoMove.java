package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.actors.Ghost;

public class AutoMove implements Runnable{

    private volatile boolean onMove;
    private Ghost ghost;
    private Main main;

    public AutoMove(Main main, Ghost ghost) {
        this.ghost = ghost;
        this.main = main;
        this.onMove = true;
    }

    @Override
    public void run() {
        while (onMove) {
            ghostMove();
        }
    }

    public void stopMove() { onMove = false; }

    public boolean moveState() {
        return onMove;
    }

    private void ghostMove(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("GHOST position: "+ghost.getX()+","+ghost.getY());
        ghost.move();
        main.refresh();
    }
}
