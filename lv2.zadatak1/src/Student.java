import Klase.Odsjek;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import Izuzeci.PremladStudentException;
import Izuzeci.StudentBuducnostException;
import Izuzeci.DijeljenjeSNulomException;



public class Student {
    String ime, prezime, brojIndeksa;
    Odsjek odsjek;
    Date datumRodjenja;
    Integer godinaStudija;
    List<Integer> ocjene;


    public String getIme() {
        return ime;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public void setOdsjek(Odsjek odsjek){
    this.odsjek = odsjek;
    }
    public Date getDatumRodjenja() {
        return datumRodjenja;
    }
    public Integer getGodinaStudija() {
        return godinaStudija;
    }
    public List<Integer> getOcjene() {
        return ocjene;
    }
    public void setOcjene(List<Integer> ocjene) {
        this.ocjene = ocjene;
    }
    public void setPrezime(String prezime){
        this.prezime = prezime;
    }
    public void setBrojIndeksa(String brojIndeksa){
        this.brojIndeksa = brojIndeksa;
    }
    public void setOdsjek(String odsjek){
        this.odsjek = Odsjek.valueOf(odsjek.toUpperCase());
    }
    public void setGodinaStudija(Integer godinaStudija) {
        if (this.odsjek != Odsjek.RS && godinaStudija > 0 && godinaStudija < 6)
            this.godinaStudija = godinaStudija;
        else if (this.odsjek == Odsjek.RS && godinaStudija > 0 && godinaStudija <= 2)
            this.godinaStudija = godinaStudija;
    }

    public Student(String ime, String prezime, Date datumRodjenja, String brojIndeksa,
                   Odsjek odsjek, Integer godinaStudija) throws Exception
    {
        setIme(ime);
        setPrezime(prezime);
        setDatumRodjenja(datumRodjenja);
        setBrojIndeksa(brojIndeksa);
        setOdsjek(odsjek);
        setGodinaStudija(godinaStudija);
        setOcjene(new ArrayList<Integer>());
    }
    public class StudentBuducnostException extends Exception
    {
        public StudentBuducnostException (String message)
        {
            super(message);
        }
    }
    public class PremladStudentException extends Exception
    {
        public PremladStudentException (String message)
        {
            super(message);
        }
    }
    public void setDatumRodjenja(Date datumRodjenja) throws StudentBuducnostException, PremladStudentException {
        Date today = new Date();
        if (datumRodjenja.compareTo(today) >= 0)
            throw new StudentBuducnostException("Datum rođenja ne može biti u budućnosti!");


        else if (today.getYear() - datumRodjenja.getYear() < 16)
            throw new PremladStudentException("Student ne može biti mlađi od 16 godina!");


        this.datumRodjenja = datumRodjenja;
    }
    public Double Prosjek() throws DijeljenjeSNulomException {
        if (ocjene == null || ocjene.size()== 0) {
            throw new DijeljenjeSNulomException("Student nema nijednu unesenu ocjenu!");
        }
        int suma = 0;
        for (int ocjena : ocjene) {
            suma += ocjena;
        }
        return (double) suma / ocjene.size();
    }
    public String toString() {
        try {
            return "Student: " + ime + " " + prezime + ", broj indeksa: " + brojIndeksa + ", prosjek: " + Prosjek();
        } catch (DijeljenjeSNulomException e) {
            System.out.println(e.getMessage());
            System.out.println("Nije moguće ispisati podatke");
            return "";
        }
    }
}

