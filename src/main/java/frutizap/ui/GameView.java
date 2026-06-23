package frutizap.ui;

import frutizap.logic.JuegoFracciones;
import frutizap.logic.Tablero;
import frutizap.model.Jugador;
import frutizap.model.Tarjeta;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Vista del juego de memoria FrutiZap en JavaFX.
 * Contiene la lógica de la interfaz de usuario y la
 * gestión de las fases de memoria y preguntas de fracciones.
 */
public class GameView {

    private Tablero tablero;
    private Jugador jugador;
    private final VBox root;
    private GridPane boardPane;
    private HBox infoBar;
    private Label titleLabel;
    private Button[] cardButtons;
    private Button reiniciarButton;
    private final Label attemptsLabel;
    private final Label scoreLabel;
    private final Label statusLabel;

    private JuegoFracciones juegoFracciones;
    private VBox fractionPane;
    private Label fractionTitleLabel;
    private Label questionLabel;
    private Label fruitLabel;
    private Button[] fractionOptionButtons;
    private FractionView fractionView;
    private Label timerLabel;
    private Label feedbackLabel;
    private Button submitFractionButton;
    private Button retryFractionButton;
    private Button contFractionButton;

    private int firstSelectedIndex = -1;
    private int secondSelectedIndex = -1;
    private boolean busy = false;
    private int intentos = 0;
    private Phase currentPhase = Phase.MEMORY;
    private Timeline countdownTimer;
    private int timeRemainingSeconds = 60;
    private static final int MAX_TIME_SECONDS = 60;
    private static final int POINTS_MATCH = 10;
    private static final int POINTS_CORRECT_FRACTION = 5;
    private static final int POINTS_WRONG_FRACTION = -2;

    private enum Phase {
        MEMORY,
        FRACTIONS,
        END
    }

    public GameView() {
        this.tablero = new Tablero();
        this.jugador = new Jugador("Jugador");
        this.root = new VBox(16);
        this.cardButtons = new Button[tablero.getTotalTarjetas()];
        this.attemptsLabel = new Label();
        this.scoreLabel = new Label();
        this.statusLabel = new Label();
        buildUI();
    }

    /**
     * Retorna el nodo raíz que representa toda la interfaz del juego.
     *
     * @return nodo raíz de JavaFX para la vista del juego
     */
    public Parent getRoot() {
        return root;
    }

    /**
     * Inicializa el estado visual del juego y actualiza las etiquetas.
     */
    public void startGame() {
        actualizarEstado();
    }

    /**
     * Construye los componentes visuales principales del juego.
     * Crea el tablero de tarjetas, la barra de información y el panel de preguntas.
     */
    private void buildUI() {
        root.setPadding(new Insets(20));
        root.setSpacing(16);

        titleLabel = new Label("FrutiZap - Memoria");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        attemptsLabel.setFont(Font.font(16));
        scoreLabel.setFont(Font.font(16));
        infoBar = new HBox(24, attemptsLabel, scoreLabel);
        infoBar.setAlignment(Pos.CENTER_LEFT);

        statusLabel.setStyle("-fx-font-size: 14px;");

        timerLabel = new Label();
        timerLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #d35400;");
        timerLabel.setVisible(false);

        boardPane = new GridPane();
        boardPane.setHgap(12);
        boardPane.setVgap(12);
        boardPane.setAlignment(Pos.CENTER);

        int cols = 4;
        for (int index = 0; index < tablero.getTotalTarjetas(); index++) {
            Button cardButton = new Button("?");
            cardButton.setPrefSize(120, 120);
            cardButton.setFont(Font.font(32));
            cardButton.setTextFill(Color.web("#2d3436"));
            cardButton.setBackground(new Background(new BackgroundFill(getColorForIndex(index % 3), new CornerRadii(8), Insets.EMPTY)));
            final int selectionIndex = index;
            cardButton.setOnAction(event -> manejarSeleccion(selectionIndex));
            cardButtons[index] = cardButton;
            boardPane.add(cardButton, index % cols, index / cols);
        }


        Button reiniciarButton = new Button("Reiniciar");
        reiniciarButton.setOnAction(event -> reiniciarJuego());
        reiniciarButton.setStyle("-fx-font-size: 14px;");

        // keep reference so we can reuse when switching screens
        this.reiniciarButton = reiniciarButton;

        fractionPane = buildFractionPane();
        fractionPane.setVisible(false);

        root.getChildren().addAll(titleLabel, infoBar, timerLabel, statusLabel, boardPane, fractionPane, reiniciarButton);
    }

