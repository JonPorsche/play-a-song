package gamelogic;

import application.Main;
import game.Audioinfo;
import gamelogic.sprites.Player;
import gamelogic.sprites._old_WaveWorldObj;
import gamelogic.sprites.base.GameObject;
import gamelogic.sprites.iteam;
import javafx.beans.property.ObjectProperty;
import gamelogic.sprites.base.Sprite;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameLevel {

  // Private Memory
  protected File levelSourceTrack;
  protected List<_old_WaveWorldObj> mapChunks = new ArrayList<>();
  protected HashMap<Number, GameObject> iteams = new HashMap<>();
  protected double maxAmplitude = 0;

  protected double gameSpeed = 1;

  // Property Pointer
  protected ObjectProperty<Number> gamePlayerPosPropPointer;
  protected ObjectProperty<Number> gamePlayerScorePropPointer;

  protected Player playerSpritesobject = new Player( );
  private List<iteam> iteams = new ArrayList<>( );
  private List<Sprite> Iteamsprites = new ArrayList<>( );


  public GameLevel(
      ObjectProperty<Number> playerPosPrPo, // <- GameManager.gamePlayerPos
      ObjectProperty<Number> playerScorePrPo // <- GameManager.gamePlayerScore
  ) {

    // Reset PropertyValues
    playerPosPrPo.setValue( 0 );
    playerScorePrPo.setValue( 0 );

    // Declare PropertyPointers
    this.gamePlayerPosPropPointer = playerPosPrPo;
    this.gamePlayerScorePropPointer = playerScorePrPo;
  }

  public boolean levelIsReady( ) {
    return this.gamePlayerPosPropPointer != null
      && this.gamePlayerScorePropPointer != null;
  }

  public boolean generateTrackSteps( ) {
    if (this.levelSourceTrack == null) return false;

    File levelSourceTrack = this.levelSourceTrack;
    List<_old_WaveWorldObj> mapChunks = this.mapChunks;

    //File trackFile = new File( "src/assets/example-track.mp3" );

    Thread thread = new Thread() {

      @Override
      public void run() {

        double[] tempAmpArr = new Audioinfo().getLeft( levelSourceTrack.getAbsolutePath() );

        int i = 0;
        for (double curAmplValue : tempAmpArr ) {
          if (maxAmplitude < curAmplValue) maxAmplitude = curAmplValue;

          // @ToDo: Move to draw process
          _old_WaveWorldObj worldChunkGameObject = new _old_WaveWorldObj( );
          worldChunkGameObject.setgamespeed( 1 );
          worldChunkGameObject.setX( i * Main.MAP_CHUNK_WIDTH_PX );
          worldChunkGameObject.setY( 0 );
          //gameObject.setHeight( 50 + 6 * curAmplValue );
          worldChunkGameObject.setHeight( Main.MAP_CHUNK_BASE_HEIGHT_PX + Main.MAP_CHUNK_HEIGHT_PX * curAmplValue );
          worldChunkGameObject.setWidth( Main.MAP_CHUNK_WIDTH_PX );

          //mapChunks.add( curAmplValue );

          mapChunks.add( worldChunkGameObject );
          i++;
        }

      }

    }; thread.start();

    return false;
  }
}
