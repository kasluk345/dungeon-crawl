package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Shield extends Item{
    private final static int DEFENCE_BOOST = 20;


    public Shield(Cell cell) {
        super(cell);
        this.setDefenceBoost(DEFENCE_BOOST);
    }

    @Override
    public String getTileName() {
        return "shield";
    }
}
