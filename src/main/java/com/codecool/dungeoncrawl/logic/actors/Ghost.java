package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Random;

public class Ghost extends Actor {
    private final static int START_HEALTH = 40;
    private final static int START_ATTACK = 10;
    private Random random = new Random();
    private int randX;
    private int randY;

    public Ghost(Cell cell) {
        super(cell);
        this.setHealth(START_HEALTH);
        this.setAttack(START_ATTACK);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    public void move() {
        randX = random.nextInt(6)-3;
        randY = random.nextInt(6)-3;
        System.out.println("GHOST random: "+randX+","+randY);
        super.move(randX,randY);
    }

    public void getGhostPosition() {
        int x = this.getX();
        int y = this.getY();
        System.out.println("GHOST position: "+x+","+y);
    }
}
