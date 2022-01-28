package uicomponents.game;

import application.Main;
import game.GameManager;
import game.GamePlayingState;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import scenes.gameview.GameViewController;


public class PausePaneController {
    public PausePane pausePane = new PausePane();
    public SimpleBooleanProperty gameisFinished = new SimpleBooleanProperty();
    EventHandler<ActionEvent> listner;
    EventHandler<ActionEvent> listnenFinished;
    GameManager gM;

    public PausePaneController(GameManager gM, GameViewController gV) {
        this.gM = gM;
        listner = (ActionEvent event) -> {
            gM.getPlayingStateProperty().setValue(GamePlayingState.PLAY);
            gM.play();
        };

        listnenFinished = (ActionEvent event) -> {
            gV.gameisFinishedProperty().set(true);
        };
        pausePane.menuB.setOnAction(listnenFinished);

    }
    public void isLoading(){
        Platform.runLater(() ->{
            pausePane.setVisible(true);
            pausePane.playB.setVisible(false);});
    }

    public void play() {
        Platform.runLater(() ->{

            pausePane.playB.removeEventHandler(ActionEvent.ACTION,listner);
            pausePane.pausetext.setText("Continue ?");
            pausePane.setVisible(false);
        });
    }
    public void pause() {
            Platform.runLater(() ->{
                pausePane.setVisible(true);
                pausePane.playB.setVisible(true);
                pausePane.playB.setOnAction(listner);
                pausePane.gameState.setText("     Pause");
            });
    }

    public void finished(){
        Platform.runLater(() -> {
            pausePane.setVisible(true);
            pausePane.gameState.setText("Level Clear");
            pausePane.removePlay();
            pausePane.setGameOver();
            pausePane.scoreText.setText(gM.getPlayerScoreProperty().getValue().toString());
            pausePane.playB.removeEventHandler(ActionEvent.ACTION,listner);
            pausePane.playB.setOnAction(listnenFinished);
        });
    }

    public void ready(){pausePane.playB.setOnAction(listner);
        Platform.runLater(()->{
            pausePane.playB.setVisible(true);
            pausePane.pausetext.setText("Play");
            pausePane.playB.setOnAction(listner);
        });



    }


    public void gameOver() {
       Platform.runLater(()->pausePane.gameState.setText(" Gameover"));

    }
}
