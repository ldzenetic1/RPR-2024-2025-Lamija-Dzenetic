package com.example.lv8z2;

import com.example.lv8z2.model.PredmetModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import com.example.lv8z2.model.Predmet;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML
    private Label ucitavanjeLabel;
    @FXML
    private ListView<Predmet> predmetiListView;
    @FXML
    private TextField nazivField;
    @FXML
    private TextField ECTSField;
    @FXML
    private Button azurirajPredmetButton;
    @FXML
    private Label porukaLabel;
    private PredmetModel model;
    private ObservableList<Predmet> predmetiObservableList = FXCollections.observableArrayList();
    private Predmet izabraniPredmet;
    public HelloController(PredmetModel model) {
        this.model = model;
    }
    @FXML
    public void initialize() {
        // Example data for testing, replace with actual model loading
        predmetiObservableList.addAll(
                new Predmet("Matematika", 15.0),
                new Predmet("Fizika", 10.0),
                new Predmet("Hemija", 5.0)
        );

        ucitavanjeLabel.setText("Podaci o predmetima su učitani.");
        ucitavanjeLabel.setStyle("-fx-background-color: green;");

        predmetiListView.setItems(predmetiObservableList);

        azurirajPredmetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                azurirajPredmet();
            }
        });

        predmetiListView.getSelectionModel().selectedItemProperty().addListener((observable, staraVrijednost, novaVrijednost) -> {
            if (novaVrijednost != null) {
                izabraniPredmet = novaVrijednost;
                ispuniPolja(novaVrijednost);
                porukaLabel.setVisible(false);
            }
        });
    }

    private void ispuniPolja(Predmet predmet) {
        nazivField.setText(predmet.getNaziv());
        ECTSField.setText(String.valueOf(predmet.getECTS()));
    }

    private void azurirajPredmet() {
        if (izabraniPredmet != null) {
            String naziv = nazivField.getText();
            String ECTSString = ECTSField.getText();

            // Validate fields
            if (naziv.isEmpty() || ECTSString.isEmpty()) {
                porukaLabel.setVisible(true);
                porukaLabel.setText("Sva polja moraju biti popunjena!");
                return;
            }

            try {
                double ECTS = Double.parseDouble(ECTSString);
                izabraniPredmet.setNaziv(naziv);
                izabraniPredmet.setECTS(ECTS);

                porukaLabel.setVisible(true);
                porukaLabel.setText("Predmet uspješno ažuriran!");
                predmetiListView.refresh();
            } catch (NumberFormatException e) {
                porukaLabel.setVisible(true);
                porukaLabel.setText("ECTS mora biti broj.");
            } catch (IllegalArgumentException e) {
                porukaLabel.setVisible(true);
                porukaLabel.setText(e.getMessage());
            }
        }
    }
}
