//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;
import static java.lang.Math.sqrt;
public class Main {

    public static boolean DaLiJeProst(int n) {
        if (n < 2) return false;
        for (int i = 2; i < sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        int broj;
        do{
            System.out.printf("Unesite broj n: ");
            Scanner in = new Scanner(System.in);
            broj = in.nextInt();
            if(broj > 500) System.out.println("Uneseni broj je prevelik!");
            if(broj < 2) {
                System.out.println("Nije moguce izvrsiti izracunavanje prostih brojeva");
                return;
            }
        }while(broj > 500);
        System.out.printf("Prosti brojevi: ");
        for(int i = 2; i < 2 * broj; i++){
            if(DaLiJeProst(i)){
                System.out.printf(String.valueOf(i));
                System.out.printf(" ");
            }
        }
    }
}