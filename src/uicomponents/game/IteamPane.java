package uicomponents.game;

import application.Main;
import game.sprites.Coin;
import game.sprites.Iteam;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class IteamPane extends StackPane {
    public IteamPane() {

        this.setHeight(Main.WINDOW_HEIGHT);
        this.setWidth(Main.WINDOW_WIDTH);



    }
    public void setCenterViewFrame( double playerPos ) {
        int width = (int) this.getWidth();
        Platform.runLater(
                ( ) -> this.setTranslateX( 0 - playerPos )
        );
    }


    public void addIteam(Iteam iteam) {
        Platform.runLater(
                () -> this.getChildren().add(iteam)
        );
    }
    public void removeIteam(Iteam iteam) {
        Platform.runLater(
                () -> this.getChildren().remove(iteam)
        );
    }
}
