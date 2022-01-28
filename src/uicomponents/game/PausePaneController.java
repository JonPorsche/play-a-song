package uicomponents.game;

import game.GameManager;
import game.GamePlayingState;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import scenes.gameview.GameViewController;

public class PausePaneController {
  protected EventHandler<ActionEvent> listner;
  protected EventHandler<ActionEvent> listnenFinished;
  protected GameManager gM;

  public PausePane pausePane = new PausePane();
  public SimpleBooleanProperty gameisFinished = new SimpleBooleanProperty();

  public PausePaneController(GameManager gM, GameViewController gV) {
    this.gM = gM;

    this.listner = (ActionEvent event) -> {
      gM.getPlayingStateProperty().setValue(GamePlayingState.PLAY);
      gM.play();
    };
    this.listnenFinished = (ActionEvent event) -> gV.gameisFinishedProperty().set(true);

    this.pausePane.menuB.setOnAction(listnenFinished);
  }
  public void isLoading() {
    PausePaneController pPC = this;

    Platform.runLater( () -> {
      pPC.pausePane.setVisible(true);
      pPC.pausePane.playB.setVisible(false);
    } );
  }

  public void play() {
    PausePaneController pPC = this;

    Platform.runLater( () -> {
      pPC.pausePane.playB.removeEventHandler(ActionEvent.ACTION,listner);
      pPC.pausePane.pausetext.setText("Continue ?");
      pPC.pausePane.setVisible(false);
    } );
  }
  public void pause() {
    PausePaneController pPC = this;

    Platform.runLater( () -> {
      pPC.pausePane.setVisible(true);
      pPC.pausePane.playB.setVisible(true);
      pPC.pausePane.playB.setOnAction(listner);
      pPC.pausePane.gameState.setText("     Pause");
    } );
  }

  public void finished() {
    PausePaneController pPC = this;

    Platform.runLater( () -> {
      pPC.pausePane.setVisible(true);
      pPC.pausePane.gameState.setText("Level Clear");
      pPC.pausePane.removePlay();
      pPC.pausePane.setGameOver();
      pPC.pausePane.scoreText.setText(gM.getPlayerScoreProperty().getValue().toString());
      pPC.pausePane.playB.removeEventHandler(ActionEvent.ACTION,listner);
      pPC.pausePane.playB.setOnAction(listnenFinished);
    });
  }

  public void ready() {
    PausePaneController pPC = this;
    pPC.pausePane.playB.setOnAction(listner);

    Platform.runLater( () -> {
      pPC.pausePane.playB.setVisible(true);
      pPC.pausePane.pausetext.setText("Play");
      pPC.pausePane.playB.setOnAction(listner);
    } );
  }


  public void gameOver() {
    PausePaneController pPC = this;

    Platform.runLater(
      () -> pPC.pausePane.gameState.setText(" Gameover")
    );
  }
}
