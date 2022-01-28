package uicomponents.game;

import application.Main;

import game.sprites.basic.Sprite;
import game.sprites.logic.PlayerCharacter;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import scenes.gameview.GameView;

public class GameDisplay extends Pane {
  public WorldPane gameWorldPane = new WorldPane( );
  public IteamPane gameWorldIteams = null;
  public GameView gameView;
  private PlayerCharacter playerCharter;

  public GameDisplay( ) {
    super();

    this.setBackground(Background.EMPTY);
  }

  public void initCanvas( ) {
    this.gameWorldIteams = new IteamPane( );
  }

  public void updateAbsoluteLayerPos(Double x) {
    gameWorldIteams.setCenterViewFrame(x);
    gameWorldPane.setCenterViewFrame(x);
  }

  public void removeIteam(Sprite sprite) {
    Platform.runLater(() -> gameWorldIteams.removeIteam(sprite));
  }

  public void declarePlayerCharacter(PlayerCharacter playerCharacter) {
    this.playerCharter = playerCharacter;
  }

  public void showPlay( ) {
    GameDisplay scope = this;

    Platform.runLater( ( ) -> {
      ObservableList<Node> nodes = scope.getChildren( );
      nodes.clear( );
      nodes.addAll( scope.gameWorldPane,scope.gameWorldIteams, scope.playerCharter.getSprite());
    } );
  }
}

