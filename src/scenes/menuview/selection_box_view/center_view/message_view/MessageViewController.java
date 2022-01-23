package scenes.menuview.selection_box_view.center_view.message_view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MessageViewController {
    private Pane messageRootView;
    private MessageView messageView;
    private Label message;

    public MessageViewController(){
        messageView = new MessageView();
        this.message = messageView.message;
        messageRootView = messageView;
        initialize();
    }

    public void initialize(){}

    public Pane getMessageRootView() {
        return messageRootView;
    }
}
