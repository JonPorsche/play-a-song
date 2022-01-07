package gamelogic;


import uicomponents.Sprite;

public class WaveWorldObj extends Knockable implements GameObject {
    private int painWidth;
    private int painHeight;
    private double gamespeed = 1;
    private double pixelpermiliscond;

    @Override
    public void update(double gamespeed) {
        this.x = this.x-(pixelpermiliscond *gamespeed);
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


    //Hypothenuse = Math.sqrt(Math.pow(Kathete1, 2) + Math.pow(Kathete2,2));
}
