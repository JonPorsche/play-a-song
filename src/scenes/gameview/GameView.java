package scenes.gameview;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import uicomponents.game.GameDisplay;
import uicomponents.game.OverlayPane;

import java.awt.*;

public class GameView extends AnchorPane {

  public Pane mainPane = new Pane();
  public Pane overlayView = new Pane();
  public Pane playerPane = new Pane();
  public OverlayPane gameOverlayPanePane = new OverlayPane();
  public GameDisplay gameDisplay = new GameDisplay( this);

  public GameView( ) {
    super();
   this.prefHeight(1000);
    this.prefWidth(640);
    playerPane.setLayoutX(0);
    playerPane.setLayoutY(0);
    playerPane.setPrefHeight(640);
    playerPane.setPrefWidth(1000);
    overlayView.setLayoutY(0);
    overlayView.setLayoutX(0);
    overlayView.setPrefHeight(640);
    overlayView.setPrefWidth(1000);
    overlayView.setBackground(Background.EMPTY);
    overlayView.getChildren().add(gameOverlayPanePane);
    mainPane.setLayoutY(0);
    mainPane.setLayoutX(0);
    mainPane.setPrefHeight(640);
    mainPane.setPrefWidth(1000);
    mainPane.getChildren().add(gameDisplay);
    mainPane.setBackground(Background.EMPTY);
    this.getChildren( ).addAll( overlayView,mainPane,playerPane);
  }
}
