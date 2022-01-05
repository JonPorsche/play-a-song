package scenes.menuview;

import application.Main;
import business.data.Song;
import business.data.Track;
import business.service.MP3Player;
import business.service.PlaylistManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import presentation.application.MainApp;
import presentation.scenes.topview.TopViewController;
import java.util.ArrayList;

public class PlaylistViewController {

    private Pane playlistRootView;
    public static ObservableList<Song> songsListModel = FXCollections.observableArrayList();
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
                return new TrackCell();
            }
        });

        ArrayList<Track> tracks = playlist.getTracks();

        songsListModel = songsListView.getItems();
        songsListModel.addAll(PlaylistManager.trackList);
        songsListModel.addListener(new ListChangeListener<Track>() {
            @Override
            public void onChanged(Change<? extends Track> c) {
                if(player.isPlaying()){
                    player.pause();
                }
                player.setPlayheadPosition(0);
                player.setTrackNumber(0);
                player.loadTrack();
                player.setNumberOfTracks(playlist.numberOfTracks());
            }
        });

        player.trackNumberProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                songsListView.getSelectionModel().select(player.getTrackNumber());
            }
        });
    }

    private void handleSongsListViewClick(){
        songsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                Song selectedSong = songsListView.getSelectionModel().getSelectedItem();
                System.out.println(selectedSong.toString());
            }
        });
    }

    public Pane getPlaylistRootView() {
        return playlistRootView;
    }
}

