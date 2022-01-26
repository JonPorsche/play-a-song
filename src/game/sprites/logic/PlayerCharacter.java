package game.sprites.logic;

import game.GameEngine;
import game.GamePlayingState;
import game.sprites.optic.SpriteCircle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;

public class PlayerCharacter extends SpriteCircle {
  private boolean hasIteam;
  private Circle eye;
  private Circle eye2;
  File imgFile = new File("src/resources/Playerdefault.png");
  Image imgNormal = new Image(imgFile.toURI().toString());
  File img2File = new File("src/resources/PlayerSlow.png");
  Image imgSlow = new Image(img2File.toURI().toString());
  File img3File = new File("src/resources/PlayerSpeed.png");
  Image imgSpeed = new Image(img3File.toURI().toString());




  public PlayerCharacter(){

    this.setRadius(40);
    this.setFill(new ImagePattern(imgNormal));



  }
  public void setPlayerImgSpeed(){
    this.setFill(new ImagePattern(imgSpeed));

  }
  public void setPlayerimgSlow(){
    this.setFill(new ImagePattern(imgSlow));

  }


  public void setHasIteam(boolean hasIteam) {
    this.hasIteam = hasIteam;
  }
  public boolean gethasIteam() {
    return hasIteam;
  }

  public void addSizeModifer(double sizeModifer, GameEngine gE) {
    new Thread(()->{
      this.setRadius(getRadius()+sizeModifer);
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
      this.setRadius(getRadius()-sizeModifer);
    }).start();
  }


  }

