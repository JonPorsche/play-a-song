package game;


// @ToDo: RandomItemGenerator, PlayerMovement(Up/Down), updateGuiCanvas,

import business.service.Mp3Player;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import game.sprites.logic.PlayerCharacter;
import game.sprites.basic.Iteam;
import game.sprites.optic.PlayerSprite;
import javafx.animation.AnimationTimer;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import uicomponents.game.GameDisplay;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BinaryOperator;

public class GameEngine {

  private static final double MAX_SPEED =4 ;
  private static final double MIN_SPEED =-4 ;
  private GameDisplay gameDisplaySelector;
  private AnimationTimer gameAnimatedThread = null;

  // PROPERTYS
  protected IntegerProperty playerPosX = new SimpleIntegerProperty();
  protected IntegerProperty playerPosY = new SimpleIntegerProperty();
  protected DoubleProperty playerRadius= new SimpleDoubleProperty();
  protected PlayerCharacter player = new PlayerCharacter( );
  protected DoubleProperty gameSpeed = new SimpleDoubleProperty(0);
  protected ConcurrentHashMap<Number, Iteam> vissableIteams= new ConcurrentHashMap<>();
  protected ConcurrentHashMap<Number, Iteam> KnockableIteams= new ConcurrentHashMap<>();
  protected HashMap<Number, Iteam> Iteams= new HashMap<>();
  Mp3Player mp3Player = new Mp3Player();

  // PROPERTYS - POINTER
  public ObjectProperty<GamePlayingState> gamePlayingStatePropPointer;
  protected ObjectProperty<Boolean> gameIsRunningPropPointer;
  protected ObjectProperty<GameLevel> gameLoadedLevelPropPointer;
  protected ObjectProperty<Double> gamePlayerPosPropPointer;
  protected ObjectProperty<Number> gamePlayerScorePropPointer;
  protected  ObjectProperty<Number> gamePlayerLifePointer;
  protected FloatProperty pastgameSpeed = new SimpleFloatProperty(1);
  private double plusscore;
  private boolean cooldown;

  public GameEngine(
          ObjectProperty<GamePlayingState> playingStatePrPo, // <- GameManager.gamePlayingState
          ObjectProperty<Boolean> isRunningPrPo, // <- GameManager.gameIsRunning
          ObjectProperty<GameLevel> loadedLevelPrPo, // <- GameManager.gameLoadedLevel
          ObjectProperty<Double> playerPosPrPo, // <- GameManager.gamePlayerPos
          ObjectProperty<Number> playerScorePrPo, // <- GameManager.gamePlayerScore
          ObjectProperty<Number> gamePlayerLife) {
    // Declare PropertyPointers
    this.gamePlayingStatePropPointer = playingStatePrPo;
    this.gameIsRunningPropPointer = isRunningPrPo;
    this.gameLoadedLevelPropPointer = loadedLevelPrPo;
    this.gamePlayerPosPropPointer = playerPosPrPo;
    this.gamePlayerScorePropPointer = playerScorePrPo;
    this.gamePlayerLifePointer = gamePlayerLife;


    this.bindInternPropertyComputing( );
  }

  public boolean isReady( ) {
    return this.gamePlayingStatePropPointer != null
    &&     this.gameIsRunningPropPointer != null
    &&     this.gameLoadedLevelPropPointer != null
    &&     this.gameLoadedLevelPropPointer.getValue( ) != null
    &&     this.gamePlayerPosPropPointer != null
    &&     this.gamePlayerScorePropPointer != null
    &&     this.gamePlayerLifePointer != null;
  }

  public void declareGameDisplayPane( GameDisplay guiGameDisplaySelector) {
    this.gameDisplaySelector = guiGameDisplaySelector;
    player = new PlayerCharacter();
    guiGameDisplaySelector.declarePlayerCharacter( this.player );
  }

  public void addIteamPattern( ) {

  }

  public GameDisplay getGameDisplayPane( ) {
    return this.gameDisplaySelector;
  }

  public void setNewLevel( GameLevel gL, List<BinaryOperator> iteamFactorysOperators ) {
    GameEngine gE = this;
    SimpleBooleanProperty levelIsLoadedProp = this.gameDisplaySelector.gameWorldPane.isDoneLoadingLevelProperty( );

    if (!levelIsLoadedProp.getValue( ))
      levelIsLoadedProp.addListener( (o, old, newState) -> {
        if (newState) this.afterWorldDrawFinished( iteamFactorysOperators );
      });
    else levelIsLoadedProp.setValue( false );

    // Update PropertyValues
    this.gamePlayingStatePropPointer.setValue( GamePlayingState.NOTREADY );
    this.gamePlayerPosPropPointer.setValue( gL.gamePlayerPos );
    this.gamePlayerScorePropPointer.setValue( gL.gamePlayerScore );
    this.gamePlayerLifePointer.setValue(gL.playerLife);
    this.gameDisplaySelector.initCanvas( gL.getMapPixelWidth( ) );
    this.gameLoadedLevelPropPointer.setValue( gL );
    //this.playerPosX.setValue( gL.playerPosX );
    this.playerPosY.setValue( gL.playerPosY );
  }

