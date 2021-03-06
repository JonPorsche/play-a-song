package application;

import business.service.KeyChoiceManager;
import business.service.PlaylistManager;
import game.GameManager;
import game.GamePlayingState;
import game.sprites.basic.Iteam;
import game.sprites.logic.Coin;

import game.sprites.logic.SlowMo;
import game.sprites.logic.Speed;
import game.sprites.optic.SpeedSprite;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scenes.gameview.GameViewController;
import scenes.menuview.MenuViewController;
import uicomponents.game.GameDisplay;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

public class Main extends Application {

    public static final int WINDOW_WIDTH = 1080;
    public static final int WINDOW_HEIGHT = 640;
    public static final int PLAYER_RADIUS = 20;

    public static final int MAP_CHUNK_WIDTH_PX = 100;
    public static final double MAP_CHUNK_BASE_HEIGHT_PX = 50;
    public static final double MAP_CHUNK_HEIGHT_PX = 6;

    public static final String MENU_VIEW = "MenuView";
    public static final String GAME_VIEW = "GameView";

    public Properties defaultProp = null;
    public Properties appProp = null;
    private static Main INSTANCE = new Main();

    // TODO Declare Game here
    public static GameManager gameManager;
    Map<String, Scene> scenes;
    Pane rootPane;
    Stage primaryStage;
    Scene scene;

    // CONTROLLERS
    MenuViewController menuViewController;
    GameViewController gameViewController;

    @Override
    public void init() throws Exception {
        super.init();
        KeyChoiceManager.getInstance();
        loadProperties();
    }

    @Override
    public void start(Stage primaryStage) {
        Main.gameManager = new GameManager( )
        .addIteamPattern(
            (x, y) -> Coin.getFromFactory( (int)x, (int)y ),
            (x, y) -> SlowMo.getFromFactory( (int)x, (int)y ),
            (x, y) -> Speed.getFromFactory( (int)x, (int)y )
        );

        //this.menuViewController = new MenuViewController(this);
        MenuViewController.getInstance();
        this.gameViewController = new GameViewController(this);


        // Not only checks if m3u playlist file is filled. Also loads playlist (songs array) if is true.
        PlaylistManager.getInstance().checkM3UFileStatus();

        loadScenes();
        //setStartView();

        scene = this.scenes.get(MENU_VIEW);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Play a Song");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * Loads the scenes into a hash map
     *
     * @author Jones Porsche
     */
    private void loadScenes() {
        scenes = new HashMap<String, Scene>();
        scenes.put(MENU_VIEW, new Scene(MenuViewController.getInstance().getMenuRootView(), WINDOW_WIDTH, WINDOW_HEIGHT));
        scenes.put(GAME_VIEW, new Scene(this.gameViewController.getMenuRootView(), WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    /**
     * Loads the start view to the primary stage. Is usually the menu view.
     *
     * @author Jones Porsche
     */
    private void setStartView() {
        //rootPane = scenes.get(MENU_VIEW).getRoot( );
    }

    /**
     * Starts the game instance and controller and adds the game view to the scenes hash map
     *
     * @author Jones Porsche
     */
    public void startGame() {
        // TODO Start the Game and GameController instances here
        // game = new Game();
        // gameController = new GameController(game);
        // TODO Put the Game view in the scenes hash map
        // scenes.put(GAME_VIEW, gameController.getGameDisplayPane());
    }

    private boolean keyEventIsBinded = false;

    /**
     * Switches the scene of the primary stage based on the received name of the new scene
     *
     * @param scene String with the name of the scene
     * @author Jones Porsche
     */
    public void switchScene(String scene) {
        switch (scene) {
            case GAME_VIEW:
                Scene newScene = scenes.get(scene); //new Scene(rootPane, WINDOW_WIDTH, WINDOW_HEIGHT);
                newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                primaryStage.setScene(newScene);
                Main.gameManager.loadLevelFromSong(PlaylistManager.getInstance().getSelectedSongPath());
                newScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent t) {
                        if (gameManager.getPlayingStateProperty().getValue() == GamePlayingState.PLAY){
                            if (t.getCode() == KeyChoiceManager.getInstance().getMoveDown()) {
                                gameManager.playerGoDown();
                            }
                            if (t.getCode() == KeyChoiceManager.getInstance().getMoveUp()) {
                                gameManager.playerGoUp();
                            }
                        }
                    }
                });
                break;

            case MENU_VIEW:
                this.scene = null;
                primaryStage.setScene(scenes.get(scene)/*.getScene()*/);
            }
        }



    private void loadProperties() {
        try {
            defaultProp = new Properties();
            FileInputStream finDefault = new FileInputStream("./properties/default.xml");
            defaultProp.loadFromXML(finDefault);

            appProp = new Properties(defaultProp);
            FileInputStream finIndividual = new FileInputStream("./properties/individual.xml");
            appProp.loadFromXML(finIndividual);

            String moveUp = appProp.getProperty("move-up");
            String moveDown = appProp.getProperty("move-down");

            KeyChoiceManager.getInstance().setMoveUp(KeyCode.valueOf(moveUp));
            KeyChoiceManager.getInstance().setMoveDown(KeyCode.valueOf(moveDown));

        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public GameManager getGameManger() {
        return this.gameManager;
    }

    public void defineGameDisplayPane(GameDisplay gameDisplay) {
        if (this.gameManager != null);
          //  this.gameManager.declareGameDisplayPane(gameDisplay);
    }

    @Override
    public void stop() {

        appProp.put("move-up", KeyChoiceManager.getInstance().getMoveUp().getName().toUpperCase());
        appProp.put("move-down", KeyChoiceManager.getInstance().getMoveDown().getName().toUpperCase());

        try {
            FileOutputStream fos = new FileOutputStream("./properties/individual.xml");
            appProp.storeToXML(fos, "Individual Game Key Controls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }
}
