package game.sprites;

import game.sprites.basic.SpriteCircle;

import static javafx.scene.paint.Color.BLUE;

public class PlayerCharacter extends SpriteCircle {
  private boolean hasIteam;
  public PlayerCharacter(){
    this.setFill(BLUE);
    this.setRadius(40);

  }


  public void setHasIteam(boolean hasIteam) {
    this.hasIteam = hasIteam;
  }
  public boolean gethasIteam() {
    return hasIteam;
  }
}

