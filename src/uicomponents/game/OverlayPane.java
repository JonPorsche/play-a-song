package uicomponents.game;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import javax.swing.text.html.ImageView;


public class OverlayPane extends AnchorPane {
  //public PlayerCharacter player = new PlayerCharacter();
  public Label scoreLabel;
  public Label positionPercentLabel;
  public Button pauseButton;
  Label scoreTitleLabel = new Label( "Score:" );
  Label positionTitleLabel = new Label( "Life:" );
  HBox pauseBox;
  HBox healthBox;
  HBox scoreBox;
  HBox posstionBox;
  ImageView health = new ImageView(null);
  ImageView health1 = new ImageView(null);
  ImageView health2 = new ImageView(null);
  public OverlayPane( ) {
    super( );
    this.setHeight(200);
    this.setWidth(1000);
    setLayoutX(0);
    setLayoutY(0);
    setPauseButton();
    setScoreLabel();
    setpositonPercantage();
    setScoreBox();
    setHealthBox();
    OverlayPane pane = this;




  }
  private void setScoreBox(){
    HBox scorePane = new HBox( scoreBox,posstionBox,pauseBox);
    scorePane.setLayoutX(0);
    scorePane.setLayoutY(0);
    this.getChildren( ).addAll(scorePane);
    scorePane.getStyleClass().addAll("score-box");



  }
  private void setHealthBox(){
    healthBox = new HBox();
    healthBox.setLayoutX(800);
    healthBox.setLayoutY(0);
    healthBox.setPrefWidth(300);
    healthBox.getStyleClass().addAll("score-box");

  }
  private void setPauseButton() {
    pauseButton = new Button();
    pauseButton.getStyleClass().addAll("pause-btn");
    pauseBox = new HBox(pauseButton);
    pauseBox.setPrefWidth(100);
    HBox.setMargin(pauseButton,new Insets(0,10,0,20));
  }
  private  void setpositonPercantage(){
    this.positionPercentLabel = new Label( "000%" );
    positionPercentLabel.getStyleClass().addAll("titles");
    positionTitleLabel.getStyleClass().addAll("titles");
    posstionBox = new HBox(positionTitleLabel,positionPercentLabel);
    posstionBox.setPrefWidth(150);
    HBox.setMargin(positionPercentLabel,new Insets(0,10,0,2));
    HBox.setMargin(positionTitleLabel,new Insets(0,10,0,0));
  }

  private  void setScoreLabel(){
    this.scoreLabel = new Label( "0000P" );
    scoreLabel.getStyleClass().addAll("titles");
    scoreTitleLabel.getStyleClass().addAll("titles");
    scoreBox = new HBox(scoreTitleLabel,scoreLabel);
    scoreBox.setPrefWidth(150);
    HBox.setMargin(scoreLabel,new Insets(0,10,0,2));
    HBox.setMargin(scoreTitleLabel,new Insets(0,10,0,0));


  }





}
