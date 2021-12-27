package application;

import gamelogic.Game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import scene.GameController;


public class Main extends Application {
	
	private Game game;
	
	@Override
	public void init() throws Exception {
		super.init();
		game = new Game();
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			GameController controller = new GameController(game);
			Scene scene = new Scene(controller.getRoot(),1000,600);
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
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
