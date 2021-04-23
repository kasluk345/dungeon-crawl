package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.KeysModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;
import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import javax.sql.DataSource;
import java.sql.SQLException;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;
    private InventoryDao inventoryDao;
    private KeysDao keysDao;

    private PlayerModel readPlayer;
    private GameState readGameState;
    private InventoryModel readInventory;
    private KeysModel readKeys;

    private HashMap<String,String> DBcredentials = new HashMap<>();

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
        inventoryDao = new InventoryDaoJdbc(dataSource);
        keysDao = new KeysDaoJdbc(dataSource);
    }

    public void savePlayerGame(Player player, String currentMap, Timestamp currentData) {
        PlayerModel currentPlayer = new PlayerModel(player);
        PlayerModel registeredPlayer = isPlayerInDB(currentPlayer);

        if (registeredPlayer == null) {
            playerDao.add(currentPlayer);
            GameState gameState = new GameState(currentMap, currentData, currentPlayer);
            gameStateDao.add(gameState);
            InventoryModel inventoryModel = new InventoryModel(currentPlayer, player.getInventory());
            inventoryDao.add(inventoryModel);
            KeysModel keysModel = new KeysModel(inventoryModel, player.getInventory());
            keysDao.add(keysModel);
        } else {
            currentPlayer = registeredPlayer;
            playerDao.update(currentPlayer);
            GameState gameState = new GameState(currentMap, currentData, currentPlayer);
            gameStateDao.update(gameState);
            InventoryModel inventoryModel = new InventoryModel(currentPlayer, player.getInventory());
            inventoryModel.setId(inventoryDao.getInventoryId(currentPlayer.getId()));
            inventoryDao.update(inventoryModel);
            KeysModel keysModel = new KeysModel(inventoryModel, player.getInventory());
            keysModel.setId(keysDao.getKeysId(inventoryModel.getId()));
            keysDao.update(keysModel);
            System.out.println("CURRENT player is: " + currentPlayer.getPlayerName() + ", ID=" + currentPlayer.getId());
        }
    }


    public PlayerModel isPlayerInDB(PlayerModel currentPlayer) {
        System.out.println("Checking all players: ");
        List<PlayerModel> players = playerDao.getAll();
        for(PlayerModel player: players) {
           // System.out.println("checking: " + player.getPlayerName());
            if(currentPlayer.getPlayerName().equals(player.getPlayerName())) {
                System.out.println("\t Player with name: " + player.getPlayerName() + " is in DB!");
                return player;
            }
        }
        System.out.println("\t Player with name: "+currentPlayer.getPlayerName()+" is NOT in DB!");
        return null;
    }

    public boolean loadPlayerGame(Player player) {
        PlayerModel currentPlayer = new PlayerModel(player);
        PlayerModel registeredPlayer= isPlayerInDB(currentPlayer);

        if(registeredPlayer == null) {
            System.out.println("You do not have any saved game!!!");
            return false;
        } else {
            currentPlayer = registeredPlayer;
            PlayerModel readPlayer = playerDao.get(currentPlayer.getId());
            GameState readGameState = gameStateDao.get(currentPlayer.getId());
            InventoryModel readInventory = inventoryDao.get(currentPlayer.getId());
            KeysModel readKeys = keysDao.get(readInventory.getId());

            this.readPlayer = readPlayer;
            this.readGameState = readGameState;
            this.readInventory = readInventory;
            this.readKeys = readKeys;

//            //PRINT DATA FROM DB IN CONSOLE
//            System.out.println("======================================================");
//            System.out.println(readPlayer.toString());
//            System.out.println();
//            System.out.println(readGameState.toString());
//            System.out.println();
//            System.out.println(readInventory.toString());
//            System.out.println();
//            System.out.println("\nRead data for player: "+currentPlayer.getPlayerName() +", ID="+currentPlayer.getId());
//            System.out.println("======================================================");

            //TODO: return and load this data into main
            return true;
        }
    }

    public GameState getGameState(int id) {
        return this.gameStateDao.get(id);
    }

    public GameState getReadGameState() {
        return this.readGameState;
    }


    ///can be in another class
    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        System.out.println("Read DB credentials...");
        readDataDB();

        String dbName = DBcredentials.get("dbName");
        String user = DBcredentials.get("user");
        String password = DBcredentials.get("password");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

    private void readDataDB() {

        try {
            String shortPath = "src/main/resources/DB-credentials.txt";
            File file = new File(shortPath);
            Scanner sc = new Scanner(file);

            while(sc.hasNext()) {
                String[] data = sc.nextLine().split(":");
                //System.out.println(data[0]+"="+data[1]); //!display password in console!
                DBcredentials.put(data[0],data[1]);
            }
            System.out.println("Successfully read DB data from the file");
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error - File Not Found");
        }
    }

    public PlayerDao getPlayerDao() {
        return playerDao;
    }

    public PlayerModel getReadPlayer() {
        return readPlayer;
    }

    public InventoryModel getReadInventory() {
        return readInventory;
    }

    public KeysModel getReadKeys() {
        return readKeys;
    }
}
