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
  Label scoreTitleLabel = new Label( "Score:" );
  Label positionTitleLabel = new Label( "Score:" );
  HBox pauseBox;
  HBox scoreBox;
  HBox posstionBox;
  public OverlayPane( ) {
    super( );
    this.setHeight(200);
    this.setWidth(200);
    setLayoutX(0);
    setLayoutY(0);
    setPauseButton();
    setScoreLabel();
    setpositonPercantage();
    setScoreBox();
    OverlayPane pane = this;
    getStyleClass().addAll("score-box");


  }
  private void setScoreBox(){
    HBox scorePane = new HBox( scoreBox,posstionBox,pauseBox);
    scorePane.setLayoutX(0);
    scorePane.setLayoutY(0);
    this.getChildren( ).addAll(scorePane);
    this.getStyleClass().addAll("score-box");
    this.setStyle("-fx-background-color:  rgba(39, 50, 49, 1)");


  }
  private void setPauseButton() {
    pauseButton = new Button();
    pauseButton.getStyleClass().addAll("pause-btn");
    pauseBox = new HBox(pauseButton);
    HBox.setMargin(pauseButton,new Insets(0,10,0,10));
  }
  private  void setpositonPercantage(){
    this.positionPercentLabel = new Label( "000%" );
    positionPercentLabel.getStyleClass().addAll("titles");
    positionTitleLabel.getStyleClass().addAll("titles");
    posstionBox = new HBox(positionTitleLabel,positionPercentLabel);
    HBox.setMargin(positionPercentLabel,new Insets(0,10,0,2));
    HBox.setMargin(positionTitleLabel,new Insets(0,10,0,0));
  }

  private  void setScoreLabel(){
    this.scoreLabel = new Label( "0000P" );
    scoreLabel.getStyleClass().addAll("titles");
    scoreTitleLabel.getStyleClass().addAll("titles");
    scoreBox = new HBox(scoreTitleLabel,scoreLabel);
    HBox.setMargin(scoreLabel,new Insets(0,10,0,2));
    HBox.setMargin(scoreTitleLabel,new Insets(0,10,0,0));


  }





}
