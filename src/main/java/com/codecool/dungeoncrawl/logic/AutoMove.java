package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Enemy;
import com.codecool.dungeoncrawl.logic.actors.Ghost;

import java.util.ArrayList;
import java.util.List;

public class AutoMove implements Runnable {

    private final List<Actor> enemies;
    private final Main main;


    public AutoMove(Main main, List<Actor> enemies) {
        this.enemies = enemies;
        this.main = main;
    }


    @Override
    public void run() {
        while (enemies.size() > 0) {
            //System.out.println(Thread.currentThread().getName()+" autoMove");
            checkHealth();
            moveEnemies();
            main.refresh();
        }
    }

    private void moveEnemies() {
        try {
            Thread.sleep(750);
            for (Actor enemy : enemies) {
                enemy.move();
                // main.refresh();
            }
        } catch (InterruptedException e) {
            System.out.println("Koniec");
            Thread.currentThread().interrupt();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void checkHealth() {
        for (Actor enemy : enemies) {
            if (enemy.getHealth() <= 0) {
                enemies.remove(enemy);
                //System.out.println("GHOST" + ghost.getGhostID() + " has been eliminated!|| LEFT: " + ghosts.size());
                break;
            }
        }
    }


}


