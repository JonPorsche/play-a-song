package scenes.menuview.selection_box_view.center_view.message_view;

import application.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MessageView extends VBox {
    Label message;

    public MessageView(){
        this.message = new Label("To add songs select a folder with mp3 files");

        setMessageViewStyle();

        this.getChildren().add(message);
    }

    private void setMessageViewStyle() {
        this.setId("selection-box-center");
        this.setMinHeight(Main.WINDOW_HEIGHT * 0.6625);
        this.setMaxHeight(Main.WINDOW_HEIGHT * 0.6625);
        this.setAlignment(Pos.BOTTOM_CENTER);
    }
}
