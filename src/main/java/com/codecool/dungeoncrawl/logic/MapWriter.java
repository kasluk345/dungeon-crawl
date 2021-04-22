
package com.codecool.dungeoncrawl.logic;

import java.util.Formatter;

import com.codecool.dungeoncrawl.logic.actors.Dog;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.*;

public class MapWriter {
    public static String getSavedMap(GameMap map) {
        return saveMap(map);
    }

    private static String saveMap(GameMap map) {
        String currentMapState = readCurrentMap(map); //return currentMapState in String
        saveCurrentMapTXT(currentMapState); //save String into TXT file
        return currentMapState;

    }


    private static String readCurrentMap(GameMap map) {
        String currentMap = map.getWidth() + " " + map.getHeight() + System.lineSeparator();

        //read map horizontally, line by line
        for (int x = 0; x < map.getHeight(); x++) {
            for (int y = 0; y < map.getWidth(); y++) {
                Cell cell = map.getCell(y, x);

                switch (cell.getType()) {
                    case EMPTY:
                        currentMap += ' ';
                        break;
                    case WALL:
                        currentMap += '#';
                        break;
                    case LOCKEDDOOR:
                        currentMap += 'd';
                        break;
                    case DOOR:
                        currentMap += 'o';
                        break;
                    case BAD_DOOR:
                        currentMap += '!';
                        break;
                    case PORTAL:
                        currentMap += 'p';
                        break;
                    case WALL1:
                        currentMap += '&';
                        break;
                    case FORREST:
                        currentMap += '^';
                        break;
                    case WIN:
                        currentMap += '$';
                        break;
                    case FFLOOR:
                        currentMap += ',';
                        break;
                    case NPC:
                        currentMap += 's'; //skeleton
                        break;
                    case FLOOR:
                        currentMap += getObjectOnFLOOR(cell);  //on FLOOR can be Item or Actor
                        break;
                    default:
                        currentMap += '?';
                        //throw new RuntimeException("Unrecognized character: ");
                }
                //enter after last sign in line
                if (y == map.getWidth() - 1) { currentMap += System.lineSeparator(); }
            }
        }
        return currentMap;
    }

    private static char getObjectOnFLOOR(Cell cell) {
        //ACTORS
        if(cell.getActor() != null) {
            //System.out.println(cell.getActor().getClass().getSimpleName());
            if (cell.getActor() instanceof Skeleton) { //skeleton is set by NPC, not as Actor class
                return 's';
            } else if (cell.getActor() instanceof Player) {
                return '@';
            } else if (cell.getActor() instanceof Ghost) {
                return 'x';
            } else if (cell.getActor() instanceof Dog) {
                return 'f';
            }
        }
        //ITEMS
        if(cell.getItem()!= null ) {
            if (cell.getItem() instanceof Sword) {
                return 'm';
            } else if (cell.getItem() instanceof Shield) {
                return 't';
            } else if (cell.getItem() instanceof Armor) {
                return 'a';
            } else if (cell.getItem() instanceof Key) {
                return 'k';
            } else if (cell.getItem() instanceof Helmet) {
                return 'h';
            } else if (cell.getItem() instanceof AttackPotion) {
                return '1';
            } else if (cell.getItem() instanceof HealthPotion) {
                return '2';
            } else if (cell.getItem() instanceof DefensePotion) {
                return '3';
            } else if (cell.getItem() instanceof Gauntlet) {
                return 'g';
            } else if (cell.getItem() instanceof Ring) {
                return 'r';
            }
        }
        //default only FLOOR
        return '.';
    }

    public static void saveCurrentMapTXT(String currentMap) {
        String shortPath = "src/main/resources/savedMap.txt";

        try {
            Formatter savedMap = new Formatter(shortPath);
            savedMap.format("%s", currentMap);
            savedMap.close();
        } catch (Exception e) {
            System.out.println("Error while saving the map");
        }
        System.out.println("The map has been saved! (path: ["+shortPath+"]");
    }


}

