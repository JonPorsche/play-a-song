package gamelogic;

import javafx.geometry.Bounds;
import uicomponents.Sprite;

public abstract class Knockable<T> extends Drawable {
    /*boolean isInObjBounce( int x, int y ) {
        return false;
    }*/
    boolean isInObjBounce( Bounds xy ) {
        return xy.contains( this.x, this.y )
            || xy.contains( this.x + this.width, this.y + this.height );
    }
    boolean isInObjBounce( Sprite otherSpriteObj ) {
        return this.isInObjBounce( otherSpriteObj.getBounds() );
    }
    boolean isInObjBounce( GameObject otherGameObj ) {
        return this.isInObjBounce( otherGameObj.getSpriteObject( ) );
    }
}
