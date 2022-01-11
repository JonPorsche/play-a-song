package scenes.menuview;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OptionsView extends VBox {
    Label toBeDoneText = new Label("To be done");

    public OptionsView(){
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        this.getChildren().add(toBeDoneText);
    }
}
