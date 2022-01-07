package gamelogic;

import uicomponents.PlayerSprite;
import uicomponents.Sprite;

public abstract class Drawable<T extends Sprite> {
  private T spriteObject;

  protected double x;
  protected double y;
  protected double height;
  protected double width;

  public T getSpriteObject( ) {
    return this.spriteObject;
  }
}
