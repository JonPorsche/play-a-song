package scenes.menuview;

import business.data.Song;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class PlaylistView extends VBox {

    ListView<Song> songsListView = new ListView<>();
    Label instructionText = new Label("To add songs select a folder with mp3 files");

    public PlaylistView(){
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }
}
