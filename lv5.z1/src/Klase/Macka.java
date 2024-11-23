package Klase;

import java.util.Date;
public class Macka extends Ljubimac {
    private VrstaMacke vrstaMacke;

    public Macka(String ime, Date datumRodjenja, String stanjeZdravlja, VrstaMacke vrstaMacke) {
        super(ime, datumRodjenja, stanjeZdravlja);
        this.vrstaMacke = vrstaMacke;
    }

    @Override
    public String PrikaziInformacije() {
        return "Klase.Mačka: " + vrstaMacke;
    }
}