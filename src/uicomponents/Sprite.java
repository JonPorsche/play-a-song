package uicomponents;

import gamelogic.GameObject;
import javafx.beans.property.SimpleObjectProperty;

public interface Sprite {
	void render();
	SimpleObjectProperty<GameObject> gameObjectProperty();
}
