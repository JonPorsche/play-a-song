package gamelogic;

public class player implements GameObject{
    private double x;
    private double y;
    private int painWidth;
    private int painHeight;
    private double gamespeed = 1;
    private double pixelpermiliscond;



    @Override
    public void update(double gamespeed) {
        y = y+0.05;



    }

    public void updateHeigt(double value){
        y = y + value;
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
}
