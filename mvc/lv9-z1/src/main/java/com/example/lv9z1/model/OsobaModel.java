package com.example.lv9z1.model;

import com.example.lv9z1.model.Osoba;

import javafx.collections.*;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.*;
import java.util.Date;


public class OsobaModel
{
    private ObservableList<Osoba> osobe;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final String DATABASE_URL = "jdbc:sqlite:baza.db";

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    public OsobaModel() {
        osobe = FXCollections.observableArrayList();
    }
    public String dodajOsobu(Integer id, String ime, String prezime, String adresa, Date datumRodjenja, String maticniBroj, Uloga uloga) {
        try {
            Osoba newOsoba = new Osoba(id, ime, prezime, adresa, datumRodjenja, maticniBroj, uloga);
            osobe.add(newOsoba);
            return "Osoba je uspjesno dodana!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public String obrisiOsobu(Integer id)
    {
        if(osobe.removeIf(osoba -> osoba.getId() == id))
            return "Osoba je uspjesno obrisana!";
        else return "Osoba nije pronadjena!";
    }
    public List<Osoba> getOsobe() {
        return osobe;
    }
    public void napuni(){
        osobe.add(new Osoba(1,"Neko","Nekic","Neka adresa", new Date(97,8,25), "2509997123456", Uloga.STUDENT));
        osobe.add(new Osoba(2,"Neko 2","Nekic 2","Neka adresa 2",new Date(97,8,25), "2509997123456", Uloga.NASTAVNO_OSOBLJE));
    }
    public void napuniPodatkeIzTxtDatoteke(String putanjaDoDatoteke) throws IOException, ParseException {
        osobe = FXCollections.observableArrayList();
        BufferedReader reader = new BufferedReader(new FileReader(putanjaDoDatoteke));


        String linija;
        while ((linija = reader.readLine()) != null) {
            String[] polja = linija.split(",");
            if (polja.length == 7) {
                Integer id = Integer.parseInt(polja[0]);
                String ime = polja[1];
                String prezime = polja[2];
                String adresa = polja[3];
                Date datumRodjenja = dateFormat.parse(polja[4]);
                String maticniBroj = polja[5];
                Uloga uloga = Uloga.valueOf(polja[6].toUpperCase());


                Osoba osoba = new Osoba(id, ime, prezime, adresa, datumRodjenja, maticniBroj,uloga);
                osobe.add(osoba);
            }
        }
        reader.close();
    }
    public static void kreirajTabeluAkoNePostoji() {
        String kreirajOsobaTabeluSql = """
      CREATE TABLE IF NOT EXISTS Osoba (
          id INTEGER,
          ime TEXT,
          prezime TEXT,
          adresa TEXT,
          datumRodjenja TEXT,
          maticniBroj TEXT,
          uloga TEXT
      );
   """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(kreirajOsobaTabeluSql);
            System.out.println("Tabela je kreirana ili vec postoji!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void napuniInicijalnimPodacima() {
        String insertSQL = """
      INSERT INTO Osoba (id, ime, prezime, adresa, datumRodjenja, maticniBroj, uloga)
      VALUES (?, ?, ?, ?, ?, ?, ?);
   """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setInt(1, 1);
            pstmt.setString(2, "John");
            pstmt.setString(3, "Doe");
            pstmt.setString(4, "Some Address");
            pstmt.setString(5, "1995-01-15");
            pstmt.setString(6, "1501995123456");
            pstmt.setString(7, "STUDENT");
            pstmt.executeUpdate();

            pstmt.setInt(1, 2);
            pstmt.setString(2, "Alice");
            pstmt.setString(3, "Alister");
            pstmt.setString(4, "Another Address");
            pstmt.setString(5, "1980-05-20");
            pstmt.setString(6, "2005980444444");
            pstmt.setString(7, "NASTAVNO_OSOBLJE");
            pstmt.executeUpdate();

            System.out.println("Ubaceni pocetni podaci!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void isprazniTabeluOsoba() {
        String upit = "DELETE FROM Osoba";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            int brojObrisanihRedova = stmt.executeUpdate(upit);
            System.out.println("Obrisani redovi tabele. Broj obrisanih redova: " + brojObrisanihRedova);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static List<Osoba> dajSveOsobe() {
        List<Osoba> osobe = new ArrayList<>();
        String upit = "SELECT * FROM Osoba";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(upit)) {

            while (rs.next()) {
                Osoba osoba = new Osoba(
                        rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("adresa"),
                        OsobaModel.dateFormat.parse(rs.getString("datumRodjenja")),
                        rs.getString("maticniBroj"),
                        Uloga.valueOf(rs.getString("uloga"))
                );
                osobe.add(osoba);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return osobe;
    }
    public static Osoba dajOsobuPoId(Integer id) {
        Osoba osoba = null;
        String upit = "SELECT * FROM Osoba WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(upit)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                osoba = new Osoba(
                        rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("adresa"),
                        OsobaModel.dateFormat.parse(rs.getString("datumRodjenja")),
                        rs.getString("maticniBroj"),
                        Uloga.valueOf(rs.getString("uloga"))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return osoba;
    }
    public static String azurirajOsobu(Integer id, String novoIme, String novoPrezime, String novaAdresa, Date noviDatumRodjenja, String noviMaticniBroj, Uloga novaUloga) {
        StringBuilder upit = new StringBuilder("UPDATE Osoba SET ");
        boolean imaPromjene = false;

        // lista koja cuva parametre
        List<Object> parametri = new ArrayList<>();

        // provjera vrijednosti pojedinih polja (da li su prazna ili ne)
        if (novoIme != null && !novoIme.isEmpty()) {
            upit.append("ime = ?, ");
            parametri.add(novoIme);
            imaPromjene = true;
        }
        if (novoPrezime != null && !novoPrezime.isEmpty()) {
            upit.append("prezime = ?, ");
            parametri.add(novoPrezime);
            imaPromjene = true;
        }
        if (novaAdresa != null && !novaAdresa.isEmpty()) {
            upit.append("adresa = ?, ");
            parametri.add(novaAdresa);
            imaPromjene = true;
        }
        if (noviDatumRodjenja != null) {
            upit.append("datumRodjenja = ?, ");
            parametri.add(dateFormat.format(noviDatumRodjenja));  // Format the date
            imaPromjene = true;
        }
        if (noviMaticniBroj != null && !noviMaticniBroj.isEmpty()) {
            upit.append("maticniBroj = ?, ");
            parametri.add(noviMaticniBroj);
            imaPromjene = true;
        }
        if (novaUloga != null) {
            upit.append("uloga = ?, ");
            parametri.add(novaUloga.name());
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
                return "Osoba je uspjesno azurirana";
            } else {
                return "Ne postoji osoba sa datim id-em";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    private static OsobaModel instance = null;

    public static OsobaModel getInstance() {
        if (instance == null) {
            instance = new OsobaModel();
        }
        return instance;
    }

    public static void removeInstance() {
        instance = null;
    }




}
