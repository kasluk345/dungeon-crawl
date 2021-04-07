package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Random;

import static com.codecool.dungeoncrawl.logic.actors.Player.currentPlayerPosition;

public class Enemy extends Actor {
    private final static int START_HEALTH = 40;
    private final static int START_ATTACK = 10;
    private final boolean advancedMovement;

    public Enemy(Cell cell) {
        super(cell);
        this.setHealth(START_HEALTH);
        this.setAttack(START_ATTACK);
        this.advancedMovement = false;
    }

    public Enemy(Cell cell, boolean advMove) {
        super(cell);
        this.setHealth(START_HEALTH);
        this.setAttack(START_ATTACK);
        this.advancedMovement = true;
    }

    @Override
    public String getTileName() {
        return "enemy";
    }

    public void advancedMove() {

        int[] playerPosition = currentPlayerPosition;
        int[] myPosition = {this.getX(), this.getY()};
        System.out.println("PLAYER position: " + playerPosition[0] + "," + playerPosition[1]);
        System.out.println("MY position: " + this.getX() + "," + this.getY());

        int[] newPosition = super.moveToPlayer(currentPlayerPosition, myPosition);

        System.out.println("GO TO : " + newPosition[0] + "," + newPosition[1]);

        if (super.checkPosition(this, newPosition)) {
            super.move(newPosition[0], newPosition[1]);
        }
    }

    public boolean getEnemyType() {
        return this.advancedMovement;
    }

}
