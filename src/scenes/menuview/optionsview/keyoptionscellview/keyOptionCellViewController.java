package scenes.menuview.optionsview.keyoptionscellview;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class keyOptionCellViewController {
    private Pane keyOptionRootView;
    private KeyOptionCellView keyOptionCellView;
    private Label functionName;
    private Button keySelectBtn;

    public keyOptionCellViewController() {
        keyOptionCellView = new KeyOptionCellView("Move Up");
        this.functionName = keyOptionCellView.functionName;
        this.keySelectBtn = keyOptionCellView.keySelectBtn;
        keyOptionRootView = keyOptionCellView;
        initialize();
    }

    private void initialize() {
    }

    public Pane getKeyOptionRootView() {
        return keyOptionRootView;
    }
}
