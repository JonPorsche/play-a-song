package game.sprites.basic;

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
}
