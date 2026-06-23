package frutizap.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entrada JavaFX para el juego FrutiZap.
 * Inicializa la ventana principal y arranca la vista del juego.
 */
public class MainFX extends Application {

    /**
     * Método principal que lanza la aplicación JavaFX.
     *
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Crea y muestra la escena principal del juego.
     *
     * @param primaryStage escenario principal de JavaFX
     */
    @Override
    public void start(Stage primaryStage) {
        // start on main menu; provide navigation callbacks
        Runnable onExit = () -> primaryStage.close();
        Scene scene = new Scene(new javafx.scene.layout.Region(), 680, 520);
        primaryStage.setTitle("FrutiZap - FrutiZap");
        primaryStage.setScene(scene);

        // onPlay: create GameView and show it
        Runnable onPlay = () -> {
            GameView gameView = new GameView();
            scene.setRoot(gameView.getRoot());
            primaryStage.sizeToScene();
            gameView.startGame();
        };

        Runnable onSettings = () -> {
            // simple placeholder: reuse main menu for now
            MainMenuView settings = new MainMenuView(() -> onPlay.run(), () -> {}, () -> onExit.run());
            scene.setRoot(settings.getRoot());
        };

        MainMenuView menu = new MainMenuView(onPlay, onSettings, onExit);
        scene.setRoot(menu.getRoot());
        primaryStage.show();
    }
}
