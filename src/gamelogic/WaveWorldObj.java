package gamelogic;


import javafx.beans.property.SimpleBooleanProperty;

public class WaveWorldObj implements GameObject{
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

    @Override
    public void update(double gamespeed, double Radius, double playerX, double playerY) {
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
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public boolean getIsVissable() {
        return false;
    }

    @Override
    public void setIsUsed(boolean isUseD) {

    }

    @Override
    public SimpleBooleanProperty isUseDProperty() {
        return null;
    }


    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
