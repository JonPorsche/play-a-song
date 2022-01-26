package game.sprites.optic;

import game.sprites.basic.Sprite;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

import static javafx.scene.paint.Color.RED;

public class CoinSprite extends SpriteLogic implements Sprite {
    public CoinSprite(int xPos, int yPos, int radius) {
        super(xPos, yPos);
        this.setCenterX(xPos);
        this.setCenterY(yPos);
        this.setFill(RED);
        this.setRadius(radius);
        File imgFile = new File("src/resources/Coin.png");
        this.sound = new File("src/resources/sounds/coin.mp3");
        Image img = new Image(imgFile.toURI().toString());
        this.setImagePatterns(new ImagePattern(img));

        this.setImageContent();
    }


}
