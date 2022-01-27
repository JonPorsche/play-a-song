package game.sprites.logic;

import business.service.Mp3Player;
import game.GameEngine;
import game.sprites.basic.Iteam;
import game.sprites.optic.CoinSprite;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class Coin extends CoinSprite implements Iteam {
    /*public static CoinSprite getFromFactory( int xPos, int yPos ) {
        return new CoinSprite( xPos, yPos );
    }*/
    public static Coin getFromFactory(int xPos, int yPos ) {
        return new Coin( xPos, yPos );
    }

    public Coin(int xPos, int yPos) {
        super(xPos, yPos);
        //@TODO Coin(iteam) alle iteam spirtes hinzufügen
        File imgFile = new File("src/resources/Coin.png");
        Image img = new Image(imgFile.toURI().toString());
        this.setImagePatterns(new ImagePattern(img));
        File img2File = new File("src/resources/Coin-2.png");
        this.sound = new File("src/resources/sounds/coin.mp3");
        Image img2 = new Image(img2File.toURI().toString());
        this.setImagePatterns(new ImagePattern(img2));
        this.setImageContent();
        this.setRadius(20);
        this.setCenterX(xPos);
        this.setCenterY(yPos);
        this.gamespeed = 1;
        this.sizeModifer = -1;
        this.score =100;

    }

    @Override
    public void collision(GameEngine ge, PlayerCharacter pl) {
        if (!isUsed.getValue()) {
            isUsed.setValue(true);
            ge.addScore(score);
            Mp3Player soundP = new Mp3Player();
            soundP.load(sound.getAbsolutePath());
            soundP.play();
        }
    }

    @Override
    public void setIsVisabile(boolean b) {
    isVisabile.setValue(b);
    }
}

