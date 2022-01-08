package application;

import com.sun.javafx.css.Size;
import gamelogic.Game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import scenes.gameview.GameController;

import static java.lang.Boolean.TRUE;
import static javafx.scene.SceneAntialiasing.BALANCED;


public class Main extends Application {
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 600;

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
    }

    public static void main(String[] args) {
        launch(args);
    }
}