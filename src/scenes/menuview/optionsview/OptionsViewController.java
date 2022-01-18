package scenes.menuview.optionsview;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import scenes.menuview.optionsview.infotextview.InfoTextView;
import scenes.menuview.optionsview.infotextview.InfoTextViewController;

import java.util.Map;

public class OptionsViewController {
    private Pane optionsRootView;
    private OptionsView optionsView;
    private Pane infoTextView;
    private InfoTextViewController infoTextViewController;

    public OptionsViewController(){
        optionsView = new OptionsView();
        infoTextView = optionsView.infoTextView;
        infoTextViewController = optionsView.infoTextViewController;
        optionsRootView = optionsView;
        initialize();
    }

    private void initialize() {
    }

    public Pane getOptionsRootView() {
        return optionsRootView;
    }
}
