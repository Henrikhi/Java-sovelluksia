package asteroids;

import javafx.scene.shape.Polygon;

public class Ammus extends Hahmo {
    
    public Ammus(int x, int y) {
        super(new Polygon(-2, -2, -2, 2, 2, -2, 2, 2), x, y);
    }
    
    @Override
    public void liiku() {
        getHahmo().setTranslateX(getHahmo().getTranslateX() + getLiike().getX());
        getHahmo().setTranslateY(getHahmo().getTranslateY() + getLiike().getY());

        if (getHahmo().getTranslateX() < 0) {
            setElossa(false);
        }

        if (getHahmo().getTranslateX() > AsteroidiSovellus.LEVEYS) {
            setElossa(false);
        }

        if (getHahmo().getTranslateY() < 0) {
            setElossa(false);
        }

        if (getHahmo().getTranslateY() > AsteroidiSovellus.KORKEUS) {
            setElossa(false);
        }

    }
    
}
