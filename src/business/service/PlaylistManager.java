package business.service;

import business.data.Song;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.DirectoryChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jones Prosche
 */
public class PlaylistManager {

    private static PlaylistManager INSTANCE = new PlaylistManager();
    private static String directoryPath;
    public static ObservableList<Song> songs = FXCollections.observableArrayList();
    private String selectedSongPath = null;
    public static File m3uFile = new File("./playlist/playlist.m3u");
    public SimpleObjectProperty<PlaylistStatus> playlistStatus;

    /**
     * Hided constructor to avoid the generation of more than one instance of the singleton.
     * @author Jones Porsche
     */
    private PlaylistManager() {
        playlistStatus = new SimpleObjectProperty<>();
    }

    public static PlaylistManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlaylistManager();
        }
        return INSTANCE;
    }

    /**
     * Opens a default system window to select a directory and captures its absolute path.
     * It also calls the createPlaylist method.
     * @author Jones Porsche
     */
    public static void selectDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                File selectedDirectory = directoryChooser.showDialog(null);
                directoryChooser.setTitle("Select a folder with mp3 files");
                if (selectedDirectory != null) {
                    directoryPath = selectedDirectory.getAbsolutePath();
                    List<File> files = new ArrayList<>();
                    createPlaylist(directoryPath, files);
                }
            }
        });
    }

    /**
     * Searches for mp3 files in a starting directory path and all of its subsequent directories.
     * Creates a song instance for every mp3 file and adds it to the songs array.
     * Calls the method that writes every mp3 file path into the m3u file.
     * @param directoryName The absolute path to search for mp3 files
     * @param files         List to collect all mp3 files found in a directory. On the first input it's always empty, but is necessary, because the function is called recursively further.
     * @author Jones Porsche
     */
    private static void createPlaylist(String directoryName, List<File> files) {

        File directory = new File(directoryName);
        File[] fileList = directory.listFiles();
        String filePath;

        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile() && file.getName().matches(".*[0-9]+.*\\.(mp3)$")) {
                    files.add(file);
                    filePath = file.getAbsolutePath();
                    songs.add(createSong(filePath));
                    writeM3UFile();
                } else if (file.isDirectory()) {
                    createPlaylist(file.getAbsolutePath(), files);
                }
            }
        }
    }

    /**
     * Creates a new song object based on the mp3 file path and collects the song information from the ID3 tags.
     * @param songFilePath The path of the mp3 file
     * @return a new song object
     * @author Jones Porsche
     * @see Song
     */
    private static Song createSong(String songFilePath) {

        String title = null;
        int duration = 0;
        String albumTitle = null;
        String artist = null;
        byte[] albumImage = null;

        try {
            Mp3File mp3File = new Mp3File(songFilePath);
            if (mp3File.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3File.getId3v2Tag();
                artist = id3v2Tag.getArtist();
                title = id3v2Tag.getTitle();
                duration = (int) mp3File.getLengthInSeconds() * 1000;
                albumTitle = id3v2Tag.getAlbum();
                albumImage = id3v2Tag.getAlbumImage();
            } else {
                System.out.println("The mp3 file does not exists or does not have a ID3v1Tag");
            }
        } catch (UnsupportedTagException | InvalidDataException | IOException e) {
            System.err.println("File not found.");
            e.printStackTrace();
        }
        return new Song(title, duration, albumTitle, artist, songFilePath, albumImage);
    }

    /**
     * Checks first if the m3u file exists. If not, creates it and calls the method again.
     * Writes down one mp3 file path per line. The paths come from the songs array.
     * @author Jones Porsche
     */
    private static void writeM3UFile() {
        try {
            FileOutputStream fileOutputStream = null;
            fileOutputStream = new FileOutputStream(m3uFile);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            String path;
            for (Song song : songs) {
                path = song.getSongFilePath();
                writer.write(path);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException m3uFileDoesNotExist) {
            m3uFile = new File("./playlist/playlist.m3u");
            writeM3UFile();
        }
    }

    /**
     * Reads the playlist m3u file line by line and loads the songs array with the song instances.
     * @author Jones Porsche
     */
    public static void loadPlaylistFromM3UFile() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(m3uFile.getAbsolutePath()));
            String line = reader.readLine();
            while (line != null) {
                if (line.charAt( 0 ) == '#') continue;
                File f = new File( line );
                boolean fExist = f.exists( );
                if (!fExist) continue;

                songs.add(createSong(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Simply deletes everything in the playlist.m3u file
     * @author Jones Porsche
     */
    public static void cleanM3UFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(m3uFile);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called at the application start, checks if the m3u playlist file is filled or empty and sets the playlist status.
     * Is the playlist filled, calls the method to read the m3u file and load the songs array.
     * @author Jones Porsche
     * @see application.Main start() method
     */
    public void checkPlaylistStatus(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(m3uFile.getAbsolutePath()));
            String line = reader.readLine();
            if(line != null) {
                playlistStatus.set(PlaylistStatus.FILLED);
                loadPlaylistFromM3UFile();
            }
            else playlistStatus.set(PlaylistStatus.EMPTY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats the time to a String like mm:ss
     * @author Jones Porsche
     * @param milliseconds
     * @return
     */
    public static String formatTime(int milliseconds) {
        int seconds = milliseconds / 1000;
        int hours = seconds / 3600;
        int minutes = (seconds - (3600 * hours)) / 60;
        int seg = seconds - ((hours * 3600) + (minutes * 60));

        return String.format("%01d", minutes) + ":" + String.format("%02d", seg);

    }

    public final String getSelectedSongPath() {
        return this.selectedSongPath;
    }

    public final void setSelectedSongPath(String selectedSongPath) {
        this.selectedSongPath = selectedSongPath;
    }

    public ObservableList<Song> getSongListProperty() {
        return this.songs;
    }
}
