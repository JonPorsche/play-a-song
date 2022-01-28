package game.sprites.optic;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class PlayerSprite extends SpriteCircle {
  File imgFile = new File("src/resources/Playerdefault.png");

  public PlayerSprite(int xPos, int yPos) {
    this.setX(xPos);
    this.setY(yPos);
    this.setRadius(30);

    this.img= new Image(imgFile.toURI().toString());
    this.setFill(new ImagePattern(img));
  }
}
