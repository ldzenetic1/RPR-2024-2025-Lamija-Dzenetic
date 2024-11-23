package Klase;
import java.util.Date;

public class Pas extends Ljubimac {
    private VrstaPsa vrstaPsa;

    public Pas(String ime, Date datumRodjenja, String stanjeZdravlja, VrstaPsa vrstaPsa) {
        super(ime, datumRodjenja, stanjeZdravlja);
        this.vrstaPsa = vrstaPsa;
    }

    @Override
    public String PrikaziInformacije() {
        return "Klase.Pas: " + vrstaPsa;
    }
}