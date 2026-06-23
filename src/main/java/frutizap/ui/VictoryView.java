package frutizap.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Vista de victoria que muestra puntaje y estrellas y permite reintentar o volver al menú.
 */
public class VictoryView {

    private final VBox root;

    public VictoryView(int puntaje, Runnable onReplay, Runnable onMenu) {
        root = new VBox(12);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label title = new Label("🎉 ¡Victoria! 🎉");
        title.setFont(Font.font(28));

        Label score = new Label("Puntaje final: " + puntaje);
        score.setFont(Font.font(18));

        // calcular estrellas simples
        int estrellas = 1;
        if (puntaje >= 30) estrellas = 3;
        else if (puntaje >= 15) estrellas = 2;

        HBox stars = new HBox(6);
        stars.setAlignment(Pos.CENTER);
        String star = "⭐";
        for (int i = 0; i < estrellas; i++) {
            Label s = new Label(star);
            s.setFont(Font.font(26));
            stars.getChildren().add(s);
        }

        HBox actions = new HBox(12);
        actions.setAlignment(Pos.CENTER);
        Button replay = new Button("Jugar de nuevo");
        Button menu = new Button("Volver al menú");
        replay.setOnAction(e -> onReplay.run());
        menu.setOnAction(e -> onMenu.run());
        actions.getChildren().addAll(replay, menu);

        root.getChildren().addAll(title, score, stars, actions);
    }

    public Parent getRoot() {
        return root;
    }
}