  public void spawnGameIteam( Iteam newGameIteam ) {
    this.gameLoadedLevelPropPointer.getValue( ).setIteam( newGameIteam);
  }
  public void spawnGameIteam( int x, int y, List<BinaryOperator> iteamFactorysOperators ) {
    int randomIndex = ThreadLocalRandom.current().nextInt( 0, iteamFactorysOperators.size( ) );

    this.spawnGameIteam(
      (Iteam)iteamFactorysOperators.get( randomIndex ).apply( x, y ) // cast Object -> IteamObj
    );
  }

  private void spawnRandomIteams( List<BinaryOperator> iteamFactorysOperators ) {
    GameEngine gE = this;
    GameLevel gL = gameLoadedLevelPropPointer.getValue();

    gL.setUpperbound(gameDisplaySelector.gameWorldPane.getAllXYUpperArray());
    gL.setBottombound(gameDisplaySelector.gameWorldPane.getAllXYBottomArray());
    this.Iteams = gameLoadedLevelPropPointer.getValue().getSortedItems();

    if (iteamFactorysOperators.size( ) > 0) {
      new Thread(() -> {
        int worldPixelLength = gL.getMapPixelWidth();
        int lengent = worldPixelLength / 10;

        for (int drawChunkPosX = 0; drawChunkPosX < worldPixelLength; drawChunkPosX += lengent) {
          int xStart = drawChunkPosX;
          int xEnd = drawChunkPosX + lengent;

          int curIteamPosX;
          Random ran = new Random();
          for (curIteamPosX = xStart + 100; curIteamPosX < xEnd-1000; ) {
            int freeSpace = gE.getChunkSpace(curIteamPosX);
            int randomSpaceOffset = ran.nextInt(freeSpace);
            int spaceBounce = gE.getChunkBounce(randomSpaceOffset);
            int curIteamPosY = spaceBounce + randomSpaceOffset;
            gE.spawnGameIteam(curIteamPosX, curIteamPosY, iteamFactorysOperators);
            curIteamPosX = curIteamPosX + ran.nextInt(1000) + 500;
          }
        }

        gE.gamePlayingStatePropPointer.setValue(GamePlayingState.READY);
      }).start( );
    }
  }

  private void afterWorldDrawFinished( List<BinaryOperator> iteamFactorysOperators ) {
    if (this.gameLoadedLevelPropPointer.getValue( ).mapChunks.size() > 100 ) {
      mp3Player.load(getPlayingLevel().getSong());

      if (iteamFactorysOperators.size() >= 1)
        this.spawnRandomIteams( iteamFactorysOperators );
      else
        this.gamePlayingStatePropPointer.setValue( GamePlayingState.READY );
    } else System.out.println( "Map ist leider zu klein zum Spielen! Waehle ein laengeres Lied aus..." );
  }

