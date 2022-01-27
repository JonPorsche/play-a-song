package uicomponents.game;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class PausePane extends AnchorPane {

    Button playB;
    Label pausetext;
    VBox pauseBox;
    AnchorPane menuBox;

    public PausePane() {
        getStyleClass().addAll("pause-pane");
        this.setWidth(Main.WINDOW_WIDTH);
        this.setHeight(Main.WINDOW_HEIGHT+200);
        setPlayB();
        setPausetext();
        setPauseBox();
        setMenuBox();

    }
    private void setMenuBox(){
        menuBox = new AnchorPane();
        menuBox.setLayoutX(300);
        menuBox.setLayoutY(250);
        menuBox.setPrefHeight(200);
        menuBox.setPrefWidth(400);
        menuBox.getChildren().add(pauseBox);
        getChildren().add(menuBox);
        menuBox.getStyleClass().addAll("menu-pane");

    }

    private void setPauseBox() {
        pauseBox = new VBox();
        pauseBox.setLayoutX(50);
        pauseBox.setLayoutY(30);
        pauseBox.setPrefWidth(150);
        VBox.setVgrow(pausetext, Priority.ALWAYS);
        VBox.setVgrow(playB, Priority.ALWAYS);
        pauseBox.getStyleClass().addAll("menu-vbox");
        pauseBox.getChildren().addAll(pausetext,playB);

    }

    public void setPausetext() {
        pausetext = new Label("Loading !");
        pausetext.getStyleClass().addAll("pause-text");


    }

    private void setPlayB() {
        playB = new Button();
        playB.getStyleClass().addAll("play-btn");


    }
}