package game;

import game.sprites.logic.PlayerCharacter;
import game.sprites.basic.Iteam;
import game.sprites.optic.SpriteLogic;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import uicomponents.game.GameDisplay;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BinaryOperator;

public class GameManager {
  //private List<GameLevel> loadedLevelList; // @ToDo: DoThis
  private GameEngine gameEngine;
  private List<BinaryOperator> iteamFactoryCollection = new ArrayList<>();

  // Global Property Handle
  protected ObjectProperty<GamePlayingState> gamePlayingState = new SimpleObjectProperty<>(GamePlayingState.NOTREADY);
  private ObjectProperty<Boolean> gameIsRunning = new SimpleObjectProperty<>();
  protected ObjectProperty<GameLevel> gameLoadedLevel = new SimpleObjectProperty<>();
  protected HashMap<Number, Iteam> sortedItemsByPosX = new HashMap<>(); // +-10
  protected PlayerCharacter playerCharacterObject = new PlayerCharacter();

  // Level Property Handle
  protected ObjectProperty<Double> gamePlayerPos = new SimpleObjectProperty<>();
  protected ObjectProperty<Number> gamePlayerScore = new SimpleObjectProperty<>();
  protected ObjectProperty<Number> gamePlayerLife = new SimpleObjectProperty<>();

  public GameManager( ) {
    this.gameEngine = new GameEngine(
      this.gamePlayingState,
      this.gameIsRunning,
      this.gameLoadedLevel,
      this.gamePlayerPos,
      this.gamePlayerScore,
      this.gamePlayerLife
    );
    gamePlayingState.setValue(GamePlayingState.LOADING);
  }

  /*public PlayerCharacter getPlayerSpritesObject() {
    return playerSpritesObject;
  }*/

  public void declareGameDisplayPane(GameDisplay guiGameDisplaySelector) {
    this.gameEngine.declareGameDisplayPane(guiGameDisplaySelector);
  }

  /* Methoden */
  public void loadLevelFromSong( File playingTrackFile ) {
    if (playingTrackFile.exists( ))
      this.gameEngine.setNewLevel( new GameLevel (
          playingTrackFile.getAbsolutePath( )
      ), this.iteamFactoryCollection );
  }

  public void loadLevelFromSong( String newLevelSongPath ) {

    this.loadLevelFromSong( new File( newLevelSongPath ) );
  }

  public void startPlaying( ) {
    /*int canWidth = Main.WINDOW_WIDTH * 15; //Main.gameManager.getCanvasWidthPx( );*/
    this.gameEngine.getGameDisplayPane().initCanvas( this.gameLoadedLevel.getValue( ).getMapPixelWidth( ) );

    this.gameEngine.startPlaying( );

  }

  /* Actions */
  public void playerGoUp( ) {
    double modValueUp = 20;
    gameEngine.player.setY(gameEngine.player.getY()-20);
  }
  public void playerGoDown( ) {
    double modValueDown = 20;
    gameEngine.player.setY(gameEngine.player.getY()+20);


  }


  public void play( ) {
   gameEngine.startPlaying();
  }
  public void pause( ) {
    GamePlayingState curState = this.gamePlayingState.getValue( );
    gameEngine.pausePlaying();
  }

  /* Propertys */
  public ObjectProperty<Boolean> gameIsRunningProperty( ) {
    return this.gameIsRunning;
  }

  /* --- GETTER --- */
  public ObjectProperty<GamePlayingState> getPlayingStateProperty( ) {
    return this.gamePlayingState;
  }

  public GamePlayingState getPlayingState( ) {
    return this.getPlayingStateProperty().getValue();
  }

  public ObjectProperty<Double> getPlayerPosProperty() {
    return this.gamePlayerPos;
  }

  public int getPlayerPos( ) {
    return this.getPlayerPosProperty().getValue().intValue();
  }

  public ObjectProperty<GameLevel> getLoadedLevelProperty() {
    return this.gameLoadedLevel;
  }

  public GameLevel getLoadedLevel( ) {
    return this.getLoadedLevelProperty().getValue();
  }

  public ObjectProperty<Number> getPlayerScoreProperty() {
    return this.gamePlayerScore;
  }

  public int getPlayerScore( ) {
    return this.getPlayerScoreProperty().getValue().intValue();
  }

  public int getCanvasWidthPx( ) {
    if (this.gameLoadedLevel.getValue( ) != null)
      return this.gameLoadedLevel.getValue( ).getMapPixelWidth( );
    else System.out.println( "Noch kein Canvas vorhanden! =null" );

    return 0;
  }

  public GameManager addIteamPattern( BinaryOperator factoryOperator ) {
    Object factoryResult = factoryOperator.apply( 0, 0 );

    try {
      SpriteLogic testResultCasting = (SpriteLogic)factoryResult;

      if (testResultCasting.x == 0
      &&  testResultCasting.y == 0
      ) {
        this.iteamFactoryCollection.add( factoryOperator );
      }
    } catch (Exception ex) {
      System.err.println( ex.getMessage( ) );
    }

    return this;
  }

  public GameManager addIteamPattern( BinaryOperator... factoryOperator ) {
    for (BinaryOperator curOp : factoryOperator)
      this.addIteamPattern( curOp );

    return this;
  }
}
