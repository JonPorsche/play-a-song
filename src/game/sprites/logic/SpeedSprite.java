package game.sprites.logic;
import business.service.Mp3Player;
import game.GameEngine;
import game.sprites.basic.Iteam;
import game.sprites.optic.SpriteLogic;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

import static javafx.scene.paint.Color.BLUE;

public class SpeedSprite extends SpriteLogic implements Iteam {
    public static SpeedSprite getFromFactory(int xPos, int yPos ) {
        return new SpeedSprite( xPos, yPos );
    }

    public SpeedSprite(int xPos, int yPos ) {
        super(xPos,yPos);
        this.setFill(BLUE);
        this.setRadius(20);
        this.setCenterX(xPos);
        this.setCenterY(yPos);
        this.setFill(BLUE);
        this.gamespeed =3;
        this.sizeModifer =-5;
        this.score = 1000;
        this.sound = new File("src/resources/sounds/speed.mp3");
        File imgFile = new File("src/resources/Speed.png");
        Image img = new Image(imgFile.toURI().toString());
        this.setImagePatterns( new ImagePattern(img) );
        //Image img = new Image("myjavafxapp/resources/texture_bg.png");
        //this.setImagePatterns(new ImagePattern(img));
        //this.setImageContent();
    }

    @Override
    public void collision(GameEngine ge, PlayerCharacter pl) {
        if (!isUsed.getValue()) {
            pl.addSizeModifer(sizeModifer,ge);
            pl.setPlayerImgSpeed();
            ge.addGamespeed((float) gamespeed);
            ge.addScore(score);
            isUsed.setValue(true);
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