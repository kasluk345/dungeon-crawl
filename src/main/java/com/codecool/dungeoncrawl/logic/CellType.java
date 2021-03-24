package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    WALL("wall"),
    FLOOR("floor"),
    NPC("npc");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
