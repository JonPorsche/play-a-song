package game;

import game.sprites.Iteam;
import game.sprites.PlayerCharacter;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static application.Main.PLAYER_RADIUS;


// @ToDo: SongMapGenerator, HashMap<int x, obj iteam>,

public class GameLevel {

  // ATTRIBUTE
  protected String levelSourceTrack;
  protected List<Number> mapChunks = new ArrayList<>();
  protected HashMap<Number, Iteam> sortedItemsByPosX = new HashMap<>(); // +-10
  protected PlayerCharacter playerSpritesObject = new PlayerCharacter();
  protected double maxAmplitude = 0;

  // PROPERTYS
  protected IntegerProperty playerPosX = new SimpleIntegerProperty();
  protected IntegerProperty playerPosY = new SimpleIntegerProperty();
  protected FloatProperty gameSpeed = new SimpleFloatProperty(1);

  // PROPERTYS - POINTER
  protected ObjectProperty<GamePlayingState> gamePlayingStatePropPointer;
  protected ObjectProperty<Boolean> gameIsRunningPropPointer;
  protected ObjectProperty<Number> gamePlayerPosPropPointer;
  protected ObjectProperty<Number> gamePlayerScorePropPointer;

  public GameLevel(
      String newLevelSongPath,
      ObjectProperty<GamePlayingState> playingStatePrPo, // <- GameManager.gamePlayingState
      ObjectProperty<Boolean> isRunningPrPo, // <- GameManager.gameIsRunning
      ObjectProperty<Number> playerPosPrPo, // <- GameManager.gamePlayerPos
      ObjectProperty<Number> playerScorePrPo // <- GameManager.gamePlayerScore
  ) {
    this.levelSourceTrack = newLevelSongPath;

    // Declare PropertyPointers
    this.gamePlayingStatePropPointer = playingStatePrPo;
    this.gameIsRunningPropPointer = isRunningPrPo;
    this.gamePlayerPosPropPointer = playerPosPrPo;
    this.gamePlayerScorePropPointer = playerScorePrPo;
  }

  public boolean levelIsReady() {
    return this.levelSourceTrack != null
        && this.gamePlayingStatePropPointer != null
        && this.gameIsRunningPropPointer != null
        && this.gamePlayerPosPropPointer != null
        && this.gamePlayerScorePropPointer != null;
  }


  private void generateMapChunks() {
    // this.mapChunks.add( currentAmp );
  }

  public List<Iteam> iteamsInDangerZoner() {
    int curPlayerPosX = this.playerPosX.getValue( ).intValue( );
    List<Iteam> allResults = new ArrayList<>( );

    for (int areaIndex = curPlayerPosX - PLAYER_RADIUS; areaIndex < curPlayerPosX + PLAYER_RADIUS; areaIndex++) {
      Iteam result = sortedItemsByPosX.get( areaIndex );

      if (result != null) allResults.add( result );
    }

    return allResults;
  }

  public boolean generateTrackSteps( ) {
    if (this.levelSourceTrack == null) return false;

    String levelSourceTrackPath = this.levelSourceTrack;
    List<Number> mapChunks = this.mapChunks;

    new Thread( ) {
      @Override
      public void run( ) {

        int i = 0;
        for (double curAmplValue : new Audioinfo( levelSourceTrackPath ).getLeft( )) {
          if (maxAmplitude < curAmplValue) maxAmplitude = curAmplValue;
          mapChunks.add(curAmplValue);
          i++;
        }

      }
    }.start( );

    return false;
  }


  public List<Number> getMapChunks() { return this.mapChunks; }
  public int getMapChunk( int chunkIndex ) { return this.mapChunks.get( chunkIndex ).intValue( ); }
  public HashMap<Number, Iteam> getSortedItems( ) { return this.sortedItemsByPosX; }
  public Iteam getItemFromPosX( int posX ) { return this.sortedItemsByPosX.get( posX ); }
  public PlayerCharacter getPlayerSprite( ) { return this.playerSpritesObject; }

  // PROPERTYS
  public IntegerProperty getPlayerPosXProperty( ) { return this.playerPosX; }
  public IntegerProperty getPlayerPosYProperty( ) { return this.playerPosY; }
  public FloatProperty getGameSpeedProperty( ) { return this.gameSpeed; }

  // PROPERTYS - POINTER
  public ObjectProperty<Number> getGamePlayerPosProperty( ) { return this.gamePlayerPosPropPointer; }
  public ObjectProperty<Number> getGamePlayerScoreProperty( ) { return this.gamePlayerScorePropPointer; }
}