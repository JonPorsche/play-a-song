package gamelogic;

import game.Audioinfo;

import java.io.File;

public class IteamTakenListner implements IteamTaken {
    @Override
    public void IteamTaken(player player, Game game, double size, double speed, long duration) {
        Audioinfo sound = new Audioinfo();
        sound.play(new File("src/assets/FFXIV_Remove_Item.mp3").getAbsolutePath());
        Thread iteamthread = new Thread() {
            @Override
            public void run() {

                player.setSizeModifer(size);
                player.setSpeedModfer(speed);
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                player.setSizeModifer(-size);
                player.setSpeedModfer(-speed);


            }
        };
        iteamthread.start();


    }
}
