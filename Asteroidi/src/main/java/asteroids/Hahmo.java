package asteroids;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Hahmo {

    private Polygon hahmo;
    private Point2D liike;
    private Boolean elossa;

    public Hahmo(Polygon monikulmio, int x, int y) {
        this.hahmo = monikulmio;
        this.hahmo.setTranslateX(x);
        this.hahmo.setTranslateY(y);
        this.elossa = true;

        this.liike = new Point2D(0, 0);
    }

    public Polygon getHahmo() {
        return this.hahmo;
    }

    public void kaannaVasemmalle() {
        this.hahmo.setRotate(this.hahmo.getRotate() - 5);
    }

    public void kaannaOikealle() {
        this.hahmo.setRotate(this.hahmo.getRotate() + 5);
    }

    public void kiihdyta() {
        double muutosX = 0.05 * Math.cos(Math.toRadians(this.hahmo.getRotate()));
        double muutosY = 0.05 * Math.sin(Math.toRadians(this.hahmo.getRotate()));

        this.liike = this.liike.add(muutosX, muutosY);
    }

    public Point2D getLiike() {
        return this.liike;
    }

    public void setLiike(Point2D uusiLiike) {
        this.liike = uusiLiike;
    }

    public void liiku() {
        this.hahmo.setTranslateX(this.hahmo.getTranslateX() + liike.getX());
        this.hahmo.setTranslateY(this.hahmo.getTranslateY() + liike.getY());

        if (this.hahmo.getTranslateX() < 0) {
            this.hahmo.setTranslateX(AsteroidiSovellus.LEVEYS);
        }

        if (this.hahmo.getTranslateX() > AsteroidiSovellus.LEVEYS) {
            this.hahmo.setTranslateX(0);
        }

        if (this.hahmo.getTranslateY() < 0) {
            this.hahmo.setTranslateY(AsteroidiSovellus.KORKEUS);
        }

        if (this.hahmo.getTranslateY() > AsteroidiSovellus.KORKEUS) {
            this.hahmo.setTranslateY(0);
        }

    }

    public boolean tormaa(Hahmo toinen) {
        Shape tormaysAlue = Shape.intersect(this.hahmo, toinen.getHahmo());
        return tormaysAlue.getBoundsInLocal().getWidth() != -1;
    }

    public void setElossa(boolean totuusarvo) {
        this.elossa = totuusarvo;
    }
    
    public boolean isElossa() {
        return this.elossa;
    }

}
