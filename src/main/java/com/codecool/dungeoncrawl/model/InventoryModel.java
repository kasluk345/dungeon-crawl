package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Inventory;

public class InventoryModel extends BaseModel {
    private String inventory;
    private int playerId;


    public InventoryModel(PlayerModel playerModel, Inventory inventory){
        this.inventory = inventory.toString();
        this.playerId = playerModel.getId();
    }

    public InventoryModel(int playerID, String inventory){
        this.inventory = inventory;
        this.playerId = playerID;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
