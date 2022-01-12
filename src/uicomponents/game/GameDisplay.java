package uicomponents.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class GameDisplay extends StackPane {
  public WorldPane gameWorldPane = new WorldPane( );
  public OverlayPane gameOverlayPanePane = new OverlayPane( );

  public GameDisplay( ) {
    super( );

    StackPane.setMargin( this.gameWorldPane, new Insets( 0, 0, 2, 5 ) );
    StackPane.setAlignment( this.gameOverlayPanePane, Pos.CENTER_LEFT );

    this.getChildren( ).addAll( this.gameWorldPane, this.gameOverlayPanePane );
  }
}