    private void startEngine() {
        mp3Player.play();
        getGameSpeedProperty().setValue((double) 1 / (double) 60);
        Double test = getGameSpeedProperty().get();

    GameEngine gE = this;
   /* if (!gE.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PLAY )
    || this.gameAnimatedThread != null
    ) return;*/

    this.gameAnimatedThread = new AnimationTimer() {

      long lastUpdated = 0;
      long lastRendered = 0;
      final int UPS = 144;
      final int FPS = 144;
      final int SECONDS2NANO_SECONDS = 1_000 * 1_000_000;
      final int UPNS_DELTA = SECONDS2NANO_SECONDS / UPS;
      final int FPNS_DELTA = SECONDS2NANO_SECONDS / FPS;
      double curPlayerPosX = gamePlayerPosPropPointer.getValue( ).intValue( );
      double gameSpeed;
      double pixelPerSceond;
      {
        try {
          pixelPerSceond = mp3Player.getLenghth(gameLoadedLevelPropPointer.getValue().getSong());
        } catch (InvalidDataException e) {
          e.printStackTrace();
        } catch (UnsupportedTagException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void handle(long now) {

        if (lastRendered + FPNS_DELTA < now) {
            gameDisplaySelector.updateAbsoluteLayerPos((double) gamePlayerPosPropPointer.getValue());
            lastRendered = now;
        }
        if (lastUpdated + UPNS_DELTA < now) {
          if(!gE.gamePlayingStatePropPointer.getValue( ).equals( GamePlayingState.PLAY )){
            mp3Player.pause();
            stop();
          }
          double delta = lastUpdated == 0 ? 0 : (now - lastUpdated) / (double)SECONDS2NANO_SECONDS;
          curPlayerPosX = getGamePlayerPosProperty( ).getValue( ).intValue( );
          gameSpeed = getGameSpeedProperty( ).getValue( );

          if(gameSpeed>MAX_SPEED){
            gameSpeed = 4;
          }

          if (gameSpeed < MIN_SPEED){
            gameSpeed = -4;
          }
          double value = curPlayerPosX +12.90 +gameSpeed ;
          gamePlayerScorePropPointer.setValue((int)(curPlayerPosX+ 13.99+ plusscore+gameSpeed));
          plusscore = 0;
          gamePlayerPosPropPointer.setValue(value);
         if( mapCollsion() && !cooldown){
           gamePlayerLifePointer.setValue( gamePlayerLifePointer.getValue().intValue() -1);
           setCooldown();
         };
        lastUpdated = now;
      }
      }
    };

    gameAnimatedThread.start();
  }

  public void startPlaying( ) {
    this.gamePlayingStatePropPointer.setValue( GamePlayingState.PLAY );
    this.startEngine( );
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

    for ( Number i :vissableIteams.keySet()) {
      if(KnockableIteams.containsKey(i)){
        Iteam iteam = KnockableIteams.get(i);
        Double radius = Double.valueOf(iteam.getRadius());
        if( iteam.getX() +radius < getPlayerPosYProperty().get()+500 - player.getRadius()){
         KnockableIteams.remove(iteam);
        }
      }
    }
    for (Number i :vissableIteams.keySet()){
      if(!KnockableIteams.containsValue(vissableIteams.get(i))) {
        double x = vissableIteams.get(i).getX();
        double rd = vissableIteams.get(i).getRadius();
        if (x - rd > gamePlayerPosPropPointer.get() + 500 + player.getRadius()) {
        } else if (x + rd < gamePlayerPosPropPointer.get() - player.getRadius()) {
        } else {
          KnockableIteams.put(i, vissableIteams.get(i));

        }
      }
    }
    for (Number i :KnockableIteams.keySet()){
      Iteam iteam = KnockableIteams.get(i);
     if( voidIteamCollsion(KnockableIteams.get(i), newPos)){
       System.out.println("collsion");

       iteam.collision(this, player);
       gameDisplaySelector.gameWorldIteams.removeIteam(KnockableIteams.get(i).getSprite());
       KnockableIteams.remove(KnockableIteams.get(i));

     };
    }


  }

  public void addScore(double score) {
    plusscore += score;
  }


  public boolean voidIteamCollsion(Iteam iteam, double newPos){
    double playerX = newPos +500;
    double playerY = player.getY();
    double playerRadiu = player.getRadius();
    double distance = Math.sqrt(Math.pow(iteam.getX() -playerX , 2) + (Math.pow(iteam.getY() - playerY, 2)));
    if(distance <= (playerRadiu+ iteam.getRadius()) && distance >= Math.abs(playerRadiu -iteam.getRadius())){
      return true;
    };
    return false;
  }
  public boolean mapCollsion(){
    int playerX = (int) (gamePlayerPosPropPointer.getValue()+500);
    double playerY = player.getY();
    double differntValue = player.getRadius();
    GameLevel gl = gameLoadedLevelPropPointer.getValue();
    if((gl.getDownBoarder(playerX)-differntValue > playerY) && (gl.getUpperBoarder(playerX)+differntValue < playerY)) {
      return false;

    }else{
      PlayerSprite tempPlayer;
      tempPlayer = new PlayerSprite(playerX, (int) playerY);
      tempPlayer.setRadius(player.getRadius());
      for (int width = (int) (playerX - player.getRadius()); width < playerX + player.getRadius(); width++) {
        if(tempPlayer.getLayoutBounds().contains(new Point2D(width, gl.getUpperBoarder(width)))){
          System.out.println("Collision");
          player.setY(gl.getUpperBoarder(width)+player.getRadius());
          return true;}
        Bounds test = tempPlayer.getLayoutBounds();
       Point2D boarder = new Point2D(width, gl.getDownBoarder(width));
        if(tempPlayer.getLayoutBounds().contains(boarder)){
          System.out.println("Collision");
          player.setY(gl.getDownBoarder(width)-player.getRadius());
          return true;
        }
      }
    }
    return false;

  }

  public void updateVissableIteams(Double oPos, Double newPos){
    double differncePos = newPos-oPos;
    Iterator<Number> iterator;
    for (iterator = vissableIteams.keySet().iterator(); iterator.hasNext(); ) {
      Number i = iterator.next();
      Iteam iteam = vissableIteams.get(i);
      if (iteam != null) {
        Double radius = Double.valueOf(iteam.getRadius());
        if (i.doubleValue() + radius <= newPos - 500) {

          gameDisplaySelector.removeIteam(iteam.getSprite());
          iteam.setIsVisabile(false);

        }
      }
    }
    //@TODO Do sprite
    for (int old = oPos.intValue(); old <= newPos.intValue() +1200; old++){
      Iteam iteam = Iteams.get(old);
      if (iteam != null) {
      if(!vissableIteams.contains(Iteams.get(old))){

        int index = old;

        iteam.setIsVisabile(true);
        gameDisplaySelector.gameWorldIteams.addIteam(iteam.getSprite());
        vissableIteams.put(index,iteam);

        }
      }
    }
  }

  public int getChunkSpace( int xPos ) {
    int iteamCricle = 20;
    GameLevel gL = gameLoadedLevelPropPointer.getValue();

    return (int) (gL.getDownBoarder( xPos ) -iteamCricle-40
        - (gL.getUpperBoarder( xPos) + (iteamCricle*2)));
  }

  public int getChunkBounce( int xPos) {
    GameLevel gL = gameLoadedLevelPropPointer.getValue();
    return (int)gL.getUpperBoarder( xPos ) + 60;
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
      if(newPosition > 0){
      if (isLoadedLevelReady( )) {
        pLevelProp.getValue().gamePlayerPos = newPosition;
      }
      if
      (newPosition > gameDisplaySelector.gameWorldPane.getLength()-2000){
        gamePlayingStatePropPointer.setValue(GamePlayingState.FINISHED);
      }
    }});

    this.gamePlayerScorePropPointer.addListener( (o, oS, newScore) ->  {
      if (isLoadedLevelReady( ))
        pLevelProp.getValue().gamePlayerScore = (int) (newScore.intValue());
        plusscore = 0;
      });
    this.playerPosX.addListener( (o, oP, newPosition) ->  {
      if (isLoadedLevelReady( ))
        pLevelProp.getValue( ).playerPosX = newPosition.intValue( );
    });
    this.gamePlayerLifePointer.addListener( (o,oP,newPostion) -> {
      if(pLevelProp.getValue( ) != null) {
        pLevelProp.getValue( ).playerLife = newPostion.intValue( );
      }
    } );
    this.gamePlayerLifePointer.addListener((o,oP,newPostion)->{
      if(newPostion.intValue()<=0){
        gamePlayingStatePropPointer.setValue(GamePlayingState.GAMEOVER);
      }
    });

    this.playerPosY.addListener( (o, oP, newPosition) ->  {
      if (isLoadedLevelReady( ))
        pLevelProp.getValue( ).playerPosY = newPosition.intValue( );
    });
    this.playerRadius.addListener((o, oP, newPosition) ->  {
      if (isLoadedLevelReady( ))
        pLevelProp.getValue( ).playerRadius = newPosition.intValue( );
    });


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
  public DoubleProperty getGameSpeedProperty( ) { return this.gameSpeed;
  }

  // PROPERTYS - POINTER
  public ObjectProperty<Double> getGamePlayerPosProperty( ) { return this.gamePlayerPosPropPointer; }
  public ObjectProperty<Number> getGamePlayerLifePointer() {return  gamePlayerLifePointer;}
  public ObjectProperty<Number> getGamePlayerScoreProperty( ) { return this.gamePlayerScorePropPointer; }

  public void addGamespeed(float gamespeedMod) {

    this.gameSpeed.setValue(gameSpeed.getValue() + gamespeedMod);
    new Thread(()->{
      int seconds = 0;
      while (seconds <= 10) {
        if (gamePlayingStatePropPointer.getValue() == GamePlayingState.PLAY){
          seconds += 1;
        }
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      this.gameSpeed.setValue(gameSpeed.getValue() +gamespeedMod);
    }).start();//

  }
  public void setCooldown(){
    this.cooldown = true;
    new Thread(()-> {
      int seconds = 0;
      while (seconds <= 1) {
        if (gamePlayingStatePropPointer.getValue() == GamePlayingState.PLAY) {
          seconds += 1;
        }
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      this.cooldown = false;
    }).start();//
  }
}


