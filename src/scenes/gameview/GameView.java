package scenes.gameview;

import application.Main;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import uicomponents.game.GameDisplay;
import uicomponents.game.PausePane;

public class GameView extends AnchorPane {

  public Pane mainView = new Pane();
  public AnchorPane overlayView = new AnchorPane();
  public AnchorPane pauseView = new AnchorPane();
  public PausePane pausePane = new PausePane();
  public GameDisplay gameDisplay = new GameDisplay( );

  public GameView( ) {
    super();
    this.prefHeight(Main.WINDOW_HEIGHT);
    this.prefWidth(Main.WINDOW_WIDTH);
    setMainView();
    setOverlayView();
 
    setPauseView();
    this.getChildren( ).addAll(mainView, pauseView,overlayView);
  }

  private void setPauseView() {
    pauseView.setLayoutX(0);
    pauseView.setLayoutY(0);
    pauseView.setPrefHeight(Main.WINDOW_HEIGHT);
    pauseView.setPrefWidth(Main.WINDOW_WIDTH);
  }

  private void setMainView() {
    mainView.setLayoutY(0);
    mainView.setLayoutX(0);
    mainView.setPrefHeight(Main.WINDOW_HEIGHT);
    mainView.setPrefWidth(Main.WINDOW_WIDTH);
    mainView.getChildren().add(gameDisplay);
  }

  private void setOverlayView(){
    overlayView.setLayoutY(0);
    overlayView.setLayoutX(0);
    overlayView.setPrefHeight(100);
    overlayView.setPrefWidth(Main.WINDOW_WIDTH);
  }
}
