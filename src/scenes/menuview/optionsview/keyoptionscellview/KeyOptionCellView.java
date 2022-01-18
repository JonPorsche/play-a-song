package scenes.menuview.optionsview.keyoptionscellview;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import static scenes.menuview.optionsview.OptionsView.OPTIONS_VIEW_HEIGHT;

public class KeyOptionCellView extends HBox{
     Label functionName;
     Button keySelectBtn;

    public KeyOptionCellView(String functionName) {
        this.functionName = new Label(functionName);
        setKeyOptionCellViewStyle();
        keySelectBtn = new Button("N / A");
        this.getChildren().addAll(this.functionName, keySelectBtn);
    }

    private void setKeyOptionCellViewStyle(){
        this.getStyleClass().add("border-to-test-blue");
        this.setMinHeight(OPTIONS_VIEW_HEIGHT * 0.1538);
    }
}