    private VBox buildFractionPane() {
        VBox pane = new VBox(12);
        pane.setPadding(new Insets(10));
        pane.setBackground(new Background(new BackgroundFill(Color.web("#fef9e7"), new CornerRadii(8), Insets.EMPTY)));
        pane.setStyle("-fx-border-color: #f7dc6f; -fx-border-width: 2;");

        fractionTitleLabel = new Label("Fase 2: Actividades de fracciones");
        fractionTitleLabel.setFont(Font.font(20));
        fractionTitleLabel.setStyle("-fx-font-weight: bold;");

        questionLabel = new Label();
        questionLabel.setFont(Font.font(16));

        fruitLabel = new Label();
        fruitLabel.setFont(Font.font(18));

        HBox optionsRow = new HBox(12);
        optionsRow.setAlignment(Pos.CENTER);
        fractionOptionButtons = new Button[3];
        for (int i = 0; i < fractionOptionButtons.length; i++) {
            Button opt = new Button();
            opt.setPrefSize(140, 80);
            opt.setFont(Font.font(20));
            opt.setWrapText(true);
            opt.setStyle("-fx-background-radius: 8; -fx-border-radius: 8;");
            final int idx = i;
            opt.setOnAction(evt -> handleFractionSelection(opt.getText()));
            fractionOptionButtons[i] = opt;
            optionsRow.getChildren().add(opt);
        }

        feedbackLabel = new Label();
        feedbackLabel.setFont(Font.font(16));

        // visual fraction view
        fractionView = new FractionView(1, 2);

        // control buttons for retry/continue appear on incorrect
        HBox actionRow = new HBox(12);
        actionRow.setAlignment(Pos.CENTER);
        retryFractionButton = new Button("Intentar de nuevo");
        contFractionButton = new Button("Continuar");
        retryFractionButton.setVisible(false);
        contFractionButton.setVisible(false);
        retryFractionButton.setOnAction(e -> {
            for (Button b : fractionOptionButtons) b.setDisable(false);
            feedbackLabel.setText("");
            retryFractionButton.setVisible(false);
            contFractionButton.setVisible(false);
        });
        contFractionButton.setOnAction(e -> {
            retryFractionButton.setVisible(false);
            contFractionButton.setVisible(false);
            if (juegoFracciones.tieneSiguientePregunta()) mostrarPreguntaActual(); else terminarJuego(true);
        });
        actionRow.getChildren().addAll(retryFractionButton, contFractionButton);

        pane.getChildren().addAll(fractionTitleLabel, questionLabel, fruitLabel, fractionView, optionsRow, actionRow, feedbackLabel);
        return pane;
    }

    /**
     * Maneja la selección de una tarjeta por parte del jugador.
     * Valida el estado actual del juego y actualiza la selección.
     *
     * @param index índice de la tarjeta seleccionada
     */
    private void manejarSeleccion(int index) {
        if (busy || currentPhase != Phase.MEMORY) {
            return;
        }

        if (tablero.getTarjeta(index).isFueEncontrada() || tablero.getTarjeta(index).isEstaVolteada()) {
            return;
        }

        tablero.getTarjeta(index).voltear();
        actualizarBoton(index);

        if (firstSelectedIndex == -1) {
            firstSelectedIndex = index;
            statusLabel.setText("Selecciona otra tarjeta.");
            return;
        }

        if (secondSelectedIndex == -1 && index != firstSelectedIndex) {
            secondSelectedIndex = index;
            intentos++;
            actualizarEstado();
            evaluarPareja();
        }
    }

