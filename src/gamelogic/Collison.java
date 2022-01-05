package gamelogic;

public class Collison {

    public boolean iteamPlayerCollison(double gamespeed, double Radius, double playerX, double playerY, double iteamX, double iteamY, double itemWidth, double itemHeight, boolean isUsed){

        double Xn = Math.max(iteamX, Math.min(playerX, iteamX+itemWidth));
        double Yn = Math.max(iteamY, Math.min(playerY, iteamY+itemHeight));
        double distanceX = Xn - playerX;
        double distanceY = Yn - playerY;
        if((distanceX * distanceX + distanceY * distanceY) <= Radius* Radius && !isUsed){
            return true;


        };
        return false;

    }

    public boolean wavePlayerCollsion(double y, double x, double radius){
    return false;
    };

}
