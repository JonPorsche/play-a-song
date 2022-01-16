package uicomponents.game;

import application.Main;
import game.GameManager;
import game.sprites.Coin;
import application.Main;
import game.sprites.Iteam;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public class GameDisplay extends StackPane {
  //double width = Main.WINDOW_WIDTH;
  //double height = Main.WINDOW_HEIGHT;
  public WorldPane gameWorldPane = null;
  public IteamPane gameWorldIteams = null;
  public OverlayPane gameOverlayPanePane = new OverlayPane();
  //public GamePane gamePane = new GamePane(gameWorldPane,gameWorldIteams,width,height);

  public GameDisplay( ) {
    super();
    /*

    Pane background = new Pane( );
    background.getChildren( ).add( this.gameWorldPane );

    StackPane.setMargin( background, new Insets( 0, 0, 2, 5 ) );

     */
  }

  public void initCanvas( int canWidth ) {
    this.gameWorldPane = new WorldPane( Main.WINDOW_WIDTH *15 /*canWidth*/, Main.WINDOW_HEIGHT );
    this.gameWorldIteams = new IteamPane( Main.WINDOW_WIDTH *15 /*canWidth*/, Main.WINDOW_HEIGHT );

    StackPane.setAlignment( this.gameOverlayPanePane, Pos.CENTER );
    //StackPane.setMargin( this.gameWorldPane, new Insets(0, 0, 2, 5) );

    this.getChildren( ).addAll( this.gameWorldPane, this.gameWorldIteams, this.gameOverlayPanePane );
  }

  public void addIteam(Iteam iteam) {
    Platform.runLater(
        () -> gameWorldIteams.addIteam(iteam)
    );
  }

  public void UpdateView( Double x ){
    gameWorldPane.setCenterViewFrame( x );
    gameWorldIteams.setCenterViewFrame( x );
  }

  public void removeIteam(Iteam iteam) {
    Platform.runLater(() -> gameWorldIteams.removeIteam(iteam) );
  }
}

