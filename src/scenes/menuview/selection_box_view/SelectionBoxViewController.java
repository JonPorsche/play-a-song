package scenes.menuview.selection_box_view;

import javafx.scene.layout.Pane;
import scenes.menuview.selection_box_view.center_view.playlist_view.PlaylistViewController;
import scenes.menuview.selection_box_view.top_view.TopViewController;

public class SelectionBoxViewController {
    private static SelectionBoxViewController INSTANCE = new SelectionBoxViewController();
    private Pane selectionBoxRootView;
    private SelectionBoxView selectionBoxView;

    private SelectionBoxViewController(){
        this.selectionBoxView = new SelectionBoxView();
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
        TopViewController.getInstance().updateTitle(title);
        selectionBoxView.setCenter(pane);
    }

    public Pane getSelectionBoxRootView() {
        return selectionBoxRootView;
    }
}
