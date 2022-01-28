package scenes.gameview;

import application.Main;
import game.GameManager;
import javafx.beans.property.SimpleBooleanProperty;
import scenes.BasicView;
import uicomponents.game.GameDisplay;
import uicomponents.game.OverlayController;
import uicomponents.game.PausePaneController;

public class GameViewController extends BasicView {
  protected GameManager gameManager;
  public GameDisplay gameDisplayPane;
  public OverlayController overlayController;
  public PausePaneController pausPaneController;
  private SimpleBooleanProperty gameisFinished = new SimpleBooleanProperty(false);

  public GameViewController( Main app ) {
    super( app );
    GameView gameViewPane = new GameView( );

    GameManager gM = app.getGameManger( );
    this.gameManager = gM;

    gameisFinished.set(false);
    gameisFinished.addListener((o,oP,nP)->{
     app.switchScene(Main.MENU_VIEW);
    });

    pausPaneController = new PausePaneController(gM, this);
    overlayController = new OverlayController(gM);

    gameViewPane.pauseView.getChildren().add(pausPaneController.pausePane);
    gameViewPane.overlayView.getChildren().add(overlayController.overlayPane);

    this.menuRootView = gameViewPane;
    this.gameDisplayPane = gameViewPane.gameDisplay;

    app.defineGameDisplayPane( gameViewPane.gameDisplay);

    // Gib der Engine den AusgabePunkt mit
    gM.declareGameDisplayPane( this.gameDisplayPane );

    // Wenn sich der SpielStatus ändert
    // Dann pass die GUI auf das neue Szenario an
    gameViewPane.gameDisplay.setVisible(false);
    gameViewPane.overlayView.setVisible(false);

    pausPaneController.isLoading();

    gM.getPlayingStateProperty( ).addListener( (o, oV, newState) -> {
      switch (newState) { // @ToDo: GameViewPanel Switching
        case LOADING:
          //Display Loadingscreen
          gameViewPane.gameDisplay.setVisible(false);
          gameViewPane.overlayView.setVisible(false);
          pausPaneController.isLoading();

        case READY:
          gameDisplayPane.showPlay();
          gameViewPane.gameDisplay.setVisible(true);
          pausPaneController.ready();
          break;

        case PLAY:
          gameDisplayPane.showPlay();
          gameViewPane.gameDisplay.setVisible(true);
          overlayController.play();
          pausPaneController.play();
          gameViewPane.pausePane.setVisible(false);
          gameViewPane.overlayView.setVisible(true);
          break;

        case PAUSE:
          gameViewPane.pausePane.setVisible(true);
          gameViewPane.overlayView.setVisible(false);
          overlayController.pause();
          pausPaneController.pause();
          break;

        case GAMEOVER:
          gameViewPane.overlayView.setVisible(false);
          gameViewPane.pausePane.setVisible(true);
          pausPaneController.finished();
          pausPaneController.gameOver();
          break;

        case FINISHED:
          pausPaneController.finished();
          gameViewPane.pausePane.setVisible(true);
          gameViewPane.overlayView.setVisible(false);
          break;

        default:
      }
    } );

    // Wenn eine neues Level und somit eine neue World geladen wird
    // Dann Zeichne die Leinwand erneut...
    gM.getLoadedLevelProperty( ).addListener( (o, oV, newLoadedLevel) -> {
      if (gameViewPane.gameDisplay.gameWorldPane != null) {
        try {
          gameViewPane.gameDisplay.gameWorldPane.setWorldSteps( newLoadedLevel.getMapChunks( ), newLoadedLevel.getMaxAmplitude( ) );
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    } );
  }

  public SimpleBooleanProperty gameisFinishedProperty() {
    return gameisFinished;
  }
}
