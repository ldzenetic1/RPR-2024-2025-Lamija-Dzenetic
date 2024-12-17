package com.example.lv7.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;


class OsobaModelTest {
    private OsobaModel osobaModel;
    @BeforeEach
    void setUp() {
        osobaModel = new OsobaModel();
        osobaModel.napuni();
    }
    @Test
    void azurirajOsobu() {
    }

    @Test
    void napuniIspravnoPopunjavaListuOsobe() {
        assertEquals(2, osobaModel.getOsobe().size(), "Lista treba sadržavati 2 osobe nakon punjenja");

        Osoba prvaOsoba = osobaModel.getOsobe().get(0);
        assertEquals("Neko", prvaOsoba.getIme());
        assertEquals("Nekic", prvaOsoba.getPrezime());
        assertEquals(Uloga.STUDENT, prvaOsoba.getUloga());

        Osoba drugaOsoba = osobaModel.getOsobe().get(1);
        assertEquals("Neko 2", drugaOsoba.getIme());
        assertEquals("Nekic 2", drugaOsoba.getPrezime());
        assertEquals(Uloga.NASTAVNO_OSOBLJE, drugaOsoba.getUloga());
    }
    @Test
    void azurirajOsobuUspjesnoAzuriraPodatkeKadaIdPostoji() {
        String rezultat = osobaModel.azurirajOsobu(1, "NovoIme", null, "Nova Adresa", null, null, null);
        assertEquals("Osoba je uspjesno azurirana!", rezultat);

        Osoba azuriranaOsoba = osobaModel.dajOsobuPoId(1);
        assertEquals("NovoIme", azuriranaOsoba.getIme(), "Ime treba biti ažurirano");
        assertEquals("Neka adresa", azuriranaOsoba.getAdresa(), "Adresa se ne treba promijeniti jer je null");
    }

    @Test
    void azurirajOsobuVracaPorukuKadaIdNePostoji() {
        String rezultat = osobaModel.azurirajOsobu(999, "NovoIme", "NovoPrezime", "Nova Adresa", new Date(), "123456789", Uloga.STUDENT);
        assertEquals("Osoba nije pronadjena!", rezultat);
    }

    @Test
    void azurirajOsobuAzuriraSamoOdredjenaPolja() {
        String rezultat = osobaModel.azurirajOsobu(2, null, "NovoPrezime", null, null, null, Uloga.STUDENT);
        assertEquals("Osoba je uspjesno azurirana!", rezultat);

        Osoba azuriranaOsoba = osobaModel.dajOsobuPoId(2);
        assertEquals("NovoPrezime", azuriranaOsoba.getPrezime(), "Prezime treba biti ažurirano");
        assertEquals(Uloga.STUDENT, azuriranaOsoba.getUloga(), "Uloga treba biti ažurirana");
        assertEquals("Neko 2", azuriranaOsoba.getIme(), "Ime ne treba biti promijenjeno jer je null");
    }

}