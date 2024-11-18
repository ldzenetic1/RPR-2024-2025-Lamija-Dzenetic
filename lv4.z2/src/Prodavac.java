class Prodavac implements Zdravlje {
    private String ime;
    private String prezime;
    private int brojStanda;
    private String idLicence;

    public Prodavac(String ime, String prezime, int brojStanda, String idLicence) {
        this.ime = ime;
        this.prezime = prezime;
        this.brojStanda = brojStanda;
        this.idLicence = idLicence;
    }

    @Override
    public boolean zdravlje(double koeficijentZdravlja) {
        return idLicence.endsWith("01");
    }
}
