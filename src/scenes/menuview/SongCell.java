package scenes.menuview;

import business.data.Song;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javafx.scene.image.ImageView;
import uicomponents.ImageViewPane;

import java.io.ByteArrayInputStream;

public class SongCell extends ListCell<Song> {

    private HBox listItemBox;
    private VBox infoBox;
    private Label title;
    private Label artist;
    private Label album;
    private Label duration;
    private ImageView coverImage;
    private ImageViewPane coverImageViewPane;

    public SongCell() {
        // View definition
        listItemBox = new HBox();

        // COVER
        coverImage = new ImageView();
        coverImageViewPane = new ImageViewPane(coverImage);
        coverImageViewPane.setMaxSize(72,72);
        HBox.setMargin(coverImageViewPane, new Insets(4));

        // INFO BOX
        infoBox = new VBox();
        title = new Label();
        artist = new Label();
        album = new Label();
        duration = new Label();
        infoBox.setMaxWidth(238);

        title.getStyleClass().addAll("main-text", "title");
        VBox.setMargin(title, new Insets(12,12,0,12));

        artist.getStyleClass().addAll("main-text", "artist");
        VBox.setMargin(artist, new Insets(8,12,0,12));

        album.getStyleClass().addAll("main-text", "album");
        VBox.setMargin(album, new Insets(8,12,0,12));

        duration.getStyleClass().addAll("main-text", "duration");
        duration.setAlignment(Pos.CENTER);
        HBox.setMargin(duration, new Insets(40,0,0,0));

        infoBox.setAlignment(Pos.BASELINE_LEFT);
        HBox.setHgrow(infoBox, Priority.ALWAYS ); // To make the remaining time label move to the right when the screen gets wider

        infoBox.getChildren().addAll(title, artist, album);

        listItemBox.getChildren().addAll(coverImageViewPane, infoBox, duration);
    }

    protected void updateItem(Song item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);

        if (!empty) {
            coverImage.setImage(new Image(new ByteArrayInputStream(item.getAlbumImage())));
            title.setText(item.getTitle());
            artist.setText(item.getArtist());
            album.setText(item.getAlbumTitle());
            //duration.setText(MP3Player.formatTime(item.getDuration()));

            this.setGraphic(listItemBox);
        }
    }
}
