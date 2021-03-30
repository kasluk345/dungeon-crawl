package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.MapLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {

    public final ArrayList<Item> inventory;

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
        String inventoryString = "";

        for (Item item : inventory) {
            inventoryString += item.getName() + System.lineSeparator();
        }
        return inventoryString;
    }


    public int getSwordsCount() {
        int counter = 0;
        for (Item item : inventory) {
            if (item.getClass().getSimpleName().equals("Sword")) {
                counter++;
            }
        }
        return counter;
    }

    public int getShieldsCount() {
        int counter = 0;
        for (Item item : inventory) {
            if (item.getClass().getSimpleName().equals("Shield")) {
                counter++;
            }
        }
        return counter;
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


    public int getArmorCount() {
        int counter = 0;
        for (Item item : inventory) {
            if (item.getClass().getSimpleName().equals("Armor")) {
                counter++;
            }
        }
        return counter;
    }
}
