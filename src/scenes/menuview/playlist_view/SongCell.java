package scenes.menuview.playlist_view;

import application.Main;
import business.data.Song;
import business.service.PlaylistManager;
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
    private double margin;
    private double coverSize;

    public SongCell() {
        // View definition
        listItemBox = new HBox();

        // COVER
        coverImage = new ImageView();
        coverImageViewPane = new ImageViewPane(coverImage);

        // INFO BOX
        infoBox = new VBox();
        title = new Label();
        artist = new Label();
        album = new Label();
        duration = new Label();

        margin = Main.WINDOW_WIDTH * 0.0037;
        coverSize = Main.WINDOW_WIDTH * 0.0667;

        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        setListItemBoxStyle();
        setCoverStyle();
        setInfoBoxStyle();
        setTitleStyle();
        setArtistStyle();
        setAlbumStyle();
        setDurationStyle();

        infoBox.setAlignment(Pos.BASELINE_LEFT);
        HBox.setHgrow(infoBox, Priority.ALWAYS ); // To make the remaining time label move to the right when the screen gets wider

        infoBox.getChildren().addAll(title, artist, album);

        listItemBox.getChildren().addAll(coverImageViewPane, infoBox, duration);
    }

    private void setListItemBoxStyle(){
        double width = Main.WINDOW_WIDTH * 0.394;
        listItemBox.setId("list-item-box");
        listItemBox.setMinWidth(width);
        listItemBox.setMaxWidth(width);
    }

    private void setCoverStyle() {
        coverImageViewPane.setMinSize(coverSize, coverSize);
        coverImageViewPane.setMaxSize(coverSize, coverSize);
        HBox.setMargin(coverImageViewPane, new Insets(margin));
    }

    private void setInfoBoxStyle(){
        infoBox.setMinWidth(Main.WINDOW_WIDTH * 0.2574);
        infoBox.setMaxWidth(Main.WINDOW_WIDTH * 0.2574);
        HBox.setMargin(infoBox, new Insets(margin));
    }

    private void setTitleStyle(){
        title.getStyleClass().add("text-font");
        title.setId("song-title");
        VBox.setMargin(title, new Insets(0,0,0,0));
    }

    private void setArtistStyle(){
        artist.getStyleClass().add("text-font");
        artist.setId("artist");
        VBox.setMargin(artist, new Insets((margin / 2),0,0,0));
    }

    private void setAlbumStyle(){
        album.getStyleClass().add("text-font");
        album.setId("album");
        VBox.setMargin(album, new Insets(margin,0,0,0));
    }

    private void setDurationStyle(){
        duration.getStyleClass().add("text-font");
        duration.setId("duration");
        duration.setAlignment(Pos.CENTER_RIGHT);
        duration.setMaxWidth(Main.WINDOW_WIDTH * 0.037);
        duration.setMinWidth(Main.WINDOW_WIDTH * 0.037);
        HBox.setMargin(duration, new Insets((coverSize / 2 - margin),0,0,(margin / 2)));
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
            duration.setText(PlaylistManager.formatTime(item.getDuration()));

            this.setGraphic(listItemBox);
        }
    }
}
