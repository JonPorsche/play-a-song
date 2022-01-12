package game.sprites;

import javafx.scene.paint.ImagePattern;

import java.util.List;

public class Iteam extends Sprite /* imp. Knockout*/ {
  public Iteam(int xPos) {

  }

  public void setImageContent(ImagePattern newStyles) {
    this.setFill( newStyles );
  }
  public void setImageSerienContent(ImagePattern[] newStyles) { }
  public void setImageSerienContent(List<ImagePattern> newStyles) { }
}
