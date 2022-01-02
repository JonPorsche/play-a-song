package scenes.menuview;

import application.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
    private TextArea selectionBoxTitle;

    // Selection box center
    private TextArea instructionText;

    // Selection box bottom
    private HBox selectionBoxFooter;
    private Button addSongsBtn;

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

        rootView = menuView;
        initialize();
    }

    public Pane getRootView() {
        return rootView;
    }

    public void initialize(){

    }
}
