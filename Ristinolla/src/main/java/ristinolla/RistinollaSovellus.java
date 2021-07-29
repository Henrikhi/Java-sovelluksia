package ristinolla;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RistinollaSovellus extends Application {

    public static void main(String[] args) {
        launch(RistinollaSovellus.class);
    }

public int pisteetO;
public int pisteetX;

    @Override
    public void start(Stage ikkuna) throws Exception {
        pisteetO = 0;
        pisteetX = 0;
        Ristinolla ristinolla = new Ristinolla();
        ArrayList<Button> napit = new ArrayList<>();

        BorderPane asettelu = new BorderPane();
        VBox ylatekstit = new VBox();
        ylatekstit.setSpacing(5);
        Label kenenVuoro = new Label("Vuoro: X");
        Label pistetilanneotsikko = new Label("pistetilanne: ");
        Label xVoitot = new Label("X: 0");
        Label oVoitot = new Label("O: 0");
        GridPane ruudukko = new GridPane();
        ruudukko.setHgap(10);
        ruudukko.setVgap(10);
        ruudukko.setPadding(new Insets(10, 10, 10, 10));

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Button nappi = new Button(ristinolla.haeArvo(x, y));
                napit.add(nappi);
                nappi.setFont(Font.font("Monospaced", 40));
                ruudukko.add(nappi, x, y);

                int xKoord = x;
                int yKoord = y;

                nappi.setOnMouseClicked((klik) -> {
                    ristinolla.asetaArvo(xKoord, yKoord);
                    nappi.setText(ristinolla.haeArvo(xKoord, yKoord));
                    if (ristinolla.loppu()) {
                        if (ristinolla.vuoro().equals("X")) {
                            kenenVuoro.setText("pelaaja O voitti! X aloittaa seuraavan.");
                            pisteetO++;
                            oVoitot.setText("O: " + pisteetO);
                        } else {
                            kenenVuoro.setText("pelaaja X voitti! O aloittaa seuraavan.");
                            pisteetX++;
                            xVoitot.setText("X: " + pisteetX);
                        }

                        ristinolla.tyhjenna();
                        for (Button namiska : napit) {
                            namiska.setText(" ");
                        }
                    } else if (ristinolla.tasapeli()) {
                        ristinolla.tyhjenna();
                        kenenVuoro.setText("Tasapeli. Seuraavan pelin aloittaa: " + ristinolla.vuoro());
                        for (Button namiska : napit) {
                            namiska.setText(" ");
                        }
                    } else {
                        kenenVuoro.setText("Vuoro: " + ristinolla.vuoro());
                    }
                });

            }
        }

        ylatekstit.getChildren().addAll(kenenVuoro, pistetilanneotsikko, xVoitot, oVoitot);
        asettelu.setTop(ylatekstit);
        asettelu.setCenter(ruudukko);
        asettelu.setPadding(new Insets(20));

        Scene nakyma = new Scene(asettelu);

        ikkuna.setScene(nakyma);
        ikkuna.show();

    }

}
