package jokinid.flappybirds;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Pylvas {

    private Polygon pylvasYla;
    private Polygon pylvasAla;
    private Random randomi = new Random();
    private int vali;

    public Pylvas(int vali, int x) {
        this.vali = vali;
        int apuluku = (FlappyBirdsMain.korkeus - vali) / 2;
        this.pylvasYla = new Polygon(-20, -200, -20, apuluku, 20, apuluku, 20, -200);
        this.pylvasAla = new Polygon(-20, FlappyBirdsMain.korkeus + 200, -20, apuluku + vali,
                20, apuluku + vali, 20, FlappyBirdsMain.korkeus + 200);
        this.pylvasAla.setTranslateX(x);
        this.pylvasYla.setTranslateX(x);
    }

    public Polygon getPylvasAla() {
        return pylvasAla;
    }

    public Polygon getPylvasYla() {
        return pylvasYla;
    }

    public void muutaKorkeus() {
        int muutos = randomi.nextInt(FlappyBirdsMain.korkeus / 2) - FlappyBirdsMain.korkeus / 4;
        this.pylvasAla.setTranslateY(this.pylvasAla.getTranslateY() - muutos);
        this.pylvasYla.setTranslateY(this.pylvasYla.getTranslateY() - muutos);

        if (this.pylvasAla.getTranslateY() < this.vali - FlappyBirdsMain.korkeus / 2
                || this.pylvasAla.getTranslateY() > FlappyBirdsMain.korkeus / 2 - this.vali) {
            this.pylvasYla.setTranslateY(muutos);
            this.pylvasAla.setTranslateY(muutos);
        }
    }

    public void liiku() {
        this.pylvasYla.setTranslateX(this.pylvasYla.getTranslateX() - 2);
        this.pylvasAla.setTranslateX(this.pylvasAla.getTranslateX() - 2);

        if (this.pylvasYla.getTranslateX() <= 0) {
            FlappyBirdsMain.pisteet.addAndGet(1);

            this.pylvasYla.setTranslateX(FlappyBirdsMain.leveys);
            this.pylvasAla.setTranslateX(FlappyBirdsMain.leveys);
            muutaKorkeus();
        }
    }

}
