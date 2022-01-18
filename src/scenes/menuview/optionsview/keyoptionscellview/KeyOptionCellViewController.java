package scenes.menuview.optionsview.keyoptionscellview;

import application.Main;
import business.service.GameControlsManager;
import business.service.KeyListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import scenes.menuview.optionsview.infotextview.InfoTextViewController;

import static scenes.menuview.optionsview.infotextview.InfoTextView.ASSIGN_KEY_ACTIONS;
import static scenes.menuview.optionsview.infotextview.InfoTextView.PRESS_KEY;

public class KeyOptionCellViewController {
    private Pane keyOptionRootView;
    private KeyOptionCellView keyOptionCellView;
    private Label functionName;
    private Button keySelectionBtn;
    private SimpleObjectProperty<KeySelectionBtnStatus> keySelectionBtnStatus;
    private InfoTextViewController infoTextViewController;
    private KeyListener keyListener;
    private Main application;
    private int keyPressCounter;

    public KeyOptionCellViewController(String functionName, InfoTextViewController infoTextViewController) {
        this.infoTextViewController = infoTextViewController;
        keyOptionCellView = new KeyOptionCellView(functionName);
        this.functionName = keyOptionCellView.functionName;
        initialize();
    }

    private void initialize() {
        keySelectionBtn = keyOptionCellView.keySelectionBtn;
        keyOptionRootView = keyOptionCellView;
        keySelectionBtnStatus = new SimpleObjectProperty<>(KeySelectionBtnStatus.DEFAULT);
        handleKeySelectBtnClick();
    }

    private void handleKeySelectBtnClick(){
        keySelectionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                keyPressCounter = 0;
                switch (keySelectionBtnStatus.get()){
                    case DEFAULT:
                        keySelectionBtnStatus.setValue(KeySelectionBtnStatus.PRESSED);
                        infoTextViewController.switchInfoText(PRESS_KEY);
                        getKey(functionName.getText());
                        break;
                    case PRESSED:
                        keySelectionBtnStatus.setValue(KeySelectionBtnStatus.DEFAULT);
                        infoTextViewController.switchInfoText(ASSIGN_KEY_ACTIONS);
                        break;
                }
                System.out.println("clicked " + functionName.getText());
            }
        });
    }

    private void getKey(String functionName){
        keySelectionBtn.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(keyPressCounter == 0) {
                    KeyCode keyCode = event.getCode();
                    keySelectionBtn.setText(keyCode.getName());
                    GameControlsManager.getInstance().setGameControls(functionName, keyCode);
                    System.out.println(GameControlsManager.getInstance().getGameControls());
                    keySelectionBtnStatus.setValue(KeySelectionBtnStatus.DEFAULT);
                    infoTextViewController.switchInfoText(ASSIGN_KEY_ACTIONS);
                    keyPressCounter++;
                    return;
                }
            }
        });
    }

    public Pane getKeyOptionRootView() {
        return keyOptionRootView;
    }
}
