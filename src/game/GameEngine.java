package game;


// @ToDo: RandomItemGenerator, PlayerMovement(Up/Down), updateGuiCanvas,

import application.Main;
import game.sprites.Iteam;
import game.sprites.PlayerCharacter;
import game.sprites.SlowMoIteam;
import game.sprites.SpeedIteam;
import javafx.beans.property.*;
import uicomponents.game.GameDisplay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import static game.GameIteam.getRandom;

public class GameEngine {

  private GameDisplay gameDisplaySelector;

  // PROPERTYS
  protected IntegerProperty playerPosX = new SimpleIntegerProperty();
  protected IntegerProperty playerPosY = new SimpleIntegerProperty();
  protected DoubleProperty playerRadius= new SimpleDoubleProperty();
  protected FloatProperty gameSpeed = new SimpleFloatProperty(1);
  protected ConcurrentHashMap<Number, Iteam> vissableIteams= new ConcurrentHashMap<>();
  protected HashMap<Number, Iteam> KnockableIteams= new HashMap<>();
  protected HashMap<Number, Iteam> Iteams= new HashMap<>();
  protected  IntegerProperty gameWidth = new SimpleIntegerProperty(1000);
  protected  int lastiteamX =0;



  // PROPERTYS - POINTER
  protected ObjectProperty<GamePlayingState> gamePlayingStatePropPointer;
  protected ObjectProperty<Boolean> gameIsRunningPropPointer;
  protected ObjectProperty<GameLevel> gameLoadedLevelPropPointer;
  protected ObjectProperty<Double> gamePlayerPosPropPointer;
  protected ObjectProperty<Number> gamePlayerScorePropPointer;


  public GameEngine(
      ObjectProperty<GamePlayingState> playingStatePrPo, // <- GameManager.gamePlayingState
      ObjectProperty<Boolean> isRunningPrPo, // <- GameManager.gameIsRunning
      ObjectProperty<GameLevel> loadedLevelPrPo, // <- GameManager.gameLoadedLevel
      ObjectProperty<Double> playerPosPrPo, // <- GameManager.gamePlayerPos
      ObjectProperty<Number> playerScorePrPo // <- GameManager.gamePlayerScore
  ) {
    // Declare PropertyPointers
    this.gamePlayingStatePropPointer = playingStatePrPo;
    this.gameIsRunningPropPointer = isRunningPrPo;
    this.gameLoadedLevelPropPointer = loadedLevelPrPo;
    this.gamePlayerPosPropPointer = playerPosPrPo;
    this.gamePlayerScorePropPointer = playerScorePrPo;


    this.bindInternPropertyComputing( );
  }

  public boolean isReady( ) {
    return this.gamePlayingStatePropPointer != null
    &&     this.gameIsRunningPropPointer != null
    &&     this.gameLoadedLevelPropPointer != null
    &&     this.gameLoadedLevelPropPointer.getValue( ) != null
    &&     this.gamePlayerPosPropPointer != null
    &&     this.gamePlayerScorePropPointer != null;
  }

  public void declareGameDisplayPane( GameDisplay guiGameDisplaySelector) {
    this.gameDisplaySelector = guiGameDisplaySelector;
  }

  public void setNewLevel( GameLevel gL ) {
    // Update PropertyValues

    this.gameLoadedLevelPropPointer.setValue( gL );
    this.gamePlayingStatePropPointer.setValue( GamePlayingState.NOTREADY );
    this.gamePlayerPosPropPointer.setValue( gL.gamePlayerPos );
    this.gamePlayerScorePropPointer.setValue( gL.gamePlayerScore );
    this.playerPosX.setValue( gL.playerPosX );
    this.playerPosY.setValue( gL.playerPosY );
    this.setGameIteams();
    this.Iteams= gameLoadedLevelPropPointer.getValue().getSortedItems();


    if (this.gameLoadedLevelPropPointer.getValue( ).mapChunks.size() > 100 )
      this.gamePlayingStatePropPointer.setValue( GamePlayingState.READY );
  }

