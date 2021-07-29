
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Hahmo {

    private Polygon hahmo;
    private Point2D liike;
    private int hyppyaika;
    private boolean ilmassa;

    public Hahmo(Polygon monikulmio, int x, int y) {
        this.hahmo = monikulmio;
        this.hahmo.setTranslateX(x);
        this.hahmo.setTranslateY(y);
        this.liike = new Point2D(0, 0);
        this.hyppyaika = 0;
        this.ilmassa = false;
    }

    public Polygon getHahmo() {
        return this.hahmo;
    }

    public Point2D getLiike() {
        return this.liike;
    }

    public void setLiike(Point2D uusiLiike) {
        this.liike = uusiLiike;
    }

    public boolean tormaa(Hahmo toinen) {
        Shape tormaysAlue = Shape.intersect(this.hahmo, toinen.getHahmo());
        return tormaysAlue.getBoundsInLocal().getWidth() != -1;
    }

    public void liiku() {
        this.hahmo.setTranslateX(this.hahmo.getTranslateX() + this.liike.getX());
        this.hahmo.setTranslateY(this.hahmo.getTranslateY() + this.liike.getY());

        if (this.hahmo.getTranslateX() < 0) {
            this.hahmo.setTranslateX(HyppelypeliMain.leveys);
        }
        if (this.hahmo.getTranslateX() > HyppelypeliMain.leveys) {
            this.hahmo.setTranslateX(0);
        }
        if (this.hahmo.getTranslateY() > HyppelypeliMain.korkeus) {
            this.hahmo.setTranslateY(HyppelypeliMain.korkeus);
        }

    }


    public void vasemmalle() {
        this.hahmo.setTranslateX(this.hahmo.getTranslateX() - 10);
    }

    public void oikealle() {
        this.hahmo.setTranslateX(this.hahmo.getTranslateX() + 10);
    }


    public int getSijaintiX() {
        return (int) this.hahmo.getTranslateX();
    }

    public int getSijantiY() {
        return (int) this.hahmo.getTranslateY();
    }

    public void setSijaintiX(int x) {
        this.hahmo.setTranslateX(x);
    }

    public void setSijaintiY(int y) {
        this.hahmo.setTranslateY(y);
    }

    public int getHyppyaika() {
        return this.hyppyaika;
    }

    public void setHyppyaika(int uusiAika) {
        this.hyppyaika = uusiAika;
    }
    
    public boolean getIlmassa() {
        return this.ilmassa;
    }
    
    public void setIlmassa(boolean uusiArvo) {
        this.ilmassa = uusiArvo;
    }

}
