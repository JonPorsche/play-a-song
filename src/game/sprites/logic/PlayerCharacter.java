package game.sprites.logic;

import application.Main;
import game.GameEngine;
import game.GamePlayingState;
import game.sprites.optic.PlayerSprite;
import game.sprites.optic.SpriteCircle;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;

public class PlayerCharacter  {
  File img2File = new File("src/resources/PlayerSlow.png");
  Image imgSlow = new Image(img2File.toURI().toString());
  File img3File = new File("src/resources/PlayerSpeed.png");
  Image imgSpeed = new Image(img3File.toURI().toString());
  PlayerSprite playerSprite;


  public PlayerCharacter(){
    playerSprite = new PlayerSprite(Main.WINDOW_WIDTH/2,Main.WINDOW_HEIGHT/2);

  }
  public void setPlayerImgSpeed(){
    playerSprite.setFill(new ImagePattern(imgSpeed));

  }
  public void setPlayerimgSlow(){
    playerSprite.setFill(new ImagePattern(imgSlow));

  }
  public void addSizeModifer(double sizeModifer, GameEngine gE) {
    new Thread(()->{
      playerSprite.setRadius(playerSprite.getRadius()+sizeModifer);
      int seconds = 0;
      while (seconds <= 10) {
        if (gE.gamePlayingStatePropPointer.getValue() == GamePlayingState.PLAY){
          seconds += 1;
        }
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      playerSprite.setRadius(playerSprite.getRadius()-sizeModifer);
    }).start();
  }


  public Double getRadius() {
    return playerSprite.getRadius();
  }

  public double getY() {
    return playerSprite.getY();
  }

  public double getX() {
    return playerSprite.getX();
  }

  public void setY(double v) {
    playerSprite.setCenterY(v);
  }

  public Node getSprite() {
    return playerSprite;

  }
}

