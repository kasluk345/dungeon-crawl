package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.AutoMove;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.util.ArrayList;

import java.awt.*;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label armorLabel = new Label();
    Label attackLabel = new Label();
    Label defenseLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        ui.add(new Label("Armor : "), 0, 1);
        ui.add(armorLabel, 1, 1);

        ui.add(new Label("Attack: "), 0, 2);
        ui.add(attackLabel, 1, 2);

        ui.add(new Label("Defence: "), 0, 3);
        ui.add(defenseLabel, 1, 3);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();

        //async move of enemy: Thread - by AutoMove class
        Runnable ghostMove = new AutoMove(this,map.getGhost());
        new Thread(ghostMove).start(); //comment this line to stop ghost

        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                map.getEnemy().move(0, +1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                map.getEnemy().move(0, -1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                map.getEnemy().move(+1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                map.getEnemy().move(-1,0);
                refresh();
                break;
            case I:
                if (map.getPlayer().getInventory().toString() == "") {
                    new InventoryWindow("Inventory EMPTY");
                } else {
                    new InventoryWindow(map.getPlayer().getInventory().toString());
                }
        }
    }

    public synchronized void refresh() {
       // System.out.println("PLAYER position: "+map.getPlayer().getX()+","+map.getPlayer().getY());
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null ) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                }
                else if (cell.getItem() != null ) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        armorLabel.setText("" + map.getPlayer().getArmor());
        attackLabel.setText("" + map.getPlayer().getAttack());
        defenseLabel.setText("" + map.getPlayer().getDefence());
        if (map.getPlayer().isPlayerIsDead()) {
            new EndWindow();
        }
    }
}
