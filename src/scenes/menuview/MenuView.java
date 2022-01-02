package scenes.menuview;

import application.Main;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuView extends Pane {
    HBox menuContainer = new HBox();

    // MENU BUTTON BOX
    VBox menuBtnBox = new VBox();
    Button playlistBtn = new Button("PLAYLIST");
    Button optionsBtn = new Button("OPTIONS");

    // SELECTION BOX
    BorderPane selectionBox = new BorderPane();

    // Selection box top
    TextArea selectionBoxTitle = new TextArea("SELECTION BOX TITLE");

    // Selection box center
    TextArea instructionText = new TextArea("To add songs select a folder with mp3 files");

    // Selection box bottom
    HBox selectionBoxFooter = new HBox();
    Button addSongsBtn = new Button("ADD SONGS");

    public MenuView(){
        setMenuContainerStyle();
        setMenuBtnBoxStyle();
        setSelectionBoxStyle();

        menuBtnBox.getChildren().addAll(playlistBtn, optionsBtn);

        selectionBoxFooter.getChildren().add(addSongsBtn);

        selectionBox.setTop(selectionBoxTitle);
        selectionBox.setCenter(instructionText);
        selectionBox.setBottom(selectionBoxFooter);

        menuContainer.getChildren().addAll(menuBtnBox, selectionBox);
        this.getChildren().add(menuContainer);
    }

    private void setMenuContainerStyle() {
        menuContainer.getStyleClass().add("border-to-test-blue");
        menuContainer.setMinSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    }

    private void setMenuBtnBoxStyle(){
        menuBtnBox.getStyleClass().addAll("border-to-test-orange");
        menuBtnBox.setMinSize(Main.WINDOW_WIDTH*0.4592,Main.WINDOW_HEIGHT*0.8875);

        HBox.setMargin(menuBtnBox, new Insets(
                Main.WINDOW_HEIGHT*0.05625,
                Main.WINDOW_WIDTH*0.0074,
                Main.WINDOW_HEIGHT*0.05625,
                Main.WINDOW_WIDTH*0.0074));
    }

    private void setSelectionBoxStyle(){
        selectionBox.getStyleClass().add("border-to-test-blue");
        selectionBox.setMinSize(Main.WINDOW_WIDTH*0.4592,Main.WINDOW_HEIGHT*0.8875);
        selectionBox.setMaxSize(Main.WINDOW_WIDTH*0.4592,Main.WINDOW_HEIGHT*0.8875);

        HBox.setMargin(selectionBox, new Insets(
                Main.WINDOW_HEIGHT*0.05625,
                Main.WINDOW_WIDTH*0.0666,
                Main.WINDOW_HEIGHT*0.05625,
                0));
    }
}
