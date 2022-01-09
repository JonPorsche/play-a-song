package gamelogic.sprites.base;

/*public abstract class Drawable<T extends Sprite> {
  private T spriteObject;

  protected double x;
  protected double y;
  protected double height;
  protected double width;

  /*public T getSpriteObject( ) {
    return this.spriteObject;
  }*-/

  public double getWidth() {
    return 0;
  }

  public double getHeight() {
    return 0;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }
}*/

public interface Drawable<T extends Sprite> {

  public double getWidth();
  public double getHeight();

  public double getX();
  public double getY();

  public void setX(double x);
  public void setY(double y);
}
