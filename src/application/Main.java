package application;

import com.sun.javafx.css.Size;
import gamelogic.Game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scenes.gameview.GameController;
import scenes.menuview.MenuViewController;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.TRUE;
import static javafx.scene.SceneAntialiasing.BALANCED;


public class Main extends Application {
    public static final int WINDOW_WIDTH = 1080;
    public static final int WINDOW_HEIGHT = 640;

    Game game;
    Map<String, Pane> scenes;
    Pane rootPane;
    Stage primaryStage;
    Scene scene;

    // CONTROLLERS
    MenuViewController menuViewController;
    GameController gameController;

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) {
        menuViewController = new MenuViewController(this);

        loadScenes();
        setStartView();

        scene = new Scene(rootPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Play a Song");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();

/*		try {
			GameController controller = new GameController(game);
			Scene scene = new Scene(controller.getGameDisplayPane( ),Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT,Boolean.TRUE, BALANCED);
			scene.getAntiAliasing();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent t) {
					System.out.println("worked");
					if(t.getCode() == KeyCode.ENTER){
						controller.getPlayerObject().updateHeigt(-30);

					}


				}
			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
    }

    /**
     * Loads the scenes into a hash map
     *
     * @author Jones Porsche
     */
    private void loadScenes() {
        scenes = new HashMap<String, Pane>();
        scenes.put("MenuView", menuViewController.getMenuRootView());
    }

    /**
     * Loads the start view to the primary stage. Is usually the menu view.
     *
     * @author Jones Porsche
     */
    private void setStartView() {
        rootPane = scenes.get("MenuView");
    }

    /**
     * Starts the game instance and controller and adds the game view to the scenes hash map
     * @author Jones Porsche
     * @see GameController
     */
    public void startGame() {
        game = new Game();
        gameController = new GameController(game);
        scenes.put("GameView", gameController.getGameDisplayPane());
    }

    /** Switches the scene of the primary stage based on the received name of the new scene
     * @param scene String with the name of the scene
     * @author Jones Porsche
     */
    public void switchScene(String scene) {
        switch (scene) {
            case "GameView":
                rootPane = scenes.get(scene);
                Scene newScene = new Scene(rootPane, WINDOW_WIDTH, WINDOW_HEIGHT);
                primaryStage.setScene(newScene);

                // TODO Handle this event in the game controller?
                try {
                    newScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

                        @Override
                        public void handle(KeyEvent t) {
                            if (t.getCode() == KeyCode.ENTER) {
                                gameController.getPlayerObject().updateHeigt(-30);
                            } else if (t.getCode() == KeyCode.ESCAPE) {
                                switchScene("MenuView");
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "MenuView":
                primaryStage.setScene(scenes.get(scene).getScene());
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
