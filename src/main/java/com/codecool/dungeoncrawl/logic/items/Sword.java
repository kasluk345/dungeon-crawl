package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item{
    private final static int ATTACK_BOOST = 20;


    public Sword(Cell cell) {
        super(cell);
        this.setAttackBoost(ATTACK_BOOST);
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
