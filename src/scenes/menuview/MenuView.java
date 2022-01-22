package scenes.menuview;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

// TODO Divide the view of this class in more classes
public class MenuView extends Pane {
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
    Button addSongsBtn = new Button("ADD SONGS");
    Button clearPlaylistBtn = new Button("CLEAR");
    Button playBtn = new Button("PLAY");

    public MenuView(){
        setMenuContainerStyle();
        setMenuBtnBoxStyle();
        setBtnsStyle();
        setSelectionBoxStyle();
        setSelectionBoxHeaderStyle();
        setSelectionBoxCenterStyle();
        setSelectionBoxFooterStyle();

        playlistBtn.getStyleClass().add("titles");
        optionsBtn.getStyleClass().add("titles");
        addSongsBtn.getStyleClass().add("titles");
        clearPlaylistBtn.getStyleClass().add("titles");
        playBtn.getStyleClass().add("titles");
        selectionBoxTitle.getStyleClass().add("titles");

        menuBtnBox.getChildren().addAll(playlistBtn, optionsBtn);
        selectionBoxHeader.getChildren().add(selectionBoxTitle);
        selectionBoxCenter.getChildren().add(instructionText);
        selectionBoxFooter.getChildren().addAll(addSongsBtn, clearPlaylistBtn, playBtn);
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

    private void setMenuBtnBoxStyle(){
        menuBtnBox.setMinSize(Main.WINDOW_WIDTH*0.4592,Main.WINDOW_HEIGHT*0.8875);
        menuBtnBox.setAlignment(Pos.CENTER);

        HBox.setMargin(menuBtnBox, new Insets(
                Main.WINDOW_HEIGHT*0.05625,
                Main.WINDOW_WIDTH*0.0074,
                Main.WINDOW_HEIGHT*0.05625,
                Main.WINDOW_WIDTH*0.0074));

        VBox.setMargin(playlistBtn, new Insets(0,0,4,0));
        VBox.setMargin(optionsBtn, new Insets(4,0,0,0));
    }

    private void setBtnsStyle(){
        playlistBtn.getStyleClass().addAll("text-btn", "text-btn-enabled-color", "titles");
        optionsBtn.getStyleClass().addAll("text-btn", "text-btn-disabled-color","titles");

        VBox.setMargin(playlistBtn, new Insets(8));
        VBox.setMargin(playlistBtn, new Insets(8));
    }

    private void setSelectionBoxStyle(){
        selectionBox.setId("selection-box");
        selectionBox.setMinSize(Main.WINDOW_WIDTH*0.4592,Main.WINDOW_HEIGHT*0.8875);
        selectionBox.setMaxSize(Main.WINDOW_WIDTH*0.4592,Main.WINDOW_HEIGHT*0.8875);

        HBox.setMargin(selectionBox, new Insets(
                Main.WINDOW_HEIGHT*0.05625,
                Main.WINDOW_WIDTH*0.0666,
                Main.WINDOW_HEIGHT*0.05625,
                0));
    }

    private void setSelectionBoxHeaderStyle(){
        selectionBoxHeader.setMinHeight(68);
        selectionBoxHeader.setMaxHeight(68);
        selectionBoxHeader.setAlignment(Pos.CENTER);
        selectionBoxTitle.setAlignment(Pos.CENTER);
    }

    private void setSelectionBoxCenterStyle(){
        selectionBoxCenter.setId("selection-box-center");
        selectionBoxCenter.setMinHeight(Main.WINDOW_HEIGHT*0.6625);
        selectionBoxCenter.setMaxHeight(Main.WINDOW_HEIGHT*0.6625);
        selectionBoxCenter.setAlignment(Pos.BOTTOM_CENTER);
    }

    private void setSelectionBoxFooterStyle(){
        selectionBoxFooter.setMinHeight(84);
        selectionBoxFooter.setMaxHeight(84);
        selectionBoxFooter.setAlignment(Pos.CENTER);
        HBox.setMargin(playBtn,new Insets(0,10,0,10));
    }
}
