import java.util.List;

class Voce extends PrehrambeniProizvod {
    public Voce(String latinskiNaziv, String zemljaPorijekla, List<Double> nutritivneVrijednosti) {
        super(latinskiNaziv, zemljaPorijekla, nutritivneVrijednosti);
    }

    @Override
    public boolean zdravlje(double koeficijentZdravlja) {
        return dajBrojKalorija() < 50 && koeficijentZdravlja > 0.75;
    }
}