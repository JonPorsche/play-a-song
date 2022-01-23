package scenes.menuview;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import scenes.menuview.button_box_view.ButtonBoxViewController;
import scenes.menuview.selection_box_view.SelectionBoxViewController;

import java.util.HashMap;
import java.util.Map;

// TODO Divide the view of this class in more classes
public class MenuView extends Pane {

    Map<String, Pane> menuViews;
    HBox menuContainer = new HBox();
    Pane buttonBoxView;
    Pane selectionBoxView;

    public MenuView() {
        this.menuViews = new HashMap<String, Pane>();
        loadMenuViews();
        setMenuContainerStyle();
        buttonBoxView = menuViews.get("ButtonBoxView");
        selectionBoxView = menuViews.get("SelectionBoxView");
        menuContainer.getChildren().addAll(buttonBoxView, selectionBoxView);
        this.getChildren().add(menuContainer);
    }

    private void setMenuContainerStyle() {
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        menuContainer.setId("menu-container");
        menuContainer.setMinSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    }

    private void loadMenuViews(){
        menuViews.put("ButtonBoxView", ButtonBoxViewController.getInstance().getButtonBoxRootView());
        menuViews.put("SelectionBoxView", SelectionBoxViewController.getInstance().getSelectionBoxRootView());
    }
}
