package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Enemy;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Door;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private Item item;
    private Door door;
    private ArrayList<Actor> ghosts = new ArrayList<Actor>();
    private Enemy enemyBasic;
    private Enemy enemyAdvanced;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Player getPlayer() {
        return player;
    }

/*    public void setGhost(Ghost ghost) { this.ghost = ghost; }

    public Ghost getGhost() { return ghost; }*/

    public void setGhosts(Ghost ghost) { this.ghosts.add(ghost); }

    public ArrayList<Actor> getGhosts() { return ghosts; }

    public void setEnemy(Enemy enemy) {
        if (!enemy.getEnemyType()) {
            this.enemyBasic = enemy;
        } else {
            this.enemyAdvanced = enemy;
        }
    }

    public Enemy getEnemy() {
        return enemyBasic;
    }

    public Enemy getAdvancedEnemy() {
        return enemyAdvanced;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Actor> getEnemies() {
        List<Actor> enemies = new ArrayList<>();
        enemies.add(this.getAdvancedEnemy());
        enemies.addAll(this.getGhosts());
        return enemies;
    }
}
