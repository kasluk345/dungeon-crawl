package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Shield extends Item{

    public Shield(Cell cell) {

        super(cell);
        cell.setType(CellType.ITEM);
    }

    @Override
    public String getTileName() {
        return "shield";
    }
}
