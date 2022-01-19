package scenes.menuview.optionsview;

import business.service.KeyChoiceManager;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import scenes.menuview.optionsview.infotextview.InfoTextViewController;
import scenes.menuview.optionsview.keyoptionscellview.KeyOptionCellViewController;
import scenes.menuview.optionsview.keyoptionscellview.KeySelectionBtnStatus;

import static scenes.menuview.optionsview.infotextview.InfoTextView.ASSIGN_KEY_ACTIONS;
import static scenes.menuview.optionsview.infotextview.InfoTextView.LOST_ASSIGNMENT;

public class OptionsViewController {
    private static OptionsViewController INSTANCE = new OptionsViewController();
    private Pane optionsRootView;
    private OptionsView optionsView;
    private Pane infoTextView;
    private InfoTextViewController infoTextViewController;
    private Pane moveUpView;
    private KeyOptionCellViewController moveUpViewController;
    private Pane moveDownView;
    private KeyOptionCellViewController moveDownViewController;
    private int keyPressCounter;

    private OptionsViewController(){
        initialize();
    }

    public static OptionsViewController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OptionsViewController();
        }
        return INSTANCE;
    }

    private void initialize() {
        optionsView = new OptionsView();
        infoTextView = optionsView.infoTextView;
        infoTextViewController = optionsView.infoTextViewController;
        moveUpView = optionsView.moveUpView;
        moveUpViewController = optionsView.moveUpViewController;
        moveDownView = optionsView.moveDownView;
        moveDownViewController = optionsView.moveDownViewController;
        optionsRootView = optionsView;
    }

    public void captureKey(String functionName, Scene scene){
        keyPressCounter = 0;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(keyPressCounter == 0) {
                    KeyCode keyCode = event.getCode();
                    switch (functionName){
                        case "Move Up":
                            KeyChoiceManager.getInstance().setMoveUp(keyCode);
                            moveUpViewController.setButtonLabel(keyCode.getName());
                            if(KeyChoiceManager.getInstance().getMoveDown() == keyCode){
                                KeyChoiceManager.getInstance().setMoveDown(KeyCode.UNDEFINED);
                                moveDownViewController.setButtonLabel(KeyChoiceManager.getInstance().getMoveDown().getName());
                                infoTextViewController.switchInfoText(LOST_ASSIGNMENT,"Move Down");
                            } else infoTextViewController.switchInfoText(ASSIGN_KEY_ACTIONS);
                            moveUpViewController.setKeySelectionBtnStatus(KeySelectionBtnStatus.DEFAULT);
                            keyPressCounter++;
                            break;
                        case "Move Down":
                            KeyChoiceManager.getInstance().setMoveDown(keyCode);
                            moveDownViewController.setButtonLabel(keyCode.getName());
                            if(KeyChoiceManager.getInstance().getMoveUp() == keyCode){
                                KeyChoiceManager.getInstance().setMoveUp(KeyCode.UNDEFINED);
                                moveUpViewController.setButtonLabel(KeyChoiceManager.getInstance().getMoveUp().getName());
                                infoTextViewController.switchInfoText(LOST_ASSIGNMENT, "Move Up");
                            } else infoTextViewController.switchInfoText(ASSIGN_KEY_ACTIONS);
                            moveDownViewController.setKeySelectionBtnStatus(KeySelectionBtnStatus.DEFAULT);
                            keyPressCounter++;
                            break;
                    }
                }
            }
        });
    }

    public Pane getOptionsRootView() {
        return optionsRootView;
    }
}
