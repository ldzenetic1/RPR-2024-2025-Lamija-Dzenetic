package com.example.lv7.controller;

import com.example.lv7.model.Osoba;
import com.example.lv7.model.OsobaModel;

public class OsobaController
{
    private OsobaModel model;
    private OsobaView view;


    public OsobaController(OsobaModel model, OsobaView view)
    {
        this.model = model;
        this.view = view;
    }
    public Osoba dajOsobuPoId(Integer id) {
        Osoba osoba = model.dajOsobuPoId(id);
        if (osoba == null) {
            view.setPoruka("Osoba nije pronađena!");
        } else {
            view.setPoruka("Pronađena osoba: " + osoba.toString());
        }
        return osoba;
    }


    public void azurirajIme(Integer id){
        try {
            model.azurirajOsobu(id, view.getUlazniTekst(), null, null, null, null, null);
            view.setPoruka("Ime je uspjesno azurirano!");
        }
        catch(Exception e){


            view.setPoruka("Greska: " + e.getMessage());
        }
    }


}

