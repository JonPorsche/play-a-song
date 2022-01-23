package scenes.menuview.selection_box_view.center_view.options_view.infotext_view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import static scenes.menuview.selection_box_view.center_view.options_view.infotext_view.InfoTextView.*;

public class InfoTextViewController {
    private Pane infoTextRootView;
    private InfoTextView infoTextView;
    private Label infoText;

    public InfoTextViewController() {
        initialize();
    }

    private void initialize(){
        this.infoTextView = new InfoTextView();
        this.infoText = infoTextView.infoText;
        infoTextRootView = infoTextView;
    }

    public void switchInfoText(String newText){
        switch (newText){
            case PRESS_KEY:
                infoText.setText(PRESS_KEY);
                break;
            case ASSIGN_KEY_ACTIONS:
                infoText.setText(ASSIGN_KEY_ACTIONS);
                break;
        }
    }

    public void switchInfoText(String newText, String functionName){
                infoText.setText(newText + functionName);
    }

    public Pane getInfoTextRootView() {
        return infoTextRootView;
    }
}
