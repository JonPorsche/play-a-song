package game.sprites;

import game.sprites.basic.SpriteCircle;
import javafx.application.Platform;
import javafx.scene.paint.ImagePattern;
import java.util.ArrayList;
import java.util.List;

public class Iteam extends SpriteCircle {
  public List<ImagePattern>imagePatterns = new ArrayList<>();
  public boolean isUsed;
  int animationIndex = 0;

  public Iteam(int xPos,int yPos) {
    this.setRadius(10);
  }
  public void setCenterViewFrame( double playerPos ) {
    Platform.runLater(
            ( ) -> this.setTranslateX( 0 - playerPos )
    );
  }

  public void setImagePatterns( ImagePattern imagePattern){
    this.imagePatterns.add(imagePattern);

  }
  public void updateSprite(){
    if(animationIndex < imagePatterns.size()){
      animationIndex++;
    }else {
      animationIndex = 0;
    }
    setImageContent();
  }


  public void setImageContent() {

    this.setFill( imagePatterns.get(animationIndex));
  }
  public void setImageSerienContent(ImagePattern[] newStyles) { }
  public void setImageSerienContent(List<ImagePattern> newStyles) { }
}
