
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Sovellus {

    private static void v() throws InterruptedException {
        //Thread.sleep(2000);    <- suositus, älä muokkaa tätä.
        //Sen sijaan voit muokata alla olevaa. Jos et halua viivettä, aseta 0.
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner lukija = new Scanner(System.in);
        Random randomi = new Random();
        Vihollinen vanhavihollinen = null;
        HashMap<String, Integer> voitot = new HashMap<>();

        //################################################################
        //################################################################
        //vihollistiedot
        ArrayList<Vihollinen> viholliset = new ArrayList<>();

        //                                  NIMI  MAXHP   DAMAGE   TARKKUUS%  KRIT%
        Vihollinen luuranko = new Vihollinen("luuranko", 60, 40, 90, 10);
        Vihollinen tonttu = new Vihollinen("kääpiö", 40, 30, 100, 5);
        Vihollinen kyklooppi = new Vihollinen("kyklooppi", 140, 80, 40, 30);
        Vihollinen zombie = new Vihollinen("zombie", 100, 60, 70, 10);
        Vihollinen velho = new Vihollinen("velho", 70, 70, 60, 40);
        Vihollinen kentauri = new Vihollinen("kentauri", 110, 60, 50, 20);
        //Vihollinen klooni päävastukseksi??

        //muista addata vihollinen listalle
        viholliset.add(luuranko);
        viholliset.add(tonttu);
        viholliset.add(kyklooppi);
        viholliset.add(zombie);
        viholliset.add(velho);
        viholliset.add(kentauri);

        //vihollistiedot
        //################################################################
        //################################################################
        //taistelijatiedot
        int maxHp = 100; //100
        int hp = maxHp;
        int maxIskuvoima = 70; //70
        int osumatarkkuus = 70; //70 prosenttia
        int kriittinenIskuTN = 10; //10 prosenttia

        int hpPotionMaara = 3; //3
        int hpPotionTeho = 50; //50
        int hpPotionTodennakoisyys = 50; //50 prosenttia
        int pakenemisTodennakoisyys = 90; //90 prosenttia

        //taistelijatiedot
        //##############################################################
        //##############################################################
        System.out.println("\n\n\t //Hirvos-production// \n\n");
        
        System.out.println("\t#############################");
        System.out.println("\t### TERVETULOA AREENALLE! ###");
        System.out.println("\t#############################");
        System.out.println("\n\n\n");
        v();

        uusitaistelu:
        while (hp > 0) {
            Vihollinen vihollinen = viholliset.get(randomi.nextInt(viholliset.size()));
            while (vihollinen.equals(vanhavihollinen)) {
                vihollinen = viholliset.get(randomi.nextInt(viholliset.size()));
            }
            vanhavihollinen = vihollinen;
            System.out.println("\tSeuraava vastustajasi on " + vihollinen.getNimi() + ".");
            v();
            boolean taisteluJatkuu = true;

            while (taisteluJatkuu) {
                System.out.println("");
                v();
                System.out.println("\tSinun HP: " + hp);
                System.out.println("\tSinun iskuvoima: " + maxIskuvoima);
                System.out.println("\tSinulla on HP-juomia jäljellä: " + hpPotionMaara);
                System.out.println("");
                v();
                v();
                System.out.println("\t" + vihollinen.getNimi() + " HP: " + vihollinen.getHp());
                System.out.println("\t" + vihollinen.getNimi() + " iskuvoima: " + vihollinen.getMaxiskuvoima());
                System.out.println("");
                v();
                System.out.println("\tMitä haluat tehdä?");
                v();
                System.out.println("");
                System.out.println("\t1. Hyökkää!");
                System.out.println("\t2. Juo HP-juoma.");
                System.out.println("\t3. Pakene!");
                System.out.print("\t>  ");

                String komento = lukija.nextLine();
                System.out.println("");

                if (komento.equals("1")) {
                    v();
                    System.out.println("\tHyökkäät viholliseen...");
                    v();
                    if (randomi.nextInt(100) < osumatarkkuus) {
                        if (randomi.nextInt(100) < kriittinenIskuTN) {
                            int KriittinenIsku = (int) 1.5 * (maxIskuvoima / 4 + randomi.nextInt((int) (0.75 * maxIskuvoima)));
                            System.out.println("\t... ja osuit kriittiseen kohtaan! " + vihollinen.getNimi() + " kärsi "
                                    + KriittinenIsku + " vahinkoa.");
                            v();
                            vihollinen.setHp(vihollinen.getHp() - KriittinenIsku);
                        } else {
                            int isku = maxIskuvoima / 4 + randomi.nextInt((int) (0.75 * maxIskuvoima));
                            System.out.println("\t... ja osuit! " + vihollinen.getNimi() + " kärsi "
                                    + isku + " vahinkoa.");
                            v();
                            vihollinen.setHp(vihollinen.getHp() - isku);
                        }
                        if (vihollinen.getHp() < 1) {
                            v();
                            System.out.println("\n\t" + vihollinen.getNimi() + " ei selvinnyt iskustasi.\n");
                            if (voitot.containsKey(vihollinen.getNimi())) {
                                voitot.put(vihollinen.getNimi(), voitot.get(vihollinen.getNimi()) + 1);
                            } else {
                                voitot.put(vihollinen.getNimi(), 1);
                            }
                            vihollinen.setHp(vihollinen.getMaxHp());
                            v();
                            if (randomi.nextInt(100) < hpPotionTodennakoisyys) {
                                System.out.println("\t" + vihollinen.getNimi() + " pudotti HP-juoman! Nappaat sen talteen.");
                                hpPotionMaara++;
                                v();
                                System.out.println("\tSinulla on nyt " + hpPotionMaara + " HP-juomaa.");
                            }
                            v();

                            while (true) {
                                System.out.println("\n\tMitä haluat tehdä? ");
                                v();
                                System.out.println("\t1. Jatkaa taistelemista!");
                                System.out.println("\t2. Lopettaa.");
                                v();
                                System.out.print("\n\t> ");
                                String paatos = lukija.nextLine();

                                v();
                                if (paatos.equals("1")) {
                                    continue uusitaistelu;
                                } else if (paatos.equals("2")) {
                                    v();
                                    hp = -999999;
                                    continue uusitaistelu;
                                } else {
                                    System.out.println("\t'" + paatos + "' ei ole käypä komento.");
                                    v();
                                }
                            }
                        }
                    } else {
                        v();
                        System.out.println("\t... mutta " + vihollinen.getNimi() + " väisti iskusi.");
                    }
                    v();
                    System.out.println("\t" + vihollinen.getNimi() + " hyökkää sinuun...");
                    if (randomi.nextInt(100) < vihollinen.getOsumatarkkuus()) {
                        if (randomi.nextInt(100) < vihollinen.getKriittinenTN()) {
                            int vihollisenKriittinenIsku = (int) 1.5 * (vihollinen.getMaxiskuvoima() / 4 + randomi.nextInt(randomi.nextInt((int) (0.75 * vihollinen.getMaxiskuvoima()))));
                            v();
                            System.out.println("\t... ja osui arkaan paikkaan! Kärsit " + vihollisenKriittinenIsku + " vahinkoa.");
                            hp -= vihollisenKriittinenIsku;
                            if (hp < 1) {
                                v();
                                System.out.println("\n\n\tOlet liian heikko jatkamaan taistelua.\n");
                                v();
                                continue uusitaistelu;
                            }
                        } else {
                            int vihollisenIsku = (int) vihollinen.getMaxiskuvoima() / 4 + randomi.nextInt(randomi.nextInt((int) (0.75 * vihollinen.getMaxiskuvoima())));
                            v();
                            System.out.println("\t... ja osui! Kärsit " + vihollisenIsku + " vahinkoa.");
                            hp -= vihollisenIsku;
                            if (hp < 1) {
                                v();
                                System.out.println("\n\n\tOlet liian heikko jatkamaan taistelua.\n");
                                v();
                                continue uusitaistelu;
                            }
                        }
                    } else {
                        v();
                        System.out.println("\t... mutta onnistuit väistämään!");
                    }

                } else if (komento.equals("2")) {
                    v();
                    if (hpPotionMaara > 0) {
                        hp += hpPotionTeho;
                        hpPotionMaara--;
                        System.out.println("\tJoit HP-juoman. Tunnet sen ravinneen sinua " + hpPotionTeho + " HP:lla.");
                        v();
                        System.out.println("\tSinulla on nyt " + hp + " HP:ta ja " + hpPotionMaara + " HP-juomaa jäljellä.");
                    } else {
                        System.out.println("\tSinulla ei ole yhtään HP-juomaa jäljellä! Tapa vihollisia saadaksesi niitä.");
                    }

                } else if (komento.equals("3")) {
                    v();
                    System.out.println("\tKoitat paeta...");
                    v();
                    if (randomi.nextInt(100) < pakenemisTodennakoisyys) {
                        System.out.println("\t...ja pääsit livahtamaan pakoon!");
                        vihollinen.setHp(vihollinen.getMaxHp());
                        v();
                        System.out.println("\t" + vihollinen.getNimi() + " löysi uuden haastajan, eikä ole kiinnostunut pelkureiden kanssa taistelemisesta.");
                        v();
                        System.out.println("");
                        while (true) {
                            System.out.println("\n\tMitä haluat tehdä? ");
                            v();
                            System.out.println("\t1. Jatkaa taistelemista!");
                            System.out.println("\t2. Lopettaa.");
                            v();
                            System.out.print("\n\t> ");
                            String paatos = lukija.nextLine();

                            v();
                            if (paatos.equals("1")) {
                                continue uusitaistelu;
                            } else if (paatos.equals("2")) {
                                v();
                                hp = -999999;
                                continue uusitaistelu;
                            } else {
                                System.out.println("\t'" + paatos + "' ei ole käypä komento.");
                                v();
                            }
                        }
                    } else {
                        System.out.println("\t... mutta " + vihollinen.getNimi() + " saa sinut kiinni!");
                        int vihollisenIsku = (int) 1.5 * (vihollinen.getMaxiskuvoima() / 4 + randomi.nextInt(randomi.nextInt((int) (0.75 * vihollinen.getMaxiskuvoima()))));
                        v();
                        System.out.println("\t" + vihollinen.getNimi() + " iski selkääsi, aiheuttaen " + vihollisenIsku + " vahinkoa.");
                        hp -= vihollisenIsku;
                        if (hp < 1) {
                            v();
                            System.out.println("Olet liian heikko jatkamaan taistelua.");
                            continue uusitaistelu;
                        }
                    }

                } else {
                    System.out.println("\t'" + komento + "' ei ole käypä komento.");
                    v();
                }

            }

        }
        v();

        if (hp == -999999) {
            if (voitot.keySet().isEmpty()) {
                System.out.println("\tPelkuri...");
            } else {
                System.out.println("\tVoittosi tilastot: \n");
                voitot.keySet().forEach(key -> {
                    if (voitot.get(key) == 1) {
                        System.out.println("\t" + key + ": " + voitot.get(key) + " voitto.");
                    } else {
                        System.out.println("\t" + key + ": " + voitot.get(key) + " voittoa.");
                    }
                });
            }
        } else {
            System.out.println("\tJäät makaamaan paikallesi, ja esität kuollutta.");
            v();
            System.out.println("\tHetken maattuasi huomaat areenan reunalla pienen raon, jossa voisit piileksiä.");
            System.out.println("\tKeskellä areenaa on menossa suuri taistelu, jota kaikki seuraavat.");
            v();
            System.out.println("\tTaistelun temmellyksen turvin ryömit piiloon pieneen rakoon, toivoen, ettei sinua huomata.");
        }

        System.out.println("\n\n\t############################");
        System.out.println("\t### KIITOS PELAAMISESTA! ###");
        System.out.println("\t############################\n\n");
        System.out.println("\n\n\t //Hirvos-production// \n\n");

    }
}
