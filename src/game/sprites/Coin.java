package game.sprites;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.YELLOW;

public class Coin extends Iteam{

    public Coin(int xPos, int yPos) {
        super(xPos, yPos);
        //@TODO Coin(iteam) alle iteam spirtes hinzufügen
        Image img = new Image("src/resources/Coin.png");
        this.setImagePatterns( new ImagePattern(img) );
        this.setImageContent();
        this.setRadius(20);
        this.setCenterX(xPos);
        this.setCenterY(yPos);

    }
}
