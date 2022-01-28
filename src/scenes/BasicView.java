package scenes;

import application.Main;
import javafx.scene.layout.Pane;

public class BasicView {
  protected static Main application;
  protected Pane menuRootView;
  protected Pane playlistRootView;
  protected Pane bottomRootView;

  public BasicView(Main application) {
    this.application = application;
  }

  public Pane getMenuRootView() {
    return menuRootView;
  }
}
