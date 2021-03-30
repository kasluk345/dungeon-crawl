package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.actors.Ghost;

public class AutoMove implements Runnable{

    private volatile boolean cancelled;
    private Ghost ghost;

    public AutoMove(Ghost ghost) {
        this.ghost = ghost;
    }

    @Override
    public void run() {
        while (!cancelled) {
            doStuff();
        }
    }

    public void cancel()
    {
        cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    private void doStuff(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("(AMclass)GHOST position: "+ghost.getX()+","+ghost.getY());
        ghost.move();
    }
}
