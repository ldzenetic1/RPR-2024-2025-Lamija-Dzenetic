package Main;

import model.*;
import view.*;
import controller.*;

public class Main {
    public static void main(String[] args) {
        try{
            Predmet predmet = new Predmet("Razvoj programskih rjesenja", 6.0);
            PredmetView predmetView = new PredmetView("Tehnike programiranja", "7.5");
            predmetView.setUlazniTekst("Novi naziv");
            PredmetController predmetController = new PredmetController(predmet, predmetView);
            predmetController.azurirajNaziv();
            System.out.println("1) View ispisuje: " + predmetView.getPoruka());
            predmetController.dajPredmeteIzTxtDatoteke("src/data/predmeti.txt");
            System.out.println("2) View ispisuje: " + predmetView.getPoruka());
        }
        catch(Exception e){
            e.getMessage();
        }
    }
}