package game.sprites.optic;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class PlayerSprite extends SpriteLogic {
    public PlayerSprite(int xPos, int yPos) {
        super(xPos, yPos);
        File imgFile = new File("src/resources/Playerdefault.png");
        Image imgNormal = new Image(imgFile.toURI().toString());
        this.setRadius(30);
        this.setFill(new ImagePattern(imgNormal));

    }
}
