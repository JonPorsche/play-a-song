package scenes.menuview;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuView extends Pane {
    VBox menuBtnBox = new VBox();
    Button playlistBtn = new Button();
    Button optionsBtn = new Button();

    public MenuView(){
        setMenuViewStyle();
        this.getChildren().addAll(playlistBtn, optionsBtn);
    }

    private void setMenuViewStyle() {
        //this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }
}
