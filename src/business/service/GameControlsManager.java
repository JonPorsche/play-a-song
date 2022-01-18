package business.service;

import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class GameControlsManager {
    private static GameControlsManager INSTANCE = new GameControlsManager();
    private Map<String, KeyCode> gameControls;

    private GameControlsManager() {
        gameControls = new HashMap<>();
        gameControls.put("Move Up", KeyCode.UP);
        gameControls.put("Move Down", KeyCode.DOWN);
    }

    public static GameControlsManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameControlsManager();
        }
        return INSTANCE;
    }

    public Map<String, KeyCode> getGameControls() {
        return gameControls;
    }

    public void setGameControls(Map<String, KeyCode> gameControls) {
        this.gameControls = gameControls;
    }

    public void setGameControls(String functionName, KeyCode keyCode) {
        gameControls.put(functionName, keyCode);
    }
}
