package game;


import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import javafx.stage.Stage;



public class Audioinfo {
    int pixelPerSecond;
    int lengthSong;

    Minim minim;
    float[] leftChannel;
    

    MinimHelper helper = new MinimHelper();
    private float[][] spectra;

    public double[] getLeft(String fileLoaction) {
        AudioPlayer player;
        minim = new Minim(helper);
        AudioSample song;
        song = minim.loadSample(fileLoaction,2048);
        player = minim.loadFile(fileLoaction);
        lengthSong = player.length();
        float[] leftChannel = song.getChannel(AudioSample.LEFT);
        int datamillisecond = leftChannel.length/lengthSong;
        int postionSongData = 0;
        double[] songData = new double[lengthSong];
        for (int i = 0; i <lengthSong;i++){
            float value = 0;
            for(int lenght = 0; lenght<datamillisecond; lenght++,postionSongData++ ){
                value += leftChannel[postionSongData];


            }
            songData[i] = value;



        }
    return songData;
    }



    public void start(Stage primaryStage) throws Exception {


    }


    /*public static class MinimHelper {



    }*/
}
