package model;
import java.util.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class OsobaTest {

    @org.junit.jupiter.api.Test
    public void IspravnaOsoba() {
    Date datumRodjenja = new Date(2003, 6, 16);
    Osoba osoba = new Osoba(1, "Lamija", "Dzenetic", "Jablanica 22", datumRodjenja, "1606003221021", Uloga.STUDENT );
    assertEquals(1, osoba.getId());
    assertEquals("Lamija", osoba.getIme());
    assertEquals("Dzenetic", osoba.getPrezime());
    assertEquals("Jablanica 22", osoba.getAdresa());
    assertEquals(datumRodjenja, osoba.getDatumRodjenja());
    assertEquals("1606003221021", osoba.getMaticniBroj());
    assertEquals(Uloga.STUDENT, osoba.getUloga());
    }

    @org.junit.jupiter.api.Test
    public void NeispravnostImena() {
        Date datumRodjenja = new Date(2003, 6, 16);
        Osoba osoba = new Osoba(1, "L", "Dzenetic", "Jablanica 22", datumRodjenja, "1606003221021", Uloga.STUDENT );
        Exception ex1 = assertThrows(IllegalArgumentException.class , () -> {osoba.setIme("");});
        assertEquals("Ime mora imati izmedju 2 i 50 znakova.", ex1.getMessage());

        Exception ex2 = assertThrows(IllegalArgumentException.class , () -> {osoba.setIme("L");});
        assertEquals("Ime mora imati izmedju 2 i 50 znakova.", ex2.getMessage());

        String string  = new String("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        Exception ex3 = assertThrows(IllegalArgumentException.class , () -> {osoba.setIme(string);});
        assertEquals("Ime mora imati izmedju 2 i 50 znakova.", ex3.getMessage());
    }

    @org.junit.jupiter.api.Test
    public void DuzinaMaticni() {
        Date datumRodjenja = new Date(2003, 6, 16);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Osoba(1, "Lamija", "Dzenetic", "Jablanica 22", datumRodjenja, "221021", Uloga.STUDENT );
        });
        assertEquals("Maticni broj mora imati tacno 13 karaktera!", ex.getMessage());
    }

    @org.junit.jupiter.api.Test
    public void PodudarnostMaticni() {
        Date datumRodjenja = new Date(2003, 6, 16);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Osoba(1, "Lamija", "Dzenetic", "Jablanica 22", datumRodjenja, "2210212210212", Uloga.STUDENT );
        });
        assertEquals("Maticni broj se ne poklapa sa datumom rodjenja!", ex.getMessage());
    }
    @org.junit.jupiter.api.Test
    public void NeispravnaPutanja() {
        String neispravnaPutanja = "datoteka.txt";
        assertThrows(IOException.class, () -> {
            Osoba.ucitajOsobeIzTxtDatoteke(neispravnaPutanja);
        });
    }



}