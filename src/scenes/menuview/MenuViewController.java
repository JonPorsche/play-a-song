package scenes.menuview;

import application.Main;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuViewController {
    private HBox menuContainer;
    private VBox menuBtnBox;
    private Button playlistBtn;
    private Button optionsBtn;
    private Main application;
    private Pane rootView;

    public MenuViewController(Main application){
        this.application = application;

        MenuView menuView = new MenuView();
        this.menuContainer = menuView.menuContainer;
        this.menuBtnBox = menuView.menuBtnBox;
        this.playlistBtn = menuView.playlistBtn;
        this.optionsBtn = menuView.optionsBtn;

        rootView = menuView;
        initialize();
    }

    public Pane getRootView() {
        return rootView;
    }

    public void initialize(){

    }
}
