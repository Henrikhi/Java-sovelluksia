package defaultpackage.poistakuusipalloa;

import java.util.Scanner;

public class NewMain {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        Ruudukko ruudukko = new Ruudukko();
//        ruudukko.tulosta();

        ruudukko.tarkistaJaTulostaKaikki();

//        for (int i = 0; i < 100000; i++) {
//            ruudukko.randomPoistot();
//            if (ruudukko.toimivaRatkaisu()) {
//                ruudukko.tulosta();
//            }
//        }
    }

}
