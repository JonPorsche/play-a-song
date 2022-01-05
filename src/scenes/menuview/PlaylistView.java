package scenes.menuview;

import business.data.Song;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class PlaylistView extends VBox {

    ListView<Song> songsListView = new ListView<>();

    public PlaylistView(){
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        this.getChildren().add(songsListView);
    }
}
