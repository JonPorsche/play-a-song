package uicomponents.game;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class OverlayPane extends AnchorPane {

  protected Label scoreTitleLabel = new Label( "Score:" );
  protected Label positionTitleLabel = new Label( "Life:" );
  protected HBox pauseBox;
  protected HBox healthBox;
  protected HBox scoreBox;
  protected HBox posstionBox;

  public Label scoreLabel;
  public Label positionPercentLabel;
  public Button pauseButton;

  public OverlayPane( ) {
    super( );

    this.setHeight(200);
    this.setWidth(1000);
    this.setLayoutX(0);
    this.setLayoutY(0);
    this.setPauseButton();
    this.setScoreLabel();
    this.setpositonPercantage();
    this.setScoreBox();
    this.setHealthBox();
  }

  private void setScoreBox(){
    HBox scorePane = new HBox( scoreBox,posstionBox,pauseBox);
    scorePane.setLayoutX(0);
    scorePane.setLayoutY(0);
    this.getChildren( ).addAll(scorePane);
    scorePane.getStyleClass().addAll("score-box");
  }

  private void setHealthBox(){
    this.healthBox = new HBox();
    this.healthBox.setLayoutX(800);
    this.healthBox.setLayoutY(0);
    this.healthBox.setPrefWidth(300);
    this.healthBox.getStyleClass().addAll("score-box");
  }

  private void setPauseButton() {
    this.pauseButton = new Button();
    this.pauseButton.getStyleClass().addAll("pause-btn");
    this.pauseBox = new HBox(this.pauseButton);
    this.pauseBox.setPrefWidth(100);

    HBox.setMargin(
      this.pauseButton,
      new Insets(0,10,0,20)
    );
  }

  private void setpositonPercantage() {
    this.positionPercentLabel = new Label( "000%" );
    this.positionPercentLabel.getStyleClass().addAll("titles");
    this.positionTitleLabel.getStyleClass().addAll("titles");
    this.posstionBox = new HBox(this.positionTitleLabel, this.positionPercentLabel);
    this.posstionBox.setPrefWidth(150);

    HBox.setMargin(
      this.positionPercentLabel,
      new Insets(0,10,0,2)
    );
    HBox.setMargin(
      this.positionTitleLabel,
      new Insets(0,10,0,0)
    );
  }

  private  void setScoreLabel(){
    this.scoreLabel = new Label( "0000P" );
    this.scoreLabel.getStyleClass().addAll("titles");
    this.scoreTitleLabel.getStyleClass().addAll("titles");
    this.scoreBox = new HBox(scoreTitleLabel,scoreLabel);
    this.scoreBox.setPrefWidth(150);

    HBox.setMargin(
      scoreLabel,
      new Insets(0,10,0,2)
    );
    HBox.setMargin(
      scoreTitleLabel,
      new Insets(0,10,0,0)
    );
  }
}
