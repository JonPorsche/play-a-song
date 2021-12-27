package gamelogic;



public class wave implements GameObject{
    private double x;
    private double y;
    private int painWidth;
    private int painHeight;
    private double gamespeed = 1;
    private double pixelpermiliscond;

    @Override
    public void update(double gamespeed) {
        x = x-(pixelpermiliscond *gamespeed);


    }

    public void setgamespeed(double pixelpermiliscond){
        this.pixelpermiliscond= pixelpermiliscond;

    }
    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setX(double x) {

        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y= y;

    }

    @Override
    public double getAngle() {
        return 0;
    }

    @Override
    public double getRadius() {
        return 0;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }


}
