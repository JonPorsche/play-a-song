package business.service;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Mp3Player {

    private SimpleMinim minim = new SimpleMinim();
    int playlistSize;
    private SimpleIntegerProperty time = new SimpleIntegerProperty();
    private SimpleAudioPlayer audioPlayer;
    private SimpleBooleanProperty isSongLoaded = new SimpleBooleanProperty();
    private boolean isLoaded = false;
    private SimpleBooleanProperty playing = new SimpleBooleanProperty(false);

    public void play() {
        new Thread(){
            public void run() {
                playing.set(true);
                audioPlayer.play();
                if(playing.get() == true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    }}
        }.start();
        timer();

    }
    public void timer() {
        new Thread(){
            public void run() {
                time.setValue(audioPlayer.position()/1000);
                while(playing.get()) {
                    time.setValue(audioPlayer.position()/1000);
                    System.out.println(audioPlayer.getGain());
                    try {

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }

        }.start();


    }



    public void pause() {
        try {
            audioPlayer.pause();
            playing.set(false);
            return;

        }catch(Exception e) {
            System.out.println("Song is not loaded.");
        }

    }

    public void stop() {
        try {
            minim.dispose();
            playing.set(false);
            isSongLoaded.set(false);
            return;

        }catch(Exception e) {
            System.out.println("Song is not loaded.");
        }
    }


    public void load(String track) {
        stop();
        time.setValue(0);
        isSongLoaded.set(true);
        audioPlayer = minim.loadMP3File(track);

    }


}
