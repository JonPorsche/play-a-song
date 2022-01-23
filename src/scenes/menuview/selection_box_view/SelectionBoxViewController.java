package scenes.menuview.selection_box_view;

import application.Main;
import business.service.PlaylistManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import scenes.BasicView;
import scenes.menuview.selection_box_view.playlist_view.PlaylistViewController;

import java.util.HashMap;
import java.util.Map;

public class SelectionBoxViewController {
    private static SelectionBoxViewController INSTANCE = new SelectionBoxViewController();
    private Pane selectionBoxRootView;
    private SelectionBoxView selectionBoxView;

    // Selection box top
    private VBox selectionBoxHeader;
    private Label selectionBoxTitle;

    // Selection box center
    private VBox selectionBoxCenter;
    private Label instructionText;

    private SelectionBoxViewController(){
        this.selectionBoxView = new SelectionBoxView();
        this.selectionBoxHeader = selectionBoxView.selectionBoxHeader;
        this.selectionBoxTitle = selectionBoxView.selectionBoxTitle;
        this.selectionBoxCenter = selectionBoxView.selectionBoxCenter;
        this.instructionText = selectionBoxView.instructionText;
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