    /**
     * Evalúa si las dos tarjetas seleccionadas forman una pareja.
     * Actualiza el puntaje, el estado de la UI y el flujo del juego.
     */
    private void evaluarPareja() {
        busy = true;
        boolean pareja = tablero.verificarPareja(firstSelectedIndex, secondSelectedIndex);
        if (pareja) {
            jugador.sumarPuntos(POINTS_MATCH);
            statusLabel.setText("¡Pareja encontrada!");
            marcarParejasEncontradas();
            actualizarEstado();
            limpiarSeleccion();
            busy = false;
            if (tablero.juegoTerminado()) {
                statusLabel.setText("Has completado la Fase 1. Preparando Fase 2...");
                iniciarFaseFracciones();
            }
        } else {
            statusLabel.setText("No son pareja. Intentos: " + intentos);
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> {
                actualizarBoton(firstSelectedIndex);
                actualizarBoton(secondSelectedIndex);
                limpiarSeleccion();
                busy = false;
            });
            delay.play();
        }
    }

    /**
     * Actualiza la apariencia de un botón de tarjeta según el estado de la tarjeta.
     *
     * @param index índice de la tarjeta a actualizar
     */
    private void actualizarBoton(int index) {
        Tarjeta tarjeta = tablero.getTarjeta(index);
        Button button = cardButtons[index];
        if (tarjeta.isFueEncontrada() || tarjeta.isEstaVolteada()) {
            button.setText(tarjeta.getFruta());
            button.setDisable(tarjeta.isFueEncontrada());
        } else {
            button.setText("?");
            button.setDisable(false);
        }
    }

    /**
     * Actualiza los indicadores de intentos y puntaje, y refresca los botones del tablero.
     */
    private void actualizarEstado() {
        attemptsLabel.setText("Intentos: " + intentos);
        scoreLabel.setText("Puntaje: " + jugador.getPuntaje());
        for (int index = 0; index < tablero.getTotalTarjetas(); index++) {
            actualizarBoton(index);
        }
    }

    /**
     * Inicializa la Fase 2 del juego con preguntas de fracciones y activa el temporizador.
     */
    private void iniciarFaseFracciones() {
        currentPhase = Phase.FRACTIONS;
        // switch to a dedicated fraction screen to avoid UI overlap
        fractionPane.setVisible(true);
        timerLabel.setVisible(true);
        statusLabel.setText("Fase 2: responde las preguntas de fracciones.");
        // replace root children so the board is not visible underneath
        root.getChildren().clear();
        titleLabel.setText("FrutiZap - Fracciones");
        root.getChildren().addAll(titleLabel, infoBar, timerLabel, statusLabel, fractionPane, reiniciarButton);
        juegoFracciones = new JuegoFracciones();
        juegoFracciones.agregarPregunta(new frutizap.model.FraccionPregunta("Manzana", 1, 2, "Selecciona la fracción que representa la mitad de una manzana."));
        juegoFracciones.agregarPregunta(new frutizap.model.FraccionPregunta("Naranja", 1, 4, "Selecciona la fracción que representa un cuarto de una naranja."));
        juegoFracciones.agregarPregunta(new frutizap.model.FraccionPregunta("Uva", 3, 4, "Selecciona la fracción que representa tres cuartos de un racimo de uvas."));
        juegoFracciones.mezclarPreguntas();
        timeRemainingSeconds = MAX_TIME_SECONDS;
        iniciarTemporizador();
        mostrarPreguntaActual();
    }

    /**
     * Muestra la siguiente pregunta de fracciones en la UI.
     * Si no quedan preguntas, finaliza el juego con victoria.
     */
    private void mostrarPreguntaActual() {
        if (juegoFracciones == null || !juegoFracciones.tieneSiguientePregunta()) {
            terminarJuego(true);
            return;
        }
        frutizap.model.FraccionPregunta pregunta = juegoFracciones.obtenerSiguientePregunta();
        questionLabel.setText(pregunta.getDescripcion());
        fruitLabel.setText("🍎 " + pregunta.getFruta());
        feedbackLabel.setText("");
        fractionView.updateFraction(pregunta.getNumerador(), pregunta.getDenominador());

        // Generar tres opciones: la correcta y dos distractoras simples
        String correct = pregunta.getFraccionTexto();
        String[] distractors = generarDistractores(pregunta.getNumerador(), pregunta.getDenominador());
        // Mezclar y asignar a botones
        String[] options = new String[]{correct, distractors[0], distractors[1]};
        java.util.List<String> list = java.util.Arrays.asList(options);
        java.util.Collections.shuffle(list);
        for (int i = 0; i < fractionOptionButtons.length; i++) {
            fractionOptionButtons[i].setText(list.get(i));
            fractionOptionButtons[i].setDisable(false);
            fractionOptionButtons[i].setBackground(new Background(new BackgroundFill(getColorForIndex(i), new CornerRadii(8), Insets.EMPTY)));
        }
        if (retryFractionButton != null) retryFractionButton.setVisible(false);
        if (contFractionButton != null) contFractionButton.setVisible(false);
    }

    /**
     * Valida la respuesta ingresada por el jugador para la pregunta actual.
     * Actualiza el puntaje y avanza a la siguiente pregunta o termina el juego.
     */
    private void validarRespuestaFraccion() {
        // Método obsoleto para entrada numérica; se usa handleFractionSelection con botones.
        return;
    }

    /**
     * Maneja la selección de una opción de fracción (botón).
     * @param optionText texto de la opción, ejemplo "1/2"
     */
    private void handleFractionSelection(String optionText) {
        if (currentPhase != Phase.FRACTIONS || juegoFracciones == null) return;
        String[] parts = optionText.split("/");
        int numerador = 0;
        int denominador = 1;
        try {
            numerador = Integer.parseInt(parts[0].trim());
            denominador = Integer.parseInt(parts[1].trim());
        } catch (Exception e) {
            feedbackLabel.setText("Opción inválida.");
            return;
        }

        frutizap.model.FraccionPregunta currentQuestion = juegoFracciones.obtenerSiguientePregunta();
        boolean correcta = juegoFracciones.validarRespuestaActual(numerador, denominador);
        // disable options
        for (Button b : fractionOptionButtons) b.setDisable(true);
        if (correcta) {
            jugador.sumarPuntos(POINTS_CORRECT_FRACTION);
            feedbackLabel.setText("✅ ¡Correcto! +" + POINTS_CORRECT_FRACTION + " puntos.");
            actualizarEstado();
            // avanzar automáticamente tras breve pausa
            PauseTransition pause = new PauseTransition(Duration.seconds(1.0));
            pause.setOnFinished(evt -> {
                if (juegoFracciones.tieneSiguientePregunta()) mostrarPreguntaActual(); else terminarJuego(true);
            });
            pause.play();
        } else {
            jugador.sumarPuntos(POINTS_WRONG_FRACTION);
            feedbackLabel.setText("❌ Incorrecto. La respuesta correcta era " + currentQuestion.getFraccionTexto());
            actualizarEstado();
            // mostrar botones para intentar o continuar
            if (retryFractionButton != null) retryFractionButton.setVisible(true);
            if (contFractionButton != null) contFractionButton.setVisible(true);
        }
    }

    private String[] generarDistractores(int num, int den) {
        // Generador simple de distractores: variaciones pequeñas del denominador/numerador
        int a = Math.max(1, num);
        int b = Math.max(2, den);
        String d1 = (a == 1 ? 1 : a - 1) + "/" + b;
        String d2 = (a + 1) + "/" + (b == 2 ? 4 : b);
        if (d1.equals(a + "/" + b)) d1 = (a + 2) + "/" + (b + 1);
        return new String[]{d1, d2};
    }

    private Color getColorForIndex(int i) {
        Color[] palette = new Color[]{Color.web("#ffadad"), Color.web("#ffd6a5"), Color.web("#caffbf")};
        return palette[i % palette.length];
    }

    /**
     * Inicia el temporizador de la Fase 2 y actualiza la etiqueta de tiempo.
     * Finaliza el juego cuando el tiempo llega a cero.
     */
    private void iniciarTemporizador() {
        if (countdownTimer != null) {
            countdownTimer.stop();
        }
        timerLabel.setText("Tiempo restante: " + timeRemainingSeconds + "s");
        countdownTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeRemainingSeconds--;
            timerLabel.setText("Tiempo restante: " + timeRemainingSeconds + "s");
            if (timeRemainingSeconds <= 0) {
                countdownTimer.stop();
                terminarJuego(false);
            }
        }));
        countdownTimer.setCycleCount(Timeline.INDEFINITE);
        countdownTimer.play();
    }

    /**
     * Termina el juego y muestra el mensaje final según la condición de victoria.
     *
     * @param victoria true si el jugador respondió todas las preguntas, false si el tiempo se agotó
     */
    private void terminarJuego(boolean victoria) {
        currentPhase = Phase.END;
        if (countdownTimer != null) {
            countdownTimer.stop();
        }
        if (fractionOptionButtons != null) {
            for (Button b : fractionOptionButtons) b.setDisable(true);
        }
        // show a dedicated victory/end screen to avoid overlap with other panes
        if (victoria) {
            // build victory view and navigate
            javafx.scene.Scene scene = root.getScene();
            if (scene != null) {
                VictoryView v = new VictoryView(jugador.getPuntaje(), () -> {
                    // replay: recreate game view
                    GameView g = new GameView();
                    scene.setRoot(g.getRoot());
                    g.startGame();
                }, () -> {
                    // back to menu
                    MainMenuView menu = new MainMenuView(() -> {
                        GameView g = new GameView();
                        scene.setRoot(g.getRoot());
                        g.startGame();
                    }, () -> {}, () -> {
                        ((javafx.stage.Stage) scene.getWindow()).close();
                    });
                    scene.setRoot(menu.getRoot());
                });
                scene.setRoot(v.getRoot());
            } else {
                statusLabel.setText("🎉 ¡Juego completado! Puntaje final: " + jugador.getPuntaje());
                feedbackLabel.setText("Has respondido todas las fracciones. ¡Bien hecho!");
            }
        } else {
            statusLabel.setText("⏰ Se acabó el tiempo. Puntaje final: " + jugador.getPuntaje());
            feedbackLabel.setText("Intenta de nuevo para mejorar tu puntaje.");
        }
    }

    /**
     * Desactiva los botones de las tarjetas que ya fueron encontradas como pareja.
     */
    private void marcarParejasEncontradas() {
        if (tablero.getTarjeta(firstSelectedIndex).isFueEncontrada()) {
            cardButtons[firstSelectedIndex].setDisable(true);
        }
        if (tablero.getTarjeta(secondSelectedIndex).isFueEncontrada()) {
            cardButtons[secondSelectedIndex].setDisable(true);
        }
    }

    /**
     * Restablece los índices de selección a su estado inicial.
     */
    private void limpiarSeleccion() {
        firstSelectedIndex = -1;
        secondSelectedIndex = -1;
    }

    /**
     * Reinicia todo el juego, incluido el tablero, puntaje y temporizador.
     */
    private void reiniciarJuego() {
        if (countdownTimer != null) {
            countdownTimer.stop();
        }
        this.tablero = new Tablero();
        this.jugador = new Jugador("Jugador");
        this.firstSelectedIndex = -1;
        this.secondSelectedIndex = -1;
        this.busy = false;
        this.intentos = 0;
        this.currentPhase = Phase.MEMORY;
        this.cardButtons = new Button[tablero.getTotalTarjetas()];
        this.juegoFracciones = null;
        this.timeRemainingSeconds = MAX_TIME_SECONDS;
        root.getChildren().clear();
        buildUI();
        actualizarEstado();
    }
}
