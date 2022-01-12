package game;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import uicomponents.game.GameDisplay;

import java.io.File;

public class GameManager {
  //private List<GameLevel> loadedLevelList; // @ToDo: DoThis
  private GameEngine gameEngine;

  // Global Property Handle
  protected ObjectProperty<GamePlayingState> gamePlayingState = new SimpleObjectProperty<>();
  private ObjectProperty<Boolean> gameIsRunning = new SimpleObjectProperty<>();
  protected ObjectProperty<GameLevel> gameLoadedLevel = new SimpleObjectProperty<>();

  // Level Property Handle
  protected ObjectProperty<Number> gamePlayerPos = new SimpleObjectProperty<>();
  protected ObjectProperty<Number> gamePlayerScore = new SimpleObjectProperty<>();

  public GameManager( ) {
    this.gameEngine = new GameEngine( this.gamePlayingState, this.gameIsRunning );
  }

  public void declareGameDisplayPane( GameDisplay guiGameDisplaySelector) {
    this.gameEngine.declareGameDisplayPane(guiGameDisplaySelector);
  }

  /* Methoden */
  public void loadLevelFromSong( File playingTrackFile ) {
    if (playingTrackFile.exists( ))
      this.gameLoadedLevel.setValue( new GameLevel(
        playingTrackFile.getAbsolutePath( ),
        this.gamePlayingState,
        this.gameIsRunning,
        this.gamePlayerPos,
        this.gamePlayerScore
      ) );
  }

  public void loadLevelFromSong( String newLevelSongPath ) {
    this.loadLevelFromSong( new File( newLevelSongPath ) );
  }

  /* Actions */
  public void playerGoUp( ) {

  }
  public void playerGoDown( ) {

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

  public ObjectProperty<Number> getPlayerPosProperty() {
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