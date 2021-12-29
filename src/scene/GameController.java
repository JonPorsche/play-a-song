package scene;

import application.Main;
import game.Audioinfo;
import gamelogic.*;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import uicomponents.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.RED;

public class GameController {
	private GameView gameDisplayPane;
	private Game game;
	player playerObject;
	int i= 0;
	Audioinfo audioinfo ;
	private double[] amplitudeArray;
	private double maxAmplitude = 0;
	double gameSpeed = 1;
	playerSprite playerSpritesobject = new playerSprite();
	private List<iteam>iteams = new ArrayList<>();
	private List<Sprite> Upsprites = new ArrayList<>();
	private List<Sprite> Iteamsprites = new ArrayList<>();
	private List<Sprite> iteamsSprites = new ArrayList<>();
	private playerSprite playerSprite;

	private boolean gameIsRunning;

	public GameController(Game game) {
		GameView view = new GameView();
		
		gameDisplayPane = view;
		
		this.game = game;
		
		initialize();
	}
	public player getPlayerObject(){

		return playerObject;
	}

	public <pixels, i> void initialize() {
		File trackFile = new File( "src/assets/example-track.mp3" );
		System.out.println( trackFile.getAbsolutePath() );

		playerObject= new player();
		playerObject.setgamespeed(1);
		playerObject.setX(400);
		playerObject.setY(500);
		playerObject.setRadius(40);
		playerObject.setSpeedModfer(0.08);
		game.add(playerObject);
		playerSpritesobject = new playerSprite();
		playerSpritesobject.setRadius(playerObject.getRadius());
		playerSpritesobject.setCenterX(playerObject.getX());
		playerSpritesobject.setCenterX(playerObject.getY());
		playerSpritesobject.setFill(RED);
		addSprite(playerSpritesobject);
		gameDisplayPane.getChildren().add(playerSpritesobject);
		playerSpritesobject.gameObjectProperty().set(playerObject);
		game.update(gameSpeed);
		audioinfo = new Audioinfo();
		amplitudeArray = audioinfo.getLeft( trackFile.getAbsolutePath() );

		gameIsRunning= true;
		setIteams();
		SpawnIteam();

		Thread thread = new Thread() {

			@Override
			public void run() {
				List<Double> allWorldSteps = new ArrayList();

				for (double curAmplValue : amplitudeArray) {
					if (maxAmplitude < curAmplValue) maxAmplitude = curAmplValue;

					WaveWorldObj gameObject;
					WaveRSprite sprite;
					gameObject = new WaveWorldObj( );
					gameObject.setgamespeed( 1 );
					gameObject.setX( i * 100 );
					gameObject.setY( 0 );
					gameObject.setHeight( 50 + 6 * curAmplValue );
					gameObject.setWidth( 100 );

					/*sprite = new WaveRSprite( );
					sprite.setX(50);
					sprite.setY(50);
					sprite.setHeight(gameObject.getHeight( ));
					sprite.setWidth(25);*/

					game.add( gameObject );
					allWorldSteps.add( 50 + 6 * curAmplValue );
					/*addSprite(sprite);
					sprite.gameObjectProperty().set(gameObject);
					allWorldSteps.add( sprite );*/
				}

				Platform.runLater(
					() -> gameDisplayPane.getWorld( ).setWorldSteps( allWorldSteps, maxAmplitude )
				);

				int curGamePos = 0 - Main.WINDOW_WIDTH/2;
				for (; curGamePos < amplitudeArray.length*100; curGamePos++) {
					try {
						Thread.sleep( 5 );//17,5 );

						final int finalCurGamePos = curGamePos;
						Platform.runLater(
							() -> gameDisplayPane.setCenterViewFrame(finalCurGamePos)
						);

					} catch (InterruptedException e) {
						e.printStackTrace( );
					}
				}
			}

		}; thread.start();



		
		AnimationTimer gameThread = new AnimationTimer() {
			private long lastUpdated = 0;
			private long lastRendered = 0;
			private final int UPS = 144;
			private final int FPS = 144;
			
			private final int SECONDS2NANO_SECONDS = 1_000 * 1_000_000;
			private final int UPNS_DELTA = SECONDS2NANO_SECONDS / UPS;
			private final int FPNS_DELTA = SECONDS2NANO_SECONDS / FPS;
			@Override
			public void handle(long now) {
				int i = 0;
				if (lastRendered + FPNS_DELTA < now) {
					for(Sprite e : new ArrayList<Sprite>(Upsprites)) {
						if(i == 77){
						try {
							collision(e);
							i = 0;
						} catch (Exception ex) {
							ex.printStackTrace();
						}}
						i++;
						e.render();


					}
					for(Sprite e : iteamsSprites) {
						if(i == 77){
							try {
								iteamCollison(e);
								i = 0;
							} catch (Exception ex) {
								ex.printStackTrace();
							}}
						i++;


						e.render();

					}
					game.update(6.94);
					lastRendered = now;
				}
				
				if (lastUpdated + UPNS_DELTA < now) {
					double delta = lastUpdated == 0 ? 0 : (now - lastUpdated) / (double)SECONDS2NANO_SECONDS;

					lastUpdated = now;
				}
			}
			
		};
		
		gameThread.start();
		audioinfo.play();
		
	}
	private void addSprite( Sprite sprite ) {
		if(Upsprites.size() == 999){
			Platform.runLater( ( )-> gameDisplayPane.getChildren().remove( Upsprites.get(1)) );
			Upsprites.remove(1);
		}
		Upsprites.add(sprite);

	}

