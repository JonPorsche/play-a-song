package gamelogic.sprites;


import gamelogic.sprites.base.GameObject;

public class _old_WaveWorldObj implements GameObject {
    private double x;
    private double y;
    private double height;
    private double width;
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
    public double setX(double x) {

        this.x = x;
        return x;
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
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }


    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
