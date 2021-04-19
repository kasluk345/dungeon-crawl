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

}
