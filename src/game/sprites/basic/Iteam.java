package game.sprites.basic;

import game.GameEngine;
import game.sprites.logic.PlayerCharacter;

public interface Iteam {
    void collision(GameEngine ge, PlayerCharacter pl);
    void setIsVisabile(boolean b);
    Sprite getSprite();
    int getX();
    int getRadius();
    double getY();
}
