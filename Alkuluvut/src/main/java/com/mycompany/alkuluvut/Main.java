package com.mycompany.alkuluvut;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        Alkuluvut lista = new Alkuluvut();

        System.out.println("Tervetuloa alkulukugeneraatti-sovellukseen!");
        while (true) {
            lista.tyhjenna();
            System.out.println("");
            System.out.println("Mitä haluat tehdä?");
            System.out.println("1. Testata, onko jokin tietty luku alkuluku? (vastaa \"1\") ");
            System.out.println("2. Tulostaa alkulukuja tiettyyn indeksiin asti? (vastaa \"2\") ");
            System.out.println("3. Tulostaa tietty lukumäärä alkulukuja? (vastaa \"3\")");
            System.out.println("");
            String komento = lukija.nextLine();

            if (komento.equals("1")) {
                System.out.println("");
                System.out.println("Syötä luku.");
                int haluttuLuku = Integer.parseInt(lukija.nextLine());
                System.out.println("");
                int i = 2;
                while (i <= haluttuLuku) {
                    if (lista.onkoLukuJaollinenEdellisilla(i)) {
                        lista.lisaaListalle(i);
                    }
                    i++;
                }
                if (lista.sisaltaa(haluttuLuku)) {
                    System.out.println("Luku " + haluttuLuku + " on alkuluku.");
                } else {
                    System.out.println("Luku " + haluttuLuku + " ei ole alkuluku.");
                }
                System.out.println("");
                System.out.println("Haluatko jatkaa sovelluksen käyttöä? (\"k\" -> kyllä, muuten ei.) ");
                String lopetetaanko = lukija.nextLine();
                System.out.println("");
                if (!lopetetaanko.equals("k")) {
                    System.out.println("Kiitos käynnistä!");
                    break;
                }

            } else if (komento.equals("2")) {
                System.out.println("");
                System.out.println("Mikä numero on yläraja? Jos et halua ylärajaa, syötä -1.");
                int ylaRaja = Integer.parseInt(lukija.nextLine());
                if (ylaRaja == -1) {
                    int i = 2;
                    while (true) {
                        if (lista.onkoLukuJaollinenEdellisilla(i)) {
                            lista.lisaaListalle(i);
                            System.out.println(i);

                        }
                        i++;
                    }
                }
                System.out.println("");

                System.out.println("Haluatko lisäksi tietää alkulukujen joukon koon? (\"k\" -> kyllä, muuten ei)");
                String kolmasKomento = lukija.nextLine();
                System.out.println("");

                int i = 2;
                while (i <= ylaRaja) {
                    if (lista.onkoLukuJaollinenEdellisilla(i)) {
                        lista.lisaaListalle(i);
                        System.out.println(i);

                    }
                    i++;
                }
                if (kolmasKomento.equals("k")) {
                    System.out.println("Alkulukuja, jotka ovat korkeintaan " + ylaRaja + ", on " + lista.koko() + " kpl.");
                }
                System.out.println("");
                System.out.println("Haluatko jatkaa sovelluksen käyttöä? (\"k\" -> kyllä, muuten ei.) ");
                String lopetetaanko = lukija.nextLine();
                System.out.println("");
                if (!lopetetaanko.equals("k")) {
                    System.out.println("Kiitos käynnistä!");
                    break;
                }
            } else if (komento.equals("3")) {
                System.out.println("");

                System.out.println("Kuinka monta alkulukua haluat tulostettavan?");
                int lukuMaara = Integer.parseInt(lukija.nextLine());

                System.out.println("");
                int i = 2;
                while (true) {
                    if (lista.koko() > lukuMaara) {
                        break;
                    }
                    if (lista.onkoLukuJaollinenEdellisilla(i)) {
                        lista.lisaaListalle(i);
                        System.out.println(i);
                    }
                    i++;
                }
                System.out.println("");
                System.out.println("Haluatko jatkaa sovelluksen käyttöä? (\"k\" -> kyllä, muuten ei.) ");
                String lopetetaanko = lukija.nextLine();
                System.out.println("");
                if (!lopetetaanko.equals("k")) {
                    System.out.println("Kiitos käynnistä!");
                    break;
                }

            }
        }

    }
}
