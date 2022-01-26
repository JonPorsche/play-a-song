package game.sprites.logic;

import business.service.Mp3Player;
import game.GameEngine;
import game.sprites.basic.Iteam;
import game.sprites.basic.Knockable;
import game.sprites.basic.Sprite;
import game.sprites.optic.CoinSprite;
import game.sprites.optic.SlowMotionSprite;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

import static javafx.scene.paint.Color.RED;

public class SlowMo implements Iteam {
  SlowMotionSprite slowMotionSprite = null;
  int x;
  int y;
  double gamespeed =0.80;
  double sizeModifer =5;
  double score = 1000;
  double radius;

  public SlowMo(int xPos, int yPos )  {
    this.x = xPos;
    this.y = yPos;

  }
  @Override
  public void collision(GameEngine ge, PlayerCharacter pl) {
    if (!slowMotionSprite.isUsed.getValue()) {
      pl.addSizeModifer(slowMotionSprite.sizeModifer,ge);
      pl.setPlayerimgSlow();
      ge.addScore(slowMotionSprite.score);
      slowMotionSprite.isUsed.setValue(true);
      Mp3Player soundP = new Mp3Player();
      soundP.load(slowMotionSprite.sound.getAbsolutePath());
      soundP.play();
    }
  }

  @Override
  public void setIsVisabile(boolean b) {
    if(b){
      slowMotionSprite= new SlowMotionSprite(x, y);

    }else{
      slowMotionSprite = null;
    }
  }

  @Override
  public Sprite getSprite() {
    return slowMotionSprite;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getRadius() {

    return (int) radius;
  }

  @Override
  public double getY() {
    return y;
  }
}
