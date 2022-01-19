package scenes.menuview.optionsview.infotextview;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import static scenes.menuview.optionsview.infotextview.InfoTextView.*;

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
