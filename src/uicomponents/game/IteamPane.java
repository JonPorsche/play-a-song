package uicomponents.game;

import application.Main;
import game.sprites.Iteam;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class IteamPane extends Pane {
    public IteamPane(double width, double height) {

        this.setMinHeight(height);
        this.setMinWidth(width*15);
        Label lable = new Label("hallo");





    }
    public void setCenterViewFrame( double playerPos ) {
        int width = (int) this.getWidth();
        Platform.runLater(
                ( ) -> this.setTranslateX( 0 - playerPos )
        );
    }


    public void addIteam(Iteam iteam) {
        Platform.runLater(
                () -> this.getChildren().add(iteam)
        );
    }
    public void removeIteam(Iteam iteam) {
        Platform.runLater(
                () -> this.getChildren().remove(iteam)
        );
    }
}
