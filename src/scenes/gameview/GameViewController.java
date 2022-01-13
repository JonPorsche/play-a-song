package scenes.gameview;

import application.Main;
import game.GameManager;
import scenes.BasicView;
import uicomponents.game.WorldPane;

public class GameViewController extends BasicView {
  protected GameManager gameManager;

  public GameViewController( Main app ) {
    super( app );

    GameView gameViewPane = new GameView( );
    this.menuRootView = gameViewPane;
    WorldPane worldPane = gameViewPane.gameDisplay.gameWorldPane;

    this.gameManager = app.getGameManger( );

    app.defineGameDisplayPane( gameViewPane.gameDisplay);

    GameManager gM = this.gameManager;

    /*/ Wenn sich die PlayerPosition (only X-Pos) verändert
    // Dann verschiebe die Leinwand auf die neue Position
    gM.getPlayerPosProperty( ).addListener(
      (o, oV, newDouble) -> worldPane.setCenterViewFrame( newDouble )
    );*/

    // Wenn sich der SpielStatus ändert
    // Dann pass die GUI auf das neue Szenario an
    gM.getPlayingStateProperty( ).addListener( (o, oV, newState) -> {
      switch (newState) { // @ToDo: GameViewPanel Switching
        case NOTREADY:
          // Display ErrorMsg
          break;
        case READY:
          // Display GoButton
          break;
        case PAUSE:
          // Display PauseMenu
          break;
        case FINISHED:
          // Display ScorePanel
          break;
        default:
      }
    } );

    // Wenn eine neues Level und somit eine neue World geladen wird
    // Dann Zeichne die Leinwand erneut...
    gM.getLoadedLevelProperty( ).addListener(
      (o, oV, newLoadedLevel) -> worldPane.setWorldSteps( newLoadedLevel.getMapChunks( ), newLoadedLevel.getMaxAmplitude( ) )
    );
  }
}
