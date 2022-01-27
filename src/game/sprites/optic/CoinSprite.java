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
        this.setRadius(radius);
        this.soundFile = new File("src/resources/sounds/coin.mp3");
        this.img = new Image(imgFile.toURI().toString());
        this.setFill(new ImagePattern(img));
    }


}
