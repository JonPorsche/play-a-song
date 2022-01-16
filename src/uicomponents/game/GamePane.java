package uicomponents.game;

import application.Main;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

public class GamePane extends Pane {
    public GamePane(WorldPane gameWorldPane, IteamPane gameWorldIteams, double width, double height) {
        this.setMinHeight(width);
        this.setMinWidth(height);
        this.getChildren().addAll(gameWorldPane,gameWorldIteams);
    }

    public void setCenterViewFrame(double playerPos ) {
        Platform.runLater(
                ( ) -> this.setTranslateX( 0 - playerPos )
        );
    }

}
