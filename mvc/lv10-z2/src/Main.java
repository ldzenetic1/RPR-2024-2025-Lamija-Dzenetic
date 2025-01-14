import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    private static final int VEL_KOLEKCIJE = 1000;
    private static final int BROJ_NITI = 50;
    private static final AtomicBoolean zamijenjeni = new AtomicBoolean(false);

    public static void main(String[] args) {
        int[] kolekcija = new int[VEL_KOLEKCIJE];
        Random random = new Random();
        for (int i = 0; i < VEL_KOLEKCIJE; i++) {
            kolekcija[i] = random.nextInt(1000);
        }
        System.out.println("Prije sortiranja:");
        ispisiKolekciju(kolekcija);
        paralelnoSortiranje(kolekcija);
        System.out.println("Poslije sortiranja:");
        ispisiKolekciju(kolekcija);
    }

    public static void paralelnoSortiranje(int[] collection) {
        Thread[] niti = new Thread[BROJ_NITI];
        boolean isSorted = false;
        while (!isSorted) {
            zamijenjeni.set(false);
            for (int t = 0; t < BROJ_NITI; t++) {
                int threadId = t; // ID niti
                niti[t] = new Thread(() -> {
                    for (int i = threadId; i < collection.length - 1; i += BROJ_NITI) {
                        if (collection[i] > collection[i + 1]) {
                            // Zamena elemenata
                            int temp = collection[i];
                            collection[i] = collection[i + 1];
                            collection[i + 1] = temp;
                            zamijenjeni.set(true);
                        }
                    }
                });
                niti[t].start();
            }
            for (Thread nit : niti) {
                try {
                    nit.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isSorted = !zamijenjeni.get();
        }
    }

    public static void ispisiKolekciju(int[] kolekcija) {
        for (int el : kolekcija) {
            System.out.print(el + " ");
        }
        System.out.println();
    }
}
