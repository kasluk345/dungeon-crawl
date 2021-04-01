package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.Random;

public abstract class Item implements Drawable {

    private static final String[] POSSIBLE_ADJECTIVES = {
            "Vagluocium",
            "Weshourhil",
            "Bostralium",
            "Ogreocium",
            "Scasten",
            "Breatrium",
            "Kruyrium",
            "Nogryx",
            "Guchine",
            "Pawhum",
            "Iowhenese",
            "Nefloethil",
            "Iasteatine",
            "Odriynor",
            "Thoytine",
            "Spoirhil",
            "Cluesium",
            "Joblese",
            "Sablium",
            "TuspianEshaotium",
            "Zescautine",
            "Vafroesten",
            "Hoprolium",
            "Criethil",
            "Drudian",
            "Crelium",
            "Othum",
            "Kachite",
            "Jetrite",
    };
    protected final String name;
    protected static final Random RANDOM = new Random();

    private Cell cell;

    public Item(Cell cell) {
        this.name = generateName();
        this.cell = cell;
        this.cell.setItem(this);
    }

    protected String generateName(){
        String adjective = POSSIBLE_ADJECTIVES[RANDOM.nextInt(POSSIBLE_ADJECTIVES.length)];
        String itemType = this.getClass().getSimpleName();

        return adjective + " " + itemType;
    }

    public String getName() {
        return name;
    }
}
