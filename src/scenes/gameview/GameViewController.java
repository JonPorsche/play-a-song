package scenes.gameview;

import application.Main;
import business.service.PlaylistManager;
import game.GameManager;
import scenes.BasicView;
import uicomponents.game.GameDisplay;
import uicomponents.game.WorldPane;

public class GameViewController extends BasicView {
  protected GameManager gameManager;
  public GameDisplay gameDisplayPane;

  public GameViewController( Main app ) {
    super( app );

    GameView gameViewPane = new GameView( );
    this.menuRootView = gameViewPane;

    GameManager gM = app.getGameManger( );
    this.gameManager = gM;

    this.gameDisplayPane = gameViewPane.gameDisplay;
    app.defineGameDisplayPane( gameViewPane.gameDisplay);

    // Gib der Engine den AusgabePunkt mit
    gM.declareGameDisplayPane( this.gameDisplayPane );

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
      (o, oV, newLoadedLevel) -> {
        if (gameViewPane.gameDisplay.gameWorldPane != null)
          gameViewPane.gameDisplay.gameWorldPane.setWorldSteps( newLoadedLevel.getMapChunks( ), newLoadedLevel.getMaxAmplitude( ) );
      }
    );
  }
}
