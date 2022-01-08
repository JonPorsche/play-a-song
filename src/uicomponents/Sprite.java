package uicomponents;

import gamelogic.GameObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public interface Sprite {
	Bounds getBounds();

	Rectangle getRectangle();

	Line getLine();



	void render();
	SimpleObjectProperty<GameObject> gameObjectProperty();
}
