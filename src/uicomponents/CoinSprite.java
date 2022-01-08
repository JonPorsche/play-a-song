package uicomponents;

import gamelogic.GameObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.ORANGE;
import static javafx.scene.paint.Color.YELLOW;

public class CoinSprite extends Circle implements Sprite {
        public SimpleObjectProperty<GameObject> gameObject;
        public CoinSprite(){
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
        public void render() {

            if(gameObject != null){
                GameObject coin = gameObject.get();
                this.setRadius(coin.getRadius());
                this.setCenterX(coin.getX());
                this.setCenterY(coin.getY());
                this.setFill(YELLOW);
            }


        }

        @Override
        public SimpleObjectProperty<GameObject> gameObjectProperty() {
            return gameObject;
        }
    }

