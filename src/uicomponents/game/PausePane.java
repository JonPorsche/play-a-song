package uicomponents.game;

import application.Main;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class PausePane extends AnchorPane {

    Button playB;
    Label pausetext;
    HBox pauseBox;

    public PausePane() {
        getStyleClass().addAll("pause-pane");
        this.setWidth(Main.WINDOW_WIDTH);
        this.setHeight(Main.WINDOW_HEIGHT+200);
        setPlayB();
        setPausetext();
        setPauseBox();

    }

    private void setPauseBox() {
        pauseBox = new HBox();
        pauseBox.setLayoutX(400);
        pauseBox.setLayoutY(300);
        HBox.setHgrow(pausetext, Priority.ALWAYS);
        HBox.setHgrow(playB, Priority.ALWAYS);
        pauseBox.getChildren().addAll(pausetext,playB);
        getChildren().add(pauseBox);
    }

    public void setPausetext() {
        pausetext = new Label("Loading !");
        pausetext.setLayoutX(430);
        pausetext.setLayoutY(250);
        pausetext.getStyleClass().addAll("pause-text");


    }

    private void setPlayB() {
        playB = new Button();
        playB.getStyleClass().addAll("play-btn");


    }
}