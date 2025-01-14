import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main  {

    private static final int VEL_KOLEKCIJE = 100_000_000;
    private static final int BROJ_NITI = 16;

    public static void main(String[] args) {
        int[] kolekcija = new int[VEL_KOLEKCIJE];
        Random random = new Random();
        for (int i = 0; i < VEL_KOLEKCIJE; i++) {
           kolekcija[i] = random.nextInt(VEL_KOLEKCIJE);
        }
        int element = kolekcija[random.nextInt(VEL_KOLEKCIJE)];
        System.out.println("Traženi broj: " + element);
        paralelnaPretraga(kolekcija, element);
    }

    public static void paralelnaPretraga(int[] kolekcija, int element) {
        ExecutorService executorService = Executors.newFixedThreadPool(BROJ_NITI);
        int partSize = kolekcija.length / BROJ_NITI;
        for (int i = 0; i < BROJ_NITI; i++) {
            int pocetak = i * partSize;
            int kraj = (i == BROJ_NITI - 1) ? kolekcija.length : (i + 1) * partSize;
            executorService.submit(new Pretraga(kolekcija, element, pocetak, kraj));
        }
        executorService.shutdown();
    }

    static class Pretraga implements Runnable {
        private final int[] kolekcija;
        private final int element;
        private final int pocetak;
        private final int kraj;

        public Pretraga(int[] collection, int target, int start, int end) {
            this.kolekcija = collection;
            this.element = target;
            this.pocetak = start;
            this.kraj = end;
        }
        @Override
        public void run() {
            for (int i = pocetak; i < kraj; i++) {
                if (kolekcija[i] == element) {
                    System.out.println("Broj pronađen u opsegu " + pocetak + "-" + kraj + " na indeksu " + i);
                    System.exit(0);
                }
            }
        }
    }
}
