package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Gauntlet extends Item{
    private int attack = 10;
    public Gauntlet(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "gauntlet";
    }
}
