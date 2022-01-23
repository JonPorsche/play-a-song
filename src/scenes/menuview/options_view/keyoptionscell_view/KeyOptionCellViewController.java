package scenes.menuview.options_view.keyoptionscell_view;

import business.service.KeyChoiceManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import scenes.menuview.MenuViewController;
import scenes.menuview.options_view.OptionsViewController;
import scenes.menuview.options_view.infotext_view.InfoTextViewController;

import static scenes.menuview.options_view.infotext_view.InfoTextView.*;

public class KeyOptionCellViewController {
    private Pane keyOptionRootView;
    private KeyOptionCellView keyOptionCellView;
    private Label functionName;
    private Button keySelectionBtn;
    private SimpleObjectProperty<KeySelectionBtnStatus> keySelectionBtnStatus;
    private InfoTextViewController infoTextViewController;
    private SimpleObjectProperty<KeyCode> moveUp;
    private SimpleObjectProperty<KeyCode> moveDown;
    private SimpleStringProperty buttonLabel;

    public KeyOptionCellViewController(String functionName, String buttonLabel, InfoTextViewController infoTextViewController) {
        this.infoTextViewController = infoTextViewController;
        this.buttonLabel = new SimpleStringProperty();
        this.buttonLabel.setValue(buttonLabel);
        keyOptionCellView = new KeyOptionCellView(functionName, this.buttonLabel.get());
        this.functionName = keyOptionCellView.functionName;
        initialize();
    }

    private void initialize() {
        keySelectionBtn = keyOptionCellView.keySelectionBtn;
        keyOptionRootView = keyOptionCellView;
        keySelectionBtnStatus = new SimpleObjectProperty<>(KeySelectionBtnStatus.DEFAULT);
        moveUp = new SimpleObjectProperty<>();
        moveUp.bindBidirectional(KeyChoiceManager.getInstance().moveUpProperty());
        moveDown = new SimpleObjectProperty<>();
        moveDown.bindBidirectional(KeyChoiceManager.getInstance().moveDownProperty());
        handleKeySelectBtnClick();
        handleButtonLabelChange();
        handleKeySelectionBtnStatusChanges();
    }

    private void handleKeySelectBtnClick(){
        keySelectionBtn.setOnAction(event -> {
            switch (keySelectionBtnStatus.get()){
                case DEFAULT:
                    keySelectionBtnStatus.setValue(KeySelectionBtnStatus.PRESSED);
                    infoTextViewController.switchInfoText(PRESS_KEY);
                    OptionsViewController.getInstance().captureKey(functionName.getText(), keySelectionBtn.getScene());
                    break;
                case PRESSED:
                    keySelectionBtnStatus.setValue(KeySelectionBtnStatus.DEFAULT);
                    infoTextViewController.switchInfoText(ASSIGN_KEY_ACTIONS);
                    break;
            }
        });
    }

    private void handleButtonLabelChange(){
        buttonLabel.addListener((observable, oldValue, newValue) -> keySelectionBtn.setText(newValue));
    }

    private void handleKeySelectionBtnStatusChanges(){
        keySelectionBtnStatus.addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case DEFAULT:
                    MenuViewController.switchBtnStyle(keySelectionBtn,"outlined-btn-focused","outlined-btn");
                    break;
                case PRESSED:
                    MenuViewController.switchBtnStyle(keySelectionBtn,"outlined-btn","outlined-btn-focused");
                    break;
            }
        });
    }

    public String getButtonLabel() {
        return buttonLabel.get();
    }

    public SimpleStringProperty buttonLabelProperty() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel.set(buttonLabel.toUpperCase());
    }

    public KeySelectionBtnStatus getKeySelectionBtnStatus() {
        return keySelectionBtnStatus.get();
    }

    public SimpleObjectProperty<KeySelectionBtnStatus> keySelectionBtnStatusProperty() {
        return keySelectionBtnStatus;
    }

    public void setKeySelectionBtnStatus(KeySelectionBtnStatus keySelectionBtnStatus) {
        this.keySelectionBtnStatus.set(keySelectionBtnStatus);
    }

    public Pane getKeyOptionRootView() {
        return keyOptionRootView;
    }
}
