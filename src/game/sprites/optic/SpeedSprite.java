package game.sprites.optic;

import game.sprites.basic.Sprite;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

import static javafx.scene.paint.Color.BLUE;

public class SpeedSprite extends SpriteCircle implements Sprite {
    File imgFile = new File("src/resources/Speed.png");


    public SpeedSprite(int xPos, int yPos) {
        this.setRadius(20);
        this.setCenterX(xPos);
        this.setCenterY(yPos);
        this.img = new Image(imgFile.toURI().toString());
        soundFile = new File("src/resources/sounds/speed.mp3");
        this.setFill(new ImagePattern(img));
    }
}
