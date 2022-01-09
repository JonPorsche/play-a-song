package gamelogic.sprites;

import gamelogic.sprites.base.GameObject;
import gamelogic.sprites.base.Knockable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.BLACK;


//public class MapWave extends Line implements Sprite {
public class MapWave extends Circle implements Knockable, GameObject {
   protected int leftY;
   protected int rightY;

   public MapWave( int leftY, int rightY ) {
     this.leftY = leftY;
     this.rightY = rightY;
   }

   @Override
   public Line getLine(){
     return this;
   }

    @Override
    public Circle player() {
        return null;
    }

    @Override
    public Bounds getBounds(){

        return this.getBoundsInParent();
    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }

    @Override
    public void render() {

        if (gameObject != null) {

            GameObject wave = gameObject.get();
            this.setFill(BLACK);
            this.setEndX(wave.getX());
            this.setEndY(wave.getY());
            this.setStartY(100);
            this.setStartX(wave.getX());
        }


    }

    @Override
    public SimpleObjectProperty<GameObject> gameObjectProperty() {

       return gameObject;
    }

  public boolean isColliedWithObj( Knockable otherObject ) {
    double delta = otherObject.getX() - (this.getX() + this.getRadius());
    /*if () { // Object davor

    }*/

    return false;
  }
}
