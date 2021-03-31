package com.codecool.dungeoncrawl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StartWindow {

    public static java.lang.String handlePlayerName() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Dungeon Crawl 2021");
        window.setMaxWidth(500);
        window.setMinWidth(500);
        window.setMaxHeight(500);
        window.setMinHeight(500);

        final TextField name = new TextField();
        name.setText("Enter your name.");
        Button submit = new Button("Submit");
        Button clear = new Button("Clear");


        VBox layout = new VBox(10);
        layout.getChildren().addAll(name, submit, clear);
        layout.setAlignment(Pos.CENTER);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if ((name.getText() != null && !name.getText().isEmpty())) {
                    name.setText(name.getText());
                    window.close();
                } else {
                    name.setText("Enter your name");
                }
            }
        });
        clear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                name.clear();
            }
        });


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return name.getText();
    }
}

