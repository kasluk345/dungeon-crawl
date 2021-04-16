package com.codecool.dungeoncrawl.logic;

import java.util.Formatter;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.logic.actors.Dog;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapWriter {
    static int counter=-1;
    public static void saveMap(GameMap map) {
        String currentMap = "";
        counter +=1;
        int enter=1;


        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (y == map.getHeight()-1) {
                    //System.out.println("ENTER "+enter);
                    enter++;
                    currentMap += cell.getType().getTileName()+System.lineSeparator();
                } else {
                    currentMap += cell.getType().getTileName();
                }
                //System.out.println(currentMap);
                if (cell.getActor() != null ) {
                    //Tiles.drawTile(context, cell.getActor(), x, y);
                }
                else if (cell.getItem() != null ) {
                    //Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    //Tiles.drawTile(context, cell, x, y);
                }
            }
        }


        String shortPath = "src/main/resources/savedMap.txt";
        if(counter==0) { //test zapisz tylko raz
            try {
                Formatter savedMap = new Formatter(shortPath);
                //savedMap.format("%s %s %s ", "1", "@", "#"); //example
                savedMap.format("%s",currentMap);
                savedMap.close();
            } catch (Exception e) {
                System.out.println("Error while saving the map");
            }
            System.out.println("The map has been saved!");
        }
    }
}
