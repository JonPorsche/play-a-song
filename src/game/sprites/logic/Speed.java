package game.sprites.logic;
import business.service.Mp3Player;
import game.GameEngine;
import game.sprites.basic.Iteam;
import game.sprites.basic.Sprite;
import game.sprites.optic.SpeedSprite;
import javafx.scene.paint.ImagePattern;

public class Speed extends SpriteLogic  implements Iteam {
    int x;
    int y;
    double gamespeed =4;
    double sizeModifer =-1;
    double score = 1000;
    double radius = 20;


    public Speed(int xPos, int yPos ) {
        this.x = xPos;
        this.y = yPos;

    }

    @Override
    public void collision(GameEngine ge, PlayerCharacter pl) {
        if (!isUsed.getValue()) {
            pl.addSizeModifer(sizeModifer,ge);
            ge.addGamespeed((float) gamespeed);
            ge.addScore(score);
            isUsed.setValue(false);
            Mp3Player soundP = new Mp3Player();
            soundP.load(sprite.soundFile.getAbsolutePath());
            soundP.play();
        }

    }

    @Override
    public void setIsVisabile(boolean b) {
        if(b){
        sprite = new SpeedSprite(x,y);
        setImagePatterns(new ImagePattern(sprite.img));
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
        return (int) radius;
    }

    @Override
    public double getY() {
        return y;
    }


}