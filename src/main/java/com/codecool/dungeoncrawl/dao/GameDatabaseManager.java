package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.scene.image.Image;
import org.postgresql.ds.PGSimpleDataSource;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileNotFoundException;

import javax.sql.DataSource;
import java.sql.SQLException;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private HashMap<String,String> DBcredentials = new HashMap<>();

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
    }

    public void savePlayer(Player player) {
        PlayerModel model = new PlayerModel(player);
        playerDao.add(model);
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
