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

public class SlowMo extends SpriteLogic implements Iteam {
  int x;
  int y;
  double gamespeed =-4;
  double sizeModifer =1;
  double score = 1000;
  double radius = 20;
  public static SlowMo getFromFactory(int xPos, int yPos ) {
    return new SlowMo( xPos, yPos );
  }


  public SlowMo(int xPos, int yPos )  {
    this.x = xPos;
    this.y = yPos;

  }
  @Override
  public void collision(GameEngine ge, PlayerCharacter pl) {
    if (!isUsed.getValue()) {
      pl.addSizeModifer(sizeModifer,ge);
      ge.addScore(score);
      ge.addGamespeed((float) gamespeed);
      isUsed.setValue(true);
      Mp3Player soundP = new Mp3Player();
      soundP.load(this.sprite.soundFile.getAbsolutePath());
      soundP.play();
    }
  }

  @Override
  public void setIsVisabile(boolean b) {
    if(b){
    this.sprite= new SlowMotionSprite(x, y);


    }else{
     sprite = null;
    }
  }

  @Override
  public Sprite getSprite() {
    return sprite;
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
