package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    WALL("wall"),
    FLOOR("floor"),
    NPC("npc"),
    SWORD("sword"),
    SHIELD("shield"),
    HEALTHPOTION("health potion"),
    ARMOR("armor"),
    KEY("key"),
    LOCKEDDOOR("locked door"),
    DOOR("door");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
