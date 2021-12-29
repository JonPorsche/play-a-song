package gamelogic;

import javafx.beans.property.SimpleBooleanProperty;

public interface GameObject {
	void update(double delta);

	void update(double gamespeed, double Radius, double playerX, double playerY);

	double getX();
	double getY();
	void setX(double x);
	void setY(double y);
	double getAngle();
	double getRadius();
	double getWidth();
	double getHeight();
	boolean getIsVissable();
	void setIsUsed(boolean isUseD);
	SimpleBooleanProperty isUseDProperty();

}
