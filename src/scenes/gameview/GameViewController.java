package scenes.gameview;

import application.Main;
import scenes.BasicView;

public class GameViewController extends BasicView {
  public GameViewController( Main app ) {
    super( app );

    GameView gameViewPane = new GameView( );
    this.menuRootView = gameViewPane;

    app.defineGameDisplayPane( gameViewPane.gameDisplay);
  }
}
