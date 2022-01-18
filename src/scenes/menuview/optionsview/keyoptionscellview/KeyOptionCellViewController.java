package scenes.menuview.optionsview.keyoptionscellview;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class KeyOptionCellViewController {
    private Pane keyOptionRootView;
    private KeyOptionCellView keyOptionCellView;
    private Label functionName;
    private Button keySelectBtn;

    public KeyOptionCellViewController(String functionName) {
        keyOptionCellView = new KeyOptionCellView(functionName);
        this.functionName = keyOptionCellView.functionName;
        initialize();
    }

    private void initialize() {
        keySelectBtn = keyOptionCellView.keySelectBtn;
        keyOptionRootView = keyOptionCellView;
    }

    public Pane getKeyOptionRootView() {
        return keyOptionRootView;
    }
}
