package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
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
    private HashMap<String,String> DBcredentials = new HashMap<>();

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
    }

    public void savePlayer(Player player) {
        PlayerModel model = new PlayerModel(player);
        playerDao.add(model);
    }

    public void savePlayerGame(Player player,String currentMap, Timestamp currentData) {
        PlayerModel currentPlayer = new PlayerModel(player);
        PlayerModel registeredPlayer= isPlayerInDB(currentPlayer);

        if(registeredPlayer == null) {
            playerDao.add(currentPlayer);
        } else {
            currentPlayer = registeredPlayer;
            playerDao.update(currentPlayer);
        }
        System.out.println("CURRENT player is: "+registeredPlayer.getPlayerName() +" ID="+currentPlayer.getId());

        GameState gameState = new GameState(currentMap, currentData, currentPlayer);
        gameStateDao.add(gameState);

    }

    public PlayerModel isPlayerInDB(PlayerModel currentPlayer) {
        System.out.println("READING all players: ");
        List<PlayerModel> players = playerDao.getAll();
        for(PlayerModel player: players) {
            //System.out.println("checking: " + player.getPlayerName());
            if(currentPlayer.getPlayerName().equals(player.getPlayerName())) {
                System.out.println("Player with name: " + player.getPlayerName() + " is in DB!");
                return player;
            }
        }
        System.out.println("Player with name: "+currentPlayer.getPlayerName()+" is NOT in DB!");
        return null;

    }

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
}
