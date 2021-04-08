package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class DefensePotion extends Item{
    private final static int DEFENCE_BOOST = 20;

    public DefensePotion(Cell cell) {
        super(cell);
        this.setDefenceBoost(DEFENCE_BOOST);
    }

    @Override
    public String getTileName() {
        return "defense potion";
    }
}
