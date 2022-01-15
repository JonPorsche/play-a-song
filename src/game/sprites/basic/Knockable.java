package game.sprites.basic;

import javafx.geometry.Bounds;

/*public abstract class Knockable<T> extends Drawable {
  boolean isInObjBounce( int x, int y ) {
      return false;
  }
  boolean isInObjBounce( Bounds xy ) {
    return xy.contains( this.x, this.y )
        || xy.contains( this.x + this.width, this.y + this.height );
  }
  boolean isInObjBounce( Sprite otherSpriteObj ) {
  boolean isInObjBounce( Sprite otherSpriteObj ) {
    return this.isInObjBounce( otherSpriteObj.getBounds() );
  }
}*/

public interface Knockable<T> extends Drawable {
  boolean isInObjBounce(Bounds xy);
  boolean isInObjBounce(Knockable otherSpriteObj);
}