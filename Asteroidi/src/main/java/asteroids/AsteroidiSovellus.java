package asteroids;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AsteroidiSovellus extends Application {

    public static int LEVEYS = 640;
    public static int KORKEUS = 480;

    public static void main(String[] args) {
        launch(AsteroidiSovellus.class);
        System.out.println("Hei maailma!");
    }

    @Override
    public void start(Stage ikkuna) throws Exception {
        Random randomi = new Random();
        Pane ruutu = new Pane();
        Text text = new Text(10, 20, "Points: 0");
        ruutu.getChildren().add(text);
        AtomicInteger pisteet = new AtomicInteger();
        ruutu.setPrefSize(LEVEYS, KORKEUS);

        Alus alus = new Alus(LEVEYS / 2, KORKEUS / 2);
        ArrayList<Ammus> ammukset = new ArrayList<>();
        ArrayList<Asteroidi> asteroidit = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Asteroidi asteroidi = new Asteroidi(randomi.nextInt(100), randomi.nextInt(100));
            asteroidit.add(asteroidi);
        }

        //loppurupinat
        ruutu.getChildren().addAll(alus.getHahmo());
        asteroidit.forEach(asteroidi -> ruutu.getChildren().add(asteroidi.getHahmo()));

        Scene nakyma = new Scene(ruutu);
        ikkuna.setTitle("Asteroids!");
        ikkuna.setScene(nakyma);
        ikkuna.show();

        //aluksen liike + kääntäminen
        Map<KeyCode, Boolean> painetutNapit = new HashMap<>();

        nakyma.setOnKeyPressed(klik -> {
            painetutNapit.put(klik.getCode(), Boolean.TRUE);
        });

        nakyma.setOnKeyReleased(klik -> {
            painetutNapit.put(klik.getCode(), Boolean.FALSE);
        });

        
        new AnimationTimer() {
            @Override
            public void handle(long nykyhetki) {
                if (painetutNapit.getOrDefault(KeyCode.LEFT, false)) {
                    alus.kaannaVasemmalle();
                }
                if (painetutNapit.getOrDefault(KeyCode.RIGHT, false)) {
                    alus.kaannaOikealle();
                }
                if (painetutNapit.getOrDefault(KeyCode.UP, false)) {
                    alus.kiihdyta();
                }

                if (painetutNapit.getOrDefault(KeyCode.SPACE, false) && ammukset.size() < 3 && pisteet.get() < 50000) {
                    painetutNapit.put(KeyCode.SPACE, Boolean.FALSE);
                    Ammus ammus = new Ammus((int) alus.getHahmo().getTranslateX(), (int) alus.getHahmo().getTranslateY());
                    ammus.getHahmo().setRotate(alus.getHahmo().getRotate());
                    ammukset.add(ammus);

                    ammus.kiihdyta();
                    ammus.setLiike(ammus.getLiike().normalize().multiply(3));
                    ruutu.getChildren().add(ammus.getHahmo());
                } else if (painetutNapit.getOrDefault(KeyCode.SPACE, false) && ammukset.size() < 9 && pisteet.get() >= 50000 && pisteet.get() < 100000) {
                    painetutNapit.put(KeyCode.SPACE, Boolean.FALSE);
                    //eka paukku
                    Ammus ammus = new Ammus((int) alus.getHahmo().getTranslateX(), (int) alus.getHahmo().getTranslateY());
                    ammus.getHahmo().setRotate(alus.getHahmo().getRotate());
                    ammukset.add(ammus);

                    ammus.kiihdyta();
                    ammus.setLiike(ammus.getLiike().normalize().multiply(3));
                    ruutu.getChildren().add(ammus.getHahmo());
                    //eka paukku, toka paukku
                    Ammus ammus2 = new Ammus((int) alus.getHahmo().getTranslateX(), (int) alus.getHahmo().getTranslateY());
                    ammus2.getHahmo().setRotate(alus.getHahmo().getRotate() - 10);
                    ammukset.add(ammus2);

                    ammus2.kiihdyta();
                    ammus2.setLiike(ammus2.getLiike().normalize().multiply(3));
                    ruutu.getChildren().add(ammus2.getHahmo());
                    //toka paukku, kolmas paukku
                    Ammus ammus3 = new Ammus((int) alus.getHahmo().getTranslateX(), (int) alus.getHahmo().getTranslateY());
                    ammus3.getHahmo().setRotate(alus.getHahmo().getRotate() + 10);
                    ammukset.add(ammus3);

                    ammus3.kiihdyta();
                    ammus3.setLiike(ammus3.getLiike().normalize().multiply(3));
                    ruutu.getChildren().add(ammus3.getHahmo());
                } else if (painetutNapit.getOrDefault(KeyCode.SPACE, false) && ammukset.size() < 10 && pisteet.get() >= 100000) {
                    painetutNapit.put(KeyCode.SPACE, Boolean.FALSE);
                    //eka paukku
                    Ammus ammus = new Ammus((int) alus.getHahmo().getTranslateX(), (int) alus.getHahmo().getTranslateY());
                    ammus.getHahmo().setRotate(alus.getHahmo().getRotate());
                    ammukset.add(ammus);

                    ammus.kiihdyta();
                    ammus.setLiike(ammus.getLiike().normalize().multiply(3));
                    ruutu.getChildren().add(ammus.getHahmo());
                    //eka paukku, toka paukku
                    Ammus ammus2 = new Ammus((int) alus.getHahmo().getTranslateX(), (int) alus.getHahmo().getTranslateY());
                    ammus2.getHahmo().setRotate(alus.getHahmo().getRotate() - 10);
                    ammukset.add(ammus2);

                    ammus2.kiihdyta();
                    ammus2.setLiike(ammus2.getLiike().normalize().multiply(3));
                    ruutu.getChildren().add(ammus2.getHahmo());
                    //toka paukku, kolmas paukku
                    Ammus ammus3 = new Ammus((int) alus.getHahmo().getTranslateX(), (int) alus.getHahmo().getTranslateY());
                    ammus3.getHahmo().setRotate(alus.getHahmo().getRotate() + 10);
                    ammukset.add(ammus3);

                    ammus3.kiihdyta();
                    ammus3.setLiike(ammus3.getLiike().normalize().multiply(3));
                    ruutu.getChildren().add(ammus3.getHahmo());
                    //kolmas paukku, neljäs paukku
                    Ammus ammus4 = new Ammus((int) alus.getHahmo().getTranslateX(), (int) alus.getHahmo().getTranslateY());
                    ammus4.getHahmo().setRotate(alus.getHahmo().getRotate() + 20);
                    ammukset.add(ammus4);

                    ammus4.kiihdyta();
                    ammus4.setLiike(ammus4.getLiike().normalize().multiply(3));
                    ruutu.getChildren().add(ammus4.getHahmo());
                    //neljäs paukku, viides paukku
                    Ammus ammus5 = new Ammus((int) alus.getHahmo().getTranslateX(), (int) alus.getHahmo().getTranslateY());
                    ammus5.getHahmo().setRotate(alus.getHahmo().getRotate() - 20);
                    ammukset.add(ammus5);

                    ammus5.kiihdyta();
                    ammus5.setLiike(ammus5.getLiike().normalize().multiply(3));
                    ruutu.getChildren().add(ammus5.getHahmo());
                }

                alus.liiku();
                asteroidit.forEach(asteroidi -> asteroidi.liiku());

                asteroidit.forEach(asteroidi -> {
                    if (alus.tormaa(asteroidi)) {
                        stop();
                    }
                });

                ammukset.forEach(ammus -> ammus.liiku());

                ammukset.forEach(ammus -> {
                    asteroidit.forEach(asteroidi -> {
                        if (ammus.tormaa(asteroidi)) {
                            text.setText("Pisteet: " + pisteet.addAndGet(1000));
                            ammus.setElossa(false);
                            asteroidi.setElossa(false);
                        }
                    });
                });

                ammukset.stream()
                        .filter(ammus -> !ammus.isElossa())
                        .forEach(ammus -> ruutu.getChildren().remove(ammus.getHahmo()));
                ammukset.removeAll(ammukset.stream()
                        .filter(ammus -> !ammus.isElossa())
                        .collect(Collectors.toList()));

                asteroidit.stream()
                        .filter(asteroidi -> !asteroidi.isElossa())
                        .forEach(asteroidi -> ruutu.getChildren().remove(asteroidi.getHahmo()));
                asteroidit.removeAll(asteroidit.stream()
                        .filter(asteroidi -> !asteroidi.isElossa())
                        .collect(Collectors.toList()));
                if (Math.random() < 0.05 && pisteet.get() < 25000) {
                    Asteroidi asteroidi = new Asteroidi(LEVEYS, LEVEYS);
                    if (!asteroidi.tormaa(alus)) {
                        asteroidit.add(asteroidi);
                        ruutu.getChildren().add(asteroidi.getHahmo());
                    }
                } else if (Math.random() < 0.1 && pisteet.get() >= 25000 && pisteet.get() < 75000) {
                    Asteroidi asteroidi = new Asteroidi(LEVEYS, LEVEYS);
                    asteroidi.setLiike(asteroidi.getLiike().multiply(2));
                    if (!asteroidi.tormaa(alus)) {
                        asteroidit.add(asteroidi);
                        ruutu.getChildren().add(asteroidi.getHahmo());
                    }
                } else if (Math.random() < 0.3 && pisteet.get() >= 75000) {
                    Asteroidi asteroidi = new Asteroidi(LEVEYS, LEVEYS);
                    if (randomi.nextInt(100) < 50) {
                        asteroidi.setLiike(asteroidi.getLiike().multiply(3));
                    } else if (randomi.nextInt(100) < 50) {
                        asteroidi.setLiike(asteroidi.getLiike().multiply(2));
                    } else {
                        asteroidi.setLiike(asteroidi.getLiike().multiply(4));
                    }
                    if (!asteroidi.tormaa(alus)) {
                        asteroidit.add(asteroidi);
                        ruutu.getChildren().add(asteroidi.getHahmo());
                    }
                }
            }

        }
                .start();
        //aluksen liike + kääntäminen
    }

}
