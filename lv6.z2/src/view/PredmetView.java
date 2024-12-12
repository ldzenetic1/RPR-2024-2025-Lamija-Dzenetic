package view;

public class PredmetView {
    private String ulazniTekst;
    private String poruka;

    public PredmetView(String ulazniTekst, String poruka) {
        this.ulazniTekst = ulazniTekst;
        this.poruka = poruka;
    }

    public String getUlazniTekst() {
        return ulazniTekst;
    }

    public void setUlazniTekst(String ulazniTekst) {
        this.ulazniTekst = ulazniTekst;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
}