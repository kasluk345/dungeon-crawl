package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ring extends Item{
    private final static int ATTACK_BOOST = 20;



    public Ring(Cell cell) {
        super(cell);
        this.setAttackBoost(ATTACK_BOOST);
    }

    @Override
    public String getTileName() {
        return "ring";
    }
}
