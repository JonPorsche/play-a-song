package game.sprites;

import game.sprites.basic.SpriteCircle;

public class PlayerCharacter extends SpriteCircle {
  private boolean hasIteam;


  public void setHasIteam(boolean hasIteam) {
    this.hasIteam = hasIteam;
  }
  public boolean gethasIteam() {
    return hasIteam;
  }
}

