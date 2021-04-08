package com.codecool.dungeoncrawl.logic.items;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    public final List<Item> inventory;

    public Inventory() {
        inventory = new ArrayList<>();
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {inventory.remove(item); }

    public void displayInventory() {
        for (Item item : inventory) {
            System.out.println(item);
        }
    }

    public String toString() {
        StringBuilder inventoryString = new StringBuilder();

        for (Item item : inventory) {
            inventoryString.append(item.getName()).append(System.lineSeparator());
        }
        return inventoryString.toString();
    }


    public ArrayList<Key> getKeysIds() {
        ArrayList<Key> keyIDs = new ArrayList<>();
        for (Item item : inventory) {
            if (item.getClass().getSimpleName().equals("Key")) {
                keyIDs.add((Key) item);
            }
        }
        return keyIDs;
    }
}
