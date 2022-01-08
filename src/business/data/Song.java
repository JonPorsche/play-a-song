package business.data;

import java.util.concurrent.atomic.AtomicInteger;

public class Song {

    private int id;
    private String title;
    private int duration;
    private String albumTitle;
    private String artist;
    private String songFilePath;
    private byte[] albumImage;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    public Song(String title, int duration, String albumTitle, String artist, String trackFilePath, byte[] albumImage) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.title = title;
        this.duration = duration;
        this.albumTitle = albumTitle;
        this.artist = artist;
        this.songFilePath = trackFilePath;
        this.albumImage = albumImage;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration(){return duration;}

    public String getAlbumTitle() {
        return albumTitle;
    }

    public String getArtist() {
        return artist;
    }

    public String getSongFilePath() {
        return songFilePath;
    }

    public byte[] getAlbumImage() {
        return albumImage;
    }

    @Override
    public String toString() {
        return "Song " + id +
                " = " + artist +
                " - " + title +
                "\n";
    }
}
