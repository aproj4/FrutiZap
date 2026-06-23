package frutizap.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Vista simple de menú principal con botones Jugar / Configuración / Salir.
 */
public class MainMenuView {

    private final VBox root;

    public MainMenuView(Runnable onPlay, Runnable onSettings, Runnable onExit) {
        root = new VBox(16);
        root.setPadding(new Insets(24));
        root.setAlignment(Pos.CENTER);

        Label title = new Label("FrutiZap");
        title.setFont(Font.font(30));

        Button play = new Button("Jugar");
        play.setPrefWidth(200);
        play.setOnAction(e -> onPlay.run());

        Button settings = new Button("Configuración");
        settings.setPrefWidth(200);
        settings.setOnAction(e -> onSettings.run());

        Button exit = new Button("Salir");
        exit.setPrefWidth(200);
        exit.setOnAction(e -> onExit.run());

        root.getChildren().addAll(title, play, settings, exit);
    }

    public Parent getRoot() {
        return root;
    }
}
