package game.sprites.logic;
import business.service.Mp3Player;
import game.GameEngine;
import game.sprites.basic.Iteam;
import game.sprites.basic.Sprite;
import game.sprites.optic.CoinSprite;
import game.sprites.optic.SpeedSprite;
import game.sprites.optic.SpriteLogic;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

import static javafx.scene.paint.Color.BLUE;

public class Speed  implements Iteam {
    SpeedSprite speedSprite = null;
    int x;
    int y;
    double gamespeed =0.80;
    double sizeModifer =5;
    double score = 1000;
    double radius;

    public Speed(int xPos, int yPos ) {
        this.x = xPos;
        this.y = yPos;

    }

    @Override
    public void collision(GameEngine ge, PlayerCharacter pl) {
        if (!speedSprite.isUsed.getValue()) {
            pl.addSizeModifer(speedSprite.sizeModifer,ge);
            pl.setPlayerImgSpeed();
            ge.addGamespeed((float) speedSprite.gamespeed);
            ge.addScore(speedSprite.score);
            speedSprite.isUsed.setValue(true);
            Mp3Player soundP = new Mp3Player();
            soundP.load(speedSprite.sound.getAbsolutePath());
            soundP.play();
        }

    }

    @Override
    public void setIsVisabile(boolean b) {
        if(b){
        speedSprite = new SpeedSprite(x,y);
    }else{
            speedSprite = null;
        }
    }

    @Override
    public Sprite getSprite() {
        return speedSprite;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getRadius() {
        return (int) radius;
    }

    @Override
    public double getY() {
        return y;
    }


}