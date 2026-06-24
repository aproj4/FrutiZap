package frutizap.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Vista simple que representa una fracción visualmente:
 * dibuja un rectángulo dividido en 'denominator' partes
 * y colorea 'numerator' partes.
 */
public class FractionView extends HBox {

    private int numerator;
    private int denominator;

    public FractionView(int numerator, int denominator) {
        this.numerator = Math.max(0, numerator);
        this.denominator = Math.max(1, denominator);

        setSpacing(8);
        setPadding(new Insets(6));

        buildView();
    }

    private void buildView() {
        getChildren().clear();

        double width = 160;
        double height = 80;

        // Contenedor de la representación visual de la fracción
        Pane bar = new Pane();
        bar.setPrefSize(width, height);

        double partWidth = width / denominator;

        for (int i = 0; i < denominator; i++) {

            Rectangle r = new Rectangle(
                    i * partWidth,
                    0,
                    partWidth - 2,
                    height
            );

            r.setArcWidth(8);
            r.setArcHeight(8);

            if (i < numerator) {
                r.setFill(Color.web("#ff7f50")); // Parte coloreada
            } else {
                r.setFill(Color.web("#ffe6d6")); // Parte sin colorear
            }

            r.setStroke(Color.web("#d35400"));
            r.setStrokeWidth(1);

            bar.getChildren().add(r);
        }

        StackPane wrapper = new StackPane(bar);
        wrapper.setPrefSize(width, height);

        // Solo se muestra la representación gráfica
        getChildren().add(wrapper);
    }

    public void updateFraction(int numerator, int denominator) {
        this.numerator = Math.max(0, numerator);
        this.denominator = Math.max(1, denominator);

        buildView();
    }
}
