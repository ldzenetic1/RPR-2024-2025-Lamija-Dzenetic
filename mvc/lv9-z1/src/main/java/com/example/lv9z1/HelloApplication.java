package com.example.lv9z1;

import com.example.lv9z1.model.OsobaModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }
    /*private static OsobaModel instance = null;

    public static OsobaModel getInstance() {
        if (instance == null) {
            instance = new OsobaModel();
        }
        return instance;
    }

    public static void removeInstance() {
        instance = null;
    }*/

    public static void main(String[] args) {

        //launch();

        Connection connect = Database.connect();
        OsobaModel osobaModel = OsobaModel.getInstance();
    }
}