package scenes.menuview;

import application.Main;
import business.service.PlaylistManager;
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
    private Button clearPlaylistBtn;
    private Button playBtn;

    private Main application;
    private Pane menuRootView;

    // CONTROLLERS
    private PlaylistViewController playlistViewController;

    public MenuViewController(Main application) {
        this.application = application;

        MenuView menuView = new MenuView();
        this.menuContainer = menuView.menuContainer;
        this.menuBtnBox = menuView.menuBtnBox;
        this.playlistBtn = menuView.playlistBtn;
        this.optionsBtn = menuView.optionsBtn;
        this.selectionBox = menuView.selectionBox;
        this.selectionBoxHeader = menuView.selectionBoxHeader;
        this.instructionText = menuView.instructionText;
        this.selectionBoxFooter = menuView.selectionBoxFooter;
        this.addSongsBtn = menuView.addSongsBtn;
        this.clearPlaylistBtn = menuView.clearPlaylistBtn;
        this.playBtn = menuView.playBtn;

        playlistViewController = new PlaylistViewController(application);
        selectionBox.setCenter(playlistViewController.getPlaylistRootView());

        menuRootView = menuView;
        initialize();
    }

    public Pane getMenuRootView() {
        return menuRootView;
    }

    public void initialize() {
        handlePlayBtnClick();
        handleAddSongsBtnClick();
        handleClearPlaylistBtnClick();
    }

    private void handlePlayBtnClick() {
        playBtn.setOnAction(event -> {
            if(PlaylistManager.songs.isEmpty()){
                System.out.println("Add songs to start playing");
            } else {
                if (PlaylistManager.getInstance().getSelectedSongPath() == null){
                    String firstSongPath = PlaylistManager.songs.get(0).getSongFilePath();
                    PlaylistManager.getInstance().setSelectedSongPath(firstSongPath);
                }
                application.startGame();
                application.switchScene("GameView");
            }
        });
    }

    /**
     * When the button "Add Songs" is clicked a system default window opens.
     * The user selects a directory, a start point to look for mp3 files in the folder and its subfolders.
     * Finally a playlist is created.
     * @author Jones Porsche
     */
    private void handleAddSongsBtnClick() {
        addSongsBtn.setOnAction(event -> PlaylistManager.selectDirectory());
    }

    /**
     * When the button "Clear Playlist" is clicked the m3u playlist file and the playlist songs array are cleared.
     * @author Jones Porsche
     */
    private void handleClearPlaylistBtnClick(){
        clearPlaylistBtn.setOnAction(event -> {
            PlaylistManager.clearM3UFile();
            PlaylistManager.songs.clear();
        });
    }
}
