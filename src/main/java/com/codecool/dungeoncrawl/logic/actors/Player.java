package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Inventory;

public class Player extends Actor {
    private Inventory inventory = new Inventory();

    public Player(Cell cell) {
        super(cell);
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        Cell nextCell = cell.getNeighbor(dx, dy);
        CellType nextCellType = nextCell.getType();

        if(cell.getType().equals(CellType.SWORD)
                ||cell.getType().equals(CellType.SHIELD)
                ||cell.getType().equals(CellType.HEALTHPOTION)){
            inventory.addItem(cell.getItem());
            cell.setType(CellType.FLOOR);
            inventory.displayInventory();
        }
    }

    public String getTileName() {
        return "player";
    }
}
