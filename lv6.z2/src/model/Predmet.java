package model;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Predmet {
    private String naziv;
    private Double ECTS;

    public Predmet(String naziv, Double ects){
        setECTS(ects);
        setNaziv(naziv);
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        if (naziv == null || naziv.length() < 5 || naziv.length() > 100) {
            throw new IllegalArgumentException("Naziv mora imati izmedju 5 i 100 znakova.");
        }
        this.naziv = naziv;
    }

    public Double getECTS() {
        return ECTS;
    }

    public void setECTS(Double ECTS) {
        int cifra = ((int)(ECTS * 10) % 10);
        if(ECTS < 5.0 || ECTS > 20.0 || cifra != 5 || cifra != 0){
            throw new IllegalArgumentException("ECTS moze minimalno imati vrijednostt 5.0, a maksimalno 20.0!");
        }
        this.ECTS = ECTS;
    }
    public static List<Predmet> ucitajPredmeteIzTxtDatoteke(String putanjaDoDatoteke) throws IOException {
        List<Predmet> predmeti = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(putanjaDoDatoteke));
        String linija;
        while ((linija = reader.readLine()) != null) {
            String[] polja = linija.split(",");
            if(polja.length == 2){
               String naziv = polja[0];
               Double ects = Double.parseDouble(polja[1]);
               try {
                   Predmet predmet = new Predmet(naziv, ects);
                   predmeti.add(predmet);
               }catch(Exception e){
                   e.getMessage();
               }

            }
        }
        reader.close();
        return predmeti;
    }
    @Override
    public String toString() {
        return "Predmet{" +
                "naziv='" + naziv + '\'' +
                ", ECTS='" + ECTS + "}";
        }
    }
