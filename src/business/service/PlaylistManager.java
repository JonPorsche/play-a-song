package business.service;

import business.data.Song;
import javafx.application.Platform;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlaylistManager {

    static String directoryPath;
    ArrayList<Song> songsList;

    /**
     * Opens a default system window to select a directory and captures its absolute path
     * @author Jones Porsche
     */
    public static void selectDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                File selectedDirectory = directoryChooser.showDialog(null);
                directoryChooser.setTitle("Open mp3 folder");
                if(selectedDirectory != null) {
                    directoryPath = selectedDirectory.getAbsolutePath();
                }
            }
        });
    }

    /*trackList.clear();
                  List<File> files = new ArrayList<>();
                    loadPlaylist(directoryPath, files);
                    playlist.setTracks(trackList);
                    playlist.numberOfTracks();
                    PlaylistViewController.trackListModel.clear();
                    PlaylistViewController.trackListModel.addAll(playlist.getTracks());*/

/*    public void loadPlaylist(String directoryName, List<File> files) {

        File directory = new File(directoryName);
        MainApp.playlistPath = directoryName;

        // Get all files from a directory.
        File[] fList = directory.listFiles();
        String filePath;

        if (fList != null) {
            for (File file : fList) {
                if (file.isFile() && file.getName().matches(".*[0-9]+.*\\.(mp3)$")) {
                    files.add(file);
                    filePath = file.getAbsolutePath();
                    trackList.add(loadTrackInfo(filePath));
                } else if (file.isDirectory()) {
                    loadPlaylist(file.getAbsolutePath(), files);
                }
            }
        }
    }*/
}
