package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.actors.Enemy;
import com.codecool.dungeoncrawl.logic.actors.Ghost;

import java.util.ArrayList;

public class AutoMove implements Runnable{

    private volatile boolean ghostOnMove;
    private volatile boolean enemyOnMove;
    private ArrayList<Ghost> ghosts;
    private Main main;
    private Enemy advancedEnemy;

    public AutoMove(Main main, ArrayList<Ghost> ghosts) {
        this.ghosts = ghosts;
        this.main = main;
        this.ghostOnMove = true;
    }

    public AutoMove(Main main, Enemy advancedEnemy) {
        System.out.println("ELOO");
        this.advancedEnemy = advancedEnemy;
        this.main = main;
        this.enemyOnMove = true;
    }

    @Override
    public void run() {
        while (ghostOnMove || enemyOnMove) {
            System.out.println(this.getClass().getSimpleName());
            //if(ghosts.size()>0 && getClass() instanceof Ghost) {
                checkGhostHealth();
                ghostMove();
                //advEnemyMove();
            //}
        }
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
                    this.ghostOnMove = false;
                    System.out.println("All ghosts has been eliminated!");
                }
            break;
            }
        }
    }

    private void advEnemyMove(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        advancedEnemy.advancedMove();
        main.refresh();
    }

}


