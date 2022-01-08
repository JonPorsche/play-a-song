package scenes.menuview;

import application.Main;
import business.data.Song;
import business.service.PlaylistManager;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class PlaylistViewController {

    private Pane playlistRootView;
    private ListView<Song> songsListView;

    public PlaylistViewController(Main application){
        PlaylistView playlistView = new PlaylistView();
        this.songsListView = playlistView.songsListView;
        playlistRootView = playlistView;
        initialize();
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
/*        PlaylistManager.songs.addListener(new ListChangeListener<Song>() {
            @Override
            public void onChanged(Change<? extends Song> c) {
                System.out.println("Playlist changed");
            }
        });*/
    }

    private void handleSongsListViewClick(){
        songsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                Song selectedSong = songsListView.getSelectionModel().getSelectedItem();
                PlaylistManager.selectedSongPath = selectedSong.getSongFilePath();
            }
        });
    }

    public Pane getPlaylistRootView() {
        return playlistRootView;
    }
}

