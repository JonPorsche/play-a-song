package scenes.menuview.selection_box_view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import scenes.menuview.selection_box_view.center_view.playlist_view.PlaylistViewController;

public class SelectionBoxViewController {
    private static SelectionBoxViewController INSTANCE = new SelectionBoxViewController();
    private Pane selectionBoxRootView;
    private SelectionBoxView selectionBoxView;

    // Selection box top
    private VBox selectionBoxHeader;
    private Label selectionBoxTitle;

    private SelectionBoxViewController(){
        this.selectionBoxView = new SelectionBoxView();
        this.selectionBoxHeader = selectionBoxView.selectionBoxHeader;
        this.selectionBoxTitle = selectionBoxView.selectionBoxTitle;
        selectionBoxView.setCenter(PlaylistViewController.getInstance().getPlaylistRootView());
        this.selectionBoxRootView = selectionBoxView;
        initialize();
    }

    public static SelectionBoxViewController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SelectionBoxViewController();
        }
        return INSTANCE;
    }

    public void initialize(){
    }

    public void updateSelectionBoxView(String title, Pane pane){
        selectionBoxTitle.setText(title);
        selectionBoxView.setCenter(pane);
    }

    public Pane getSelectionBoxRootView() {
        return selectionBoxRootView;
    }
}
