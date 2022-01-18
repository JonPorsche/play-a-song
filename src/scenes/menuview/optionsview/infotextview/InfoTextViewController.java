package scenes.menuview.optionsview.infotextview;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class InfoTextViewController {
    private Pane infoTextRootView;
    private InfoTextView infoTextView;
    private Label infoText;

    public InfoTextViewController() {
        this.infoTextView = new InfoTextView();
        this.infoText = infoTextView.infoText;
        infoTextRootView = infoTextView;
        initialize();
    }

    private void initialize(){}

    public Pane getInfoTextRootView() {
        return infoTextRootView;
    }
}
