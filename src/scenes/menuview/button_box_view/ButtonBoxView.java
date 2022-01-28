package scenes.menuview.button_box_view;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class ButtonBoxView extends VBox {
  Button playlistBtn;
  Button optionsBtn;

  public ButtonBoxView(){
    this.playlistBtn = new Button("PLAYLIST");
    this.optionsBtn = new Button("OPTIONS");
    setButtonBoxViewStyle();
    setBtnStyles();
    this.getChildren().addAll(playlistBtn,optionsBtn);
  }

  private void setButtonBoxViewStyle() {
    this.setMinSize(Main.WINDOW_WIDTH * 0.4592, Main.WINDOW_HEIGHT * 0.8875);
    this.setAlignment(Pos.CENTER);

    HBox.setMargin(this, new Insets(
    Main.WINDOW_HEIGHT * 0.05625,
    Main.WINDOW_WIDTH * 0.0074,
    Main.WINDOW_HEIGHT * 0.05625,
    Main.WINDOW_WIDTH * 0.0074)
    );

    VBox.setMargin(playlistBtn, new Insets(0, 0, 4, 0));
    VBox.setMargin(optionsBtn, new Insets(4, 0, 0, 0));
  }

  private void setBtnStyles(){
    playlistBtn.getStyleClass().addAll("text-btn-contained", "text-btn-contained-focused", "titles");
    optionsBtn.getStyleClass().addAll("text-btn-contained", "text-btn-contained-disabled-color", "titles");

    VBox.setMargin(playlistBtn, new Insets(8));
    VBox.setMargin(optionsBtn, new Insets(8));
  }
}
