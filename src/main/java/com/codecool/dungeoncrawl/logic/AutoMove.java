package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.actors.Ghost;

import java.util.ArrayList;

public class AutoMove implements Runnable{

    private volatile boolean onMove;
    private ArrayList<Ghost> ghosts;
    private Main main;

    public AutoMove(Main main, ArrayList<Ghost> ghosts) {
        this.ghosts = ghosts;
        this.main = main;
        this.onMove = true;
    }

    @Override
    public void run() {
        while (onMove) {
            checkGhostHealth();
            ghostMove();
        }
    }

    public void stopMove() { onMove = false; }

    public boolean moveState() {
        return onMove;
    }

    private void ghostMove(){
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(Ghost ghost: ghosts) {
            ghost.move();
            main.refresh();
        }
    }

    private void checkGhostHealth() {
        for(Ghost ghost: ghosts) {
            if (ghost.getHealth() <= 0) {
                ghosts.remove(ghost);
                System.out.println("GHOST" + ghost.getGhostID() + " has been eliminated!|| LEFT: " + ghosts.size());

                if (ghosts.size() == 0) {
                    this.onMove = false;
                    System.out.println("All ghosts has been eliminated!");
                }
            break;
            }
        }
    }

}


