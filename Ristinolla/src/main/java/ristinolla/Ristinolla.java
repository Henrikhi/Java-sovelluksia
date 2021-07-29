package ristinolla;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ristinolla {

    private String[][] ristinolla;
    private String vuoro;

    public Ristinolla() {
        this.ristinolla = new String[][]{{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
        this.vuoro = "X";
    }

    public String haeArvo(int x, int y) {
        if (x < 0 || x > 2 || y < 0 || y > 2) {
            return null;
        }
        return this.ristinolla[x][y];
    }

    public void asetaArvo(int x, int y) {
        if (haeArvo(x, y).equals(" ")) {
            this.ristinolla[x][y] = this.vuoro;
        } else {
            return;
        }
        if (this.vuoro.equals("X")) {
            this.vuoro = "O";
        } else {
            this.vuoro = "X";
        }
    }

    public String vuoro() {
        return this.vuoro;
    }

    private String haeArvoIndeksilla(int i) {
        if (i == 1) {
            return haeArvo(0, 0);
        }
        if (i == 2) {
            return haeArvo(0, 1);
        }
        if (i == 3) {
            return haeArvo(0, 2);
        }
        if (i == 4) {
            return haeArvo(1, 0);
        }
        if (i == 5) {
            return haeArvo(1, 1);
        }
        if (i == 6) {
            return haeArvo(1, 2);
        }
        if (i == 7) {
            return haeArvo(2, 0);
        }
        if (i == 8) {
            return haeArvo(2, 1);
        }
        if (i == 9) {
            return haeArvo(2, 2);
        }

        return null;
    }
    
    public boolean tasapeli() {
        int tyhjia = 0;
        for (int i = 1; i <= 9; i++) {
            if (haeArvoIndeksilla(i).equals(" ")) {
                tyhjia++;
            }
        }
        return tyhjia == 0;
    }

    public boolean loppu() {
        int[][] kolmeSamaa = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},
            {1, 5, 9},
            {7, 5, 3}
        };

        for (int i = 0; i < kolmeSamaa.length; i++) {
            if (haeArvoIndeksilla(kolmeSamaa[i][0]).equals(haeArvoIndeksilla(kolmeSamaa[i][1]))
                    && haeArvoIndeksilla(kolmeSamaa[i][0]).equals(haeArvoIndeksilla(kolmeSamaa[i][2]))) {
                if (haeArvoIndeksilla(kolmeSamaa[i][0]).equals("X") || haeArvoIndeksilla(kolmeSamaa[i][0]).equals("O")) {
                    return true;
                }
            }
        }

        return false;
    }

    public void tyhjenna() {
        this.ristinolla = new String[][]{{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
    }
}
