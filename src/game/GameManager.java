package game;

import business.data.Song;
import business.service.PlaylistManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class GameManager {
  //private List<GameLevel> loadedLevelList; // @ToDo: DoThis
  private ObjectProperty<GameLevel> playingLevel;
  private ObjectProperty<Boolean> gameIsRunning = new SimpleObjectProperty<>();

  /* Methoden */
  public void loadLevelFromSong( String newLevelSongPath ) {
    this.playingLevel.setValue(
        new GameLevel( newLevelSongPath )
    );
  }

  /* Actions */
  public void playerGoUp() {

  }

  /* Propertys */
  public ObjectProperty<Boolean> gameIsRunningProperty() {
    return this.gameIsRunning;
  }
}
