package jokinid.flappybirds;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Lintu {

    private Polygon keho;
    private Point2D liike;
    private boolean elossa;

    public Lintu(int x, int y) {
        this.keho = new Polygon(-10, -10, -10, 10, 10, 10, 10, -10);
        this.keho.setTranslateX(x);
        this.keho.setTranslateY(y);
        this.liike = new Point2D(0, 0);
        this.elossa = true;
    }

    public Polygon getKeho() {
        return keho;
    }

    public Point2D getLiike() {
        return liike;
    }

    public void setLiike(Point2D liike) {
        this.liike = liike;
    }

    public boolean isElossa() {
        return this.elossa;
    }

    public void setElossa(boolean elossa) {
        this.elossa = elossa;
    }

    public boolean tormaako(Polygon toinen) {
        Shape tormaysAlue = Shape.intersect(this.keho, toinen);
        return tormaysAlue.getBoundsInLocal().getWidth() != -1;
    }

    public void liiku() {
        this.keho.setTranslateY(this.keho.getTranslateY() + FlappyBirdsMain.putoamisNopeus + getLiike().getY());
        if (this.keho.getTranslateY() < 0 || this.keho.getTranslateY() >= FlappyBirdsMain.korkeus) {
            this.elossa = false;
        }
    }

}
