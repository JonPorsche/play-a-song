package scenes.menuview.optionsview;

import application.Main;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import scenes.menuview.optionsview.infotextview.InfoTextViewController;
import scenes.menuview.optionsview.keyoptionscellview.KeyOptionCellViewController;

import java.util.HashMap;
import java.util.Map;

public class OptionsView extends VBox {
    public static final double OPTIONS_VIEW_WIDTH = Main.WINDOW_WIDTH * 0.3925;
    public static final double OPTIONS_VIEW_HEIGHT = Main.WINDOW_HEIGHT * 0.65;
    Map<String, Pane> views;
    Pane infoTextView;
    InfoTextViewController infoTextViewController;
    Pane moveUpView;
    KeyOptionCellViewController moveUpViewController;
    Pane moveDownView;
    KeyOptionCellViewController moveDownViewController;

    public OptionsView(){
        startControllers();
        loadViews();

        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        setOptionsViewStyle();

        infoTextView = views.get("info text");
        moveUpView = views.get("move up");
        moveDownView = views.get("move down");
        this.getChildren().addAll(infoTextView, moveUpView, moveDownView);
    }

    private void setOptionsViewStyle(){
        this.setMaxSize(OPTIONS_VIEW_WIDTH, OPTIONS_VIEW_HEIGHT);
        this.setMinSize(OPTIONS_VIEW_WIDTH, OPTIONS_VIEW_HEIGHT);
    }

    private void startControllers(){
        infoTextViewController = new InfoTextViewController();
        moveUpViewController = new KeyOptionCellViewController("Move Up", infoTextViewController);
        moveDownViewController = new KeyOptionCellViewController("Move Down", infoTextViewController);
    }

    private void loadViews(){
        views = new HashMap<String, Pane>();
        views.put("info text", infoTextViewController.getInfoTextRootView());
        views.put("move up", moveUpViewController.getKeyOptionRootView());
        views.put("move down", moveDownViewController.getKeyOptionRootView());
    }
}
