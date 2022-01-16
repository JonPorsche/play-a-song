package game;


// @ToDo: RandomItemGenerator, PlayerMovement(Up/Down), updateGuiCanvas,

import application.Main;
import game.sprites.Iteam;
import game.sprites.PlayerCharacter;
import game.sprites.SlowMoIteam;
import game.sprites.SpeedIteam;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import uicomponents.game.GameDisplay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class GameEngine {

  private GameDisplay gameDisplaySelector;

  // PROPERTYS
  //protected IntegerProperty playerPosX = new SimpleIntegerProperty();
  protected IntegerProperty playerPosY = new SimpleIntegerProperty();
  //protected DoubleProperty playerRadius= new SimpleDoubleProperty();*/
  protected PlayerCharacter player = new PlayerCharacter( );
  protected FloatProperty gameSpeed = new SimpleFloatProperty(1);
  protected ConcurrentHashMap<Number, Iteam> vissableIteams= new ConcurrentHashMap<>();
  protected HashMap<Number, Iteam> KnockableIteams= new HashMap<>();
  protected HashMap<Number, Iteam> Iteams= new HashMap<>();
  protected IntegerProperty gameWidth = new SimpleIntegerProperty(1000);
  protected int lastiteamX =0;



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

    guiGameDisplaySelector.declarePlayerCharacter( this.player );
  }

  public GameDisplay getGameDisplayPane( ) {
    return this.gameDisplaySelector;
  }

  public void setNewLevel( GameLevel gL ) {
    // Update PropertyValues

    this.gameDisplaySelector.initCanvas( gL.getMapPixelWidth( ) );
    this.gameLoadedLevelPropPointer.setValue( gL );
    this.gamePlayingStatePropPointer.setValue( GamePlayingState.NOTREADY );
    this.gamePlayerPosPropPointer.setValue( gL.gamePlayerPos );
    this.gamePlayerScorePropPointer.setValue( gL.gamePlayerScore );
    //this.playerPosX.setValue( gL.playerPosX );
    this.playerPosY.setValue( gL.playerPosY );

    /*this.setGameIteams();
    this.Iteams= gameLoadedLevelPropPointer.getValue().getSortedItems();
    this.setIteams();*/


    if (this.gameLoadedLevelPropPointer.getValue( ).mapChunks.size() > 100 )
      this.gamePlayingStatePropPointer.setValue( GamePlayingState.READY );
  }

  private void startEngine( ) {
    GameEngine gE = this;
    if (!gE.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PLAY )) return;

    new Thread( ) {
      @Override
      public void run( ) {

        do {
          try {
            Thread.sleep( 16,666 );
            this.worldSlideNext( );
            /*gE.gamePlayerPosPropPointer.setValue(

            );*/

          } catch (InterruptedException e) {
            e.printStackTrace( );
          }
        } while (
            gE.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PLAY )
        );

      }

      private void worldSlideNext( ) {
        int curPlayerPosX = gE.getGamePlayerPosProperty( ).getValue( ).intValue( );
        int gameSpeed = gE.getGameSpeedProperty( ).getValue( ).intValue( );
        int defaultPosDistance = ( Main.WINDOW_WIDTH / 180); // 360Frames pro Sichtfeldbreite

        gE.gamePlayerPosPropPointer.setValue( (double)(
          curPlayerPosX + ( defaultPosDistance * gameSpeed )
        ) ); // aktuelle Positon X + neue Position (inkl. SpeedMultiplikator)
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

  /*private void worldSlideNext( ) {
    int curPlayerPosX = this.getGamePlayerPosProperty( ).getValue( ).intValue( );
    int gameSpeed = this.getGameSpeedProperty( ).getValue( ).intValue( );
    int defaultPosDistance = ( Main.WINDOW_WIDTH / 180); // 360Frames pro Sichtfeldbreite

    this.gamePlayerPosPropPointer.setValue( (double)(
      curPlayerPosX + ( defaultPosDistance * gameSpeed )
    ) ); // aktuelle Positon X + neue Position (inkl. SpeedMultiplikator)
  }*/


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
        if( iteam.getCenterX() +radius < getPlayerPosYProperty().get() - Main.PLAYER_RADIUS ){
         KnockableIteams.remove(iteam);
        }
      }
    }
    for (Number i :vissableIteams.keySet()){
      double x =vissableIteams.get(i).getCenterX();
      double rd =vissableIteams.get(i).getRadius();
      if (x-rd > gamePlayerPosPropPointer.get() + Main.PLAYER_RADIUS){
        }
      else if (x+rd < gamePlayerPosPropPointer.get()- Main.PLAYER_RADIUS){ }
      else {
        KnockableIteams.put(i,vissableIteams.get(i));
        System.out.println("knockable");
      }
    }
    for (Number i :KnockableIteams.keySet()){
     if( voidIteamCollsion(KnockableIteams.get(i))){
       System.out.println("collsion");
       //@TODO Make collsion
       KnockableIteams.get(i).collision();
       KnockableIteams.remove(KnockableIteams.get(i));
       gameDisplaySelector.gameWorldIteams.removeIteam(KnockableIteams.get(i));
     };
    }
  }

  public void setIteams() {
    for (Number i :Iteams.keySet()){
     // gameDisplaySelector.addIteam(Iteams.get(i));
    }
  }

  public boolean voidIteamCollsion(Iteam iteam){
    double playerX = gamePlayerPosPropPointer.get();
    double playerY = Main.WINDOW_HEIGHT/2;
    double playerRadiu = Main.PLAYER_RADIUS;
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
        if (i.doubleValue() + radius <= newPos - 500) {
          iteam.setIsVisabile(false);
          gameDisplaySelector.removeIteam(iteam);
        }
      }
    }
    //@TODO Do sprite
    for (int old = oPos.intValue(); old <= newPos.intValue() +1500; old++){
      if(Iteams.containsKey(old) && !(vissableIteams.containsKey(old))){
        Iteam iteam = Iteams.get(old);
        if (iteam != null) {
        int index = old;
        gameDisplaySelector.gameWorldIteams.addIteam(iteam);
        vissableIteams.put(index,Iteams.get(old));
        iteam.setIsVisabile(true);
        }
      }
    }
  }

  /*private int getRandomYPosInBounds( double posX ) {

  }*/

  public void setGameIteams(){
    int worldPixelLength = gameLoadedLevelPropPointer.getValue().getMapPixelWidth();

    int coin = 0;
    int x;
    int iteamCricle = 10;

    Random ran = new Random( );
    for ( x= ran.nextInt(1000)+1000; x <= worldPixelLength; ){

      double y;
      y = ran.nextInt((int) (gameLoadedLevelPropPointer.getValue().getUpperBoarder(500) - iteamCricle
                    - (gameLoadedLevelPropPointer.getValue().getDownBoarder(200) + iteamCricle)))
              + gameLoadedLevelPropPointer.getValue().getDownBoarder(200)+iteamCricle;

      gameLoadedLevelPropPointer.getValue().setIteam(getRandomIteam(x, (int) y));
      x= x+ ran.nextInt(1000)+500;
    }

    for ( int z =0; z<= worldPixelLength;){
      double y =ran.nextInt((int) (gameLoadedLevelPropPointer.getValue().getUpperBoarder(500)-iteamCricle
                    -gameLoadedLevelPropPointer.getValue().getDownBoarder(200)))
              + gameLoadedLevelPropPointer.getValue().getDownBoarder(200);
      gameLoadedLevelPropPointer.getValue().setCoin(z, (int) y);
      z= z + ran.nextInt(100)+300;
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

    this.gamePlayerPosPropPointer.addListener( new ChangeListener<Double>() {
      @Override
      public void changed( ObservableValue<? extends Double> o, Double oPos, Double newPos ) {
        if (isDisplayCanvasReady( )) {
          // @ToDo: Prüfe ob doppelt
          gameDisplaySelector.updateAbsoluteLayerPos( newPos );
          updateIteams( oPos, newPos );
        }
      }
    } );

    // Verknüpfte die EngineAttribute mit den GameLevelAttributen
    ObjectProperty<GameLevel> pLevelProp = this.gameLoadedLevelPropPointer;
    this.gamePlayerPosPropPointer.addListener( (o, oP, newPosition) -> {
      if (isLoadedLevelReady( ))
        pLevelProp.getValue( ).gamePlayerPos = newPosition;
    });
    this.gamePlayerScorePropPointer.addListener( (o, oS, newScore) ->  {
      if (isLoadedLevelReady( ))
        pLevelProp.getValue( ).gamePlayerScore = newScore.intValue( );
    });
    /*this.playerPosX.addListener( (o, oP, newPosition) ->  {
      if (isLoadedLevelReady( ))
        pLevelProp.getValue( ).playerPosX = newPosition.intValue( );
    });*/
    this.playerPosY.addListener( (o, oP, newPosition) ->  {
      if (isLoadedLevelReady( ))
        pLevelProp.getValue( ).playerPosY = newPosition.intValue( );
    });
    /*this.playerRadius.addListener((o, oP, newPosition) ->  {
      if (isLoadedLevelReady( ))
        pLevelProp.getValue( ).playerRadius = newPosition.intValue( );
    });*/
  }

  private boolean isDisplayCanvasReady( ) {
    return this.gameDisplaySelector.gameWorldPane != null
    &&     this.gameDisplaySelector.gameWorldIteams != null;
  }

  private boolean isLoadedLevelReady( ) {
    return this.gameLoadedLevelPropPointer != null
    &&  this.gameLoadedLevelPropPointer.getValue( ) != null;
  }

  // PROPERTYS
  //public IntegerProperty getPlayerPosXProperty( ) { return this.playerPosX; }
  public IntegerProperty getPlayerPosYProperty( ) { return this.playerPosY; }
  public FloatProperty getGameSpeedProperty( ) { return this.gameSpeed; }

  // PROPERTYS - POINTER
  public ObjectProperty<Double> getGamePlayerPosProperty( ) { return this.gamePlayerPosPropPointer; }
  public ObjectProperty<Number> getGamePlayerScoreProperty( ) { return this.gamePlayerScorePropPointer; }
}
