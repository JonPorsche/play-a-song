package scenes.menuview.optionsview.keyoptionscellview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import static scenes.menuview.optionsview.OptionsView.OPTIONS_VIEW_HEIGHT;

public class KeyOptionCellView extends HBox {
    Label functionName;
    Button keySelectionBtn;
    HBox leftBox;
    HBox rightBox;

    public KeyOptionCellView(String functionName, String buttonLabel) {
        this.functionName = new Label(functionName);
        keySelectionBtn = new Button(buttonLabel);

        setKeyOptionCellViewStyle();
        setFunctionNameStyle();
        setKeySelectionBtnStyle();

        leftBox = new HBox(this.functionName);
        rightBox = new HBox(keySelectionBtn);
        HBox.setHgrow(leftBox, Priority.ALWAYS);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        rightBox.setAlignment(Pos.CENTER_RIGHT);

        this.getChildren().addAll(leftBox, rightBox);
    }

    private void setKeyOptionCellViewStyle() {
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        this.setMinHeight(OPTIONS_VIEW_HEIGHT * 0.1538);
    }

    private void setFunctionNameStyle() {
        functionName.getStyleClass().add("text-font");
        functionName.setId("function-name");
    }

    private void setKeySelectionBtnStyle() {
        keySelectionBtn.getStyleClass().addAll(
                "titles",
                "outlined-btn-enabled-color",
                "outlined-btn"
        );

    }
}
