package game.sprites.basic;

import game.sprites.Sprite;

public interface Drawable<T extends Sprite> {

  public double getWidth();
  public double getHeight();

  public double getX();
  public double getY();

  public void setX(double x);
  public void setY(double y);
}
