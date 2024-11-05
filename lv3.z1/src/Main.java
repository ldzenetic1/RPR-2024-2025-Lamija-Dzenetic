import java.util.ArrayList;
import java.util.List;

interface Nutritivan{
    int dajBrojKalorija();
    boolean zdravlje(Double koeficijentZdravlja);
}


abstract class Proizvod implements Nutritivan{
    protected String latinskiNaziv;
    protected String zemljaPorijekla;
    protected List<Double> nutritivneVrijednosti;

    public Proizvod(String latinskiNaziv, String zemljaPorijekla, List<Double> nutritivneVrijednosti){
        this.latinskiNaziv = latinskiNaziv;
        this.zemljaPorijekla = zemljaPorijekla;
        this.nutritivneVrijednosti = nutritivneVrijednosti;
    }
    @Override
    public int dajBrojKalorija() {
        return nutritivneVrijednosti.stream().mapToInt(Integer::intValue).sum();
    }
}
class Voce extends Proizvod{
    public Voce(String latinskiNaziv, String zemljaPorijekla, List<Double> nutritivneVrijednosti){
        super(latinskiNaziv, zemljaPorijekla, nutritivneVrijednosti);
    }
   /* @Override
    public int dajBrojKalorija() {
        return 0;
    }*/
    @Override
    public boolean zdravlje(Double koeficijentZdravlja){
        int brojKalorija = dajBrojKalorija();
        return brojKalorija < 50 && koeficijentZdravlja > 0.75;
    }
}

class Povrce extends Proizvod{
    public Povrce(String latinskiNaziv, String zemljaPorijekla, List<Double> nutritivneVrijednosti){
        super(latinskiNaziv, zemljaPorijekla, nutritivneVrijednosti);
    }
    @Override
    public boolean zdravlje(Double koeficijentZdravlja){
        int brojKalorija = dajBrojKalorija();
        return brojKalorija < 100 && (koeficijentZdravlja < 0.7 && koeficijentZdravlja < 0.5);
    }
}