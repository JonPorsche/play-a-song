package game.sprites.optic;

import game.sprites.basic.Sprite;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

import static javafx.scene.paint.Color.RED;

public class CoinSprite extends SpriteCircle implements Sprite {
    File imgFile = new File("src/resources/Coin.png");

    public CoinSprite(int xPos, int yPos, int radius) {
        this.setCenterX(xPos);
        this.setCenterY(yPos);
        this.setFill(RED);
        this.setRadius(20);
        this.setCenterX(xPos);
        this.setCenterY(yPos);
        soundFile = new File("src/resources/sounds/Coin.mp3");
        img = new Image(imgFile.toURI().toString());
        setFill(new ImagePattern(img));

    }


}
