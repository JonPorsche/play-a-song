package uicomponents.game;

import game.GameManager;
import game.GamePlayingState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class OverlayController {

    public OverlayPane overlayPane;
    GameManager gM;
    EventHandler<ActionEvent> listner;

    public OverlayController(GameManager gM){
        listner = (ActionEvent event) -> {
            gM.getPlayingStateProperty().setValue(GamePlayingState.PAUSE);
        };
        this.overlayPane = new OverlayPane();
        gM.getPlayerScoreProperty().addListener((o,ol,on)->{
            overlayPane.scoreLabel.setText(on.toString());
        });
    }

    public void play() {
        overlayPane.pauseButton.setOnAction(listner);

    }

    public void pause() {
        overlayPane.removeEventFilter(ActionEvent.ACTION,listner);
    }
}
