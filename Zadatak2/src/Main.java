//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Double Plus(Double broj1, Double broj2){
        return broj1 + broj2;
    }
    public static Double Podijeljeno(Double broj1, Double broj2) throws Exception{
        if(broj2 == 0) throw new Exception("Nije dozvoljeno dijeljenje s nulom");
        return broj1 / broj2;
    }
    public static void main(String[] args) {
        Scanner ulaz = new Scanner(System.in);
        System.out.print("Unesite operaciju ('plus' za sabiranje, 'podijeljeno' za dijeljenje): ");
        String operacija;
        operacija = ulaz.nextLine();
        double n;
        System.out.print("Unesite brojeve: ");
        List<Double> brojevi = new ArrayList<Double>();
        do{
            n = ulaz.nextDouble();
            if(n == -400) break;
            brojevi.add(n);
        }while(n != -400);
        double rezultat = brojevi.get(0);
        if(operacija.equals("plus")){
            for(int i = 1; i < brojevi.size(); i++){
                rezultat = Plus(rezultat, brojevi.get(i));
            }
        }
        else {
            for (int i = 1; i < brojevi.size(); i++) {
                try {
                    rezultat = Podijeljeno(rezultat, brojevi.get(i));
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    return;
                }
            }
        }
        Double kon_rez = (Math.round(rezultat * 100)) / 100.00;
        System.out.println("Konacni rezultat: " + kon_rez);
    }

}