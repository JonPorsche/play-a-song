package scenes.menuview.selection_box_view.options_view.infotext_view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import static scenes.menuview.selection_box_view.options_view.OptionsView.OPTIONS_VIEW_HEIGHT;
import static scenes.menuview.selection_box_view.options_view.OptionsView.OPTIONS_VIEW_WIDTH;

public class InfoTextView extends HBox {

    public static final String ASSIGN_KEY_ACTIONS = "Assign game actions to the \n" +
            "keys or buttons of your choice.";
    public static final String PRESS_KEY = "Press the key you want\n" +
            "to assign to this function.";
    public static final String LOST_ASSIGNMENT = "Lost function assignment: ";
    Label infoText;

    public InfoTextView() {
        this.infoText = new Label(ASSIGN_KEY_ACTIONS);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        setInfoTextViewStyle();
        setInfoTextStyle();
        this.getChildren().add(infoText);
    }

    private void setInfoTextViewStyle(){
        this.setMinHeight(OPTIONS_VIEW_HEIGHT * 0.1538);
    }

    private void setInfoTextStyle() {
        HBox.setMargin(infoText, new Insets(
                OPTIONS_VIEW_HEIGHT * 0.0264,
                OPTIONS_VIEW_WIDTH * 0.0377,
                0,
                OPTIONS_VIEW_WIDTH * 0.0377));

        infoText.getStyleClass().add("text-font");
        infoText.setId("info-text");
        infoText.setMinSize(OPTIONS_VIEW_WIDTH * 0.9245, OPTIONS_VIEW_HEIGHT * 0.1033);
        infoText.setAlignment(Pos.BASELINE_CENTER);
    }
}
