package uicomponents.game;

import application.Main;
import game.sprites.Coin;
import game.sprites.Iteam;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class IteamPane extends StackPane {
  public IteamPane( int canWidth, int canHeight ) {
    this.setHeight( canHeight );
    this.setWidth( canWidth );
    this.setStyle( "-fx-background-color:rgba(0, 0, 0, 0);" );
    //this.setStyle( "-fx-background-color:rgb(100, 0, 0);" );
  }

  public void setCenterViewFrame( double playerPos ) {
    int width = (int)this.getWidth();
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
