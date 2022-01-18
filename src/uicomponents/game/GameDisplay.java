package uicomponents.game;

import application.Main;
import game.sprites.Iteam;
import game.sprites.PlayerCharacter;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.Stack;


public class GameDisplay extends Pane {
  //double width = Main.WINDOW_WIDTH;
  //double height = Main.WINDOW_HEIGHT;
  public WorldPane gameWorldPane = null;
  public IteamPane gameWorldIteams = null;
  public OverlayPane gameOverlayPanePane = new OverlayPane();
  public GamePane gamePane;

  public GameDisplay() {
    super();
    /*

    Pane background = new Pane( );
    background.getChildren( ).add( this.gameWorldPane );

    StackPane.setMargin( background, new Insets( 0, 0, 2, 5 ) );

     */
  }

  public void initCanvas(int canWidth) {
    this.gameWorldPane = new WorldPane(Main.WINDOW_WIDTH * 15 /*canWidth*/, Main.WINDOW_HEIGHT);
    this.gameWorldIteams = new IteamPane(Main.WINDOW_WIDTH * 15 /*canWidth*/, Main.WINDOW_HEIGHT);
    this.gamePane = new GamePane(gameWorldPane, gameWorldIteams, Main.WINDOW_WIDTH * 15 /*canWidth*/, Main.WINDOW_HEIGHT);

    //StackPane.setAlignment( this.gameOverlayPanePane, Pos.CENTER );
    //StackPane.setMargin( this.gameWorldPane, new Insets(0, 0, 2, 5) );
    this.getChildren().addAll(this.gameWorldPane,this.gameWorldIteams, this.gameOverlayPanePane);
  }

  public void addIteam(Iteam iteam) {
    Platform.runLater(
            () -> gameWorldIteams.addIteam(iteam)
    );
  }

  public void updateAbsoluteLayerPos(Double x) {
    gameWorldIteams.setCenterViewFrame(x);
    gameWorldPane.setCenterViewFrame(x);
  }

  public void removeIteam(Iteam iteam) {
    Platform.runLater(() -> gameWorldIteams.removeIteam(iteam));
  }

  public void declarePlayerCharacter(PlayerCharacter playerCharacter) {
    this.gameOverlayPanePane.declarePlayerCharacter(playerCharacter);


  }
}

