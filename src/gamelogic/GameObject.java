package gamelogic;

import javafx.scene.shape.Shape;
import uicomponents.PlayerSprite;
import uicomponents.Sprite;

public interface GameObject {

	void update(double delta);
	double getX();
	double getY();
	void setX(double x);
	void setY(double y);
	double getAngle();
	double getRadius();

	double getWidth();

	double getHeight();

	Sprite getSpriteObject();
}
