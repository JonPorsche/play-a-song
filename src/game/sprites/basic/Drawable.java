package game.sprites.basic;

import javafx.scene.Node;

public interface Drawable<T extends Node> {
  public double getWidth();
  public double getHeight();

  public double getX();
  public double getY();

  public void setX(double x);
  public void setY(double y);
}
