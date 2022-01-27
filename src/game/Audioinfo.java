package game;


import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import de.hsrm.mi.eibo.simpleplayer.MinimHelper;
import javafx.stage.Stage;

import java.io.IOException;

public class Audioinfo {
  private String fileLoaction;
  private int lengthSong;

  private AudioPlayer player;
  private MinimHelper helper = new MinimHelper();
  private Minim minim = new Minim(this.helper);

  public Audioinfo(String fileLoaction) {
    this.fileLoaction = fileLoaction;
  }



  public double[] getLeft() {
    this.player = minim.loadFile(fileLoaction);
    this.lengthSong = player.length()/100;

    float[] leftChannel = this.minim.loadSample(
        this.fileLoaction, 204
    ).getChannel(AudioSample.LEFT);

    int dataperRetangle = (leftChannel.length / this.lengthSong);
    int postionSongData = 0;
    double[] songData = new double[lengthSong];

    for (int i = 0; i < lengthSong; i++) {
      float value = 0;
      for (int lenght = 0; lenght < dataperRetangle; lenght++, postionSongData++) {
        value += leftChannel[postionSongData];
      }
      songData[i] = value;

    }

    return songData;

  }

  public void play() {
    this.player.play();

  }

  public void start(Stage primaryStage) throws Exception {

  }
}
