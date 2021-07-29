 package jokinid.flappybirds;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FlappyBirdsMain extends Application {

    public boolean hyppyMenossa = false;
    public int hyppyAika = 0;
    public static AtomicInteger pisteet = new AtomicInteger(0);
    public static int leveys = 640; //640
    public static int korkeus = 480 ; //480
    public int maxHyppyAika = 40; //40
    public static int putoamisNopeus = 4; //4
    public static int pylvaidenLkm = 3; //3
    public static int pylvaidenVali = 150; //150

    public static void main(String[] args) {
        launch(FlappyBirdsMain.class);
    }

    @Override
    public void start(Stage ikkuna) throws Exception {
        Pane ruutu = new Pane();
        ruutu.setPrefSize(leveys, korkeus);
        Label pisteteksti = new Label("Pisteet: " + pisteet);
        ruutu.getChildren().add(pisteteksti);
        Scene nakyma = new Scene(ruutu);
        ikkuna.setScene(nakyma);
        ikkuna.setTitle("FlappyBirds Rip-off");
        ikkuna.show();

        Lintu lintu = new Lintu(leveys / 10, korkeus / 2);
        ruutu.getChildren().add(lintu.getKeho());

        Pylvas[] pylvaat = new Pylvas[pylvaidenLkm];
        for (int i = 1; i < pylvaidenLkm + 1; i++) {
            pylvaat[i - 1] = new Pylvas(pylvaidenVali, leveys / pylvaidenLkm * i);
            pylvaat[i - 1].muutaKorkeus();
            ruutu.getChildren().addAll(pylvaat[i - 1].getPylvasAla(), pylvaat[i - 1].getPylvasYla());
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                pisteteksti.setText("Pisteet: " + pisteet);
                lintu.liiku();
                for (int i = 0; i < pylvaidenLkm; i++) {
                    pylvaat[i].liiku();
                }

                for (int i = 0; i < pylvaidenLkm; i++) {
                    if (lintu.tormaako(pylvaat[i].getPylvasAla()) || lintu.tormaako(pylvaat[i].getPylvasYla())) {
                        lintu.setElossa(false);
                    }
                }

                if (lintu.isElossa() == false) {
                    stop();
                    System.exit(0);
                }

                nakyma.setOnKeyPressed(klik -> {
                    if (klik.getCode().toString().equals("SPACE")) {
                        if (hyppyAika > maxHyppyAika / 2) {
                            hyppyAika = 0;
                        }
                        hyppyMenossa = true;
                    }
                });
                if (hyppyMenossa) {
                    hyppyAika++;

                    if (hyppyAika < maxHyppyAika * 0.25) {
                        lintu.setLiike(new Point2D(0, -2.5 * putoamisNopeus));
                    } else if (hyppyAika < maxHyppyAika * 0.5) {
                        lintu.setLiike(new Point2D(0, -2 * putoamisNopeus));
                    } else if (hyppyAika < maxHyppyAika * 0.7) {
                        lintu.setLiike(new Point2D(0, -1.5 * putoamisNopeus));
                    } else if (hyppyAika < maxHyppyAika * 0.8) {
                        lintu.setLiike(new Point2D(0, -putoamisNopeus));
                    } else if (hyppyAika < maxHyppyAika * 0.9) {
                        lintu.setLiike(new Point2D(0, -0.5 * putoamisNopeus));
                    } else {
                        lintu.setLiike(new Point2D(0, 0));
                        hyppyMenossa = false;
                    }
                }
            }
        }.start();

    }

}
