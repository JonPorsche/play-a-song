package game.sprites.optic;

import game.sprites.basic.GameObject;
import game.sprites.basic.Knockable;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

public class SpriteCircle extends Circle implements Knockable, GameObject {
  protected double sizeModifer;
  protected double speedModfer;
  protected double gamespeed = 1;
  protected double pixelpermiliscond;

  public void update( double gamespeed ) {
    this.updateHeight( speedModfer * gamespeed );
  }

  public void updateHeight( double value ){
    this.setCenterY(
        this.getCenterY( ) + value
    );
  }
  public void setgamespeed(double pixelpermiliscond){
    this.pixelpermiliscond= pixelpermiliscond;

  }

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



