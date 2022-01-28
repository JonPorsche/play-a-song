package game.sprites.basic;

import javafx.geometry.Bounds;

public interface Knockable<T> extends Drawable {
  boolean isInObjBounce(Bounds xy);
  boolean isInObjBounce(Knockable otherSpriteObj);
}