	public void collision(Sprite e) throws Exception {

		if(e.getRectangle().getBoundsInParent().intersects(playerSpritesobject.getBoundsInParent())){
			System.out.println("HELP collusion");
		}


	}

	public void iteamCollison(Sprite iteam){
		if(iteam.getBounds().intersects(playerSpritesobject.getBoundsInParent())){
			Thread iteamthread = new Thread(){
				@Override
				public void run(){
					iteam iteamObject = (iteam) iteam.gameObjectProperty().getValue();
					playerObject.setSizeModifer(iteamObject.getSizeModifer());
					playerObject.setSpeedModfer(iteamObject.getSizeModifer());
					gameDisplayPane.getChildren().remove(iteamObject);
					iteamsSprites.remove(iteam);

					try {
						Thread.sleep(iteamObject.getDuration());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					playerObject.setSizeModifer(-iteamObject.getSizeModifer());
					playerObject.setSpeedModfer(-iteamObject.getSizeModifer());


				}
			};iteamthread.start();

		}


	}



	public void SpawnIteam(){
		int iteamSpawnIntervall = 6000;


		iteam thisiteam = null;
		Thread iteamThread = new Thread() {
			@Override
			public void run() {
				while (gameIsRunning) {
					try {
						int index ;
						iteam iteamObject = new iteam();
						iteamObject.setHeight(20);
						iteamObject.setWidth(20);
						iteamObject.setX(500);
						iteamObject.setY(500);
						iteamObject.setSizeModifer(0.2);
						iteamObject.setSpeedModifer(0.2);
						iteamObject.setgamespeed(0.1);
						long duration = 10000;
						iteamObject.setDuration(duration);
						iteams.add(iteamObject);
						iteamSprite sprite = new iteamSprite();
						Thread.sleep(iteamSpawnIntervall);
						sprite.setX(iteamObject.getX());
						sprite.setY(iteamObject.getY());
						sprite.setHeight(iteamObject.getHeight());
						sprite.setWidth(iteamObject.getWidth());
						sprite.setFill(Color.BLUEVIOLET);
						game.addIteam(iteamObject);
						Platform.runLater(() -> gameDisplayPane.getChildren().add(sprite));
						sprite.gameObjectProperty().set(iteamObject);
						iteamsSprites.add(sprite);
						iteamObject.setSpeed(playerObject.getSpeedModfer());

					} catch (InterruptedException e) {
						e.printStackTrace();
					}


				}
			}
		}; iteamThread.start();
	}

	public void setIteams(){
		//test
		/*iteam iteamObject = new iteam();
		iteamObject.setHeight(20);
		iteamObject.setWidth(20);
		iteamObject.setX(500);
		iteamObject.setY(500);
		iteamObject.setSizeModifer(0.2);
		iteamObject.setSpeedModifer(0.2);
		iteamObject.setgamespeed(0.1);
		long duration = 10000;
		iteamObject.setDuration(duration);
		iteams.add(iteamObject);*/

	}





	public Pane getGameDisplayPane() {
		return gameDisplayPane;
	}

	public void newgameObject(int i, int pixels){
	}


	}





