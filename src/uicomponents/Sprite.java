package uicomponents;

import gamelogic.GameObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public interface Sprite {
	Bounds getBounds();
	Line getLine();

	Circle player();

	void render();
	SimpleObjectProperty<GameObject> gameObjectProperty();
}
