package application;

import gamelogic.Game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scenes.gameview.GameController;
import scenes.menuview.MenuViewController;

import java.util.HashMap;
import java.util.Map;


public class Main extends Application {
    public static final int WINDOW_WIDTH = 1080;
    public static final int WINDOW_HEIGHT = 640;

    private Game game;
    private Map<String, Pane> scenes;
    private Pane rootPane;
    private Stage primaryStage;
    private Scene scene;

    // CONTROLLERS
    private MenuViewController menuViewController;
    private GameController gameController;

    @Override
    public void init() throws Exception {
        super.init();
        game = new Game();
    }

    @Override
    public void start(Stage primaryStage) {
            this.primaryStage = primaryStage;
            startControllers();
            loadScenes();
            setRootView();

            scene = new Scene(rootPane, WINDOW_WIDTH, WINDOW_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            switchToMenuView();

/*		try {
			GameController controller = new GameController(game);
			Scene scene = new Scene(controller.getGameDisplayPane(),Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
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

    private void startControllers() {
        menuViewController = new MenuViewController(this);
    }

    private void loadScenes() {
        scenes = new HashMap<String, Pane>();
        scenes.put("MenuView", menuViewController.getRootView());
    }

    private void setRootView() {
        rootPane = scenes.get("MenuView");
    }

    /**
     *
     */
    public void loadGame(){
        gameController = new GameController(game);
        scenes.put("GameView", gameController.getGameDisplayPane());
    }

    public void switchToGameView(String scene) {
        if (scenes.containsKey(scene)) {
            rootPane = scenes.get(scene);
            Scene newScene = new Scene(rootPane, WINDOW_WIDTH, WINDOW_HEIGHT);
            try {
                newScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent t) {
                        if(t.getCode() == KeyCode.ENTER){
                            gameController.getPlayerObject().updateHeigt(-30);
                        } else if(t.getCode() == KeyCode.ESCAPE){
                            switchToMenuView();
                        }
                    }
                });
            } catch(Exception e) {
                e.printStackTrace();
            }
            primaryStage.setScene(newScene);
            primaryStage.show();
        }
        else System.out.println("Incorrect view name or undefined view");
    }

    public void switchToMenuView(){
        primaryStage.setScene(scene);
        primaryStage.setTitle("Play a Song");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
