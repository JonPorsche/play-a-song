package uicomponents.game;

import application.Main;

import game.sprites.logic.PlayerCharacter;
import game.sprites.basic.Iteam;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import scenes.gameview.GameView;

public class GameDisplay extends Pane {
  //double width = Main.WINDOW_WIDTH;
  //double height = Main.WINDOW_HEIGHT;
  public WorldPane gameWorldPane = new WorldPane( );
  public IteamPane gameWorldIteams = null;
  public OverlayPane gameOverlayPanePane = new OverlayPane();
  public loadingPane load = new loadingPane();
  public GameView gameView;


    public GameDisplay(GameView gameView) {

    super();
    this.setBackground(Background.EMPTY);
    /*
    Pane background = new Pane( );
    background.getChildren( ).add( this.gameWorldPane );
    StackPane.setMargin( background, new Insets( 0, 0, 2, 5 ) );

     */}
  public void initCanvas( int canWidth ) {
    //this.gameWorldPane = new WorldPane( /*Main.WINDOW_WIDTH2*/canWidth ); //, Main.WINDOW_HEIGHT);
    this.gameWorldIteams = new IteamPane( Main.WINDOW_WIDTH /*canWidth*/, Main.WINDOW_HEIGHT);


    //StackPane.setAlignment( this.gameOverlayPanePane, Pos.CENTER );
    //StackPane.setMargin( this.gameWorldPane, new Insets(0, 0, 2, 5) );

  }

  public void updateAbsoluteLayerPos(Double x) {
    //gameWorldIteams.setCenterViewFrame(x);
    gameWorldPane.setCenterViewFrame(x);
  }

  public void removeIteam(Iteam iteam) {
    Platform.runLater(() -> gameWorldIteams.removeIteam(iteam));
  }

   public void declarePlayerCharacter(PlayerCharacter playerCharacter) {
     //Platform.runLater(()->this.getChildren().add(playerCharacter));
    }
   public void showLoading() {
      Platform.runLater(()->this.getChildren().addAll(load));

    }
    public void showPlay( ) {
      GameDisplay scope = this;
      Platform.runLater(
        ( ) -> {
          ObservableList<Node> nodes = scope.getChildren( );
          nodes.clear( );
          nodes.addAll( scope.gameWorldPane,scope.gameWorldIteams );
        }
      );
    }

    public void removeLoading(){
    load.setAlignment(Pos.CENTER);
    Platform.runLater(()->this.getChildren().remove(load));

    }
}

