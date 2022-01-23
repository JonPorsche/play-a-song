package scenes.menuview.selection_box_view;

import application.Main;
import business.service.PlaylistManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import scenes.BasicView;
import scenes.menuview.MenuViewController;
import scenes.menuview.selection_box_view.playlist_view.PlaylistViewController;

public class SelectionBoxViewController extends BasicView {
    private static SelectionBoxViewController INSTANCE = new SelectionBoxViewController();
    private Pane selectionBoxRootView;
    private SelectionBoxView selectionBoxView;
    private PlaylistViewController playlistViewController;

    // Selection box top
    private VBox selectionBoxHeader;
    private Label selectionBoxTitle;

    // Selection box center
    private VBox selectionBoxCenter;
    private Label instructionText;

    // Selection box bottom
    private HBox selectionBoxFooter;
    private ToolBar toolBar;
    private HBox leftBtns;
    private Button addSongsBtn;
    private Button clearPlaylistBtn;
    private Button playBtn;

    private SelectionBoxViewController(){
        super(application);
        this.selectionBoxView = new SelectionBoxView();
        this.selectionBoxHeader = selectionBoxView.selectionBoxHeader;
        this.selectionBoxTitle = selectionBoxView.selectionBoxTitle;
        this.selectionBoxCenter = selectionBoxView.selectionBoxCenter;
        this.instructionText = selectionBoxView.instructionText;
        this.selectionBoxFooter = selectionBoxView.selectionBoxFooter;
        this.toolBar = selectionBoxView.toolBar;
        this.leftBtns = selectionBoxView.leftBtns;
        this.addSongsBtn = selectionBoxView.addSongsBtn;
        this.clearPlaylistBtn = selectionBoxView.clearPlaylistBtn;
        this.playBtn = selectionBoxView.playBtn;
        selectionBoxView.setCenter(PlaylistViewController.getInstance().getPlaylistRootView());
        this.selectionBoxRootView = selectionBoxView;
        initialize();
    }

    public static SelectionBoxViewController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SelectionBoxViewController();
        }
        return INSTANCE;
    }

    public void initialize(){
        handlePlayBtnClick();
        handleAddSongsBtnClick();
        handleClearPlaylistBtnClick();
    }

    private void handlePlayBtnClick() {
        playBtn.setOnAction(event -> {
            if (PlaylistManager.songs.isEmpty()) {
                System.out.println("Add songs to start playing");
            } else {
                if (PlaylistManager.getInstance().getSelectedSongPath() == null) {
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
     *
     * @author Jones Porsche
     */
    private void handleAddSongsBtnClick() {
        addSongsBtn.setOnAction(event -> PlaylistManager.selectDirectory());
    }

    /**
     * When the button "Clear Playlist" is clicked the m3u playlist file and the playlist songs array are cleared.
     *
     * @author Jones Porsche
     */
    private void handleClearPlaylistBtnClick() {
        clearPlaylistBtn.setOnAction(event -> {
            PlaylistManager.cleanM3UFile();
            PlaylistManager.songs.clear();
        });
    }

    public void updateSelectionBoxView(String title, Pane pane){
        selectionBoxTitle.setText(title);
        selectionBoxView.setCenter(pane);
    }

    public Pane getSelectionBoxRootView() {
        return selectionBoxRootView;
    }
}
