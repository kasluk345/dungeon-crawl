package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    WALL("wall"),
    FLOOR("floor"),
    NPC("npc"),
    LOCKEDDOOR("locked door"),
    DOOR("door"),
    GAMEOVER("game over"),
    PORTAL("portal");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
