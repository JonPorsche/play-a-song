package uicomponents;

import gamelogic.GameObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.BLACK;


public class WaveRSprite extends Rectangle implements Sprite {
   public SimpleObjectProperty<GameObject>gameObject;


   public WaveRSprite(){
       gameObject = new SimpleObjectProperty<GameObject>();

   }
   @Override
   public Rectangle getRectangle(){
     return this;
   }
    @Override
    public Line getLine(){
        return null;
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
    public void render() {

        if (gameObject != null) {

            GameObject wave = gameObject.get();
            this.setFill(BLACK);
            this.setX(wave.getX());
            this.setY(wave.getY());
            this.setWidth(wave.getWidth());
            this.setHeight(wave.getHeight());
        }


    }

    @Override
    public SimpleObjectProperty<GameObject> gameObjectProperty() {

       return gameObject;
    }
}
