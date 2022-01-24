package scenes.gameview;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import uicomponents.game.GameDisplay;
import uicomponents.game.OverlayPane;
import uicomponents.game.PausePane;

public class GameView extends AnchorPane {

  public Pane mainView = new Pane();
  public AnchorPane overlayView = new AnchorPane();
  public Pane playerView = new Pane();
  public Pane pauseView = new Pane();
  public PausePane pausePane = new PausePane();
  public OverlayPane gameOverlayPanePane = new OverlayPane();
  public GameDisplay gameDisplay = new GameDisplay( this);

  public GameView( ) {
    super();
   this.prefHeight(1000);
    this.prefWidth(640);
    playerView.setLayoutX(0);
    playerView.setLayoutY(0);
    playerView.setPrefHeight(640);
    playerView.setPrefWidth(1000);
    overlayView.setLayoutY(0);
    overlayView.setLayoutX(0);
    overlayView.setPrefHeight(640);
    overlayView.setPrefWidth(1000);
    overlayView.getChildren().add(gameOverlayPanePane);
    mainView.setLayoutY(0);
    mainView.setLayoutX(0);
    mainView.setPrefHeight(640);
    mainView.setPrefWidth(1000);
    mainView.getChildren().add(gameDisplay);
    mainView.setBackground(Background.EMPTY);
    pauseView.setLayoutX(300);
    pauseView.setLayoutY(250);
    pauseView.setPrefHeight(300);
    pauseView.setPrefWidth(400);
    pauseView.getChildren().add(pausePane);
    this.getChildren( ).addAll(mainView, playerView,overlayView, pauseView);
    pauseView.setVisible(false);
  }
}
