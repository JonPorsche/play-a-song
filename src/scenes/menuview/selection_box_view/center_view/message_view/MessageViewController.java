package scenes.menuview.selection_box_view.center_view.message_view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MessageViewController {
    private static MessageViewController INSTANCE = new MessageViewController();
    private Pane messageRootView;
    private MessageView messageView;
    private Label message;

    private MessageViewController(){
        messageView = new MessageView();
        this.message = messageView.message;
        messageRootView = messageView;
        initialize();
    }

    public static MessageViewController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MessageViewController();
        }
        return INSTANCE;
    }

    public void initialize(){}

    public Pane getMessageRootView() {
        return messageRootView;
    }
}
