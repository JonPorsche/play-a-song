package scenes.menuview.selection_box_view.bottom_view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import static scenes.menuview.selection_box_view.SelectionBoxView.*;

public class BottomView extends HBox {
    private static final double FOOTER_HEIGHT = SELECTION_BOX_HEIGHT * 0.1479;
    private static final double FOOTER_WIDTH = SELECTION_BOX_WIDTH-SELECTION_BOX_R_L_MARGINS*2;
    Button addSongsBtn;
    Button clearPlaylistBtn;
    HBox leftBtns;
    Button playBtn;
    ToolBar toolBar;

    public BottomView(){
        this.addSongsBtn = new Button("ADD SONGS");
        this.clearPlaylistBtn = new Button("CLEAR");
        this.leftBtns = new HBox(addSongsBtn,clearPlaylistBtn);
        this.playBtn = new Button("PLAY");
        this.toolBar = new ToolBar(leftBtns,playBtn);

        setSelectionBoxFooterStyle();
        setToolBarStyle();
        setLeftBtnsStyle();
        setBtnStyles();

        this.getChildren().add(toolBar);
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

    private void setSelectionBoxFooterStyle() {
        addSongsBtn.getStyleClass().addAll("fab-extended-btn", "titles");
        addSongsBtn.setId("add-songs");
        clearPlaylistBtn.getStyleClass().addAll("fab-extended-btn", "titles");
        clearPlaylistBtn.setId("clear-playlist");
        playBtn.getStyleClass().addAll("fab-extended-btn", "titles");
        playBtn.setId("play");

        BorderPane.setMargin(this,
                new Insets(0, SELECTION_BOX_R_L_MARGINS, 0, SELECTION_BOX_R_L_MARGINS));
        this.setMinHeight(FOOTER_HEIGHT);
        this.setMaxHeight(FOOTER_HEIGHT);
        this.setAlignment(Pos.CENTER);
    }

    private void setBtnStyles() {
        addSongsBtn.getStyleClass().add("titles");
        clearPlaylistBtn.getStyleClass().add("titles");
        playBtn.getStyleClass().add("titles");
    }
}
