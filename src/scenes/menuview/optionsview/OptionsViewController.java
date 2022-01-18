package scenes.menuview.optionsview;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import scenes.menuview.optionsview.infotextview.InfoTextView;
import scenes.menuview.optionsview.infotextview.InfoTextViewController;
import scenes.menuview.optionsview.keyoptionscellview.KeyOptionCellViewController;

import java.util.Map;

public class OptionsViewController {
    private Pane optionsRootView;
    private OptionsView optionsView;
    private Pane infoTextView;
    private InfoTextViewController infoTextViewController;
    private Pane moveUpView;
    private KeyOptionCellViewController moveUpViewController;
    private Pane moveDownView;
    private KeyOptionCellViewController moveDownViewController;

    public OptionsViewController(){
        initialize();
    }

    private void initialize() {
        optionsView = new OptionsView();
        infoTextView = optionsView.infoTextView;
        infoTextViewController = optionsView.infoTextViewController;
        moveUpView = optionsView.moveUpView;
        moveUpViewController = optionsView.moveUpViewController;
        moveDownView = optionsView.moveDownView;
        moveDownViewController = optionsView.moveDownViewController;
        optionsRootView = optionsView;
    }

    public Pane getOptionsRootView() {
        return optionsRootView;
    }
}
