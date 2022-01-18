package scenes.menuview.optionsview.keyoptionscellview;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class KeyOptionCellView extends HBox{
     Label functionName;
     Button keySelectBtn;

    public KeyOptionCellView(String functionName) {
        this.functionName = new Label(functionName);
        keySelectBtn = new Button("N / A");
        this.getChildren().addAll(this.functionName, keySelectBtn);
    }
}
