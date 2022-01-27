package game.sprites.logic;

import business.service.Mp3Player;
import game.GameEngine;
import game.sprites.basic.Iteam;
import game.sprites.basic.Sprite;
import game.sprites.optic.CoinSprite;
import javafx.scene.paint.ImagePattern;

public class Coin extends SpriteLogic implements Iteam {

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
        if (!isUsed.getValue()) {
            isUsed.setValue(true);
            ge.addScore(score);
            Mp3Player soundP = new Mp3Player();
            soundP.load(sprite.soundFile.getAbsolutePath());
            soundP.play();
        }
    }
    @Override
    public void setIsVisabile(boolean b) {
        if(b){
            sprite = new CoinSprite(x, y, radius);
            setImagePatterns(new ImagePattern(sprite.img));
            isVisabile.set(true);
        }else{
            sprite = null;
        }
    }

    public Sprite getSprite() {
        return sprite;
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

