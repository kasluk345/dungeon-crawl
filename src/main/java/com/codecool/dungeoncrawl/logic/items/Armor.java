package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Armor extends Item {
    private final static int ARMOR_BOOST = 50;



    public Armor(Cell cell) {
        super(cell);
        this.setDefenceBoost(ARMOR_BOOST);
    }

    @Override
    public String getTileName() {
        return "armor";
    }
}
