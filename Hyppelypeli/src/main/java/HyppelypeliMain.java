
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HyppelypeliMain extends Application {

    public static int leveys = 300;
    public static int korkeus = 400;
    public boolean menussa = true;
    public int tasojenLkm = 7;
    public int maksimiHyppyAika = 40;
    public Random randomi = new Random();

    public static void main(String[] args) {
        launch(HyppelypeliMain.class);
    }

    @Override
    public void start(Stage ikkuna) throws Exception {

        //MENU NÄYTTÖ:
        VBox menuAsettelu = new VBox();
        menuAsettelu.setPrefSize(leveys, korkeus);
        menuAsettelu.setPadding(new Insets(20));
        menuAsettelu.setSpacing(20);

        Label otsikko = new Label("Tervetuloa hyppelypeliin!");
        BorderPane tasotAsettelu = new BorderPane();
        BorderPane hyppyAsettelu = new BorderPane();
        Label tasoTekstiAlku = new Label("Tasojen lukumäärä (suositus 7): ");
        Label hyppyTekstiAlku = new Label("Hyppyjen korkeus (suositus 40): ");
        Label tasoTeksti = new Label("7");
        Label hyppyTeksti = new Label("40");
        Slider sliderTasoja = new Slider(1, 10, tasojenLkm);
        Slider sliderHyppy = new Slider(10, 100, maksimiHyppyAika);
        tasotAsettelu.setTop(tasoTekstiAlku);
        tasotAsettelu.setCenter(sliderTasoja);
        tasotAsettelu.setRight(tasoTeksti);
        hyppyAsettelu.setTop(hyppyTekstiAlku);
        hyppyAsettelu.setCenter(sliderHyppy);
        hyppyAsettelu.setRight(hyppyTeksti);

        Button aloita = new Button("Aloita!");
        menuAsettelu.getChildren().addAll(otsikko, tasotAsettelu, hyppyAsettelu, aloita);

        ArrayList<Taso> tasot = new ArrayList<>();

        Pane asettelu = new Pane();
        
        sliderTasoja.setShowTickLabels(true);
        sliderTasoja.setShowTickMarks(true);
        sliderTasoja.setOnMouseReleased(klik -> {
            tasoTeksti.setText("" + (int) sliderTasoja.getValue());
            tasojenLkm = (int) sliderTasoja.getValue();
            tasot.clear();
            for (int i = 1; i <= tasojenLkm; i++) {
                Taso taso = new Taso(randomi.nextInt(leveys), korkeus - i * (korkeus / tasojenLkm));
                tasot.add(taso);
            }

        });

        sliderHyppy.setShowTickLabels(true);
        sliderHyppy.setShowTickMarks(true);
        sliderHyppy.setOnMouseReleased(klik -> {
            hyppyTeksti.setText("" + (int) sliderHyppy.getValue());
            maksimiHyppyAika = (int) sliderHyppy.getValue();
        });

        Scene menuNakyma = new Scene(menuAsettelu);
        ikkuna.setScene(menuNakyma);
        ikkuna.setTitle("Hyppelypeli");
        ikkuna.show();

        //MENU NÄYTTÖ:
        Text pojoteksti = new Text(10, 20, "Pojoja: 0");
        asettelu.getChildren().add(pojoteksti);
        AtomicInteger pojoja = new AtomicInteger();
        asettelu.setPrefSize(leveys, korkeus);

        Ukko ukko = new Ukko(leveys / 2, korkeus / 2);
        asettelu.getChildren().add(ukko.getHahmo());

        Scene nakyma = new Scene(asettelu);

        aloita.setOnMouseClicked(klik -> {
            ikkuna.setScene(nakyma);
            menussa = false;
            tasot.forEach(taso -> asettelu.getChildren().add(taso.getHahmo()));
        });

        ikkuna.show();

        HashMap<KeyCode, Boolean> painallukset = new HashMap<>();

        nakyma.setOnKeyPressed(klik -> {
            painallukset.put(klik.getCode(), Boolean.TRUE);
        });

        nakyma.setOnKeyReleased(klik -> {
            painallukset.put(klik.getCode(), Boolean.FALSE);
        });

        new AnimationTimer() {
            @Override
            public void handle(long nykyhetki) {

                if (menussa == false) {
                    for (Taso taso : tasot) {
                        taso.setLiike(new Point2D(0, 1));
                        taso.liiku();
                    }
                    ukko.liiku();
                }

                int tormayksia = 0;
                for (Taso taso : tasot) {
                    if (ukko.tormaa(taso)) {
                        tormayksia++;
                    }
                }
                int ukkoY = ukko.getSijantiY();

                if (painallukset.getOrDefault(KeyCode.LEFT, Boolean.FALSE)) {
                    ukko.vasemmalle();
                }
                if (painallukset.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)) {
                    ukko.oikealle();
                }

                ukko.setHyppyaika(ukko.getHyppyaika() + 1);

                if (tormayksia != 0 && ukko.getHyppyaika() < (int) maksimiHyppyAika / 6) {
                    ukko.setIlmassa(true);
                }

                if (ukko.getIlmassa() == true) {
                    if (ukko.getHyppyaika() < (int) maksimiHyppyAika * 1 / 6) {
                        ukko.setLiike(new Point2D(0, -8));
                    } else if (ukko.getHyppyaika() < (int) maksimiHyppyAika * 2 / 6) {
                        ukko.setLiike(new Point2D(0, -6));
                    } else if (ukko.getHyppyaika() < (int) maksimiHyppyAika * 3 / 6) {
                        ukko.setLiike(new Point2D(0, -4));
                    } else if (ukko.getHyppyaika() < (int) maksimiHyppyAika * 4 / 6) {
                        ukko.setLiike(new Point2D(0, -2));
                    } else if (ukko.getHyppyaika() < (int) maksimiHyppyAika * 5 / 6) {
                        ukko.setLiike(new Point2D(0, 0));
                    } else if (ukko.getHyppyaika() < maksimiHyppyAika) {
                        ukko.setLiike(new Point2D(0, 2));
                    } else if (ukko.getHyppyaika() >= maksimiHyppyAika) {
                        ukko.setIlmassa(false);
                        ukko.setHyppyaika(0);
                        ukko.setLiike(new Point2D(0, 4));
                    }
                } else {
                    ukko.setHyppyaika(0);
                    ukko.setLiike(new Point2D(0, 4));
                }

                if (ukko.getSijantiY() < HyppelypeliMain.korkeus * 0.3) {
                    ukko.setSijaintiY(ukko.getSijantiY() + 3);
                    for (Taso taso : tasot) {
                        taso.setSijaintiY(taso.getSijantiY() + 3);
                    }
                }

                for (Taso taso : tasot) {
                    if (taso.getSijantiY() >= HyppelypeliMain.korkeus) {
                        taso.setSijaintiY(0);
                        taso.setSijaintiX(randomi.nextInt(HyppelypeliMain.leveys));
                        pojoteksti.setText("Pojoja: " + pojoja.addAndGet(1));
                    }
                }

                if (ukkoY == HyppelypeliMain.korkeus) {
                    stop();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(HyppelypeliMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    BorderPane loppuasettelu = new BorderPane();
                    VBox loppuasetteluntekstit = new VBox();
                    Button uudestaanNappi = new Button("Uudestaan!");
                    Button loppuNappi = new Button("Lopeta");
                    loppuasetteluntekstit.getChildren().addAll(new Text("Kiitos pelaamisesta!"),
                            pojoteksti, uudestaanNappi, loppuNappi);
                    loppuasetteluntekstit.setPadding(new Insets(10));
                    loppuasetteluntekstit.setSpacing(20);

                    loppuasettelu.setCenter(loppuasetteluntekstit);
                    ikkuna.setScene(new Scene(loppuasettelu, leveys, korkeus));

                    uudestaanNappi.setOnMouseClicked(klik -> {
                        System.exit(0);
                    });

                    loppuNappi.setOnMouseClicked(klik -> {
                        System.exit(0);
                    });

                }

            }
        }.start();
    }
}
