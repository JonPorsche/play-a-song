package uicomponents.game;

import application.Main;
import game.sprites.Coin;
import game.sprites.Iteam;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;


public class GameDisplay extends Pane {
  double width = Main.WINDOW_WIDTH;
  double height = Main.WINDOW_HEIGHT;
  public WorldPane gameWorldPane = new WorldPane(width,height);
  public OverlayPane gameOverlayPanePane = new OverlayPane();
  public IteamPane gameWorldIteams = new IteamPane(width,height);
  public GamePane gamePane = new GamePane(gameWorldPane,gameWorldIteams,width,height);

  public GameDisplay() {
    super();

    /*

    Pane background = new Pane( );
    background.getChildren( ).add( this.gameWorldPane );

    StackPane.setMargin( background, new Insets( 0, 0, 2, 5 ) );

     */
    //StackPane.setMargin(this.gameWorldPane, new Insets(0, 0, 2, 5));
    //StackPane.setAlignment( this.gameOverlayPanePane, Pos.CENTER_LEFT );


    this.getChildren().addAll(this.gameWorldPane, this.gameOverlayPanePane, this.gameWorldIteams);



  }

  public void addIteam(Iteam iteam) {
    Platform.runLater(
            () -> gameWorldIteams.addIteam(iteam)
    );


  }

  public void removeIteam(Iteam iteam) {
    Platform.runLater(() -> gameWorldIteams.removeIteam(iteam) );


  }
}

