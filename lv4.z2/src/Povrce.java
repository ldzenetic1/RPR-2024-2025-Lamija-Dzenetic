import java.util.List;

class Povrce extends PrehrambeniProizvod {
    public Povrce(String latinskiNaziv, String zemljaPorijekla, List<Double> nutritivneVrijednosti) {
        super(latinskiNaziv, zemljaPorijekla, nutritivneVrijednosti);
    }

    @Override
    public boolean zdravlje(double koeficijentZdravlja) {
        return dajBrojKalorija() < 100 && koeficijentZdravlja >= 0.5 && koeficijentZdravlja <= 0.7;
    }
}