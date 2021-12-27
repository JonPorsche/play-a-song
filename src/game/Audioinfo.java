package game;


import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import javafx.stage.Stage;



public class Audioinfo {
    int pixelPerSecond;
    int lengthSong;
    AudioPlayer player;

    Minim minim;
    float[] leftChannel;
    

    MinimHelper helper = new MinimHelper();
    private float[][] spectra;

    public double[] getLeft(String fileLoaction) {

        minim = new Minim(helper);
        AudioSample song;
        song = minim.loadSample(fileLoaction,2048);
        player = minim.loadFile(fileLoaction);
        lengthSong = player.length()/20;
        System.out.println(lengthSong);
        float[] leftChannel = song.getChannel(AudioSample.LEFT);
        System.out.println(leftChannel.length);
       // double datamillisecond = ();
        int dataperRetangle = (int) (leftChannel.length/lengthSong);
        System.out.println(dataperRetangle);
        int postionSongData = 0;
        double[] songData = new double[lengthSong];
        for (int i = 0; i <lengthSong;i++){
            float value = 0;
            for(int lenght = 0; lenght<dataperRetangle; lenght++,postionSongData++ ){
                value += leftChannel[postionSongData];


            }
            songData[i] = value;



        }
        System.out.println(songData.length);





    return songData;

    }

    public void play(){
        player.play();

    }



    public void start(Stage primaryStage) throws Exception {


    }


    /*public static class MinimHelper {



    }*/
}
