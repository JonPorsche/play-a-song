package scenes.gameview;

import javafx.scene.layout.Pane;
import uicomponents.WorldPane;

public class GameView extends Pane {
    private WorldPane gameWorldPane = new WorldPane( );

    public GameView( ) {
        super( );
        this.getChildren( ).add( this.gameWorldPane );
    }

    public WorldPane getWorld( ) {
        return this.gameWorldPane;
    }

    public void setCenterViewFrame( double playerPos ) {
        this.gameWorldPane.setLayoutX( 0 - playerPos );
    }
}