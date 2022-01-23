package scenes.menuview;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

// TODO Divide the view of this class in more classes
public class MenuView extends Pane {
    private static final double SELECTION_BOX_WIDTH = Main.WINDOW_WIDTH * 0.4592;
    private static final double SELECTION_BOX_HEIGHT = Main.WINDOW_HEIGHT * 0.8875;
    private static final double SELECTION_BOX_R_L_MARGINS = 18;
    private static final double FOOTER_HEIGHT = SELECTION_BOX_HEIGHT * 0.1479;
    private static final double FOOTER_WIDTH = SELECTION_BOX_WIDTH-SELECTION_BOX_R_L_MARGINS*2;

    HBox menuContainer = new HBox();

    // MENU BUTTON BOX
    VBox menuBtnBox = new VBox();
    Button playlistBtn = new Button("PLAYLIST");
    Button optionsBtn = new Button("OPTIONS");

    // SELECTION BOX
    BorderPane selectionBox = new BorderPane();

    // Selection box top
    VBox selectionBoxHeader = new VBox();
    Label selectionBoxTitle = new Label("PLAYLIST");

    // Selection box center
    VBox selectionBoxCenter = new VBox();
    Label instructionText = new Label("To add songs select a folder with mp3 files");

    // Selection box bottom
    HBox selectionBoxFooter = new HBox();
    ToolBar toolBar = new ToolBar();
    HBox leftBtns = new HBox();
    Button addSongsBtn = new Button("ADD SONGS");
    Button clearPlaylistBtn = new Button("CLEAR");
    Button playBtn = new Button("PLAY");

    public MenuView() {
        setMenuContainerStyle();
        setMenuBtnBoxStyle();
        setBtnsStyle();
        setSelectionBoxStyle();
        setSelectionBoxHeaderStyle();
        setSelectionBoxCenterStyle();
        setSelectionBoxFooterStyle();
        setToolBarStyle();
        setLeftBtnsStyle();

        playlistBtn.getStyleClass().add("titles");
        optionsBtn.getStyleClass().add("titles");
        addSongsBtn.getStyleClass().add("titles");
        clearPlaylistBtn.getStyleClass().add("titles");
        playBtn.getStyleClass().add("titles");
        selectionBoxTitle.getStyleClass().add("titles");

        menuBtnBox.getChildren().addAll(playlistBtn, optionsBtn);
        selectionBoxHeader.getChildren().add(selectionBoxTitle);
        selectionBoxCenter.getChildren().add(instructionText);
        leftBtns.getChildren().addAll(addSongsBtn, clearPlaylistBtn);
        toolBar.getItems().addAll(leftBtns, playBtn);
        selectionBoxFooter.getChildren().addAll(toolBar);
        selectionBox.setTop(selectionBoxHeader);
        //selectionBox.setCenter(selectionBoxCenter);
        selectionBox.setBottom(selectionBoxFooter);
        menuContainer.getChildren().addAll(menuBtnBox, selectionBox);
        this.getChildren().add(menuContainer);
    }

    private void setMenuContainerStyle() {
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        menuContainer.setId("menu-container");
        menuContainer.setMinSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    }

    private void setMenuBtnBoxStyle() {
        menuBtnBox.setMinSize(Main.WINDOW_WIDTH * 0.4592, Main.WINDOW_HEIGHT * 0.8875);
        menuBtnBox.setAlignment(Pos.CENTER);

        HBox.setMargin(menuBtnBox, new Insets(
                Main.WINDOW_HEIGHT * 0.05625,
                Main.WINDOW_WIDTH * 0.0074,
                Main.WINDOW_HEIGHT * 0.05625,
                Main.WINDOW_WIDTH * 0.0074));

        VBox.setMargin(playlistBtn, new Insets(0, 0, 4, 0));
        VBox.setMargin(optionsBtn, new Insets(4, 0, 0, 0));
    }

    private void setBtnsStyle() {
        playlistBtn.getStyleClass().addAll("text-btn", "text-btn-focused", "titles");
        optionsBtn.getStyleClass().addAll("text-btn", "text-btn-disabled-color", "titles");

        VBox.setMargin(playlistBtn, new Insets(8));
        VBox.setMargin(playlistBtn, new Insets(8));
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

    private void setSelectionBoxStyle() {
        selectionBox.setId("selection-box");
        selectionBox.setMinSize(SELECTION_BOX_WIDTH, SELECTION_BOX_HEIGHT);
        selectionBox.setMaxSize(SELECTION_BOX_WIDTH, SELECTION_BOX_HEIGHT);

        HBox.setMargin(selectionBox, new Insets(
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
}
