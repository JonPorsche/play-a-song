package scenes.menuview;

import application.Main;
import business.data.Song;
import business.service.PlaylistManager;
import business.service.PlaylistStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuViewController {

    public static final String PLAYLIST_VIEW = "PLAYLIST";
    public static final String OPTIONS_VIEW = "OPTIONS";

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
    private OptionsViewController optionsViewController;

    public MenuViewController(Main application) {
        this.application = application;

        MenuView menuView = new MenuView();
        this.menuContainer = menuView.menuContainer;
        this.menuBtnBox = menuView.menuBtnBox;
        this.playlistBtn = menuView.playlistBtn;
        this.optionsBtn = menuView.optionsBtn;
        this.selectionBox = menuView.selectionBox;
        this.selectionBoxTitle = menuView.selectionBoxTitle;
        this.selectionBoxHeader = menuView.selectionBoxHeader;
        this.instructionText = menuView.instructionText;
        this.selectionBoxFooter = menuView.selectionBoxFooter;
        this.addSongsBtn = menuView.addSongsBtn;
        this.clearPlaylistBtn = menuView.clearPlaylistBtn;
        this.playBtn = menuView.playBtn;

        playlistViewController = new PlaylistViewController(application);
        optionsViewController = new OptionsViewController();
        selectionBox.setCenter(playlistViewController.getPlaylistRootView());

        menuRootView = menuView;
        initialize();
    }

    public Pane getMenuRootView() {
        return menuRootView;
    }

    public void initialize() {
        handlePlaylistBtnClick();
        handleOptionsBtnClick();
        handlePlayBtnClick();
        handleAddSongsBtnClick();
        handleClearPlaylistBtnClick();
        handlePlaylistStatusChanges();
        handleSongsArrayChanges();
    }

    private void handlePlaylistBtnClick(){
        playlistBtn.setOnAction(event -> switchSelectionBoxView(PLAYLIST_VIEW));
    }

    private void handleOptionsBtnClick(){
        optionsBtn.setOnAction(event -> switchSelectionBoxView(OPTIONS_VIEW));
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
                application.switchScene(Main.GAME_VIEW);
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
            PlaylistManager.cleanM3UFile();
            PlaylistManager.songs.clear();
        });
    }

    /**
     * Reacts to changes in the m3u playlist file status.
     * If is empty, tells the playlist view controller to display the view with an instruction text.
     * If is filled, tells the playlist view controller to display the playlist view with the songs and infos.
     * @author Jones Porsche
     */
    private void handlePlaylistStatusChanges(){
        PlaylistManager.getInstance().playlistStatus.addListener(new ChangeListener<PlaylistStatus>() {
            @Override
            public void changed(ObservableValue<? extends PlaylistStatus> observable, PlaylistStatus oldPlaylistStatus, PlaylistStatus newPlaylistStatus) {
                switch (newPlaylistStatus){
                    case EMPTY:
                        playlistViewController.switchPlaylistView(PlaylistViewController.PLAYLIST_EMPTY);
                        break;
                    case FILLED:
                        playlistViewController.switchPlaylistView(PlaylistViewController.PLAYLIST_FILLED);
                        break;
                }
            }
        });
    }

    private void handleSongsArrayChanges(){
        PlaylistManager.getInstance().songs.addListener((ListChangeListener<Song>) c -> {
            if(PlaylistManager.songs.isEmpty()){
                PlaylistManager.getInstance().playlistStatus.set(PlaylistStatus.EMPTY);
            }
            else {
                PlaylistManager.getInstance().playlistStatus.set(PlaylistStatus.FILLED);
            }
        });
    }

    /**
     * Triggered by the playlist and options buttons.
     * Switches the center view of the BorderPane that composes the selection box,
     * between playlist and option view.
     * @author Jones Porsche
     * @param newSelectionBoxView String with the name of the new view.
     */
    public void switchSelectionBoxView(String newSelectionBoxView){
        switch (newSelectionBoxView){
            case PLAYLIST_VIEW:
                selectionBox.setCenter(playlistViewController.getPlaylistRootView());
                selectionBoxTitle.setText(PLAYLIST_VIEW);
                break;
            case OPTIONS_VIEW:
                selectionBox.setCenter(optionsViewController.getOptionsRootView());
                selectionBoxTitle.setText(OPTIONS_VIEW);
                break;
        }
    }
}
