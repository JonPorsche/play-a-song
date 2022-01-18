package game.sprites;

import game.GameEngine;
import game.sprites.basic.Knockable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;

public class SlowMoIteam extends Iteam {

  public SlowMoIteam( int xPos, int yPos ) {
    super( xPos,yPos);
    this.setCenterX(xPos);
    this.setCenterY(yPos);
    this.setFill(RED);
    this.setRadius(20);
    this.setCenterX(xPos);
    this.setCenterY(yPos);
    File imgFile = new File("src/resources/Slow.png");
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
  public void collision() {
    super.collision();

  }
}
