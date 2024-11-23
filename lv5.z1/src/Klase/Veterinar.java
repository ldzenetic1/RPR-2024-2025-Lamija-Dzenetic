package Klase;
import java.util.List;
import java.util.ArrayList;


public class Veterinar extends Objekat {
    private String ime;
    private Specijalizacija specijalizacija;
    private List<Ljubimac> pregledi;

    public Veterinar(String ime, Specijalizacija specijalizacija) {
        this.ime = ime;
        this.specijalizacija = specijalizacija;
        this.pregledi = new ArrayList<>();
    }

    public void PregledajLjubimca(Ljubimac ljubimac) throws ValidacijaVrsteException {
        if (specijalizacija == Specijalizacija.Psi && ljubimac instanceof Pas) {
            pregledi.add(ljubimac);
        } else if (specijalizacija == Specijalizacija.Mačke && ljubimac instanceof Macka) {
            pregledi.add(ljubimac);
        } else {
            throw new ValidacijaVrsteException("Nedozvoljena vrsta ljubimca za ovog veterinara.");
        }
    }

    public List<Ljubimac> getPregledi() {
        return pregledi;
    }

    @Override
    public String PrikaziInformacije() {
        return "Klase.Veterinar: " + ime;
    }
}