package uicomponents;

import gamelogic.GameObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.BLUE;

public class iteamSprite extends Rectangle implements Sprite{
    public SimpleObjectProperty<GameObject>gameObject;

    public iteamSprite(){
        gameObject = new SimpleObjectProperty<GameObject>();
    }

    @Override
    public void render() {
        GameObject item = gameObject.get();
        this.setX(item.getX());
        this.setY(item.getY());
        this.setWidth(item.getWidth());
        this.setHeight(item.getHeight());
        this.setFill(BLUE);

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
    public GameObject getiteam(){
        GameObject item = gameObject.get();
        return item;
    }


    @Override
    public SimpleObjectProperty<GameObject> gameObjectProperty() {
        return gameObject;
    }
}
