package gamelogic;

import game.Audioinfo;

import java.io.File;

public class CoinCollcted implements CoinCollsion{




    @Override
    public void coinCollsion(int points,player player,Game game) {

        Audioinfo sound = new Audioinfo();
        sound.play(new File("src/assets/FFXIV_Remove_Item.mp3").getAbsolutePath());
        game.updateScore(points);

    }
}
