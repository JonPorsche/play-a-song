package game.sprites.optic;

import game.sprites.basic.GameObject;
import game.sprites.basic.Knockable;
import game.sprites.basic.Sprite;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

import java.io.File;

public class SpriteCircle extends Circle implements Knockable, GameObject, Sprite {
  public File soundFile;
  public Image img;
  public Image img2;

  public double getAngle() {
    return 0;
  }

  @Override
  public boolean isInObjBounce(Bounds xy) {
    return false;
  }

  @Override
  public boolean isInObjBounce(Knockable otherSpriteObj) {
    return false;
  }

  @Override
  public double getWidth() {
    return this.getRadius() *2;
  }

  @Override
  public double getHeight() {
    return this.getWidth();
  }

  @Override
  public void update(double delta) {

  }

  @Override
  public double getX() {
    return this.getCenterX();
  }

  @Override
  public double getY() {
    return this.getCenterY();
  }

  @Override
  public void setX(double x) {
    this.setCenterX( x );
  }

  @Override
  public void setY(double y) {
    this.setCenterY( y );
  }

}



