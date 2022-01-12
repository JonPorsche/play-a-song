package game.sprites;

import game.GameEngine;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class SlowMoIteam extends Iteam {

  public SlowMoIteam( int xPos ) {
    super( xPos );

    Image img = new Image("myjavafxapp/resources/texture_bg.png");
    this.setImageContent( new ImagePattern(img,0,0,10,10,false) );
  }

  public void onSmash(Sprite collidedObject, GameEngine gE) {
    gE.setTickDistance( -50 );

    Thread.sleep(1000);
    gE.resetEffects();
  }
}
