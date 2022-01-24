package uicomponents.game;

import game.sprites.PlayerCharacter;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


public class OverlayPane extends AnchorPane {
  //public PlayerCharacter player = new PlayerCharacter();
  public Label scoreLabel;
  public Label positionPercentLabel;
  public Button pauseButton;


  public OverlayPane( ) {
    super( );
    pauseButton = new Button();
    this.setStyle("-fx-background-color: green");
    pauseButton.setText("Pause");
    this.setHeight(200);
    this.setWidth(200);
    Label scoreTitleLabel = new Label( "Score:" );
    this.scoreLabel = new Label( "0000P" );
    Label positionTitleLabel = new Label( "Score:" );
    this.positionPercentLabel = new Label( "000%" );
    HBox scorePane = new HBox( scoreTitleLabel, scoreLabel,positionTitleLabel,positionPercentLabel, pauseButton);
    scorePane.setLayoutX(100);
    scorePane.setLayoutY(0);

    this.getChildren( ).addAll( scorePane);
  }


}
