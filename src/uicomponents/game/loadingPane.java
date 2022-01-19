package uicomponents.game;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import uicomponents.ImageViewPane;



import static javafx.geometry.Pos.BOTTOM_CENTER;

public class loadingPane extends StackPane {
    HBox loadingContainer = new HBox();
    VBox textContainer = new VBox();
    Label loadingText = new Label();
    ImageView loadingImg = new ImageView();
    ImageViewPane loadingImagePane = new ImageViewPane(loadingImg);
    VBox ImageContainer = new VBox();
    public loadingPane(){
        setLodingContainer();
        setLabel();
        loadingText.setText("Warten kann jeder ");
        loadingContainer.getChildren().addAll(ImageContainer,textContainer);
    }

    private void setLabel() {
        textContainer.getChildren().add(loadingText);
        loadingText.setAlignment(BOTTOM_CENTER);
    }

    private void setLodingContainer() {
        loadingContainer.setAlignment(Pos.CENTER);
    }
    public void setLoadingImagePane(){
        ImageContainer.getChildren().add(loadingImagePane);
        ImageContainer.setAlignment(Pos.TOP_CENTER);

    }


}
