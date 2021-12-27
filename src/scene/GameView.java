package scene;

import javafx.scene.layout.Pane;
import uicomponents.WorldPane;
import uicomponents.WaveRSprite;

public class GameView extends Pane {
    private WorldPane gameWorldPane = new WorldPane( );

    public GameView( ) {
        super( );
        this.getChildren().add( this.gameWorldPane );
    }

    public void addNextWorldStep( WaveRSprite sprite ) {
        this.gameWorldPane.getChildren( ).add( sprite );
    }

    public void setCenterViewFrame( double playerPos ) {
        this.gameWorldPane.setLayoutX( 0 - playerPos );
    }
}
