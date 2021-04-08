package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.battle.Battle;

import static com.codecool.dungeoncrawl.logic.actors.Player.currentPlayerPosition;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health;
    private int attack;
    private int armor;
    private int defence;
    private boolean playerIsDead = false;


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {

        Cell nextCell = cell.getNeighbor(dx, dy);
        CellType nextCellType = nextCell.getType();
        Battle battle = new Battle();

        if ((nextCellType.equals(CellType.FLOOR) || nextCellType.equals(CellType.DOOR)) && nextCell.getActor() == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        //if player is around enemy, && - to prevent fight between enemies
        if (nextCellType.equals(CellType.NPC) && this.getClass().equals(Player.class)) {
            battle.fight(this, nextCell.getActor());
        }
    }

//    public void checkBattle(int dx, int dy) {
//        Cell nextCell = cell.getNeighbor(dx, dy);
//        if (nextCell.getActor() != null) {
//            if (nextCell.getActor().getClass().getSimpleName().equals("Skeleton")) {
//                Battle battle = new Battle();
//                battle.fight(this, nextCell.getActor());
//            }







    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public boolean checkPosition(Actor actor, int[] nextPosition) {
        int GAME_SIZE_X = 25;
        int GAME_SIZE_Y = 20;

        //System.out.println("NEXT:"+nextPosition[0]+nextPosition[1]);
        if(this.getX()+nextPosition[0] >=0 && this.getX()+nextPosition[0] < GAME_SIZE_X) {
            if(this.getY()+nextPosition[1] >=0 && this.getY()+nextPosition[1] < GAME_SIZE_Y) {
                //System.out.println("CURRENT: "+this.getX()+","+this.getY());
                return true;
            }
        }
        return false;
    }

    public boolean compareCoords(int[] cord1,int[] cord2) {
        //convert to String to compare two coordinates
        String c1 = Integer.toString(cord1[0]) +","+ Integer.toString(cord1[1]);
        String c2 = Integer.toString(cord2[0]) +","+ Integer.toString(cord2[1]);
        return c1.equals(c2);
    }

    public Actor checkIsPlayerAround(Actor actor) {
        //check if "actor" is close to player (standing on cell around)
        for(int i=-1;i<2;i++) {
            for(int j=-1;j<2;j++) {
                if(actor.getCell().getNeighbor(i,j).getActor() instanceof Player) {
                    //System.out.print("TU jest PLAYER : ");
                    //System.out.println(actor.getCell().getNeighbor(i,j).getActor().getX()+","+actor.getCell().getNeighbor(i,j).getActor().getY());
                    return actor.getCell().getNeighbor(i,j).getActor();
                }
            }
        }
        return null;
    }

    public boolean isPlayerIsDead() {
        return playerIsDead;
    }

    public void setPlayerIsDead(boolean playerIsDead) {
        this.playerIsDead = playerIsDead;
    }

    public int[] moveToPlayer(int[] playerPosition, int[] myPosition) {
        int distanceX = currentPlayerPosition[0] - myPosition[0];
        int distanceY = currentPlayerPosition[1] - myPosition[1];

/*        if(distanceX < 0) {return new int[]{myPosition[0]-1,myPosition[1]};}
        if(distanceX > 0) {return new int[]{myPosition[0]+1,myPosition[1]};}
        if(distanceY < 0) {return new int[]{myPosition[0],myPosition[1]-1};}
        if(distanceY > 0) {return new int[]{myPosition[0],myPosition[1]+1};}*/

        if(distanceX < 0) {return new int[]{-1,0};}
        if(distanceX > 0) {return new int[]{+1,0};}
        if(distanceY < 0) {return new int[]{0,-1};}
        if(distanceY > 0) {return new int[]{0,+1};}

        return myPosition;
    }

    public void move() {}
}

