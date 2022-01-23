package scenes.menuview.selection_box_view.playlist_view;

import application.Main;
import business.data.Song;
import business.service.PlaylistManager;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import scenes.BasicView;
import scenes.menuview.MenuViewController;

public class PlaylistViewController extends BasicView {

    private static PlaylistViewController INSTANCE = new PlaylistViewController(application);
    public static final String PLAYLIST_EMPTY = "empty";
    public static final String PLAYLIST_FILLED = "filled";
    private Pane playlistRootView;
    private ListView<Song> songsListView;
    private Label instructionText;
    private PlaylistView playlistView;

    private PlaylistViewController(Main application){
        super(application);

        playlistView = new PlaylistView();
        this.songsListView = playlistView.songsListView;
        this.instructionText = playlistView.instructionText;
        playlistRootView = playlistView;
        initialize();
    }

    public static PlaylistViewController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlaylistViewController(application);
        }
        return INSTANCE;
    }

    public void initialize(){
        handleSongsListViewClick();

        // set how a cell will look like
        songsListView.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {
            @Override
            public ListCell<Song> call(ListView<Song> param) {
                return new SongCell();
            }
        });

        PlaylistManager.songs = songsListView.getItems();
    }

    private void handleSongsListViewClick(){
        songsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                Song selectedSong = songsListView.getSelectionModel().getSelectedItem();
                PlaylistManager.getInstance().setSelectedSongPath(selectedSong.getSongFilePath());
            }
        });
    }

    /**
     * Called in MenuController, changes the playlist view between only an instruction text,
     * when the playlist is empty, or the playlist with songs and infos, when the playlist is filled.
     * @param newView Sring with the name of the view that needs to be displayed
     * @author Jones Porsche
     */
    public void switchPlaylistView(String newView){
        switch (newView){
            case PLAYLIST_EMPTY:
                playlistView.getChildren().clear();
                playlistView.getChildren().add(instructionText);
                break;
            case PLAYLIST_FILLED:
                playlistView.getChildren().clear();
                playlistView.getChildren().add(songsListView);
                break;
        }
    }

    public Pane getPlaylistRootView() {
        return playlistRootView;
    }
}

