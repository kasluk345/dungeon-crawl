package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Gauntlet extends Item{
    public Gauntlet(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "gauntlet";
    }
}
