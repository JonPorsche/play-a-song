package gamelogic.sprites;

import gamelogic.sprites.base.GameObject;

public class iteam implements GameObject {
    private double x;
    private double y;
    private double sizeModifer;
    private double speedModifer;
    private double height;
    private double width;
    private double speed;
    private long duration;
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
        this.y = y;
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

    public double getSizeModifer() {
        return sizeModifer;
    }

    public void setSizeModifer(double sizeModifer) {
        this.sizeModifer = sizeModifer;
    }

    public double getSpeedModifer() {
        return speedModifer;
    }

    public void setSpeedModifer(double speedModifer) {
        this.speedModifer = speedModifer;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setSpeed(double speedModfer) {
        this.speed = speedModfer;
    }
}
