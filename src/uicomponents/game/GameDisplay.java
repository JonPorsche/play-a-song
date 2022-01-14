package uicomponents.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class GameDisplay extends StackPane {
  public WorldPane gameWorldPane = new WorldPane( );
  public OverlayPane gameOverlayPanePane = new OverlayPane( );

  public GameDisplay( ) {
    super( );

    /*

    Pane background = new Pane( );
    background.getChildren( ).add( this.gameWorldPane );

    StackPane.setMargin( background, new Insets( 0, 0, 2, 5 ) );

     */
    StackPane.setMargin( this.gameWorldPane, new Insets( 0, 0, 2, 5 ) );
    //StackPane.setAlignment( this.gameOverlayPanePane, Pos.CENTER_LEFT );

    this.getChildren( ).addAll( this.gameWorldPane, this.gameOverlayPanePane );
  }
}
