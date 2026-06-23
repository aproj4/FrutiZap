package frutizap.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Vista simple que representa una fracción visualmente: dibuja un rectángulo dividido
 * en `denominator` partes y colorea `numerator` partes.
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

        // container for the whole fruit representation (simple bar divided)
        Pane bar = new Pane();
        bar.setPrefSize(width, height);

        double partWidth = width / denominator;
        for (int i = 0; i < denominator; i++) {
            Rectangle r = new Rectangle(i * partWidth, 0, partWidth - 2, height);
            r.setArcWidth(8);
            r.setArcHeight(8);
            if (i < numerator) {
                r.setFill(Color.web("#ff7f50")); // colored portion
            } else {
                r.setFill(Color.web("#ffe6d6")); // empty portion
            }
            r.setStroke(Color.web("#d35400"));
            r.setStrokeWidth(1);
            bar.getChildren().add(r);
        }

        // textual fraction label
        Text label = new Text(numerator + "/" + denominator);
        label.setFont(Font.font(18));

        StackPane wrapper = new StackPane(bar);
        wrapper.setPrefSize(width, height);

        getChildren().addAll(wrapper, label);
    }

    public void updateFraction(int numerator, int denominator) {
        this.numerator = Math.max(0, numerator);
        this.denominator = Math.max(1, denominator);
        buildView();
    }
}
