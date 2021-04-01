package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class DefensePotion extends Item{
    public DefensePotion(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "defense potion";
    }
}
