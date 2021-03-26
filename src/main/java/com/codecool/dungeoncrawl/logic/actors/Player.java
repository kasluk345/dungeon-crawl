package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.battle.Battle;
import com.codecool.dungeoncrawl.logic.items.Inventory;

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

        if (cell.getType().equals(CellType.SWORD)
                || cell.getType().equals(CellType.SHIELD)
                || cell.getType().equals(CellType.HEALTHPOTION)
                || cell.getType().equals(CellType.ARMOR)) {
            inventory.addItem(cell.getItem());
            cell.setType(CellType.FLOOR);
            cell.setItem(null);
            inventory.displayInventory();
            this.setAttack(calculateAttack());
            this.setDefence(calculateDefense());
            this.setArmor(calculateArmor());
        }
    }

    public int calculateAttack() {
        return START_ATTACK + inventory.getSwordsCount() * 20;
    }

    public int calculateArmor() {
        return START_ARMOR + inventory.getArmorCount() * 50;
    }

    public int calculateDefense() {
        return START_DEFENSE + inventory.getShieldsCount() * 20;

    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTileName() {
        return "player";
    }
}
