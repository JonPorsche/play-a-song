package scenes.menuview.selection_box_view.center_view.playlist_view;

import application.Main;
import business.data.Song;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class PlaylistView extends VBox {

  ListView<Song> songsListView = new ListView<>();
  Label instructionText = new Label("To add songs select a folder with mp3 files");

  public PlaylistView(){
    this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    this.setSongsListViewStyle();
    this.setInstructionTextStyle();
  }

  private void setInstructionTextStyle() {
    VBox.setMargin(instructionText, new Insets(
    Main.WINDOW_HEIGHT * 0.2812,
    Main.WINDOW_WIDTH * 0.0167,
    0,
    Main.WINDOW_WIDTH * 0.0167)
    );

    instructionText.getStyleClass().addAll("text-font", "instruction-text");
    instructionText.setMinWidth(Main.WINDOW_WIDTH * 0.4259);
    instructionText.setAlignment(Pos.BASELINE_CENTER);
  }

  private void setSongsListViewStyle() {
    songsListView.setId("songs-list-view");

    VBox.setMargin(songsListView, new Insets(
      0,
      Main.WINDOW_WIDTH * 0.0167,
      0,
      Main.WINDOW_WIDTH * 0.0167)
    );
  }
}
