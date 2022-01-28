package uicomponents.game;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class PausePane extends AnchorPane {

    Button playB;
    Button menuB;
    Label pausetext;
    Label menutext;
    VBox pauseBox;
    VBox gobackBox;
    AnchorPane menuBox;

    public PausePane() {
        getStyleClass().addAll("pause-pane");
        this.setWidth(Main.WINDOW_WIDTH);
        this.setHeight(Main.WINDOW_HEIGHT+200);
        setPlayB();
        setPausetext();
        setMenuText();
        setMenuB();
        setPauseBox();
        setgobackBox();
        setMenuBox();


    }

    private void setMenuText() {
        menutext = new Label("Menu");
        menutext.getStyleClass().addAll("pause-text");

    }

    private void setMenuB() {
        menuB = new Button();
        menuB.getStyleClass().addAll("menu-btn");
    }

    private void setgobackBox() {
        gobackBox = new VBox();
        gobackBox.setLayoutX(210);
        gobackBox.setLayoutY(37);
        gobackBox.setPrefWidth(150);
        VBox.setVgrow(menutext, Priority.ALWAYS);
        VBox.setVgrow(menuB, Priority.ALWAYS);
        gobackBox.getStyleClass().addAll("menu-vbox");
        gobackBox.getChildren().addAll(menutext,menuB);
    }

    private void setMenuBox(){
        menuBox = new AnchorPane();
        menuBox.setLayoutX(300);
        menuBox.setLayoutY(250);
        menuBox.setPrefHeight(200);
        menuBox.setPrefWidth(400);
        menuBox.getChildren().addAll(pauseBox,gobackBox);
        getChildren().add(menuBox);
        menuBox.getStyleClass().addAll("menu-pane");

    }

    private void setPauseBox() {
        pauseBox = new VBox();
        pauseBox.setLayoutX(40);
        pauseBox.setLayoutY(37);
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