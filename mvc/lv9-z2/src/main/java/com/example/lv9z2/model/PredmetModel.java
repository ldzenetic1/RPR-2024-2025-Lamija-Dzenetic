package com.example.lv9z2.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.*;
import java.text.*;


public class PredmetModel
{
    private static ObservableList<Predmet> predmeti;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final String DATABASE_URL = "jdbc:sqlite:baza.db";

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

  public PredmetModel() {
        predmeti = FXCollections.observableArrayList();
    }
    public String dodajPredmet(Integer id, String naziv, Double ECTS) {
        try {
            Predmet newPredmet = new Predmet(id, naziv, ECTS);
            predmeti.add(newPredmet);
            return "Predmet je uspjesno dodan!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public String obrisiPredmet(String naziv)
    {
        if(predmeti.removeIf(predmet -> predmet.getNaziv() == naziv))
            return "Osoba je uspjesno obrisana!";
        else return "Osoba nije pronadjena!";
    }
    public List<Predmet> getPredmeti() {
        return predmeti;
    }
    public void napuni() {
        predmeti.add(new Predmet(1,"Razvoj Programskih Rjesenja", 6.0));
        predmeti.add(new Predmet(2,"Tehnike programiranja", 7.0));
    }
    public static void kreirajTabeluAkoNePostoji() {
        String kreirajPredmetTabeluSql = """
      CREATE TABLE IF NOT EXISTS Predmet (
           id INTEGER,
          naziv TEXT,
          ects DOUBLE
      );
   """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(kreirajPredmetTabeluSql);
            System.out.println("Tabela je kreirana ili vec postoji!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void napuniInicijalnimPodacima() {
        String insertSQL = """
      INSERT INTO Predmet (id, naziv, ects)
      VALUES (?, ?, ?);
   """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, 1);
            pstmt.setString(2, "Razvoj Programskih Rjesenja");
            pstmt.setDouble(3, 6.0);
            pstmt.executeUpdate();

            pstmt.setInt(1, 2);
            pstmt.setString(2, "Tehnike programiranja");
            pstmt.setDouble(3, 7.0);
            pstmt.executeUpdate();

            System.out.println("Ubaceni pocetni podaci!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void isprazniTabeluPredmeta() {
        String upit = "DELETE FROM Predmet";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            int brojObrisanihRedova = stmt.executeUpdate(upit);
            System.out.println("Obrisani redovi tabele. Broj obrisanih redova: " + brojObrisanihRedova);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static List<Predmet> dajSvePremdete() {
        List<Predmet> osobe = new ArrayList<>();
        String upit = "SELECT * FROM Predmet";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(upit)) {

            while (rs.next()) {
                Predmet predmet  = new Predmet(
                        rs.getInt("id"),
                        rs.getString("naziv"),
                        rs.getDouble("ects")

                );
                predmeti.add(predmet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return osobe;
    }
    public static Predmet dajPredmetPoId(int id) {
        String upit = "SELECT * FROM Predmet WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(upit)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Predmet(
                        rs.getInt("id"),
                        rs.getString("naziv"),
                        rs.getDouble("ects")
                );
            } else {
                System.out.println("Predmet sa ID-em " + id + " nije pronađen.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Greška prilikom dohvaćanja predmeta: " + e.getMessage());
            return null;
        }
    }

    public static String azurirajPredmet(Integer id, String noviNaziv, Double noviECTS) {
        StringBuilder upit = new StringBuilder("UPDATE Predmet SET ");
        boolean imaPromjene = false;

        // lista koja cuva parametre
        List<Object> parametri = new ArrayList<>();

        // provjera vrijednosti pojedinih polja (da li su prazna ili ne)
        if (noviNaziv != null && !noviNaziv.isEmpty()) {
            upit.append("naziv = ?, ");
            parametri.add(noviNaziv);
            imaPromjene = true;
        }
       if(noviECTS >= 0){
           upit.append("ects = ?,  ");
           parametri.add(noviECTS);
           imaPromjene = true;
       }

        // izadji ranije ako nema polja za azuriranje
        if (!imaPromjene) {
            return "Sva polja su ista kao i prije!";
        }

        // uklanjanje zareza na kraju upita i razmaka iz SQL upita
        upit.delete(upit.length() - 2, upit.length());
        upit.append(" WHERE id = ?");

        // dodaj id kao parametar
        parametri.add(id);
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(upit.toString())) {

            // dodavanje parameatara u PreparedStatement
            for (int i = 0; i < parametri.size(); i++) {
                pstmt.setObject(i + 1, parametri.get(i));
            }

            int promijenjeniRedovi = pstmt.executeUpdate();
            if (promijenjeniRedovi > 0) {
                return "Predmet je uspjesno azurirana";
            } else {
                return "Ne postoji predmet sa datim id-em";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    public static String obrisiPredmetPoId(int id) {
        String upit = "DELETE FROM Predmet WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(upit)) {

            pstmt.setInt(1, id);
            int brojObrisanihRedova = pstmt.executeUpdate();

            if (brojObrisanihRedova > 0) {
                return "Predmet sa ID-em " + id + " je uspešno obrisan.";
            } else {
                return "Predmet sa ID-em " + id + " nije pronađen.";
            }
        } catch (SQLException e) {
            return "Greška prilikom brisanja predmeta: " + e.getMessage();
        }
    }

    private static PredmetModel instance = null;

    public static PredmetModel getInstance() {
        if (instance == null) {
            instance = new PredmetModel();
        }
        return instance;
    }

    public static void removeInstance() {
        instance = null;
    }




}