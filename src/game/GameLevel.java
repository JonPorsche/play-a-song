package game;

import game.sprites.Coin;
import game.sprites.Iteam;
import game.sprites.PlayerCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static application.Main.PLAYER_RADIUS;


// @ToDo: SongMapGenerator, HashMap<int x, obj iteam>,

public class GameLevel {

  // ATTRIBUTE
  protected String levelSourceTrack;
  protected List<Double> mapChunks = new ArrayList<>();
  protected HashMap<Number, Iteam> sortedItemsByPosX = new HashMap<>(); // +-10
  protected PlayerCharacter playerSpritesObject = new PlayerCharacter();
  protected double maxAmplitude = 0;

  // PROPERTYS
  /*protected IntegerProperty playerPosX = new SimpleIntegerProperty();
  protected IntegerProperty playerPosY = new SimpleIntegerProperty();
  protected FloatProperty gameSpeed = new SimpleFloatProperty(1);

  // PROPERTYS - POINTER
  protected ObjectProperty<GamePlayingState> gamePlayingStatePropPointer;
  protected ObjectProperty<Boolean> gameIsRunningPropPointer;
  protected ObjectProperty<Double> gamePlayerPosPropPointer;
  protected ObjectProperty<Number> gamePlayerScorePropPointer;*/

  public int playerPosX = 0;
  public int playerPosY = 0;
  public int playerRadius = 0;
  public float gameSpeed = 0;
  public double gamePlayerPos = 0.0;
  public int gamePlayerScore = 0;

  public GameLevel( String newLevelSongPath ) {
    this.levelSourceTrack = newLevelSongPath;

    this.generateMapChunks( );
  }


  private void generateMapChunks() {
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
        System.out.println(mapChunks);

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



  public int getUpperBoarder(int x) {
    return x;
  }

  public int getDownBoarder(int x) {
    return x;
  }

  public void setIteam(Iteam iteam) {
   sortedItemsByPosX.put(iteam.getCenterX(),iteam);
  }

  public void setCoin(int x, int y) {
    Coin coin = new Coin(x,y);
    sortedItemsByPosX.put(x, coin);
  }
}