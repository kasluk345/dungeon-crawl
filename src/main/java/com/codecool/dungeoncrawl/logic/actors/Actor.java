package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.battle.Battle;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health;
    private int attack;
    private int armor;
    private int defence;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {

        Cell nextCell = cell.getNeighbor(dx, dy);
        CellType nextCellType = nextCell.getType();
        Battle battle = new Battle();

        if (nextCellType.equals(CellType.FLOOR)
            || nextCellType.equals(CellType.SWORD)
            || nextCellType.equals(CellType.SHIELD)
            || nextCellType.equals(CellType.HEALTHPOTION)
            || nextCellType.equals(CellType.ARMOR)){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;

        }
        if (nextCellType.equals(CellType.NPC)) {
            battle.fight(this, nextCell.getActor());
        }
    }

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

    public boolean checkAround(int[] nextPosition) {
        int[] playerPosition = Player.getCurrentPlayerPosition();
        if(playerPosition==nextPosition) System.out.println("WALKA!!!");
        return true;
    }
}

