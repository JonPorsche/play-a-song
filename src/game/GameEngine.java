package game;


// @ToDo: RandomItemGenerator, PlayerMovement(Up/Down), updateGuiCanvas,

import application.Main;
import javafx.application.Platform;
import javafx.beans.property.*;
import uicomponents.game.GameDisplay;

public class GameEngine {

  private GameDisplay gameDisplaySelector;

  // PROPERTYS
  protected IntegerProperty playerPosX = new SimpleIntegerProperty();
  protected IntegerProperty playerPosY = new SimpleIntegerProperty();
  protected FloatProperty gameSpeed = new SimpleFloatProperty(1);

  // PROPERTYS - POINTER
  protected ObjectProperty<GamePlayingState> gamePlayingStatePropPointer;
  protected ObjectProperty<Boolean> gameIsRunningPropPointer;
  protected ObjectProperty<GameLevel> gameLoadedLevelPropPointer;
  protected ObjectProperty<Double> gamePlayerPosPropPointer;
  protected ObjectProperty<Number> gamePlayerScorePropPointer;

  public GameEngine(
      ObjectProperty<GamePlayingState> playingStatePrPo, // <- GameManager.gamePlayingState
      ObjectProperty<Boolean> isRunningPrPo, // <- GameManager.gameIsRunning
      ObjectProperty<GameLevel> loadedLevelPrPo, // <- GameManager.gameLoadedLevel
      ObjectProperty<Double> playerPosPrPo, // <- GameManager.gamePlayerPos
      ObjectProperty<Number> playerScorePrPo // <- GameManager.gamePlayerScore
  ) {
    // Declare PropertyPointers
    this.gamePlayingStatePropPointer = playingStatePrPo;
    this.gameIsRunningPropPointer = isRunningPrPo;
    this.gameLoadedLevelPropPointer = loadedLevelPrPo;
    this.gamePlayerPosPropPointer = playerPosPrPo;
    this.gamePlayerScorePropPointer = playerScorePrPo;

    this.bindInternPropertyComputing( );
  }

  public boolean isReady( ) {
    return this.gamePlayingStatePropPointer != null
    &&     this.gameIsRunningPropPointer != null
    &&     this.gameLoadedLevelPropPointer != null
    &&     this.gameLoadedLevelPropPointer.getValue( ) != null
    &&     this.gamePlayerPosPropPointer != null
    &&     this.gamePlayerScorePropPointer != null;
  }

  public void declareGameDisplayPane( GameDisplay guiGameDisplaySelector) {
    this.gameDisplaySelector = guiGameDisplaySelector;
  }

  public void setNewLevel( GameLevel gL ) {
    // Update PropertyValues

    this.gameLoadedLevelPropPointer.setValue( gL );

    this.gamePlayingStatePropPointer.setValue( GamePlayingState.NOTREADY );

    this.gamePlayerPosPropPointer.setValue( gL.gamePlayerPos );
    this.gamePlayerScorePropPointer.setValue( gL.gamePlayerScore );

    this.playerPosX.setValue( gL.playerPosX );
    this.playerPosY.setValue( gL.playerPosY );

    if (this.gameLoadedLevelPropPointer.getValue( ).mapChunks.size() > 100 )
      this.gamePlayingStatePropPointer.setValue( GamePlayingState.READY );
  }

  private void startEngine( ) {
    GameEngine gE = this;

    new Thread( ) {
      @Override
      public void run( ) {
        if (!gE.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PLAY )) return;

        do {
          try {
            Thread.sleep( 16,666 );
            //gE.worldSlideNext( );
            gE.gamePlayerPosPropPointer.setValue(
              gE.gamePlayerPosPropPointer.getValue( ) + ( Main.WINDOW_WIDTH / 180) // 360FPS pro Sichtfeldbreite
            );

          } catch (InterruptedException e) {
            e.printStackTrace( );
          }
        } while (
            gE.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PLAY )
        );

      }
    }.start( );

    /*while (!this.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.FINISHED )) {
      try {
        Thread.sleep( 5 );//17,5 );

        if (this.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PLAY )) {
          this.worldSlideNext( );
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }*/
  }

  public void startPlaying( ) {
    if (this.isReady( ) && this.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.READY )) {
      this.gamePlayingStatePropPointer.setValue( GamePlayingState.PLAY );

      this.startEngine( );
    }
  }

  public void pausePlaying( ) {
    if (this.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PLAY ))
      this.gamePlayingStatePropPointer.setValue( GamePlayingState.PAUSE );
  }

  public void continuePlaying( ) {
    if (this.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PAUSE )) {
      this.gamePlayingStatePropPointer.setValue( GamePlayingState.PLAY );
      this.startEngine( );
    }
  }

  private void gameIsFinish( ) {
    this.gamePlayingStatePropPointer.setValue( GamePlayingState.FINISHED );
  }

  public GameLevel getPlayingLevel( ) { return this.gameLoadedLevelPropPointer.getValue( ); }

  private void worldSlideNext( ) {
    int curPlayerPosX = this.getGamePlayerPosProperty( ).getValue( ).intValue( );
    int gameSpeed = this.getGameSpeedProperty( ).getValue( ).intValue( );

    this.gamePlayerPosPropPointer.setValue(
        (double)(curPlayerPosX + ( 1 * gameSpeed )) // aktuelle Positon X + neue Position (inkl. SpeedMultiplikator)
    );
  }

  private void bindInternPropertyComputing( ) {
    // Verknüpfte X-Position mit GUI-Leinwand
    this.gamePlayerPosPropPointer.addListener(
        (o, oPos, newPos) -> gameDisplaySelector.gameWorldPane.setCenterViewFrame( newPos.intValue( ) )
    );

    // Verknüpfte die EngineAttribute mit den GameLevelAttributen
    ObjectProperty<GameLevel> pLevelProp = this.gameLoadedLevelPropPointer;
    this.gamePlayerPosPropPointer.addListener( (o, oP, newPosition) -> pLevelProp.getValue( ).gamePlayerPos = newPosition );
    this.gamePlayerScorePropPointer.addListener( (o, oS, newScore) -> pLevelProp.getValue( ).gamePlayerScore = newScore.intValue( ) );
    this.playerPosX.addListener( (o, oP, newPosition) -> pLevelProp.getValue( ).playerPosX = newPosition.intValue( ) );
    this.playerPosY.addListener( (o, oP, newPosition) -> pLevelProp.getValue( ).playerPosY = newPosition.intValue( ) );
  }

  // PROPERTYS
  public IntegerProperty getPlayerPosXProperty( ) { return this.playerPosX; }
  public IntegerProperty getPlayerPosYProperty( ) { return this.playerPosY; }
  public FloatProperty getGameSpeedProperty( ) { return this.gameSpeed; }

  // PROPERTYS - POINTER
  public ObjectProperty<Double> getGamePlayerPosProperty( ) { return this.gamePlayerPosPropPointer; }
  public ObjectProperty<Number> getGamePlayerScoreProperty( ) { return this.gamePlayerScorePropPointer; }
}
