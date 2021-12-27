package uicomponents;

import gamelogic.GameObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.shape.Circle;

import static javafx.scene.paint.Color.ORANGE;


public class playerSprite extends Circle implements Sprite {
    public SimpleObjectProperty<GameObject>gameObject;
    public playerSprite(){
        gameObject = new SimpleObjectProperty<GameObject>();
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
