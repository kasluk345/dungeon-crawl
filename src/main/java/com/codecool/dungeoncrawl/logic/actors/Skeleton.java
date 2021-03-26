package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {

        super(cell);
        cell.setType(CellType.NPC);
        this.setHealth(100);
        this.setAttack(20);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
