package game.sprites;

import business.service.Mp3Player;
import game.GameEngine;
import game.sprites.basic.Iteam;
import game.sprites.basic.Knockable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;

public class SlowMoIteam extends IteamLogic implements Iteam {

  public SlowMoIteam( int xPos, int yPos ) {
    super( xPos,yPos);
    this.setCenterX(xPos);
    this.setCenterY(yPos);
    this.setFill(RED);
    this.setRadius(20);
    this.setCenterX(xPos);
    this.setCenterY(yPos);
    this.gamespeed =0.80;
    this.sizeModifer =5;
    File imgFile = new File("src/resources/Slow.png");
    this.sound = new File("src/resources/sounds/slow.mp3");
    Image img = new Image(imgFile.toURI().toString());
    this.setImagePatterns( new ImagePattern(img) );

    this.setImageContent();
  }

  public void onSmash( Knockable collidedObject, GameEngine gE ) {
    /*gE.setTickDistance( -50 );

    Thread.sleep(1000);
    gE.resetEffects();*/
  }


  @Override
  public void collision(GameEngine ge, PlayerCharacter pl) {
    if (!isUsed.getValue()) {
      pl.addSizeModifer(sizeModifer,ge);
      pl.setPlayerimgSlow();
      ge.addScore(score);
      isUsed.setValue(true);
      Mp3Player soundP = new Mp3Player();
      soundP.load(sound.getAbsolutePath());
      soundP.play();
    }
  }

  @Override
  public void setIsVisabile(boolean b) {
    isVisabile.setValue(b);
  }
}
