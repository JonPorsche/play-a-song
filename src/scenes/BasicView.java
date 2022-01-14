package scenes;

import application.Main;
import javafx.application.Application;
import javafx.scene.layout.Pane;

public class BasicView {

  protected Main application;
  protected Pane menuRootView;

  public BasicView( Main application ) {
    this.application = application;
  }

  public Pane getMenuRootView() {
    return menuRootView;
  }
}
