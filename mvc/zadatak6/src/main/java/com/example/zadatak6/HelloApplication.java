package com.example.zadatak6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        ObservableList<String> grupa1 = FXCollections.observableArrayList("Lamija", "Enela", "Amalia", "Elma");
        ObservableList<String> grupa2 = FXCollections.observableArrayList("Esma", "Amra", "Azra", "Dino", "Lejla", "Harun");
        FXCollections.sort(grupa1);
        FXCollections.sort(grupa2);
        ListView<String> listView1 = new ListView<>(grupa1);
        ListView<String> listView2 = new ListView<>(grupa2);

        listView1.setOnMouseClicked(event -> {
            String selectedItem = listView1.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                grupa1.remove(selectedItem);
                grupa2.add(selectedItem);
                FXCollections.sort(grupa1);
                FXCollections.sort(grupa2);
            }
        });
        listView2.setOnMouseClicked(event -> {
            String selectedItem = listView2.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                grupa2.remove(selectedItem);
                grupa1.add(selectedItem);
                FXCollections.sort(grupa1);
                FXCollections.sort(grupa2);
            }
        });
        Label label1 = new Label("Grupa 1");
        Label label2 = new Label("Grupa 2");

        VBox leftBox = new VBox(label1, listView1);
        leftBox.setSpacing(5);
        VBox rightBox = new VBox(label2, listView2);
        rightBox.setSpacing(5);
        HBox hbox = new HBox(leftBox, rightBox);
        hbox.setSpacing(0);
        Scene scene = new Scene(hbox, 300, 400);
        primaryStage.setTitle("Imena-Grupe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
