package business.data;

import java.util.ArrayList;

public class Playlist {

    private ArrayList<Song> songs;

    public Playlist(ArrayList<Song> tracks) {
        this.songs = tracks;
    }

    public int numberOfTracks(){
        return songs.size();
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
}
