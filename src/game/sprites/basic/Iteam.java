package game.sprites.basic;

import game.GameEngine;
import game.sprites.PlayerCharacter;
import javafx.scene.paint.ImagePattern;

public interface Iteam {
    
    void collision(GameEngine ge, PlayerCharacter pl);

    double getRadius();

    double getCenterX();

    double getCenterY();


    void setIsVisabile(boolean b);
}
