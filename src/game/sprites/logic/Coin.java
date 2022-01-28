package game.sprites.logic;

import business.service.Mp3Player;
import game.GameEngine;
import game.sprites.basic.Iteam;
import game.sprites.basic.Sprite;
import game.sprites.optic.CoinSprite;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Coin extends SpriteLogic implements Iteam {
    public static Coin getFromFactory(int xPos, int yPos ) {
        return new Coin( xPos, yPos );
    }
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
            if (sprite.soundFile != null) {
                Mp3Player soundP = new Mp3Player();
                soundP.load(sprite.soundFile.getAbsolutePath());
                soundP.play();
            } else System.err.println( "Err: Coin SoundClip nicht verfügbar!" );
        }
    }

    @Override
    public void setIsVisabile(boolean b) {
        if(b){
            sprite = new CoinSprite(x, y, radius);
            if (sprite.img != null) setImagePatterns(new ImagePattern(sprite.img));
            else System.err.println( "Err: Coin Image nicht verfügbar!" );
            isVisabile.set(true);
        }else{
            sprite = null;
        }
    }

    @Override
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

