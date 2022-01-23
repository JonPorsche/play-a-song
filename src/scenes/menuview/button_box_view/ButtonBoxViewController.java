package scenes.menuview.button_box_view;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import scenes.menuview.MenuViewController;
import scenes.menuview.options_view.OptionsViewController;

import static scenes.menuview.MenuViewController.OPTIONS_VIEW;
import static scenes.menuview.MenuViewController.PLAYLIST_VIEW;

public class ButtonBoxViewController {
    private static ButtonBoxViewController INSTANCE = new ButtonBoxViewController();
    private Pane buttonBoxRootView;
    private ButtonBoxView buttonBoxView;
    private Button playlistBtn;
    private Button optionsBtn;

    private ButtonBoxViewController(){
        this.buttonBoxView = new ButtonBoxView();
        this.playlistBtn = buttonBoxView.playlistBtn;
        this.optionsBtn = buttonBoxView.optionsBtn;
        this.buttonBoxRootView = buttonBoxView;
        initialize();
    }

    public static ButtonBoxViewController getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ButtonBoxViewController();
        }
        return INSTANCE;
    }

    private void initialize(){
        handlePlaylistBtnClick();
        handleOptionsBtnClick();
    }

    private void handlePlaylistBtnClick() {
        playlistBtn.setOnAction(event -> MenuViewController.getInstance().switchSelectionBoxView(PLAYLIST_VIEW));
    }

    private void handleOptionsBtnClick() {
        optionsBtn.setOnAction(event -> MenuViewController.getInstance().switchSelectionBoxView(OPTIONS_VIEW));
    }

    public Pane getButtonBoxRootView() {
        return buttonBoxRootView;
    }

    public Button getPlaylistBtn() {
        return playlistBtn;
    }

    public Button getOptionsBtn() {
        return optionsBtn;
    }
}
