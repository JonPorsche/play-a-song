package game.sprites;

import game.sprites.basic.SpriteCircle;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.ImagePattern;
import java.util.ArrayList;
import java.util.List;

public class Iteam extends SpriteCircle {
  public List<ImagePattern>imagePatterns = new ArrayList<>();
  public SimpleBooleanProperty  isUsed;
  public SimpleBooleanProperty isVisabile;
  public SimpleIntegerProperty animationIndex;

  public Iteam() {
    this.setRadius(10);
    isVisabile = new SimpleBooleanProperty();
    isUsed = new SimpleBooleanProperty();
    animationIndex = new SimpleIntegerProperty(0);
    isVisabile.set(false);
    isVisabile.addListener((oSt,oVis, newVis)-> animtionTimer(newVis));
    animationIndex.addListener((oSt,oVis, newVis)-> setImageContent());
  }

  private void animtionTimer(Boolean newVis) {

    Thread t1 = new Thread(new Runnable() {
      public void run()
      {
        while(isVisabile.getValue() == true){
         /* if(animationIndex.getValue() < imagePatterns.size()-1){
            animationIndex.setValue( animationIndex.getValue() +1);


          }else{
            animationIndex.set(0);
          }*/
          setImageContent();
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

        }
        // code goes here.
      }});
    t1.start();

  }
  public void setCenterViewFrame( double playerPos ) {
    Platform.runLater(
            ( ) -> this.setTranslateX( 0 - playerPos )
    );
  }

  public void setImagePatterns( ImagePattern imagePattern){
    this.imagePatterns.add(imagePattern);

  }
  public void setIsVisabile(Boolean isVisable){
    isVisabile.set(isVisable);

  }


  public void setImageContent() {

    this.setFill( imagePatterns.get(animationIndex.getValue()));
  }
  public void setImageSerienContent(ImagePattern[] newStyles) { }
  public void setImageSerienContent(List<ImagePattern> newStyles) { }
}