  private void startEngine( ) {
    GameEngine gE = this;

    new Thread( ) {
      @Override
      public void run( ) {
        if (!gE.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PLAY )) return;

        do {
          try {
            Thread.sleep( 16,666 );
            //gE.worldSlideNext( );
            gE.gamePlayerPosPropPointer.setValue(
              gE.gamePlayerPosPropPointer.getValue( ) + ( Main.WINDOW_WIDTH / 180) // 360FPS pro Sichtfeldbreite
            );

          } catch (InterruptedException e) {
            e.printStackTrace( );
          }
        } while (
            gE.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PLAY )
        );

      }
    }.start( );

    /*while (!this.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.FINISHED )) {
      try {
        Thread.sleep( 5 );//17,5 );

        if (this.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PLAY )) {
          this.worldSlideNext( );
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }*/
  }

  public void startPlaying( ) {
    if (this.isReady( ) && this.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.READY )) {
      this.gamePlayingStatePropPointer.setValue( GamePlayingState.PLAY );

      this.startEngine( );
    }
  }

  public void pausePlaying( ) {
    if (this.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PLAY ))
      this.gamePlayingStatePropPointer.setValue( GamePlayingState.PAUSE );
  }

  public void continuePlaying( ) {
    if (this.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PAUSE )) {
      this.gamePlayingStatePropPointer.setValue( GamePlayingState.PLAY );
      this.startEngine( );
    }
  }

  private void gameIsFinish( ) {
    this.gamePlayingStatePropPointer.setValue( GamePlayingState.FINISHED );
  }

  public GameLevel getPlayingLevel( ) { return this.gameLoadedLevelPropPointer.getValue( ); }

  private void worldSlideNext( ) {
    int curPlayerPosX = this.getGamePlayerPosProperty( ).getValue( ).intValue( );
    int gameSpeed = this.getGameSpeedProperty( ).getValue( ).intValue( );

    this.gamePlayerPosPropPointer.setValue(
        (double)(curPlayerPosX + ( 1 * gameSpeed )) // aktuelle Positon X + neue Position (inkl. SpeedMultiplikator)
    );
  }


  public void updateIteams(Double oPos, Double newPos){
    if(oPos != null) {
      updateVissableIteams(oPos, newPos);
      checkCollsion(oPos, newPos);
    }

  }

  private void checkCollsion(Double oPos, Double newPos) {
    double differncePos = newPos-oPos;
    for ( Number i :vissableIteams.keySet()) {
      if(KnockableIteams.containsKey(i)){
        Iteam iteam = KnockableIteams.get(i);
        Double radius = iteam.getRadius();
        if( iteam.getCenterX() +radius < 500 - playerRadius.getValue()&&  iteam.getCenterX() -radius < 500 + playerRadius.getValue()){
          gameDisplaySelector.removeIteam(iteam);
        }
      }

      Iteam iteam = vissableIteams.get(i);
      double x = iteam.getCenterX();


    }
    for (Number i :vissableIteams.keySet()){
      double x =vissableIteams.get(i).getCenterX();
      double rd =vissableIteams.get(i).getRadius();
      if(x-rd > 500+playerRadius.getValue()){
        return;}
      else if (x+rd < 500- playerRadius.getValue()){
        return;
      }
      else{
        KnockableIteams.put(i,vissableIteams.get(i));
      }


    }
    for (Number i :KnockableIteams.keySet()){
     if( voidIteamCollsion(KnockableIteams.get(i))){
       System.out.println("collsion");
     };

    }


  }

  public boolean voidIteamCollsion(Iteam iteam){
    double playerX = 500;
    double playerY = getPlayerPosYProperty().get();
    double playerRadiu = playerRadius.get();
    double distance = Math.sqrt(Math.pow(iteam.getCenterX() -playerX , 2) + (Math.pow(iteam.getCenterY() - playerY, 2)));
    if(distance <= (playerRadiu+ iteam.getRadius()) && distance >= Math.abs(playerRadiu -iteam.getRadius())){
      return true;
    };
    return false;
  }
  //@TODO punktKreisCollision
  public boolean mapCollsion(int x){
    return false;
  }

  public void updateVissableIteams(Double oPos, Double newPos){

    double differncePos = newPos-oPos;

    Iterator<Number> iterator;
    for (iterator = vissableIteams.keySet().iterator(); iterator.hasNext(); ) {
      Number i = iterator.next();
      Iteam iteam = vissableIteams.get(i);
      if (iteam != null) {
        Double radius = iteam.getRadius();
        iteam.setCenterX(iteam.getCenterX() - differncePos);
        iteam.updateSprite();
        if (i.doubleValue() + radius <= newPos - 500) {
          gameDisplaySelector.removeIteam(iteam);
        }
      }
    }
    //@TODO Do sprite


    for (int old = oPos.intValue(); old <= newPos.intValue() +1000; old++){
      if(Iteams.containsKey(old) && !(vissableIteams.containsKey(old))){
        Iteam iteam = Iteams.get(old);
        if (iteam != null) {
        vissableIteams.put(old,Iteams.get(old));
        iteam.setCenterX(iteam.getCenterX() -playerPosX.getValue());
        gameDisplaySelector.addIteam(iteam);
      }
      }
    }


  }

  public void setGameIteams(){
    int lengehtworld = gameLoadedLevelPropPointer.getValue().mapChunks.size();

    int coin = 0;
    int x;

    int iteamCricle = 10;
    Random ran = new Random();
    for ( x= ran.nextInt(1000)+1000; x <= lengehtworld; ){
      GameIteam iteam = getRandom();
      int y;
      y = ran.nextInt(gameLoadedLevelPropPointer.getValue().getUpperBoarder(x+100) - iteamCricle
              - (gameLoadedLevelPropPointer.getValue().getDownBoarder(x) + iteamCricle))
              + gameLoadedLevelPropPointer.getValue().getDownBoarder(x)+iteamCricle;

      gameLoadedLevelPropPointer.getValue().setIteam(getRandomIteam(x,y));
      x= x+ ran.nextInt(1000);
    }

    for ( int z =0; z<= lengehtworld;){
      int y =ran.nextInt(gameLoadedLevelPropPointer.getValue().getUpperBoarder(500)-iteamCricle
              -gameLoadedLevelPropPointer.getValue().getDownBoarder(200))
              + gameLoadedLevelPropPointer.getValue().getDownBoarder(200);
      gameLoadedLevelPropPointer.getValue().setCoin(z,y);
      z= z + ran.nextInt(100);
    }
  }

  private Iteam getRandomIteam(int x, int y) {

    GameIteam random = GameIteam.getRandom();
    switch (random) {
      case SLOW: return new SlowMoIteam(x,y);
      case SPEED:return new SpeedIteam(x,y);
    }
    return null;

  }

  private void bindInternPropertyComputing( ) {
    // Verknüpfte X-Position mit GUI-Leinwand
    this.gamePlayerPosPropPointer.addListener(
        (o, oPos, newPos) -> gameDisplaySelector.gameWorldPane.setCenterViewFrame( newPos.intValue( ))


    );
    this.gamePlayerPosPropPointer.addListener(
            (o, oPos, newPos) -> updateIteams(oPos,newPos));


    // Verknüpfte die EngineAttribute mit den GameLevelAttributen
    ObjectProperty<GameLevel> pLevelProp = this.gameLoadedLevelPropPointer;
    this.gamePlayerPosPropPointer.addListener( (o, oP, newPosition) -> pLevelProp.getValue( ).gamePlayerPos = newPosition );
    this.gamePlayerScorePropPointer.addListener( (o, oS, newScore) -> pLevelProp.getValue( ).gamePlayerScore = newScore.intValue( ) );
    this.playerPosX.addListener( (o, oP, newPosition) -> pLevelProp.getValue( ).playerPosX = newPosition.intValue( ) );
    this.playerPosY.addListener( (o, oP, newPosition) -> pLevelProp.getValue( ).playerPosY = newPosition.intValue( ) );
    this.playerRadius.addListener((o, oP, newPosition) -> pLevelProp.getValue( ).playerRadius = newPosition.intValue( ) );
  }

  // PROPERTYS
  public IntegerProperty getPlayerPosXProperty( ) { return this.playerPosX; }
  public IntegerProperty getPlayerPosYProperty( ) { return this.playerPosY; }
  public FloatProperty getGameSpeedProperty( ) { return this.gameSpeed; }

  // PROPERTYS - POINTER
  public ObjectProperty<Double> getGamePlayerPosProperty( ) { return this.gamePlayerPosPropPointer; }
  public ObjectProperty<Number> getGamePlayerScoreProperty( ) { return this.gamePlayerScorePropPointer; }
}
