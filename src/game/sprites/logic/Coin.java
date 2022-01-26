package game.sprites.logic;

import business.service.Mp3Player;
import game.GameEngine;
import game.sprites.basic.Iteam;
import game.sprites.basic.Sprite;
import game.sprites.optic.CoinSprite;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class Coin implements Iteam {
    CoinSprite coinSprite = null;
    int x;
    int y;
    int radius = 20;
    double score = 1000;

    public Coin(int xPos, int yPos) {
        this.x = xPos;
        this.y = yPos;
    }
    @Override
    public void collision(GameEngine ge, PlayerCharacter pl) {
        if (!coinSprite.isUsed.getValue()) {
            coinSprite.isUsed.setValue(true);
            ge.addScore(score);
            Mp3Player soundP = new Mp3Player();
            soundP.load(coinSprite.sound.getAbsolutePath());
            soundP.play();
        }
    }
    @Override
    public void setIsVisabile(boolean b) {
        if(b){
            coinSprite = new CoinSprite(x, y, radius);

        }else{
            coinSprite = null;
        }
    }

    public Sprite getSprite() {
        return coinSprite;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public double getY() {
        return y;
    }
}

