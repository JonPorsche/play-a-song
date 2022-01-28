package business.service;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import ddf.minim.AudioOutput;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;

public class Mp3Player {
  private SimpleMinim minim = new SimpleMinim(true);
  int playlistSize;
  private SimpleIntegerProperty time = new SimpleIntegerProperty();
  private SimpleAudioPlayer audioPlayer;
  private SimpleBooleanProperty isSongLoaded = new SimpleBooleanProperty();
  private boolean isLoaded = false;
  private SimpleBooleanProperty playing = new SimpleBooleanProperty(false);

  public void play( ) {
    new Thread( ) {
      public void run( ) {
        playing.set( true );
        audioPlayer.play( );
        if(playing.get() == true) {
          try { Thread.sleep( 1000 ); }
          catch (InterruptedException e) { e.printStackTrace( ); }
        }
      }
    }.start( );
    timer( );
  }

  public int getLenghth( String fileLoaction) throws InvalidDataException, UnsupportedTagException, IOException {
    Mp3File mp3File = new Mp3File( fileLoaction );
    double test = mp3File.getLengthInSeconds( );
    return (int)mp3File.getLengthInSeconds( );
  }

  public void timer( ) {
    new Thread(){
        public void run() {
      time.setValue(audioPlayer.position()/1000);

      while (playing.get( )) {
        time.setValue(audioPlayer.position()/1000);

        try { Thread.sleep(1000); }
        catch (InterruptedException e) { e.printStackTrace(); }
      }
        }

    }.start( );
  }

  public void pause( ) {
    try {
      audioPlayer.pause();
      playing.set(false);
    } catch(Exception e) { System.err.println("Song is not loaded."); }
  }

  public void stop( ) {
    try {
      minim.dispose( );
      playing.set( false );
      isSongLoaded.set( false );
    } catch(Exception e) { System.out.println( "Song is not loaded." ); }
  }

  public void load(String track) {
    stop( );
    time.setValue( 0 );
    isSongLoaded.set( true );
    audioPlayer = minim.loadMP3File( track );
  }
}
