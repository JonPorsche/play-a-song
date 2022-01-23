package scenes.menuview.selection_box_view.bottom_view;

import application.Main;
import business.service.PlaylistManager;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import scenes.BasicView;

public class BottomViewController extends BasicView {
    private static BottomViewController INSTANCE = new BottomViewController(application);
    private Pane bottomRootView;
    private BottomView bottomView;
    private Button addSongsBtn;
    private Button clearPlaylistBtn;
    private HBox leftBtns;
    private Button playBtn;
    private ToolBar toolBar;

    private BottomViewController(Main application){
        super(application);

        this.bottomView = new BottomView();
        this.addSongsBtn = bottomView.addSongsBtn;
        this.clearPlaylistBtn = bottomView.clearPlaylistBtn;
        this.leftBtns = bottomView.leftBtns;
        this.playBtn = bottomView.playBtn;
        this.toolBar = bottomView.toolBar;
        this.bottomRootView = bottomView;
        initialize();
    }

    public static BottomViewController getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new BottomViewController(application);
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

    public Pane getBottomRootView() {
        return bottomRootView;
    }
}
