package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.battle.Battle;

import java.util.Random;

import static com.codecool.dungeoncrawl.logic.actors.Player.currentPlayerPosition;

public class Ghost extends Actor {
    private final static int START_HEALTH = 50;
    private final static int START_ATTACK = 5;
    private Random random = new Random();
    private int randX;
    private int randY;
    private int id;
    static int counter=0;

    public Ghost(Cell cell) {
        super(cell);
        this.setHealth(START_HEALTH);
        this.setAttack(START_ATTACK);
        counter+=1;
        this.id = counter;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    public void move() {
        //go to random position in range +-3 cell
        randX = random.nextInt(6)-3; //-3,3
        randY = random.nextInt(6)-3; //-3,3
        //randX = 0; randY = 0;

        if (super.checkPosition(this, new int[] {randX,randY})) {
            super.move(randX,randY);
            }
        //if met player, start fight
        Player detectedPlayer = (Player) super.checkIsPlayerAround(this);
        if(detectedPlayer != null) {
            Battle battle = new Battle();
            battle.fight(detectedPlayer, this);
        }
    }

    public void getGhostPosition() {
        int x = this.getX();
        int y = this.getY();
        System.out.println("GHOST position: "+x+","+y);
    }

    public int getGhostID() {
        return this.id;
    }

    public void moveAdv() {
        //go to random position in range +-3 cell
        //randX = random.nextInt(6) - 3; //-3,3
        //randY = random.nextInt(6) - 3; //-3,3
        //randX = 0; randY = 0;

        int[] playerPosition = currentPlayerPosition;
        int[] myPosition = {this.getX(), this.getY()};
        System.out.println("PLAYER position: "+playerPosition[0]+","+playerPosition[1]);
        System.out.println("MY position: "+this.getX()+","+this.getY());

        int[] newPosition = super.moveToPlayer(currentPlayerPosition,myPosition);

        System.out.println("GO TO : "+newPosition[0]+","+newPosition[1]);

        if (super.checkPosition(this, newPosition)) {
            super.move(newPosition[0], newPosition[1]);
        }
    }
}
