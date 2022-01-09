package uicomponents;

import gamelogic.sprites.base.GameObject;
import gamelogic.sprites.base.Sprite;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.ORANGE;


public class playerSprite extends Circle implements Sprite {
    public SimpleObjectProperty<GameObject>gameObject;
    public playerSprite(){
        gameObject = new SimpleObjectProperty<GameObject>();
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
    public Line getLine() {
        return null;
    }

    @Override
    public Circle player(){
        return this;
    }

    @Override
    public void render() {

        if(gameObject != null){
            GameObject player = gameObject.get();
            this.setRadius(player.getRadius());
            this.setCenterX(player.getX());
            this.setCenterY(player.getY());
            this.setFill(ORANGE);
        }


    }

    @Override
    public SimpleObjectProperty<GameObject> gameObjectProperty() {
        return gameObject;
    }
}
