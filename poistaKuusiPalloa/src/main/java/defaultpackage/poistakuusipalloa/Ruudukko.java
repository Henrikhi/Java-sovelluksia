package defaultpackage.poistakuusipalloa;

import java.util.ArrayList;
import java.util.Random;

class Ruudukko {

    private int[][] r;
    private String[] pallot;
    private ArrayList<int[][]> ratkaisut;

    Ruudukko() {
        this.r = new int[6][6];
        this.pallot = new String[20];
        this.ratkaisut = new ArrayList<>();
        alusta();

    }

    /*
            02  03        
    
            12  13        
    
    20  21  22  23  24  25
    
    30  31  32  33  34  35
    
            42  43      
    
            52  53        
     */
    private void alusta() {
        for (int i = 0; i < this.pallot.length; i++) {
            this.pallot[i] = "  ";
        }
        for (int i = 0; i < r.length; i++) {
            r[i][2] = 1;
            r[i][3] = 1;
            r[2][i] = 1;
            r[3][i] = 1;
        }

        int indeksi = 0;
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[0].length; j++) {
                if (r[i][j] == 1) {
                    this.pallot[indeksi] = ("" + i + j);
                    indeksi++;
                }
            }
        }
    }

    private boolean toimivaRatkaisu() {
        //pikkuneliöt
        for (int i = 0; i < 5; i++) {
            if (r[i][2] == 1 && r[i][3] == 1 && r[i + 1][2] == 1 && r[i + 1][3] == 1) {
                return false;
            }
            if (r[2][i] == 1 && r[3][i] == 1 && r[2][i + 1] == 1 && r[3][i + 1] == 1) {
                return false;
            }
        }

        //pikku vinot neliöt
        if (r[2][1] == 1 && r[1][2] == 1 && r[2][3] == 1 && r[3][2] == 1) {
            return false;
        }
        if (r[2][2] == 1 && r[1][3] == 1 && r[2][4] == 1 && r[3][3] == 1) {
            return false;
        }
        if (r[3][1] == 1 && r[2][2] == 1 && r[3][3] == 1 && r[4][2] == 1) {
            return false;
        }
        if (r[3][2] == 1 && r[2][3] == 1 && r[3][4] == 1 && r[4][3] == 1) {
            return false;
        }

        //2x2 vinot neliöt
        if (r[2][1] == 1 && r[0][3] == 1 && r[2][5] == 1 && r[4][3] == 1) {
            return false;
        }
        if (r[3][1] == 1 && r[1][3] == 1 && r[3][5] == 1 && r[5][3] == 1) {
            return false;
        }
        if (r[2][0] == 1 && r[0][2] == 1 && r[2][4] == 1 && r[4][2] == 1) {
            return false;
        }
        if (r[3][0] == 1 && r[1][2] == 1 && r[3][4] == 1 && r[5][2] == 1) {
            return false;
        }

        //2x1 sivuiset neliöt
        if (r[2][1] == 1 && r[1][3] == 1 && r[3][4] == 1 && r[4][2] == 1) {
            return false;
        }
        if (r[3][1] == 1 && r[1][2] == 1 && r[2][4] == 1 && r[4][3] == 1) {
            return false;
        }

        //3x2 sivuiset neliöt
        if (r[2][0] == 1 && r[0][3] == 1 && r[3][5] == 1 && r[5][2] == 1) {
            return false;
        }
        if (r[3][0] == 1 && r[0][2] == 1 && r[2][5] == 1 && r[5][3] == 1) {
            return false;
        }

        //mitään neliöitä ei ole muodostunut!
        return true;
    }

    public void tulosta(int[][] ruudukko) {
        for (int i = 0; i < ruudukko.length; i++) {
            System.out.println("");
            for (int j = 0; j < ruudukko[0].length; j++) {
                int arvo = ruudukko[i][j];
                String merkki = "";
                if (arvo == 0) { //ollaan halutun ruudukon ulkopuolella
                    merkki = " ";
                }
                if (arvo == 1) { //pallo
                    merkki = "O";
                }
                if (arvo == 2) { //pallo on poistettu
                    merkki = " ";
                }
                System.out.print(merkki + " ");
            }
        }
        System.out.println("");
    }

    public void randomPoistot() {
        alusta();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int monesko = random.nextInt(this.pallot.length);
            String poistettava = this.pallot[monesko];
            while (poistettava.equals("  ")) {
                monesko = random.nextInt(this.pallot.length);
                poistettava = this.pallot[monesko];
            }
            this.pallot[monesko] = "  ";
            int ekaKoordinaatti = Integer.parseInt(poistettava.substring(0, 1));
            int tokaKoordinaatti = Integer.parseInt(poistettava.substring(1, 2));
            r[ekaKoordinaatti][tokaKoordinaatti] = 2;
        }
    }

    public void tarkistaJaTulostaKaikki() {
        alusta();
        for (int i = 0; i < this.pallot.length - 5; i++) {
            String eka = this.pallot[i];
            this.pallot[i] = "";
            for (int j = 1; j < this.pallot.length - 4; j++) {
                String toka = "";
                if (!this.pallot[j].equals("")) {
                    toka = this.pallot[j];
                    this.pallot[j] = "";
                    for (int k = 2; k < this.pallot.length - 3; k++) {
                        String kolmas = "";
                        if (!this.pallot[k].equals("")) {
                            kolmas = this.pallot[k];
                            this.pallot[k] = "";
                            for (int l = 3; l < this.pallot.length - 2; l++) {
                                String neljas = "";
                                if (!this.pallot[l].equals("")) {
                                    neljas = this.pallot[l];
                                    this.pallot[l] = "";
                                    for (int m = 4; m < this.pallot.length - 1; m++) {
                                        String viides = "";
                                        if (!this.pallot[m].equals("")) {
                                            viides = this.pallot[m];
                                            this.pallot[m] = "";

                                            for (int n = 5; n < this.pallot.length; n++) {

                                                String kuudes = this.pallot[n];
                                                if (!kuudes.equals("")) {
                                                    int eka1 = Integer.parseInt(eka.substring(0, 1));
                                                    int eka2 = Integer.parseInt(eka.substring(1, 2));
                                                    int toka1 = Integer.parseInt(toka.substring(0, 1));
                                                    int toka2 = Integer.parseInt(toka.substring(1, 2));
                                                    int kolmas1 = Integer.parseInt(kolmas.substring(0, 1));
                                                    int kolmas2 = Integer.parseInt(kolmas.substring(1, 2));
                                                    int neljas1 = Integer.parseInt(neljas.substring(0, 1));
                                                    int neljas2 = Integer.parseInt(neljas.substring(1, 2));
                                                    int viides1 = Integer.parseInt(viides.substring(0, 1));
                                                    int viides2 = Integer.parseInt(viides.substring(1, 2));
                                                    int kuudes1 = Integer.parseInt(kuudes.substring(0, 1));
                                                    int kuudes2 = Integer.parseInt(kuudes.substring(1, 2));

                                                    r[eka1][eka2] = 2;
                                                    r[toka1][toka2] = 2;
                                                    r[kolmas1][kolmas2] = 2;
                                                    r[neljas1][neljas2] = 2;
                                                    r[viides1][viides2] = 2;
                                                    r[kuudes1][kuudes2] = 2;

                                                    if (toimivaRatkaisu()) {
                                                        int[][] ratkaisuEhdotus = new int[r.length][r[0].length];
                                                        for (int o = 0; o < r.length; o++) {
                                                            for (int p = 0; p < r[0].length; p++) {
                                                                ratkaisuEhdotus[o][p] = r[o][p];
                                                            }
                                                        }
                                                        lisaaRatkaisu(ratkaisuEhdotus);
                                                    }

                                                    r[eka1][eka2] = 1;
                                                    r[toka1][toka2] = 1;
                                                    r[kolmas1][kolmas2] = 1;
                                                    r[neljas1][neljas2] = 1;
                                                    r[viides1][viides2] = 1;
                                                    r[kuudes1][kuudes2] = 1;
                                                }
                                            }
                                        }
                                        this.pallot[m] = viides;
                                    }
                                }
                                this.pallot[l] = neljas;
                            }
                        }
                        this.pallot[k] = kolmas;
                    }
                }
                this.pallot[j] = toka;
            }
            this.pallot[i] = eka;
        }

        int ratkaisuja = this.ratkaisut.size();
        System.out.println("Ratkaisuja löytyi " + ratkaisuja + " kpl.\n");

        for (int i = 0; i < ratkaisuja; i++) {
            System.out.println("Ratkaisu numero " + (i+1));
            tulosta(this.ratkaisut.get(i));
        }

    }

    private void lisaaRatkaisu(int[][] ratkaisuehdotus) {
        if (this.ratkaisut.isEmpty()) {
            this.ratkaisut.add(ratkaisuehdotus);
            return;
        }

        for (int i = 0; i < this.ratkaisut.size(); i++) {
            int[][] verrattava = this.ratkaisut.get(i);
            int samojaRuutuja = 0;
            for (int j = 0; j < ratkaisuehdotus.length; j++) {
                for (int k = 0; k < ratkaisuehdotus[0].length; k++) {
                    if (verrattava[j][k] == ratkaisuehdotus[j][k]) {
                        samojaRuutuja++;
                    }
                }
            }
            if (samojaRuutuja == 36) {
                return;
            }
        }
        
        this.ratkaisut.add(ratkaisuehdotus);
        
    }

}
