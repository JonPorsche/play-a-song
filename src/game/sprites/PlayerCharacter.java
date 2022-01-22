package game.sprites;

import game.GameEngine;
import game.GamePlayingState;
import game.sprites.basic.SpriteCircle;
import javafx.scene.shape.Circle;

import static javafx.scene.paint.Color.BLUE;

public class PlayerCharacter extends SpriteCircle {
  private boolean hasIteam;
  private Circle eye;
  private Circle eye2;

  public PlayerCharacter(){
    this.setFill(BLUE);
    this.setRadius(40);

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
          seconds =+ 1;
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

