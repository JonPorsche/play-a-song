package game;

import game.sprites.Iteam;
import game.sprites.PlayerCharacter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import uicomponents.game.GameDisplay;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static application.Main.PLAYER_RADIUS;

public class GameManager {
  //private List<GameLevel> loadedLevelList; // @ToDo: DoThis
  private GameEngine gameEngine;

  // Global Property Handle
  protected ObjectProperty<GamePlayingState> gamePlayingState = new SimpleObjectProperty<>();
  private ObjectProperty<Boolean> gameIsRunning = new SimpleObjectProperty<>();
  protected ObjectProperty<GameLevel> gameLoadedLevel = new SimpleObjectProperty<>();
  protected HashMap<Number, Iteam> sortedItemsByPosX = new HashMap<>(); // +-10
  public PlayerCharacter playerSpritesObject = new PlayerCharacter();

  // Level Property Handle
  protected ObjectProperty<Double> gamePlayerPos = new SimpleObjectProperty<>();
  protected ObjectProperty<Number> gamePlayerScore = new SimpleObjectProperty<>();

  public GameManager( ) {
    this.gameEngine = new GameEngine(
      this.gamePlayingState,
      this.gameIsRunning,
      this.gameLoadedLevel,
      this.gamePlayerPos,
      this.gamePlayerScore
    );
  }

  public PlayerCharacter getPlayerSpritesObject() {
    return playerSpritesObject;
  }

  public void declareGameDisplayPane(GameDisplay guiGameDisplaySelector) {
    this.gameEngine.declareGameDisplayPane(guiGameDisplaySelector);
  }

  /* Methoden */
  public void loadLevelFromSong( File playingTrackFile ) {
    if (playingTrackFile.exists( ))
      this.gameEngine.setNewLevel( new GameLevel (
          playingTrackFile.getAbsolutePath( )
      ) );
  }

  public void loadLevelFromSong( String newLevelSongPath ) {
    this.loadLevelFromSong( new File( newLevelSongPath ) );
  }

  public void startPlaying( ) {
    this.gameEngine.startPlaying( );
  }

  /* Actions */
  public void playerGoUp( ) {
    double modValueUp = 20;
    if (gameLoadedLevel.getValue().getUpperBoarder(gameLoadedLevel.getValue().gamePlayerPos) < playerSpritesObject.getCenterY()+modValueUp+playerSpritesObject.getRadius()) {
      playerSpritesObject.setCenterY(playerSpritesObject.getY() + modValueUp);

    }
  }
  public void playerGoDown( ) {
    double modValueDown = 20;
    if (gameLoadedLevel.getValue().getUpperBoarder(gameLoadedLevel.getValue().gamePlayerPos) < playerSpritesObject.getCenterY()+modValueDown+ playerSpritesObject.getRadius()) {
      playerSpritesObject.setCenterY(playerSpritesObject.getY() + modValueDown);
    }

  }
  public void play( ) {
    GamePlayingState curState = this.gamePlayingState.getValue( );

    switch (curState) {
      case FINISHED:
        // @ToDo: Reset to Start ( auch erneut Items spawnen? )
      case READY:
      case PAUSE:
        this.gamePlayingState.setValue( GamePlayingState.PLAY );
        break;
      case NOTREADY: System.out.println( "TODO MSG: Bitte wählen Sie einen Track aus!"); break;
      default:
    }
  }
  public void pause( ) {
    GamePlayingState curState = this.gamePlayingState.getValue( );

    if (curState.equals( GamePlayingState.PLAY ))
      this.gamePlayingState.setValue( GamePlayingState.PAUSE );
  }


  //@TODO punktKreisCollision
  public boolean mapCollsion(int x){
    gameLoadedLevel.getValue().getDownBoarder(x);
    gameLoadedLevel.getValue().getUpperBoarder(x);

    return false;
  }

  /* Propertys */
  public ObjectProperty<Boolean> gameIsRunningProperty( ) {
    return this.gameIsRunning;
  }

  /* --- GETTER --- */

  public ObjectProperty<GamePlayingState> getPlayingStateProperty() {
    return this.gamePlayingState;
  }

  public GamePlayingState getPlayingState() {
    return this.getPlayingStateProperty().getValue();
  }

  public ObjectProperty<Double> getPlayerPosProperty() {
    return this.gamePlayerPos;
  }

  public int getPlayerPos() {
    return this.getPlayerPosProperty().getValue().intValue();
  }

  public ObjectProperty<GameLevel> getLoadedLevelProperty() {
    return this.gameLoadedLevel;
  }

  public GameLevel getLoadedLevel() {
    return this.getLoadedLevelProperty().getValue();
  }

  public ObjectProperty<Number> getPlayerScoreProperty() {
    return this.gamePlayerScore;
  }

  public int getPlayerScore() {
    return this.getPlayerScoreProperty().getValue().intValue();
  }
}