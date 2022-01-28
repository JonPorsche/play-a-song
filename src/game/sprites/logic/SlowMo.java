package game.sprites.logic;

import business.service.Mp3Player;
import game.GameEngine;
import game.sprites.basic.Iteam;
import game.sprites.basic.Sprite;
import game.sprites.optic.SlowMotionSprite;

public class SlowMo extends SpriteLogic implements Iteam {
  protected int x;
  protected int y;
  protected double gamespeed =-4;
  protected double sizeModifer =1;
  protected double score = 1000;
  protected double radius = 20;

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
    if (b) this.sprite= new SlowMotionSprite(x, y);
    else sprite = null;
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
