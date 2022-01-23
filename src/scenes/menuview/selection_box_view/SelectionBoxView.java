package scenes.menuview.selection_box_view;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import scenes.menuview.selection_box_view.bottom_view.BottomViewController;
import scenes.menuview.selection_box_view.playlist_view.PlaylistViewController;

import java.util.HashMap;
import java.util.Map;

public class SelectionBoxView extends BorderPane {
    public static final double SELECTION_BOX_WIDTH = Main.WINDOW_WIDTH * 0.4592;
    public static final double SELECTION_BOX_HEIGHT = Main.WINDOW_HEIGHT * 0.8875;
    public static final double SELECTION_BOX_R_L_MARGINS = 18;
    Map<String, Pane> selectionBoxViews;
    Pane bottomView;

    // Selection box top
    VBox selectionBoxHeader;
    Label selectionBoxTitle;

    // Selection box center
    VBox selectionBoxCenter;
    Label instructionText;

    public SelectionBoxView() {
        this.selectionBoxViews = new HashMap<String, Pane>();
        this.selectionBoxHeader = new VBox();
        this.selectionBoxTitle = new Label("PLAYLIST");
        this.selectionBoxCenter = new VBox();
        this.instructionText = new Label("To add songs select a folder with mp3 files");

        setSelectionBoxStyle();
        setSelectionBoxHeaderStyle();
        setSelectionBoxCenterStyle();
        setTitleStyle();

        loadSelectionBoxViews();
        this.bottomView = selectionBoxViews.get("BottomView");
        selectionBoxHeader.getChildren().add(selectionBoxTitle);
        selectionBoxCenter.getChildren().add(instructionText);

        this.setTop(selectionBoxHeader);
        this.setBottom(bottomView);
    }

    private void setSelectionBoxStyle() {
        this.setId("selection-box");
        this.setMinSize(SELECTION_BOX_WIDTH, SELECTION_BOX_HEIGHT);
        this.setMaxSize(SELECTION_BOX_WIDTH, SELECTION_BOX_HEIGHT);

        HBox.setMargin(this, new Insets(
                Main.WINDOW_HEIGHT * 0.05625,
                Main.WINDOW_WIDTH * 0.0666,
                Main.WINDOW_HEIGHT * 0.05625,
                0));
    }

    private void setSelectionBoxHeaderStyle() {
        selectionBoxHeader.setMinHeight(68);
        selectionBoxHeader.setMaxHeight(68);
        selectionBoxHeader.setAlignment(Pos.CENTER);
        selectionBoxTitle.setAlignment(Pos.CENTER);
    }

    private void setSelectionBoxCenterStyle() {
        selectionBoxCenter.setId("selection-box-center");
        selectionBoxCenter.setMinHeight(Main.WINDOW_HEIGHT * 0.6625);
        selectionBoxCenter.setMaxHeight(Main.WINDOW_HEIGHT * 0.6625);
        selectionBoxCenter.setAlignment(Pos.BOTTOM_CENTER);
    }

    private void setTitleStyle() {
        selectionBoxTitle.getStyleClass().add("titles");
    }

    private void loadSelectionBoxViews(){
        selectionBoxViews.put("BottomView", BottomViewController.getInstance().getBottomRootView());
    }
}
