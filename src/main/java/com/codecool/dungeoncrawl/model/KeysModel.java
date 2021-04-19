package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Key;

import java.util.ArrayList;
import java.util.List;

public class KeysModel extends BaseModel {
    private List<Key> keysIds;
    private int inventoryId;

    public KeysModel(Inventory inventory, InventoryModel inventoryModel) {
        this.keysIds = inventory.getKeysIds();
    }
}
