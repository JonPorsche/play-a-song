package game.sprites.basic;

import game.GameEngine;
import game.sprites.logic.PlayerCharacter;

public interface Iteam {
    
    void collision(GameEngine ge, PlayerCharacter pl);

    double getRadius();

    double getCenterX();

    double getCenterY();


    void setIsVisabile(boolean b);
}
