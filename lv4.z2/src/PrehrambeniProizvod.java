import java.util.List;

abstract class PrehrambeniProizvod {
    private String latinskiNaziv;
    private String zemljaPorijekla;
    private List<Double> nutritivneVrijednosti;

    public PrehrambeniProizvod(String latinskiNaziv, String zemljaPorijekla, List<Double> nutritivneVrijednosti) {
        this.latinskiNaziv = latinskiNaziv;
        this.zemljaPorijekla = zemljaPorijekla;
        this.nutritivneVrijednosti = nutritivneVrijednosti;
    }

    public double dajBrojKalorija() {
        return nutritivneVrijednosti.stream().mapToDouble(Double::doubleValue).sum();
    }

    public abstract boolean zdravlje(double koeficijentZdravlja);
}