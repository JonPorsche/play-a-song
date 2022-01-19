package game;

import application.Main;
import game.sprites.Coin;
import game.sprites.Iteam;
import game.sprites.PlayerCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static application.Main.*;


// @ToDo: SongMapGenerator, HashMap<int x, obj iteam>,

public class GameLevel {

  // ATTRIBUTE
  protected String levelSourceTrack;
  protected List<Double> mapChunks = new ArrayList<>();
  protected HashMap<Number, Iteam> sortedItemsByPosX = new HashMap<>(); // +-10
  protected PlayerCharacter playerSpritesObject = new PlayerCharacter();
  protected double maxAmplitude = 0;
  private String track;

  // PROPERTYS
  /*protected IntegerProperty playerPosX = new SimpleIntegerProperty();
  protected IntegerProperty playerPosY = new SimpleIntegerProperty();
  protected FloatProperty gameSpeed = new SimpleFloatProperty(1);

  // PROPERTYS - POINTER
  protected ObjectProperty<GamePlayingState> gamePlayingStatePropPointer;
  protected ObjectProperty<Boolean> gameIsRunningPropPointer;
  protected ObjectProperty<Double> gamePlayerPosPropPointer;
  protected ObjectProperty<Number> gamePlayerScorePropPointer;*/

  public int playerPosX = 500;
  public int playerPosY = 350;
  public int playerRadius = 0;
  public float gameSpeed = 1;
  public double gamePlayerPos = 0.0;
  public int gamePlayerScore = 0;
  private List<Double> upperBound;
  private List<Double> bottomBound;

  public GameLevel( String newLevelSongPath ) {
    this.levelSourceTrack = newLevelSongPath;

    this.generateMapChunks( );
  }

  public PlayerCharacter getPlayerSpritesObject(){
    return playerSpritesObject;
  }

  private void generateMapChunks( ) {
    if (this.levelSourceTrack == null) return;

    String levelSourceTrackPath = this.levelSourceTrack;
    List<Double> mapChunks = this.mapChunks;

    /*new Thread( ) {
      @Override
      public void run( ) {*/

        for (double curAmplValue : new Audioinfo( levelSourceTrackPath ).getLeft( )) {
          if (maxAmplitude < curAmplValue) maxAmplitude = curAmplValue;
          mapChunks.add(curAmplValue);
        }


      /*}
    }.start( );*/
  }

  public List<Iteam> iteamsInDangerZoner() {
    List<Iteam> allResults = new ArrayList<>( );

    for (int areaIndex = this.playerPosX - PLAYER_RADIUS; areaIndex < this.playerPosX + PLAYER_RADIUS; areaIndex++) {
      Iteam result = sortedItemsByPosX.get( areaIndex );

      if (result != null) allResults.add( result );
    }

    return allResults;
  }

  public List<Iteam> iteamVisbill(int startX){
    int levelLength = 1000;
    int curentX;
    List<Iteam> allResults = new ArrayList<>( );
    for (curentX = startX;curentX <= levelLength+startX; curentX++) {
      Iteam result = sortedItemsByPosX.get(curentX);

      if (result != null) allResults.add( result );
    }

    return allResults;
  }



  public List<Double> getMapChunks() { return this.mapChunks; }
  public int getMapChunk( int chunkIndex ) { return this.mapChunks.get( chunkIndex ).intValue( ); }
  public HashMap<Number, Iteam> getSortedItems( ) { return this.sortedItemsByPosX; }
  public Iteam getItemFromPosX( int posX ) { return this.sortedItemsByPosX.get( posX ); }
  public PlayerCharacter getPlayerSprite( ) { return this.playerSpritesObject; }
  public double getMaxAmplitude( ) { return this.maxAmplitude; }

  public int getMapPixelWidth( ) {
    return this.mapChunks.size() * Main.MAP_CHUNK_WIDTH_PX;
  }



  public void setIteam(Iteam iteam) {
   putiteam((int) iteam.getX(),iteam);
  }

  public void setCoin(int x, int y) {
    Coin coin = new Coin(x,y);
    putiteam(x, coin);
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

  //@TODO getUpperLevelvalues
  public double getUpperBoarder(double x) {

    return upperBound.get((int) x) ;
  }
  //@TODO getDownLevelvalues
  public double getDownBoarder(double x) {

    return bottomBound.get((int) x);
  }

  public void setSong(String newLevelSongPath) {
    track = newLevelSongPath;

  }
  public String getSong(){
    return track;

  }
}