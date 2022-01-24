package scenes.menuview;

import application.Main;
import business.data.Song;
import business.service.PlaylistManager;
import business.service.PlaylistStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import scenes.BasicView;
import scenes.menuview.button_box_view.ButtonBoxViewController;
import scenes.menuview.selection_box_view.SelectionBoxViewController;
import scenes.menuview.selection_box_view.bottom_view.BottomViewController;
import scenes.menuview.selection_box_view.center_view.options_view.OptionsViewController;
import scenes.menuview.selection_box_view.center_view.playlist_view.PlaylistViewController;
import static scenes.menuview.selection_box_view.center_view.playlist_view.PlaylistViewController.PLAYLIST_EMPTY;
import static scenes.menuview.selection_box_view.center_view.playlist_view.PlaylistViewController.PLAYLIST_FILLED;

public class MenuViewController extends BasicView {
    private static MenuViewController INSTANCE = new MenuViewController(application);
    public static final String PLAYLIST_VIEW = "PLAYLIST";
    public static final String OPTIONS_VIEW = "OPTIONS";
    private MenuView menuView;
    private HBox menuContainer;
    private Pane buttonBoxView;
    private Pane selectionBoxView;

    private MenuViewController(Main application) {
        super(application);
        this.menuView = new MenuView();
        this.menuContainer = menuView.menuContainer;
        this.buttonBoxView = menuView.buttonBoxView;
        this.selectionBoxView = menuView.selectionBoxView;
        this.menuRootView = menuView;
        initialize();
    }

    public static MenuViewController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MenuViewController(application);
        }
        return INSTANCE;
    }

    public void initialize() {
        handlePlaylistStatusChanges();
        handleSongsArrayChanges();
    }

    /**
     * Reacts to changes in the m3u playlist file status.
     * If is empty, tells the playlist view controller to display the view with an instruction text.
     * If is filled, tells the playlist view controller to display the playlist view with the songs and infos.
     *
     * @author Jones Porsche
     */
    private void handlePlaylistStatusChanges() {
        PlaylistManager.getInstance().playlistStatus.addListener(new ChangeListener<PlaylistStatus>() {
            @Override
            public void changed(ObservableValue<? extends PlaylistStatus> observable, PlaylistStatus oldPlaylistStatus, PlaylistStatus newPlaylistStatus) {
                switch (newPlaylistStatus) {
                    case EMPTY:
                        PlaylistViewController.getInstance().switchPlaylistView(PLAYLIST_EMPTY);
                        BottomViewController.getInstance().hideBtns();
                        break;
                    case FILLED:
                        PlaylistViewController.getInstance().switchPlaylistView(PLAYLIST_FILLED);
                        BottomViewController.getInstance().showBtns();
                        break;
                }
            }
        });
    }

    private void handleSongsArrayChanges() {
        PlaylistManager.getInstance().songs.addListener((ListChangeListener<Song>) c -> {
            if (PlaylistManager.songs.isEmpty()) {
                PlaylistManager.getInstance().playlistStatus.set(PlaylistStatus.EMPTY);
            } else {
                PlaylistManager.getInstance().playlistStatus.set(PlaylistStatus.FILLED);
            }
        });
    }

    public void switchSelectionBoxView(String newSelectionBoxView) {
        switch (newSelectionBoxView) {
            case PLAYLIST_VIEW:
                SelectionBoxViewController.getInstance().updateSelectionBoxView(PLAYLIST_VIEW,
                        PlaylistViewController.getInstance().getPlaylistRootView());
                switchBtnStyle(ButtonBoxViewController.getInstance().getOptionsBtn(),
                        "text-btn-contained-enabled-color",
                        "text-btn-contained-disabled-color",
                        "text-btn-contained-focused",
                        "text-btn-contained-disabled-color");
                switchBtnStyle(ButtonBoxViewController.getInstance().getPlaylistBtn(),
                        "text-btn-contained-disabled-color",
                        "text-btn-contained-focused");
                break;
            case OPTIONS_VIEW:
                SelectionBoxViewController.getInstance().updateSelectionBoxView(OPTIONS_VIEW,
                        OptionsViewController.getInstance().getOptionsRootView());
                switchBtnStyle(ButtonBoxViewController.getInstance().getPlaylistBtn(),
                        "text-btn-contained-enabled-color",
                        "text-btn-contained-focused",
                        "text-btn-contained-disabled-color");
                switchBtnStyle(ButtonBoxViewController.getInstance().getOptionsBtn(),
                        "text-btn-contained-disabled-color",
                        "text-btn-contained-focused");
                break;
        }
    }

    public static void switchBtnStyle(Button button, String remove, String add) {
        button.getStyleClass().remove(remove);
        button.getStyleClass().add(add);
    }

    public static void switchBtnStyle(Button button, String remove1, String remove2, String add) {
        button.getStyleClass().remove(remove1);
        button.getStyleClass().remove(remove2);
        button.getStyleClass().add(add);
    }

    public static void switchBtnStyle(Button button, String remove1, String remove2, String remove3, String add) {
        button.getStyleClass().remove(remove1);
        button.getStyleClass().remove(remove2);
        button.getStyleClass().remove(remove3);
        button.getStyleClass().add(add);
    }
}
