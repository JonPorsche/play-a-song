package scenes.gameview;

import javafx.scene.layout.StackPane;
import uicomponents.game.GameDisplay;

public class GameView extends StackPane {
  public GameDisplay gameDisplay = new GameDisplay( );

  public GameView( ) {
    super( );

    this.getChildren( ).addAll( this.gameDisplay);
  }
}
