package scenes.gameview;

import javafx.scene.layout.StackPane;
import uicomponents.game.GameDisplay;
import uicomponents.game.OverlayPane;

public class GameView extends StackPane {
  public OverlayPane gameOverlayPanePane = new OverlayPane();
  public GameDisplay gameDisplay = new GameDisplay( );

  public GameView( ) {
    super( );

    this.getChildren( ).addAll( this.gameDisplay);
  }
}
