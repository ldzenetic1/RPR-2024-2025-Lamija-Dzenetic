import java.util.List;

interface Nutritivan {
    int dajBrojKalorija();
    boolean zdravlje(double koeficijentZdravlja);
}

abstract class Proizvod implements Nutritivan {
    protected String latinskiNaziv;
    protected String zemljaPorijekla;
    protected List<Integer> nutritivneVrijednosti;

    public Proizvod(String latinskiNaziv, String zemljaPorijekla, List<Integer> nutritivneVrijednosti) {
        this.latinskiNaziv = latinskiNaziv;
        this.zemljaPorijekla = zemljaPorijekla;
        this.nutritivneVrijednosti = nutritivneVrijednosti;
    }

    @Override
    public int dajBrojKalorija() {
        return nutritivneVrijednosti.stream().mapToInt(Integer::intValue).sum();
    }
}

class Voce extends Proizvod {
    public Voce(String latinskiNaziv, String zemljaPorijekla, List<Integer> nutritivneVrijednosti) {
        super(latinskiNaziv, zemljaPorijekla, nutritivneVrijednosti);
    }

    @Override
    public boolean zdravlje(double koeficijentZdravlja) {
        int brojKalorija = dajBrojKalorija();
        return brojKalorija < 50 && koeficijentZdravlja > 0.75;
    }
}

class Povrce extends Proizvod {
    public Povrce(String latinskiNaziv, String zemljaPorijekla, List<Integer> nutritivneVrijednosti) {
        super(latinskiNaziv, zemljaPorijekla, nutritivneVrijednosti);
    }

    @Override
    public boolean zdravlje(double koeficijentZdravlja) {
        int brojKalorija = dajBrojKalorija();
        return brojKalorija < 100 && koeficijentZdravlja >= 0.5 && koeficijentZdravlja <= 0.7;
    }
}

enum VrstaMeso {
    PILETINA, PURETINA, TELETINA, JANJETINA
}

class Meso extends Proizvod {
    private VrstaMeso vrsta;

    public Meso(VrstaMeso vrsta, String zemljaPorijekla, List<Integer> nutritivneVrijednosti) {
        super(vrsta.name(), zemljaPorijekla, nutritivneVrijednosti);
        this.vrsta = vrsta;
    }

    @Override
    public int dajBrojKalorija() {
        return (int) (super.dajBrojKalorija() * 1.2);
    }

    @Override
    public boolean zdravlje(double koeficijentZdravlja) {
        return koeficijentZdravlja > 0.95;
    }
}

class Prodavac {
    private String ime;
    private String prezime;
    private int brojStand;
    private String idBrojLicence;

    public Prodavac(String ime, String prezime, int brojStand, String idBrojLicence) {
        this.ime = ime;
        this.prezime = prezime;
        this.brojStand = brojStand;
        this.idBrojLicence = idBrojLicence;
    }

    public boolean zdravlje() {
        return idBrojLicence.endsWith("01");
    }
}

public class Main {
    public static void main(String[] args) {
        List<Integer> nutritivneVrijednostiVoce = List.of(10, 5, 8, 6);
        List<Integer> nutritivneVrijednostiPovrce = List.of(20, 15, 5);
        List<Integer> nutritivneVrijednostiMeso = List.of(50, 30, 40);

        Voce jabuka = new Voce("Malus domestica", "Hrvatska", nutritivneVrijednostiVoce);
        Povrce mrkva = new Povrce("Daucus carota", "Nizozemska", nutritivneVrijednostiPovrce);
        Meso piletina = new Meso(VrstaMeso.PILETINA, "Francuska", nutritivneVrijednostiMeso);

        Prodavac prodavac = new Prodavac("Ivan", "Ivić", 3, "A12301");

        System.out.println("Voće (Jabuka) - Ukupan broj kalorija: " + jabuka.dajBrojKalorija());
        System.out.println("Voće (Jabuka) - Zdravo: " + jabuka.zdravlje(0.8));

        System.out.println("Povrće (Mrkva) - Ukupan broj kalorija: " + mrkva.dajBrojKalorija());
        System.out.println("Povrće (Mrkva) - Zdravo: " + mrkva.zdravlje(0.6));

        System.out.println("Meso (Piletina) - Ukupan broj kalorija: " + piletina.dajBrojKalorija());
        System.out.println("Meso (Piletina) - Zdravo: " + piletina.zdravlje(0.96));

        System.out.println("Prodavač - Zdrav: " + prodavac.zdravlje());
    }
}
