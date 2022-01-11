package scenes.menuview;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class OptionsViewController {
    private Pane optionsRootView;
    private OptionsView optionsView;
    private Label toBeDoneText;

    public OptionsViewController(){
        optionsView = new OptionsView();
        this.toBeDoneText = optionsView.toBeDoneText;
        optionsRootView = optionsView;
        initialize();
    }

    private void initialize() {
    }

    public Pane getOptionsRootView() {
        return optionsRootView;
    }
}
