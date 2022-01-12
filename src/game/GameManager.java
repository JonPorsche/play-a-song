package game;

import application.Main;
import business.data.Song;
import business.service.PlaylistManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.File;
import java.util.List;

public class GameManager {
  //private List<GameLevel> loadedLevelList; // @ToDo: DoThis

  // Global Property Handle
  protected ObjectProperty<GamePlayingState> gamePlayingState = new SimpleObjectProperty<>();
  private ObjectProperty<Boolean> gameIsRunning = new SimpleObjectProperty<>();
  protected ObjectProperty<GameLevel> gameLoadedLevel = new SimpleObjectProperty<>();

  // Level Property Handle
  protected ObjectProperty<Number> gamePlayerPos = new SimpleObjectProperty<>();
  protected ObjectProperty<Number> gamePlayerScore = new SimpleObjectProperty<>();

  /* Methoden */
  public void loadLevelFromSong( File playingTrackFile ) {
    if (playingTrackFile.exists())
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

  /* Propertys */
  public ObjectProperty<Boolean> gameIsRunningProperty( ) {
    return this.gameIsRunning;
  }

  protected void startNewGame() {
  }

  protected void gamePlayerPause() {
  }

  protected void gamePlayerContinue() {
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