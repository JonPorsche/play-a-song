package application;

import gamelogic.Game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import scenes.gameview.GameController;
import scenes.menuview.MenuViewController;

import static com.sun.javafx.scene.control.skin.Utils.getResource;


public class Main extends Application {
	public static final int WINDOW_WIDTH = 1080;
	public static final int WINDOW_HEIGHT = 640;

	private Game game;

	// CONTROLLERS
	private MenuViewController menuViewController;

	@Override
	public void init() throws Exception {
		super.init();
		game = new Game();
	}

	@Override
	public void start(Stage primaryStage) {
		startControllers();

		try {
			Scene scene = new Scene(menuViewController.getRootView(), WINDOW_WIDTH, WINDOW_HEIGHT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.setTitle("Play a Song");
			primaryStage.show();
		} catch (Exception e){
			e.printStackTrace();
		}

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

	public static void main(String[] args) {
		launch(args);
	}
}
