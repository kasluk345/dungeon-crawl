package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Key;

public class Player extends Actor {
    private Inventory inventory = new Inventory();
    private final static int START_HEALTH = 100;
    private final static int START_ARMOR = 0;
    private final static int START_ATTACK = 20;
    private final static int START_DEFENSE = 0;

    public Player(Cell cell) {
        super(cell);
        this.setHealth(START_HEALTH);
        this.setArmor(START_ARMOR);
        this.setAttack(START_ATTACK);
        this.setDefence(START_DEFENSE);
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        Cell nextCell = cell.getNeighbor(dx, dy);

        if (cell.getType().equals(CellType.SWORD)
                || cell.getType().equals(CellType.SHIELD)
                || cell.getType().equals(CellType.KEY)
                || cell.getType().equals(CellType.ARMOR)) {
            inventory.addItem(cell.getItem());
            cell.setType(CellType.FLOOR);
            cell.setItem(null);
            inventory.displayInventory();
            this.setAttack(calculateAttack());
            this.setDefence(calculateDefense());
            this.setArmor(calculateArmor());
        }
        else if (cell.getType().equals(CellType.HEALTHPOTION)) {
            cell.setType(CellType.FLOOR);
            cell.setItem(null);
            this.heal();
        }
        else if (nextCell.getType().equals(CellType.LOCKEDDOOR)) {
            checkDoor(dx, dy);
        }
    }

    public int calculateAttack() {
        return START_ATTACK + inventory.getSwordsCount() * 20;
    }

    public int calculateArmor() {
        return START_ARMOR + inventory.getArmorCount() * 50;
    }

    public void heal(){
        if (this.getHealth() < 50) {
            this.setHealth(this.getHealth() + 50);
        } else this.setHealth(100);
    }

    public int calculateDefense() {
        return START_DEFENSE + inventory.getShieldsCount() * 20;

    }

    public void checkDoor(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        for (Key key : inventory.getKeysIds()) {
            if (key.getId() == nextCell.getDoor().getId()) {
                nextCell.setType(CellType.DOOR);
            }
        }
    }

    public String getTileName() {
        return "player";
    }
}
