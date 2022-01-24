package scenes.menuview.selection_box_view.top_view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TopView extends VBox {
    Label selectionBoxTitle;

    public TopView(){
        selectionBoxTitle = new Label("PLAYLIST");
        setTopViewStyle();
        setTitleStyle();
        this.getChildren().add(selectionBoxTitle);
    }

    private void setTopViewStyle() {
        this.setMinHeight(68);
        this.setMaxHeight(68);
        this.setAlignment(Pos.CENTER);
        selectionBoxTitle.setAlignment(Pos.CENTER);
    }

    private void setTitleStyle() {
        selectionBoxTitle.getStyleClass().add("titles");
    }
}
