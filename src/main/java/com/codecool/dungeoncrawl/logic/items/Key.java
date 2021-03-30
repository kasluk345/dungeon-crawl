package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Item{
    private static int count = 0;
    private int id = 0;


    public Key(Cell cell) {
        super(cell);
        this.id = count++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
