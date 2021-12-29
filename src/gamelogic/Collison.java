package gamelogic;

import game.Audioinfo;

import java.io.File;

public class Collison {

    public void iteamPlayerCollison(double gamespeed, double Radius, double playerX, double playerY, double iteamX, double iteamY, double itemWidth, double itemHeight, boolean isUsed, GameObject iteam, Game game){

        double Xn = Math.max(iteamX, Math.min(playerX, iteamX+itemWidth));
        double Yn = Math.max(iteamY, Math.min(playerY, iteamY+itemHeight));
        double distanceX = Xn - playerX;
        double distanceY = Yn - playerY;
        if((distanceX * distanceX + distanceY * distanceY) <= Radius* Radius && !isUsed){
            iteam.setIsUsed(true);
            game.removeIteam(iteam);
            Audioinfo sound = new Audioinfo();
            sound.play( new File( "src/assets/FFXIV_Remove_Item.mp3" ).getAbsolutePath());

        };

    }
}
