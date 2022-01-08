package gamelogic;

import javazoom.jl.player.Player;

public class CollisionWaveListner implements CollsionWave {
    @Override
    public void collsionWave(player player, Game game, double y, boolean over){
        game.updateScore(-1000);
        game.updateHealth(-1);


    }



}
