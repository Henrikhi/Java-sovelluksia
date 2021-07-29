package asteroids;

import java.util.Random;
import javafx.scene.shape.Polygon;

public class Asteroidi extends Hahmo {
    
    private double pyorimisliike;
    
    public Asteroidi(int x, int y) {
        super(new Monikulmiotehdas().luoMonikulmio(), x, y);
        
        Random randomi = new Random();
        super.getHahmo().setRotate(randomi.nextInt(360));
        
        int kiihdytystenMaara = 1 + randomi.nextInt(10);
        for (int i = 0; i < kiihdytystenMaara; i++) {
            kiihdyta();
        }
        
        this.pyorimisliike = 0.5 - randomi.nextDouble();
    }
    
    @Override
    public void liiku() {
        super.liiku();
        super.getHahmo().setRotate(super.getHahmo().getRotate() + pyorimisliike);
    }
}
