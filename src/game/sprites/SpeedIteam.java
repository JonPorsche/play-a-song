package game.sprites;
import game.GameEngine;
import game.sprites.basic.Knockable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

import static javafx.scene.paint.Color.BLUE;

public class SpeedIteam  extends Iteam {

    public SpeedIteam( int xPos, int yPos ) {
        super(xPos,yPos);
        this.setRadius(20);
        this.setCenterX(xPos);
        this.setCenterY(yPos);
        this.setFill(BLUE);
        File imgFile = new File("src/resources/Coin.png");
        Image img = new Image(imgFile.toURI().toString());
        this.setImagePatterns( new ImagePattern(img) );
        File img2File = new File("src/resources/Coin-2.png");
        Image img2 = new Image(img2File.toURI().toString());
        this.setImagePatterns( new ImagePattern(img2) );
        //Image img = new Image("myjavafxapp/resources/texture_bg.png");
        //this.setImagePatterns(new ImagePattern(img));
        //this.setImageContent();
    }

    public void onSmash( Knockable collidedObject, GameEngine gE ) {
    /*gE.setTickDistance( -50 );

    Thread.sleep(1000);
    gE.resetEffects();*/
    }
}