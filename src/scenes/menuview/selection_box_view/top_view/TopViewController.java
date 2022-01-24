package scenes.menuview.selection_box_view.top_view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class TopViewController {
    private static TopViewController INSTANCE = new TopViewController();
    private Pane topRootView;
    private TopView topView;
    private Label selectionBoxTitle;

    private TopViewController(){
        topView = new TopView();
        this.selectionBoxTitle = topView.selectionBoxTitle;
        topRootView = topView;
        initialize();
    }

    public static TopViewController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TopViewController();
        }
        return INSTANCE;
    }

    public void initialize(){}

    public void updateTitle(String title){
        selectionBoxTitle.setText(title);
    }

    public Pane getTopRootView() {
        return topRootView;
    }
}
