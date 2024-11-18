import java.util.List;

class Meso extends PrehrambeniProizvod {
    private String vrsta;

    public Meso(String vrsta, String zemljaPorijekla, List<Double> nutritivneVrijednosti) {
        super(vrsta, zemljaPorijekla, nutritivneVrijednosti);
        this.vrsta = vrsta;
    }

    @Override
    public double dajBrojKalorija() {
        return super.dajBrojKalorija() * 1.2; // Skaliranje na 120%
    }

    @Override
    public boolean zdravlje(double koeficijentZdravlja) {
        return koeficijentZdravlja > 0.95;
    }
}