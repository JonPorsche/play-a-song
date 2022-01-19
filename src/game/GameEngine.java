package game;


// @ToDo: RandomItemGenerator, PlayerMovement(Up/Down), updateGuiCanvas,

import application.Main;
import game.sprites.Iteam;
import game.sprites.PlayerCharacter;
import game.sprites.SlowMoIteam;
import game.sprites.SpeedIteam;
import javafx.animation.AnimationTimer;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javazoom.jl.player.Player;
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
  protected ConcurrentHashMap<Number, Iteam> KnockableIteams= new ConcurrentHashMap<>();
  protected HashMap<Number, Iteam> Iteams= new HashMap<>();

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
    player = new PlayerCharacter();
    player.setCenterY(500);
    player.setRadius(30);
    player.setCenterX(500);
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
    this.gameDisplaySelector.gameWorldPane.isDoneProperty().addListener((o,old,newP)-> {
      if (newP){
        gL.setUpperbound(gameDisplaySelector.gameWorldPane.getAllXYUpperArray());
        gL.setBottombound(gameDisplaySelector.gameWorldPane.getAllXYBottomArray());
        this.Iteams= gameLoadedLevelPropPointer.getValue().getSortedItems();
          this.setGameIteams();
        if (this.gameLoadedLevelPropPointer.getValue( ).mapChunks.size() > 100 )
          this.gamePlayingStatePropPointer.setValue( GamePlayingState.READY );
          startPlaying();
      }
    });

  }

  private void startEngine( ) {
    GameEngine gE = this;
    if (!gE.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PLAY )) return;
    AnimationTimer gameThread = new AnimationTimer() {
      private long lastUpdated = 0;
      private long lastRendered = 0;
      private final int UPS = 144;
      private final int FPS = 144;
      private final int SECONDS2NANO_SECONDS = 1_000 * 1_000_000;
      private final int UPNS_DELTA = SECONDS2NANO_SECONDS / UPS;
      private final int FPNS_DELTA = SECONDS2NANO_SECONDS / FPS;
      int curPlayerPosX = getGamePlayerPosProperty( ).getValue( ).intValue( );
      int gameSpeed = getGameSpeedProperty( ).getValue( ).intValue( );

      @Override
      public void handle(long now) {
        if (lastRendered + FPNS_DELTA < now) {
            gameDisplaySelector.updateAbsoluteLayerPos((double) curPlayerPosX);
            lastRendered = now;
        }

        if (lastUpdated + UPNS_DELTA < now) {
          double delta = lastUpdated == 0 ? 0 : (now - lastUpdated) / (double)SECONDS2NANO_SECONDS;
          curPlayerPosX = getGamePlayerPosProperty( ).getValue( ).intValue( );
          gameSpeed = getGameSpeedProperty( ).getValue( ).intValue( );
          gamePlayerPosPropPointer.setValue((double) ((curPlayerPosX + 5) * gameSpeed));
          if (mapCollsion()){
            System.out.println("Map Collison");
          }
          lastUpdated = now;
        }
      }

    };

    gameThread.start();

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
        if( iteam.getCenterX() +radius < getPlayerPosYProperty().get()+500 - player.getRadius()){
         KnockableIteams.remove(iteam);
        }
      }
    }
    for (Number i :vissableIteams.keySet()){
      if(!KnockableIteams.containsValue(vissableIteams.get(i))) {
        double x = vissableIteams.get(i).getCenterX();
        double rd = vissableIteams.get(i).getRadius();
        if (x - rd > gamePlayerPosPropPointer.get() + 500 + player.getRadius()) {
        } else if (x + rd < gamePlayerPosPropPointer.get() - player.getRadius()) {
        } else {
          KnockableIteams.put(i, vissableIteams.get(i));

        }
      }
    }
    for (Number i :KnockableIteams.keySet()){
     if( voidIteamCollsion(KnockableIteams.get(i), newPos)){
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

  public boolean voidIteamCollsion(Iteam iteam, double newPos){
    double playerX = newPos +500;
    double playerY = player.getY();
    double playerRadiu = player.getRadius();
    double distance = Math.sqrt(Math.pow(iteam.getCenterX() -playerX , 2) + (Math.pow(iteam.getCenterY() - playerY, 2)));
    if(distance <= (playerRadiu+ iteam.getRadius()) && distance >= Math.abs(playerRadiu -iteam.getRadius())){
      return true;
    };
    return false;
  }

  public boolean mapCollsion(){
    int playerX = (int) (gamePlayerPosPropPointer.getValue()+500);
    double playerY = player.getY();
    double differntValue = player.getRadius() +20;


    GameLevel gl = gameLoadedLevelPropPointer.getValue();
    if((gl.getDownBoarder(playerX)-differntValue > playerY) && (gl.getUpperBoarder(playerX)+differntValue < playerY)) {
      return false;

    }else{
      PlayerCharacter tempPlayer =new PlayerCharacter();
      tempPlayer.setRadius(player.getRadius());
      tempPlayer.setCenterX(playerX);
      tempPlayer.setCenterY(playerY);
      for (int width = (int) (playerX - player.getRadius()); width < playerX + player.getRadius(); width++) {
        if(tempPlayer.getLayoutBounds().contains(new Point2D(width, gl.getUpperBoarder(width)))){

          return true;}
        Bounds test = tempPlayer.getLayoutBounds();
       Point2D boarder = new Point2D(width, gl.getDownBoarder(width));
        if(tempPlayer.getLayoutBounds().contains(boarder)){
          return true;
        }
      }
    }
    return false;

  }

  public void updateVissableIteams(Double oPos, Double newPos){
    double differncePos = newPos-oPos;
    System.out.println(gameLoadedLevelPropPointer.getValue().getUpperBoarder(newPos));
    Iterator<Number> iterator;
    for (iterator = vissableIteams.keySet().iterator(); iterator.hasNext(); ) {
      Number i = iterator.next();
      Iteam iteam = vissableIteams.get(i);
      if (iteam != null) {
        Double radius = iteam.getRadius();
        if (i.doubleValue() + radius <= newPos - 500) {
          iteam.setIsVisabile(false);
          gameDisplaySelector.removeIteam(iteam);
        }
      }
    }
    //@TODO Do sprite
    for (int old = oPos.intValue(); old <= newPos.intValue() +1200; old++){
      Iteam iteam = Iteams.get(old);
      if (iteam != null) {
      if(!vissableIteams.contains(Iteams.get(old))){

        int index = old;
        gameDisplaySelector.gameWorldIteams.addIteam(Iteams.get(old));
        vissableIteams.put(index,iteam);
        iteam.setIsVisabile(true);
        }
      }
    }
  }

  public void setGameIteams(){
    int worldPixelLength = (int) gameDisplaySelector.gameWorldPane.getWidth();
    int coin = 0;
    int x;
    int iteamCricle = 20;
    GameLevel gL= gameLoadedLevelPropPointer.getValue();
    Random ran = new Random( );
    for ( x= ran.nextInt(1000)+1000; x < worldPixelLength; ){
      double y;
      int v = (int) (gL.getDownBoarder(x) -iteamCricle-40
              - (gL.getUpperBoarder(x) +iteamCricle+40));
      int b = ran.nextInt(v);
      int d  = (int) (gL.getUpperBoarder(x)+iteamCricle+40);
      y = b+d;

      gL.setIteam(getRandomIteam(x, (int) y));
      x= x+ ran.nextInt(1000)+500;
    }

    for ( int z =0; z<= worldPixelLength;){
      int v = (int) (gL.getDownBoarder(z) - iteamCricle-20 - (gL.getUpperBoarder(z) + iteamCricle+20));
      int b = ran.nextInt(v);
      int d  = (int) (gL.getUpperBoarder(z)+iteamCricle+20);
      double y = b+d;
      gL.setCoin(z, (int) y);
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
