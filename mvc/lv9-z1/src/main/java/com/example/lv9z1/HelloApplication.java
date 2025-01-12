package com.example.lv9z1;

import com.example.lv9z1.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Kreiraj tabelu ako ne postoji
        OsobaModel.kreirajTabeluAkoNePostoji();
        // Obriši sve redove iz tabele
        OsobaModel.isprazniTabeluOsoba();

        // Ubaci početne podatke
        OsobaModel.napuniInicijalnimPodacima();

        System.out.println("Sve operacije su završene!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}