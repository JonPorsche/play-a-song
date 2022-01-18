import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class MainKeyTest extends Application {
    private HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();

    @Override
    public void start(Stage stage) throws Exception {
        final Scene scene = new Scene(new Group(), 100, 100);
        stage.setScene(scene);

        scene.setOnKeyPressed(event -> {
            String codeString = event.getCode().toString();
            if (!currentlyActiveKeys.containsKey(codeString)) {
                currentlyActiveKeys.put(codeString, true);
            }
        });
        scene.setOnKeyReleased(event ->
                currentlyActiveKeys.remove(event.getCode().toString())
        );

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (removeActiveKey("LEFT")) {
                    System.out.println("left");
                }

                if (removeActiveKey("RIGHT")) {
                    System.out.println("right");
                }

                if (removeActiveKey("UP")) {
                    System.out.println("up");
                }

                if (removeActiveKey("DOWN")) {
                    System.out.println("down");
                }
            }
        }.start();

        stage.show();
    }

    private boolean removeActiveKey(String codeString) {
        Boolean isActive = currentlyActiveKeys.get(codeString);

        if (isActive != null && isActive) {
            currentlyActiveKeys.put(codeString, false);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
