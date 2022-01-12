package game;


// @ToDo: RandomItemGenerator, PlayerMovement(Up/Down), updateGuiCanvas,

import application.Main;

public class GameEngine {

  private GameLevel playingLevel;

  public GameEngine() { }

  public void selectNewLevel() {

  }

  public void start( ) {
    // hier würde der Update-Thread gestartet

    // Reset PropertyValues
    this.playingLevel.getGamePlayerPosProperty().setValue(0);
    this.playingLevel.gamePlayerScorePropPointer.setValue(0);
    this.playingLevel.playerPosX.setValue( 0 );
    this.playingLevel.playerPosY.setValue( 0 );

    int curGamePos = 0 - Main.WINDOW_WIDTH / 2;
    while (!this.playingLevel.getGamePlayerPosProperty().equals(GamePlayingState.FINISHED)) {
      try {
        Thread.sleep(5);//17,5 );

        if (this.playingLevel.getGamePlayerPosProperty().equals(GamePlayingState.PLAY)) {
					/*final int finalCurGamePos = curGamePos;
					Platform.runLater(
							() -> gameDisplayPane.setCenterViewFrame(finalCurGamePos)
					);*/
          this.worldSlideNext( );
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void worldSlideNext( ) {

  }
}
