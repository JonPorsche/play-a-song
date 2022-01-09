package gamelogic;

import application.Main;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.File;

public class GameManager {
  // Global Property Handle
  protected ObjectProperty<GamePlayingState> gamePlayingState = new SimpleObjectProperty<>( );
  protected ObjectProperty<GameLevel> gameLoadedLevel = new SimpleObjectProperty<>( );

  // Level Property Handle
  protected ObjectProperty<Number> gamePlayerPos = new SimpleObjectProperty<>( );
  protected ObjectProperty<Number> gamePlayerScore = new SimpleObjectProperty<>( );

  protected void startNewGame( File playingTrackFile ) { }

  protected void gamePlayerPause( ) { }
  protected void gamePlayerContinue( ) { }

  public void start( ) {
    // hier würde der Update-Thread gestartet

    int curGamePos = 0 - Main.WINDOW_WIDTH /2;
    //for (; curGamePos < amplitudeArray.length*100; curGamePos++) {
    while (!this.levelIsFinished( )) {
      try {
        Thread.sleep( 5 );//17,5 );

        if (this.gameIsPlaying( )) {
					/*final int finalCurGamePos = curGamePos;
					Platform.runLater(
							() -> gameDisplayPane.setCenterViewFrame(finalCurGamePos)
					);*/
          this.worldSlideNext( );
        }

      } catch (InterruptedException e) {
        e.printStackTrace( );
      }
    }
  }

  /* --- GETTER --- */

  public ObjectProperty<GamePlayingState> getPlayingStateProperty( ) { return this.gamePlayingState; }
  public GamePlayingState getPlayingState( ) { return this.getPlayingStateProperty( ).getValue( ); }

  public ObjectProperty<Number> getPlayerPosProperty( ) { return this.gamePlayerPos; }
  public int getPlayerPos( ) { return this.getPlayerPosProperty( ).getValue( ).intValue( ); }

  public ObjectProperty<GameLevel> getLoadedLevelProperty( ) { return this.gameLoadedLevel; }
  public GameLevel getLoadedLevel( ) { return this.getLoadedLevelProperty( ).getValue( ); }

  public ObjectProperty<Number> getPlayerScoreProperty( ) { return this.gamePlayerScore; }
  public int getPlayerScore( ) { return this.getPlayerScoreProperty( ).getValue( ).intValue( ); }

}
