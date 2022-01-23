package scenes.menuview.selection_box_view;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SelectionBoxView extends BorderPane {
    private static final double SELECTION_BOX_WIDTH = Main.WINDOW_WIDTH * 0.4592;
    private static final double SELECTION_BOX_HEIGHT = Main.WINDOW_HEIGHT * 0.8875;
    private static final double SELECTION_BOX_R_L_MARGINS = 18;
    private static final double FOOTER_HEIGHT = SELECTION_BOX_HEIGHT * 0.1479;
    private static final double FOOTER_WIDTH = SELECTION_BOX_WIDTH-SELECTION_BOX_R_L_MARGINS*2;

    // Selection box top
    VBox selectionBoxHeader;
    Label selectionBoxTitle;

    // Selection box center
    VBox selectionBoxCenter;
    Label instructionText;

    // Selection box bottom
    HBox selectionBoxFooter;
    ToolBar toolBar;
    HBox leftBtns;
    Button addSongsBtn;
    Button clearPlaylistBtn;
    Button playBtn;

    public SelectionBoxView() {
        this.selectionBoxHeader = new VBox();
        this.selectionBoxTitle = new Label("PLAYLIST");
        this.selectionBoxCenter = new VBox();
        this.instructionText = new Label("To add songs select a folder with mp3 files");
        this.selectionBoxFooter = new HBox();
        this.toolBar = new ToolBar();
        this.leftBtns = new HBox();
        this.addSongsBtn = new Button("ADD SONGS");
        this.clearPlaylistBtn = new Button("CLEAR");
        this.playBtn = new Button("PLAY");

        setSelectionBoxStyle();
        setSelectionBoxHeaderStyle();
        setSelectionBoxCenterStyle();
        setSelectionBoxFooterStyle();
        setToolBarStyle();
        setLeftBtnsStyle();
        setTitleStyle();
        setBtnStyles();

        selectionBoxHeader.getChildren().add(selectionBoxTitle);
        selectionBoxCenter.getChildren().add(instructionText);
        leftBtns.getChildren().addAll(addSongsBtn, clearPlaylistBtn);
        toolBar.getItems().addAll(leftBtns, playBtn);
        selectionBoxFooter.getChildren().addAll(toolBar);

        this.setTop(selectionBoxHeader);
        this.setBottom(selectionBoxFooter);
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

    private void setToolBarStyle() {
        toolBar.setMinSize(FOOTER_WIDTH, FOOTER_HEIGHT);
        toolBar.setMaxSize(FOOTER_WIDTH, FOOTER_HEIGHT);
    }

    private void setLeftBtnsStyle() {
        HBox.setMargin(addSongsBtn, new Insets(0, 6, 0, 0));
        leftBtns.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(leftBtns, Priority.ALWAYS);

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

    private void setSelectionBoxFooterStyle() {
        addSongsBtn.getStyleClass().addAll("fab-extended-btn", "titles");
        addSongsBtn.setId("add-songs");
        clearPlaylistBtn.getStyleClass().addAll("fab-extended-btn", "titles");
        clearPlaylistBtn.setId("clear-playlist");
        playBtn.getStyleClass().addAll("fab-extended-btn", "titles");
        playBtn.setId("play");

        BorderPane.setMargin(selectionBoxFooter,
                new Insets(0, SELECTION_BOX_R_L_MARGINS, 0, SELECTION_BOX_R_L_MARGINS));
        selectionBoxFooter.setMinHeight(FOOTER_HEIGHT);
        selectionBoxFooter.setMaxHeight(FOOTER_HEIGHT);
        selectionBoxFooter.setAlignment(Pos.CENTER);
    }

    private void setTitleStyle() {
        selectionBoxTitle.getStyleClass().add("titles");
    }

    private void setBtnStyles() {
        addSongsBtn.getStyleClass().add("titles");
        clearPlaylistBtn.getStyleClass().add("titles");
        playBtn.getStyleClass().add("titles");
    }
}
