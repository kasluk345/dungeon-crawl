package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.awt.dnd.DragGestureListener;

public class Door implements Drawable {
    private static int count = 0;
    private final Cell cell;
    private int id = 0;


    public Door(Cell cell) {
        this.cell = cell;
        this.cell.setDoor(this);
        this.id = count++;
    }
    public int getId() {
        return id;
    }

    @Override
    public String getTileName() {
        return "door";
    }
}
