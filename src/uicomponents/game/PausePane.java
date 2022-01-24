package uicomponents.game;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class PausePane extends AnchorPane {

    Button playB;

    public PausePane(){
        this.setStyle("-fx-background-color: RED");
        playB = new Button();
        playB.setLayoutX(this.getWidth()/2);
        this.getChildren().add(playB);







    }


}
