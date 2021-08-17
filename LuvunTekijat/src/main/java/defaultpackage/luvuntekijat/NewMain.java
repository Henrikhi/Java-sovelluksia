package defaultpackage.luvuntekijat;

import java.util.ArrayList;
import java.util.Scanner;

public class NewMain {

    public static void main(String[] args) {
        System.out.println("Anna luku.");
        Scanner lukija = new Scanner(System.in);

        int luku = lukija.nextInt();

        int indeksi = 2;

        ArrayList<Integer> tekijat = new ArrayList<>();

        while (indeksi <= luku) {
            if (luku % indeksi == 0) {
//                System.out.println("luku " + luku + " on jaollinen luvulla " + indeksi);
                tekijat.add(indeksi);
            }
            indeksi++;
        }

        System.out.println("");
        System.out.println("Luvun " + luku + " tekijät ovat ");
        for (Integer tekija : tekijat) {
            System.out.println(tekija);
        }

        System.out.println("");
        
        ArrayList<Integer> alkulukutekijat = new ArrayList<>();

        for (Integer tekija : tekijat) {
            indeksi = 2;
            boolean onAlkuluku = true;

            while (indeksi < tekija) {
                if (tekija % indeksi == 0) {
//                    System.out.println("Tekijä " + tekija + " on jaollinen luvulla " + indeksi);
                    onAlkuluku = false;
                    break;
                }
                indeksi++;
            }

            if (onAlkuluku == true) {
                alkulukutekijat.add(tekija);
            }
        }

        System.out.println("Luvun " + luku + " alkulukutekijät ovat ");
        for (Integer alkuluku : alkulukutekijat) {
            System.out.println(alkuluku);
        }
        System.out.println("");

        ArrayList<Integer> alkulukuhajotelma = new ArrayList<>();
        for (Integer alkulukutekija : alkulukutekijat) {
            int uusiluku = luku;
            while (true) {
                alkulukuhajotelma.add(alkulukutekija);

                uusiluku = uusiluku / alkulukutekija;
                if (uusiluku % alkulukutekija != 0) {
                    break;
                }
            }
        }

        System.out.println("Luvun " + luku + " alkulukuhajotelma on ");

        if (alkulukuhajotelma.size() == 0) {
            System.out.println("Nyt kävi jotain höpsösti, näin ei pitäisi käydä.");
        } else if (alkulukuhajotelma.size() == 1) {
            System.out.println(alkulukuhajotelma.get(0));
        } else if (alkulukuhajotelma.size() == 2) {
            System.out.println(alkulukuhajotelma.get(0) + "*" + alkulukuhajotelma.get(1));
        } else {
            System.out.print(alkulukuhajotelma.get(0));
            for (int i = 1; i < alkulukuhajotelma.size(); i++) {
                System.out.print("*");
                System.out.print(alkulukuhajotelma.get(i));
            }
        }

    }

}
