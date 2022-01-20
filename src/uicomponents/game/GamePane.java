package uicomponents.game;

import application.Main;
import game.sprites.PlayerCharacter;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Pane;

public class GamePane extends Pane {
    Label label = new Label();
    public GamePane(WorldPane gameWorldPane, IteamPane gameWorldIteams, double width, double height) {
        this.setHeight(width);
        this.setWidth(height);
        label.setLayoutY(300);
        label.setText(String.valueOf(0));
        this.getChildren().add(label);

    }

    public void setCenterViewFrame(double playerPos ) {
        Platform.runLater(
                ( ) -> {
                    this.setTranslateX( 0 - playerPos);
                    label.setLayoutX(playerPos);
                    label.setText(String.valueOf(playerPos));
                }

        );

    }

    public void addPlayer(PlayerCharacter playerCharacter) {
        Platform.runLater(
                ( ) -> this.getChildren().addAll(playerCharacter)
        );
    }
}
