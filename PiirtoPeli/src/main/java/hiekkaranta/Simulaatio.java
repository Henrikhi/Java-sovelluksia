package hiekkaranta;

import java.util.ArrayList;
import java.util.Random;

public class Simulaatio {

    private int leveys;
    private int korkeus;
    private Tyyppi[][] alue;

    public Simulaatio(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;

        this.alue = new Tyyppi[leveys][korkeus];
        for (int x = 0; x < leveys; x++) {
            for (int y = 0; y < korkeus; y++) {
                this.alue[x][y] = Tyyppi.TYHJA;
            }

        }
    }

    public void lisaa(int x, int y, Tyyppi tyyppi) {
        if (!(tyyppi == Tyyppi.HIEKKA || tyyppi == Tyyppi.METALLI || tyyppi == Tyyppi.VESI || tyyppi == Tyyppi.TYHJA)) {
            return;
        }
        if (x < 0 || x >= leveys || y < 0 || y >= korkeus) {
            return;
        }

        this.alue[x][y] = tyyppi;

    }

    public Tyyppi sisalto(int x, int y) {
        if (x < 0 || x >= leveys || y < 0 || y >= korkeus) {
            return Tyyppi.METALLI;
        }
        return this.alue[x][y];
    }

    public void paivita() {
        for (int x = 0; x < leveys; x++) {
            for (int y = korkeus - 1; y > 0; y--) {
                if (this.alue[x][y] == Tyyppi.TYHJA) {
                    continue;
                }

                if (this.alue[x][y] == Tyyppi.METALLI) {
                    continue;
                }

                if (this.alue[x][y] == Tyyppi.HIEKKA) {
                    liikutaHiekkaa(x, y);
                    continue;
                }

                if (this.alue[x][y] == Tyyppi.VESI) {
                    liikutaVetta(x, y);
                    continue;
                }
            }
        }

    }

    public void liikutaHiekkaa(int x, int y) {
        if (!tyhjatAlaKohdat(x, y).isEmpty()) {
            Random satunnainen = new Random();

            Piste uusiPiste = tyhjatAlaKohdat(x, y).get(satunnainen.nextInt(tyhjatAlaKohdat(x, y).size()));
            this.alue[uusiPiste.getX()][uusiPiste.getY()] = Tyyppi.HIEKKA;
            this.alue[x][y] = Tyyppi.TYHJA;
        } else {
            hiekkaVedenAlle(x, y);
        }

    }

    public void liikutaVetta(int x, int y) {
        Random satunnainen = new Random();

        if (!tyhjatAlaKohdat(x, y).isEmpty()) {
            Piste uusiPiste = tyhjatAlaKohdat(x, y).get(satunnainen.nextInt(tyhjatAlaKohdat(x, y).size()));
            this.alue[uusiPiste.getX()][uusiPiste.getY()] = Tyyppi.VESI;
            this.alue[x][y] = Tyyppi.TYHJA;
        } else if (!tyhjatSivuKohdat(x, y).isEmpty()) {
            Piste uusiPiste = tyhjatSivuKohdat(x, y).get(satunnainen.nextInt(tyhjatSivuKohdat(x, y).size()));
            this.alue[uusiPiste.getX()][uusiPiste.getY()] = Tyyppi.VESI;
            this.alue[x][y] = Tyyppi.TYHJA;
        }
    }

    public void hiekkaVedenAlle(int x, int y) {
        if (!vesiAlaKohdat(x, y).isEmpty()) {
            Random satunnainen = new Random();

            Piste uusiPiste = vesiAlaKohdat(x, y).get(satunnainen.nextInt(vesiAlaKohdat(x, y).size()));
            this.alue[uusiPiste.getX()][uusiPiste.getY()] = Tyyppi.HIEKKA;
            this.alue[x][y] = Tyyppi.VESI;
        }
    }

    public ArrayList<Piste> vesiAlaKohdat(int x, int y) {
        ArrayList<Piste> lista = new ArrayList<>();
        if (y + 1 == korkeus) {
            return lista;
        }

        if (x - 1 >= 0 && x - 1 < this.leveys && this.alue[x - 1][y + 1] == Tyyppi.VESI) {
            lista.add(new Piste(x - 1, y + 1));
        }
        if (x >= 0 && x < this.leveys && this.alue[x][y + 1] == Tyyppi.VESI) {
            lista.add(new Piste(x, y + 1));
        }
        if (x + 1 >= 0 && x + 1 < this.leveys && this.alue[x + 1][y + 1] == Tyyppi.VESI) {
            lista.add(new Piste(x + 1, y + 1));
        }

        return lista;
    }

    public ArrayList<Piste> tyhjatAlaKohdat(int x, int y) {
        ArrayList<Piste> lista = new ArrayList<>();
        if (y + 1 == korkeus) {
            return lista;
        }

        if (x - 1 >= 0 && x - 1 < this.leveys && this.alue[x - 1][y + 1] == Tyyppi.TYHJA) {
            lista.add(new Piste(x - 1, y + 1));
        }
        if (x >= 0 && x < this.leveys && this.alue[x][y + 1] == Tyyppi.TYHJA) {
            lista.add(new Piste(x, y + 1));
        }
        if (x + 1 >= 0 && x + 1 < this.leveys && this.alue[x + 1][y + 1] == Tyyppi.TYHJA) {
            lista.add(new Piste(x + 1, y + 1));
        }

        return lista;
    }

    public ArrayList<Piste> tyhjatSivuKohdat(int x, int y) {
        ArrayList<Piste> lista = new ArrayList<>();
        if (x - 1 >= 0 && x - 1 < this.leveys && this.alue[x - 1][y] == Tyyppi.TYHJA) {
            lista.add(new Piste(x - 1, y));
        }
        if (x + 1 >= 0 && x + 1 < this.leveys && this.alue[x + 1][y] == Tyyppi.TYHJA) {
            lista.add(new Piste(x + 1, y));
        }

        return lista;
    }

}
