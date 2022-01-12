package game;


// @ToDo: RandomItemGenerator, PlayerMovement(Up/Down), updateGuiCanvas,

import application.Main;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import uicomponents.game.GameDisplay;

public class GameEngine {

  private GameLevel playingLevel;
  private GameDisplay gameDisplaySelector;

  // PROPERTYS - POINTER
  protected ObjectProperty<GamePlayingState> gamePlayingStatePropPointer;
  protected ObjectProperty<Boolean> gameIsRunningPropPointer;

  public GameEngine(
    ObjectProperty<GamePlayingState> playingStatePrPo, // <- GameManager.gamePlayingState
    ObjectProperty<Boolean> isRunningPrPo // <- GameManager.gameIsRunning
  ) {
    this.gamePlayingStatePropPointer = playingStatePrPo;
    this.gameIsRunningPropPointer = isRunningPrPo;

    this.bindInternPropertyComputing( );
  }

  public void declareGameDisplayPane( GameDisplay guiGameDisplaySelector) {
    this.gameDisplaySelector = guiGameDisplaySelector;
  }

  public void selectNewLevel( ) {

  }

  public void start( ) {
    // hier würde der Update-Thread gestartet

    // Reset PropertyValues
    GameLevel gL = this.playingLevel;
    gL.getGamePlayerPosProperty().setValue(0);
    gL.gamePlayerScorePropPointer.setValue(0);
    gL.playerPosX.setValue( 0 );
    gL.playerPosY.setValue( 0 );

    int curGamePos = 0 - Main.WINDOW_WIDTH / 2;
    while (!gL.getGamePlayerPosProperty( ).equals( GamePlayingState.FINISHED )) {
      try {
        Thread.sleep(5);//17,5 );

        if (gL.getGamePlayerPosProperty( ).equals( GamePlayingState.PLAY )) {
          this.worldSlideNext( );
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void worldSlideNext( ) {
    int curPlayerPosX = this.playingLevel.getGamePlayerPosProperty( ).getValue( ).intValue( );
    int gameSpeed = this.playingLevel.getGameSpeedProperty( ).getValue( ).intValue( );

    this.playingLevel.gamePlayerPosPropPointer.setValue(
      curPlayerPosX + ( 1 * gameSpeed ) // aktuelle Positon X + neue Position (inkl. SpeedMultiplikator)
    );
  }

  private void bindInternPropertyComputing( ) {
    this.playingLevel.getGamePlayerPosProperty( ).addListener( (o, oPos, newPos) -> Platform.runLater(
      ( ) -> gameDisplaySelector.gameWorldPane.setCenterViewFrame( newPos.intValue( ) )
    ) );
  }
}
