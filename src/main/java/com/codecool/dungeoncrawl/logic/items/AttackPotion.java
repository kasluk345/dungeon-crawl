package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class AttackPotion extends Item{
    public AttackPotion(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "attack potion";
    }
}
