package uicomponents.game;

import application.Main;
import game.sprites.Coin;
import game.sprites.Iteam;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class IteamPane extends Pane {
  Label label = new Label();
  public IteamPane( double canWidth, double canHeight ) {
    this.setHeight( canHeight );
    this.setWidth( canWidth );
    this.setStyle( "-fx-background-color:rgba(0, 0, 0, 0);" );
    //this.setStyle( "-fx-background-color:rgb(100, 0, 0);" );
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
