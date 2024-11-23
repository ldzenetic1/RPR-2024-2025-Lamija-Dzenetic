package Klase;

import java.util.Date;
public class Ljubimac extends Objekat {
    protected String ime;
    protected Date datumRodjenja;
    protected String stanjeZdravlja;

    public Ljubimac(String ime, Date datumRodjenja, String stanjeZdravlja) {
        this.ime = ime;
        this.datumRodjenja = datumRodjenja;
        this.stanjeZdravlja = stanjeZdravlja;
    }

    @Override
    public String PrikaziInformacije() {
        return "Klase.Ljubimac: " + ime;
    }
}