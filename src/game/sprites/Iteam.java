package game.sprites;

import game.sprites.basic.SpriteCircle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Iteam extends SpriteCircle {
  public List<Image>images = new ArrayList<>();
  public SimpleBooleanProperty  isUsed;
  public SimpleBooleanProperty isVisabile;
  public SimpleIntegerProperty animationIndex;
  public int x;
  public int y;

  public Iteam(int xPos,int yPos) {
    isVisabile = new SimpleBooleanProperty();
    isUsed = new SimpleBooleanProperty();
    animationIndex = new SimpleIntegerProperty(0);
    isVisabile.set(false);
    isVisabile.addListener((oSt,oVis, newVis)-> animtionTimer(newVis));
  }
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
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
  public void setImagePatterns( Image imagePattern){
    this.images.add(imagePattern);

  }
  public void setIsVisabile(Boolean isVisable){
    isVisabile.set(isVisable);

  }


  public Image getImageContent() {
    return images.get(animationIndex.getValue());

  }
 
}
