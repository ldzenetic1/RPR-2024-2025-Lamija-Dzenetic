package controller;
import model.*;
import view.*;
import java.util.*;

public class PredmetController {
    private Predmet model;
    private PredmetView view;

    public PredmetController(Predmet model, PredmetView view){
        setModel(model);
        setView(view);
    }
    public Predmet getModel() {
        return model;
    }

    public void setModel(Predmet model) {
        this.model = model;
    }

    public PredmetView getView() {
        return view;
    }

    public void setView(PredmetView view) {
        this.view = view;
    }
    public void azurirajNaziv()
    {
        try {
            model.setNaziv(view.getUlazniTekst());
            view.setPoruka("Naziv je uspjesno azuriran!");
        }
        catch(Exception e) {
            view.setPoruka("Greska: " + e.getMessage());
        }
    }
    public void azurirajECTS(){
        try{
            Double ectsValue = Double.parseDouble(view.getUlazniTekst());
            model.setECTS(ectsValue);
            view.setPoruka("ECTS uspjesno azuriran!");
        }
        catch(Exception e){
            view.setPoruka("Greska: " + e.getMessage());
        }
    }
    public void dajPredmeteIzTxtDatoteke(String filePath)
    {
        try
        {
            List<Predmet> predmeti = Predmet.ucitajPredmeteIzTxtDatoteke(filePath);
            String poruka = "Predmeti ucitani iz txt datoteke su:\n";
            for (Predmet predmet : predmeti)
            {
                poruka += predmet.toString() + "\n";
            }
            view.setPoruka(poruka);
        }
        catch(Exception e)
        {
            view.setPoruka("Greska: " + e.getMessage());
        }
    }

}
