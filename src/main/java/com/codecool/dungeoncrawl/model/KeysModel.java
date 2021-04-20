package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Key;

import java.util.ArrayList;
import java.util.List;

public class KeysModel extends BaseModel {
    private String keysIds;
    private int inventoryId;

    public KeysModel(InventoryModel inventoryModel, Inventory inventory) {
        this.keysIds = inventory.getKeysIds().toString();
        this.inventoryId = inventoryModel.getId();
    }

    public KeysModel(int inventoryId, String keysIds){
        this.keysIds = keysIds;
        this.inventoryId = inventoryId;
    }


    public String getKeysIds() {
        return keysIds;
    }

    public void setKeysIds(String keysIds) {
        this.keysIds = keysIds;
    }

    public int getInventoryId() {
        return inventoryId;
    }
}
