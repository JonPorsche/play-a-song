package game.sprites.optic;

import game.sprites.basic.Sprite;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

import static javafx.scene.paint.Color.BLUE;

public class SpeedSprite extends SpriteLogic implements Sprite {

    public SpeedSprite(int xPos, int yPos) {
        super(xPos, yPos);
        this.setRadius(20);
        this.setCenterX(xPos);
        this.setCenterY(yPos);
        this.gamespeed =3;
        this.sizeModifer =-5;
        this.score = 1000;
        this.sound = new File("src/resources/sounds/speed.mp3");
        File imgFile = new File("src/resources/Speed.png");
        Image img = new Image(imgFile.toURI().toString());
        this.setImagePatterns( new ImagePattern(img) );
    }
}
