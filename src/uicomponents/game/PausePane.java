package uicomponents.game;

import application.Main;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class PausePane extends AnchorPane {

  protected Button playB;
  protected Button menuB;
  protected Label pausetext;
  protected Label menutext;
  protected Label gameOverText;
  protected Label scoreText;
  protected VBox pauseBox;
  protected VBox gobackBox;
  protected AnchorPane menuBox;
  protected HBox pauseStateBox;
  protected Label gameState;
  protected VBox gameOver;

  public PausePane() {
    this.getStyleClass().addAll("pause-pane");
    this.setWidth(Main.WINDOW_WIDTH);
    this.setHeight(Main.WINDOW_HEIGHT);
    this.setPlayB();
    this.setPausetext();
    this.setMenuText();
    this.setMenuB();
    this.setPauseBox();
    this.setgobackBox();
    this.setMenuBox();
    this.setgameOverBox();
    this.setGameState();
  }

  private void setGameState() {
    this.gameState = new Label(" Game Start");
    this.gameState.getStyleClass().addAll("pause-state");
    this.pauseStateBox = new HBox( this.gameState );
    this.pauseStateBox.setLayoutX(360);
    this.pauseStateBox.setLayoutY(100);
    this.pauseStateBox.setPrefHeight(100);
    this.pauseStateBox.setPrefWidth(400);
    this.getChildren().addAll( this.pauseStateBox );
  }

  private void setMenuText() {
    this.menutext = new Label("Menu");
    this.menutext.getStyleClass().addAll("pause-text");
  }

  private void setMenuB() {
    this.menuB = new Button();
    this.menuB.getStyleClass().addAll("menu-btn");
  }

  private void setgobackBox() {
    this.gobackBox = new VBox();
    this.gobackBox.setLayoutX(210);
    this.gobackBox.setLayoutY(37);
    this.gobackBox.setPrefWidth(150);

    VBox.setVgrow( this.menutext, Priority.ALWAYS );
    VBox.setVgrow( this.menuB, Priority.ALWAYS );

    this.gobackBox.getStyleClass().addAll("menu-vbox");
    this.gobackBox.getChildren().addAll( this.menutext, this.menuB );
  }

  private void setMenuBox() {
    this.menuBox = new AnchorPane();
    this.menuBox.setLayoutX(300);
    this.menuBox.setLayoutY(250);
    this.menuBox.setPrefHeight(200);
    this.menuBox.setPrefWidth(400);
    this.menuBox.getChildren().addAll( this.pauseBox, this.gobackBox);
    this.getChildren().add( this.menuBox);
    this.menuBox.getStyleClass().addAll("menu-pane");
  }

  private void setPauseBox() {
    this.pauseBox = new VBox();
    this.pauseBox.setLayoutX(40);
    this.pauseBox.setLayoutY(37);
    this.pauseBox.setPrefWidth(150);

    VBox.setVgrow( this.pausetext, Priority.ALWAYS );
    VBox.setVgrow( this.playB, Priority.ALWAYS );

    this.pauseBox.getStyleClass().addAll("menu-vbox");
    this.pauseBox.getChildren().addAll( this.pausetext, this.playB );
  }

  public void setPausetext() {
    this.pausetext = new Label("Loading !");
    this.pausetext.getStyleClass().addAll("pause-text");
  }

  private void setPlayB() {
    this.playB = new Button();
    this.playB.getStyleClass().addAll("play-btn");
  }

  public void removePlay() {
    PausePane pP = this;

    Platform.runLater( () -> {
      pP.menuBox.getChildren().clear();
      pP.getChildren().remove( this.menuBox );
    } );
  }
  public void setgameOverBox(){
    this.gameOverText = new Label("Score:");
    this.scoreText = new Label("Score");

    this.gameOverText.getStyleClass().addAll("pause-text");
    this.scoreText.getStyleClass().addAll("pause-text");
    this.gameOver = new VBox();
    this.gameOver.setLayoutX(40);
    this.gameOver.setLayoutY(37);
    this.gameOver.setPrefWidth(150);

    VBox.setVgrow( this.gameOverText, Priority.ALWAYS );
    VBox.setVgrow( this.scoreText, Priority.ALWAYS );

    this.gameOver.getStyleClass().addAll("menu-vbox");
    this.gameOver.getChildren().addAll( this.gameOverText, this.scoreText );
  }

  public void setGameOver() {
    PausePane pP = this;

    Platform.runLater( () -> {
      pP.menuBox.getChildren().addAll(gameOver,gobackBox);
      pP.getChildren().add(menuBox);
    });
  }
}