package scenes.menuview;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OptionsView extends VBox {
    Label toBeDoneText = new Label("To be done");

    public OptionsView(){
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        setToBeDoneTextStyle();
        this.getChildren().add(toBeDoneText);
    }

    private void setToBeDoneTextStyle() {
        VBox.setMargin(toBeDoneText, new Insets(
                Main.WINDOW_HEIGHT * 0.2812,
                Main.WINDOW_WIDTH * 0.0167,
                0,
                Main.WINDOW_WIDTH * 0.0167));

        toBeDoneText.getStyleClass().addAll("text-font", "instruction-text");
        toBeDoneText.setMinWidth(Main.WINDOW_WIDTH * 0.4259);
        toBeDoneText.setAlignment(Pos.BASELINE_CENTER);
    }
}
