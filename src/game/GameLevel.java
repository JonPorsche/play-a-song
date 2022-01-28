package game;

import application.Main;
import game.sprites.logic.Coin;
import game.sprites.logic.PlayerCharacter;
import game.sprites.basic.Iteam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static application.Main.*;

// @ToDo: SongMapGenerator, HashMap<int x, obj iteam>,
public class GameLevel {

  // ATTRIBUTE
  protected String levelSourceTrack;
  protected List<Double> mapChunks = new ArrayList<>();
  protected HashMap<Number, Iteam> sortedItemsByPosX = new HashMap<>();
  protected double maxAmplitude = 0;
  protected List<Double> upperBound;
  protected List<Double> bottomBound;

  public int playerPosX = 500;
  public int playerPosY = 350;
  public int playerRadius = 0;
  public double gamePlayerPos = 0.0;
  public int gamePlayerScore = 0;
  public int playerLife =3;

  public GameLevel( String newLevelSongPath ){
    this.levelSourceTrack = newLevelSongPath;
    this.generateMapChunks( );
  }

  private void generateMapChunks( ) {
    if (this.levelSourceTrack == null) return;

    String levelSourceTrackPath = this.levelSourceTrack;
    List<Double> mapChunks = this.mapChunks;

    for (double curAmplValue : new Audioinfo( levelSourceTrackPath ).getLeft( )) {
      if (maxAmplitude < curAmplValue) maxAmplitude = curAmplValue;
      mapChunks.add(curAmplValue);
    }
  }

  public List<Double> getMapChunks() { return this.mapChunks; }
  public HashMap<Number, Iteam> getSortedItems( ) { return this.sortedItemsByPosX; }
  public double getMaxAmplitude( ) { return this.maxAmplitude; }

  public int getMapPixelWidth( ) {
    return this.mapChunks.size() * Main.MAP_CHUNK_WIDTH_PX;
  }

  public void setIteam(Iteam iteam) {
   putiteam(iteam.getX(),iteam);
  }
  public void putiteam(int x, Iteam iteam){
    Boolean contains = false;
    while (contains == false) {

      int start = (int) (x - iteam.getRadius());
      for (int postion = start; postion <= x + iteam.getRadius(); postion++){
        if (sortedItemsByPosX.containsKey(postion))
          contains = true;
      }

      sortedItemsByPosX.put(x,iteam);
    }
  }

  public void setBottombound(List<Double> allXYBottomArray) {
    bottomBound = allXYBottomArray;
  }

  public void setUpperbound(List<Double> allXYUpperArray) {
    upperBound = allXYUpperArray;
  }

  public double getUpperBoarder(double x) {
    return upperBound.get((int) x) ;
  }

  public double getDownBoarder(double x) {
    return bottomBound.get((int) x);
  }

  public void setSong(String newLevelSongPath) {
    this.levelSourceTrack = newLevelSongPath;
  }

  public String getSong() {
    return this.levelSourceTrack;
  }
}