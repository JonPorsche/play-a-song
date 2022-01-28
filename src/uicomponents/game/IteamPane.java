package uicomponents.game;

import game.sprites.basic.Sprite;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class IteamPane extends Pane {
  Label label = new Label();

  public IteamPane( ) {
    this.setStyle( "-fx-background-color:rgba(0, 0, 0, 0);" );
    label.setLayoutY(300);
    label.setText(String.valueOf(0));
    this.getChildren().add(label);
  }

  public void setCenterViewFrame(double playerPos ) {
    Platform.runLater( ( ) -> {
      this.setTranslateX( 0 - playerPos);
      label.setLayoutX(playerPos);
      label.setText(String.valueOf(playerPos));
    } );
  }

  public void removeIteam(Sprite sprite) {
    Platform.runLater(
      () -> this.getChildren().remove(sprite)
    );
  }

  public void addIteam(Sprite sprite) {
    Platform.runLater(
      () -> this.getChildren().add((Node) sprite)
    );
  }
}
