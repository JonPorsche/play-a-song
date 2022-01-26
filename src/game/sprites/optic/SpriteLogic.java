package game.sprites.optic;



import game.sprites.optic.SpriteCircle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.ImagePattern;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SpriteLogic extends SpriteCircle {
    public List<ImagePattern>imagePatterns = new ArrayList<>();
    public SimpleBooleanProperty  isUsed;
    public SimpleBooleanProperty isVisabile;
    public SimpleIntegerProperty animationIndex;
    public double gamespeed;
    public double points;
    public double pointsModifer;
    public double score;
    public double scoreModifer;
    public int x;
    public int y;
    public File sound;

    public SpriteLogic(int xPos, int yPos) {
        isVisabile = new SimpleBooleanProperty();
        isUsed = new SimpleBooleanProperty();
        animationIndex = new SimpleIntegerProperty(0);
        isVisabile.set(false);
        isVisabile.addListener((oSt,oVis, newVis)-> animtionTimer(newVis));
        animationIndex.addListener((oSt,oVis, newVis)-> setImageContent());
    }

    public void animtionTimer(Boolean newVis) {
        isVisabile.set(newVis);
        if(newVis) {
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    while (isVisabile.getValue() == true) {
                        if (animationIndex.getValue() < imagePatterns.size() - 1) {
                            animationIndex.setValue(animationIndex.getValue() + 1);
                        } else {
                            animationIndex.set(0);
                        }
                        setImageContent();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    // code goes here.
                }
            });
            t1.start();
        }

    }

    public void setImagePatterns(ImagePattern imagePattern){
        this.imagePatterns.add(imagePattern);

    }
    public void setImageContent() {
        this.setFill(imagePatterns.get(animationIndex.getValue()));

    }
    public double getGamespeed() {
        return gamespeed;
    }


}
