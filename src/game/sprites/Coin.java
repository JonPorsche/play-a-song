package game.sprites;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.YELLOW;

public class Coin extends Iteam{

    public Coin(int xPos, int yPos) {
        super(xPos, yPos);
        //@TODO Coin(iteam) alle iteam spirtes hinzufügen
        File imgFile = new File("src/resources/Coin.png");
        Image img = new Image(imgFile.toURI().toString());
        File img2File = new File("src/resources/Coin2.png");
        Image img2 = new Image(img2File.toURI().toString());
        this.setImagePatterns(img2);
        this.x = xPos;
        this.y = yPos;

    }
}
