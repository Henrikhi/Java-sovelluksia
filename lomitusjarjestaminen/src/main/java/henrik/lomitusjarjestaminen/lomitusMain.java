package henrik.lomitusjarjestaminen;

import java.util.Arrays;
import java.util.Random;

public class lomitusMain {

    public static void main(String[] args) {
        Random randomi = new Random();
        LomitusJarjestaminen lj = new LomitusJarjestaminen();

        int[] testiListaIso = new int[100000];
        int[] testiListaPikku = new int[]{5, 12, 4, 3, 5, 7, 7, 5, 4, 3, 5, 3, 7, 8, 6, 7, 2, 9, 15, 63};

        for (int i = 0; i < testiListaIso.length; i++) {
            testiListaIso[i] = randomi.nextInt(10000);
        }

        lj.jarjesta(testiListaPikku);
        for (int i = 0; i < testiListaPikku.length; i++) {
            System.out.println(testiListaPikku[i]);
        }

//        long aikaalussa = System.currentTimeMillis();
//        testiListaIso = lj.jarjesta(testiListaIso);
//        Arrays.sort(testiListaIso);

//        System.out.println("aikaa meni " + (System.currentTimeMillis() - aikaalussa) + " millisekuntia");
    }
}
