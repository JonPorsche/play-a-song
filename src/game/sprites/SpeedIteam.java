package game.sprites;
import game.GameEngine;
import game.sprites.basic.Knockable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import static javafx.scene.paint.Color.BLUE;

public class SpeedIteam  extends Iteam {

    public SpeedIteam( int xPos, int yPos ) {
        super(xPos,yPos);
        this.setFill(BLUE);
        this.setRadius(200);
        this.setCenterX(xPos);
        this.setCenterY(yPos);
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