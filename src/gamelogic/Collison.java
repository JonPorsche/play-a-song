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

    public boolean coinCollsion(double playerRadius, double playerX, double playerY, double x, double y, double radius) {
        double distSq = (playerX - x) * (playerX - x) +
                (playerY - y) * (playerY - y);
        double radSumSq = (playerRadius + radius) * (playerRadius + radius);
        if (distSq < radSumSq)
            return true;
        else if (distSq > radSumSq)
            return false;
        else
            return false;

    }

}
