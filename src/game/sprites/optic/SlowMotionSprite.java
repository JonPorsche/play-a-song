package game.sprites.optic;

import game.sprites.basic.Sprite;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class SlowMotionSprite extends SpriteCircle  implements Sprite {
  File imgFile = new File("src/resources/Slow.png");

  public SlowMotionSprite(int xPos, int yPos) {
    this.setRadius(20);
    this.setCenterX(xPos);
    this.setCenterY(yPos);

    this.img = new Image(imgFile.toURI().toString());
    this.soundFile = new File("src/resources/sounds/slow.mp3");
    this.setFill(new ImagePattern(img));
  }
}
