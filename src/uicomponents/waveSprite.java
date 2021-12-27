package uicomponents;

import gamelogic.GameObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import static javafx.scene.paint.Color.BLACK;


public class waveSprite extends Line implements Sprite {
   public SimpleObjectProperty<GameObject>gameObject;


   public waveSprite(){
       gameObject = new SimpleObjectProperty<GameObject>();

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
}
