package henrik.nkuningattarenongelma;

public class KuningatarMain {

    public static void main(String[] args) {

        int n = 8;
        int tarkistuksia = 0;
        int ratkaisuja = 0;

        int[][] ruudukko = new int[n][n];
        boolean laitettu = false;
        int rivit = 0;
        int sarakkeet = 0;
        int peruutuksia = 0;

//        ruudukko[0][2] = 1;
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                tarkista(ruudukko, i, j);
//            }
//
//        }
//        System.out.println("testi");
        for (rivit = 0; rivit < n; rivit++) {
//            System.out.println("ollaan rivillä " + rivit);
            laitettu = false;
            for (sarakkeet = 0; sarakkeet < n; sarakkeet++) {
                if (ruudukko[rivit][sarakkeet] == 0) {
                    if (tarkista(ruudukko, rivit, sarakkeet)) {
                        ruudukko[rivit][sarakkeet] = 1;
                        laitettu = true;
//                        tulosta(ruudukko, n);
                        break;
                    }
                }
            }
            if (!laitettu) {
//                System.out.println("ennen peruutusta rivi on " + rivit);
//                tulosta(ruudukko, n);
//                System.out.println("äskeinen oli ennen peruutusta");
                peruutuksia = peruuta(ruudukko, rivit, 0);
//                System.out.println(peruutuksia);
                if (peruutuksia > Integer.MAX_VALUE / 2) {
//                    System.out.println("nyt on iso luku");
                    break;
                }
//                System.out.println("peruutuksia: " + peruutuksia);
                rivit -= peruutuksia;
//                System.out.println("peruutuksen jälkeen rivi on " + rivit);
//                tulosta(ruudukko, n);
//                System.out.println("äskeinen oli peruutuksen jälkeen");
//                tarkistuksia++;
//                System.out.println("tarkistuksia : " + tarkistuksia);
            }

            if (rivit == n - 1) {
                tulosta(ruudukko, n);
                ratkaisuja++;
                ruudukko[rivit][sarakkeet] = 0;
                peruutuksia = peruuta(ruudukko, rivit, 0);
//                System.out.println(peruutuksia);
                if (peruutuksia > Integer.MAX_VALUE / 2) {
//                    System.out.println("nyt on iso luku");
                    break;
                }
                rivit -= peruutuksia;
//                while (true) {
//                    if (sarakkeet == n - 1) {
//                        peruutuksia = peruuta(ruudukko, rivit, 0);
//                        rivit -= peruutuksia;
//                        break;
//                    }
//                    if (tarkista(ruudukko, rivit, sarakkeet + 1)) {
//                        ruudukko[rivit][sarakkeet + 1] = 1;
//                        break;
//                    }
//
//                }
            }

        }

        System.out.println("ratkaisuja: " + ratkaisuja);
    }

    private static boolean tarkistaSarake(int[][] ruudukko, int rivi, int sarake) {
        if (rivi == 0) {
            return true;
        } else {
            for (int i = rivi; i >= 0; i--) {
                if (ruudukko[i][sarake] == 1) {
//                    System.out.println("Ei käy");
                    return false;
                }
            }
            return true;
        }
    }

    private static boolean tarkistaDiagonaali(int[][] ruudukko, int rivi, int sarake) {
//        System.out.println("tarkistetaan diagonaali");
        if (rivi == 0) {
            return true;
        } else {
            for (int i = rivi - 1, a = 1; i >= 0 && (sarake - a) >= 0; i--, a++) {
//                System.out.println("tarkistetaan kohtaa " + i + (sarake - a) + " vasemmalle");
                if (ruudukko[i][sarake - a] == 1) {
//                    System.out.println("ei käy");
                    return false;
                }
            }
            for (int i = rivi - 1, a = 1; i >= 0 && (sarake + a) < ruudukko.length; i--, a++) {
//                System.out.println("tarkistetaan kohtaa " + i + (sarake + a) + " oikealle");
                if (ruudukko[i][sarake + a] == 1) {
//                    System.out.println("ei käy");
                    return false;
                }
            }
//            System.out.println("käy");
            return true;

        }
    }

    private static boolean tarkista(int[][] ruudukko, int rivi, int sarake) {
        if (tarkistaSarake(ruudukko, rivi, sarake) && tarkistaDiagonaali(ruudukko, rivi, sarake)) {
//            System.out.println(rivi + "," + sarake + " on vapaa.");
            return true;
        } else {
//            System.out.println(rivi + "," + sarake + " ei ole vapaa.");
            return false;
        }
    }

    private static int peruuta(int[][] ruudukko, int rivit, int peruutuksia) {
        rivit--;
        for (int i = 0; i < ruudukko.length; i++) {
            if (ruudukko[rivit][i] == 1) {
                ruudukko[rivit][i] = 0;
                if (i == ruudukko.length - 1) {
                    if (rivit == 0) {
                        System.out.println("Nyt on kaikki käyty!");
                        return Integer.MAX_VALUE;
                    }
                    return peruuta(ruudukko, rivit, peruutuksia + 1);
                } else {
                    for (int j = i + 1; j < ruudukko.length; j++) {
                        if (tarkista(ruudukko, rivit, j)) {
                            ruudukko[rivit][j] = 1;
                            return peruutuksia + 1;
                        }
                    }
                    return peruuta(ruudukko, rivit, peruutuksia + 1);
                }
            }

        }
        System.out.println("jotain meni pieleen");
        return -1;
    }

    private static void tulosta(int[][] ruudukko, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("");
            for (int j = 0; j < n; j++) {
                System.out.print(ruudukko[i][j] + " ");
            }

        }
        System.out.println("");
    }

}
