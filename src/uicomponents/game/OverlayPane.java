package uicomponents.game;

import game.sprites.PlayerCharacter;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class OverlayPane extends StackPane {
  //public PlayerCharacter player = new PlayerCharacter();
  public Label scoreLabel;
  public Label positionPercentLabel;
  public PlayerCharacter playerCharacter;

  public OverlayPane( ) {
    super( );
    //this.setStyle( "-fx-background-color:rgb(0, 150, 0);" );

    // --- Player Display ---
    Pane centerPlayerCursor = new Pane( );
    StackPane.setAlignment( centerPlayerCursor, Pos.CENTER );

    // Add from GameEngine
    //this.playerCharacter = new PlayerCharacter( );
    //centerPlayerCursor.getChildren( ).add( this.playerCharacter );

    // --- Score Display ---
    VBox scorePane = new VBox( );
    StackPane.setMargin( scorePane, new Insets( 50, 0, 0, 10 ) );
    //scorePane.getStyleClass().add("transparent-bkg");

    Label scoreTitleLabel = new Label( "Score:" );
    VBox.setMargin( scoreTitleLabel, new Insets( 0, 0, 10, 0 ) );
    this.scoreLabel = new Label( "0000P" );
    //StackPane.setAlignment( scorePane, Pos.TOP_LEFT );
    scorePane.getChildren( ).addAll( scoreTitleLabel, this.scoreLabel );

    // --- Positions Display (x) ---
    VBox positionPane = new VBox( );
    StackPane.setMargin( positionPane, new Insets( 50, 0, 0, 10 ) );
    StackPane.setAlignment( positionPane, Pos.TOP_RIGHT );
    //scorePane.getStyleClass().add("transparent-bkg");

    Label positionTitleLabel = new Label( "Score:" );
    VBox.setMargin( positionTitleLabel, new Insets( 0, 0, 10, 0 ) );
    this.positionPercentLabel = new Label( "000%" );
    positionPane.getChildren( ).addAll( positionTitleLabel, this.positionPercentLabel );

    // Appending...
    this.getChildren( ).addAll( centerPlayerCursor, scorePane, positionPane );
  }

  public void declarePlayerCharacter(PlayerCharacter playerCharacter) {
    ObservableList<Node> childs = this.getChildren( );
    childs.clear( );
    childs.add( playerCharacter );
  }
}
