package scenes.menuview.optionsview;

import application.Main;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import scenes.menuview.optionsview.infotextview.InfoTextViewController;
import scenes.menuview.optionsview.keyoptionscellview.KeyOptionCellView;
import java.util.HashMap;
import java.util.Map;

public class OptionsView extends VBox {
    public static final double OPTIONS_VIEW_WIDTH = Main.WINDOW_WIDTH * 0.3925;
    public static final double OPTIONS_VIEW_HEIGHT = Main.WINDOW_HEIGHT * 0.65;
    Map<String, Pane> views;
    Pane infoTextView;
    InfoTextViewController infoTextViewController;
    Pane moveUpKeyOptionView;

    public OptionsView(){
        startControllers();
        loadViews();

        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        setOptionsViewStyle();

        infoTextView = views.get("InfoText");
        this.getChildren().add(infoTextView);
    }

    private void setOptionsViewStyle(){
        this.setMaxSize(OPTIONS_VIEW_WIDTH, OPTIONS_VIEW_HEIGHT);
        this.setMinSize(OPTIONS_VIEW_WIDTH, OPTIONS_VIEW_HEIGHT);
    }

    private void startControllers(){
        infoTextViewController = new InfoTextViewController();
    }

    private void loadViews(){
        views = new HashMap<String, Pane>();
        views.put("InfoText", infoTextViewController.getInfoTextRootView());
    }
}
