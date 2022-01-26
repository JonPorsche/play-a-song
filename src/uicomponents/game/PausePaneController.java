package uicomponents.game;

import game.GameLevel;
import game.GameManager;
import game.GamePlayingState;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;


public class PausePaneController {
    public PausePane pausePane = new PausePane();
    EventHandler<ActionEvent> listner;
    GameManager gM;

    public PausePaneController(GameManager gM) {
        this.gM = gM;
        listner = (ActionEvent event) -> {
            gM.getPlayingStateProperty().setValue(GamePlayingState.PLAY);
            gM.play();
        };
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
                pausePane.playB.setOnAction(listner);});
    }
    public void ready(){pausePane.playB.setOnAction(listner);
        Platform.runLater(()->{
            pausePane.playB.setVisible(true);
            pausePane.pausetext.setText("     Play   ");});


    }


}
