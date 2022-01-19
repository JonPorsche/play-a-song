package business.service;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.KeyCode;

public class KeyChoiceManager {
    private static KeyChoiceManager INSTANCE = new KeyChoiceManager();
    private SimpleObjectProperty<KeyCode> moveUp;
    private SimpleObjectProperty<KeyCode> moveDown;

    private KeyChoiceManager() {
        moveUp = new SimpleObjectProperty<>(KeyCode.UP);
        moveDown = new SimpleObjectProperty<>(KeyCode.DOWN);
    }

    public static KeyChoiceManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new KeyChoiceManager();
        }
        return INSTANCE;
    }

    public KeyCode getMoveUp() {
        return moveUp.get();
    }

    public SimpleObjectProperty<KeyCode> moveUpProperty() {
        return moveUp;
    }

    public void setMoveUp(KeyCode moveUp) {
        this.moveUp.set(moveUp);
    }

    public KeyCode getMoveDown() {
        return moveDown.get();
    }

    public SimpleObjectProperty<KeyCode> moveDownProperty() {
        return moveDown;
    }

    public void setMoveDown(KeyCode moveDown) {
        this.moveDown.set(moveDown);
    }
}
