package game.sprites.logic;

import business.service.Mp3Player;
import game.GameEngine;
import game.sprites.basic.Iteam;
import game.sprites.basic.Sprite;
import game.sprites.optic.CoinSprite;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class Coin extends SpriteLogic implements Iteam {
  public static Coin getFromFactory(int xPos, int yPos ) {
    return new Coin( xPos, yPos );
  }

  int x;
  int y;
  int radius = 20;
  double score = 1000;
  File imgFile = new File("src/resources/Coin-2.png");
  Image img2 = new Image(imgFile.toURI().toString());

  public Coin(int xPos, int yPos) {
    this.x = xPos;
    this.y = yPos;
  }

  @Override
  public void collision(GameEngine ge, PlayerCharacter pl) {
    if (!isUsed.getValue()) {
      isUsed.setValue(true);
      ge.addScore(score);

      if (sprite.soundFile != null) {
        Mp3Player soundP = new Mp3Player();
        soundP.load(sprite.soundFile.getAbsolutePath());
        soundP.play();
      } else System.err.println( "Err: Coin SoundClip nicht verfügbar!" );

      isVisabile.set(false);
      Mp3Player soundP = new Mp3Player();
      soundP.load(sprite.soundFile.getAbsolutePath());
      soundP.play();
    }
  }

  @Override
  public void setIsVisabile(boolean b) {
    if (b) {
      sprite = new CoinSprite(x, y, radius);
      if (sprite.img != null) setImagePatterns(new ImagePattern(sprite.img));
      else System.err.println( "Err: Coin Image nicht verfügbar!" );
      setImagePatterns(new ImagePattern(sprite.img));
      setImagePatterns(new ImagePattern(img2));
      isVisabile.set(true);
    } else {
      isVisabile.set(false);
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
    return radius;
  }

  @Override
  public double getY() {
    return y;
  }
}

