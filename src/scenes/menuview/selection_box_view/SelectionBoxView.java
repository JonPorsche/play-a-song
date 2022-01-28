package scenes.menuview.selection_box_view;

import application.Main;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import scenes.menuview.selection_box_view.bottom_view.BottomViewController;
import scenes.menuview.selection_box_view.center_view.message_view.MessageViewController;
import scenes.menuview.selection_box_view.top_view.TopViewController;

import java.util.HashMap;
import java.util.Map;

public class SelectionBoxView extends BorderPane {
  public static final double SELECTION_BOX_WIDTH = Main.WINDOW_WIDTH * 0.4592;
  public static final double SELECTION_BOX_HEIGHT = Main.WINDOW_HEIGHT * 0.8875;
  public static final double SELECTION_BOX_R_L_MARGINS = 18;
  protected Map<String, Pane> selectionBoxViews;
  protected Pane topView;
  protected Pane centerView;
  protected Pane bottomView;

  public SelectionBoxView() {
    selectionBoxViews = new HashMap<String, Pane>();
    setSelectionBoxStyle();
    loadSelectionBoxViews();

    topView = selectionBoxViews.get("TopView");
    centerView = selectionBoxViews.get("MessageView");
    bottomView = selectionBoxViews.get("BottomView");

    this.setTop(topView);
    this.setCenter(centerView);
    this.setBottom(bottomView);
  }

  private void setSelectionBoxStyle() {
    this.setId("selection-box");
    this.setMinSize(SELECTION_BOX_WIDTH, SELECTION_BOX_HEIGHT);
    this.setMaxSize(SELECTION_BOX_WIDTH, SELECTION_BOX_HEIGHT);

    HBox.setMargin(this, new Insets(
    Main.WINDOW_HEIGHT * 0.05625,
    Main.WINDOW_WIDTH * 0.0666,
    Main.WINDOW_HEIGHT * 0.05625,
    0)
    );
  }

  private void loadSelectionBoxViews() {
    selectionBoxViews.put("TopView", TopViewController.getInstance().getTopRootView());
    selectionBoxViews.put("MessageView", MessageViewController.getInstance().getMessageRootView());
    selectionBoxViews.put("BottomView", BottomViewController.getInstance().getBottomRootView());
  }
}
