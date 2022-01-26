package game.sprites.optic;

import game.sprites.basic.Sprite;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

import static javafx.scene.paint.Color.RED;

public class SlowMotionSprite extends SpriteLogic  implements Sprite {
    public SlowMotionSprite(int xPos, int yPos) {
        super(xPos, yPos);
        this.setCenterX(xPos);
        this.setCenterY(yPos);
        this.setFill(RED);
        this.setRadius(20);
        this.setCenterX(xPos);
        this.setCenterY(yPos);
        File imgFile = new File("src/resources/Slow.png");
        this.sound = new File("src/resources/sounds/slow.mp3");
        Image img = new Image(imgFile.toURI().toString());
        this.setImagePatterns( new ImagePattern(img) );
        this.setImageContent();
    }
}
