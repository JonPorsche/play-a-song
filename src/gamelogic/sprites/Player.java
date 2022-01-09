package gamelogic.sprites;

import gamelogic.sprites.base.GameCircle;

public class Player extends GameCircle {
  private boolean hasIteam;

  public void setHasIteam(boolean hasIteam) {
    this.hasIteam = hasIteam;
  }
  public boolean gethasIteam() {
    return hasIteam;
  }
}

