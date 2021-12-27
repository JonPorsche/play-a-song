package gamelogic;

public interface GameObject {
	void update(double delta);
	double getX();
	double getY();
	void setX(double x);
	void setY(double y);
	double getAngle();
}
