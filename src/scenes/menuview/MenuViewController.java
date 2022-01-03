package scenes.menuview;

import application.Main;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuViewController {
    private HBox menuContainer;

    // MENU BUTTON BOX
    private VBox menuBtnBox;
    private Button playlistBtn;
    private Button optionsBtn;

    // SELECTION BOX
    private BorderPane selectionBox;

    // Selection box top
    private VBox selectionBoxHeader;
    private Label selectionBoxTitle;

    // Selection box center
    private Label instructionText;

    // Selection box bottom
    private HBox selectionBoxFooter;
    private Button addSongsBtn;
    private Button playBtn;

    private Main application;
    private Pane rootView;

    public MenuViewController(Main application){
        this.application = application;

        MenuView menuView = new MenuView();
        this.menuContainer = menuView.menuContainer;
        this.menuBtnBox = menuView.menuBtnBox;
        this.playlistBtn = menuView.playlistBtn;
        this.optionsBtn = menuView.optionsBtn;
        this.selectionBoxHeader = menuView.selectionBoxHeader;
        this.instructionText = menuView.instructionText;
        this.selectionBoxFooter = menuView.selectionBoxFooter;
        this.addSongsBtn = menuView.addSongsBtn;
        this.playBtn = menuView.playBtn;

        rootView = menuView;
        initialize();
    }

    public Pane getRootView() {
        return rootView;
    }

    public void initialize(){
        handlePlayBtnClick();
    }

    private void handlePlayBtnClick() {
        playBtn.setOnAction(event -> {
            application.loadGame();
            application.switchToGameView("GameView");
        });
    }
}
