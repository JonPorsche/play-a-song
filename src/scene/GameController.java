package scene;

import game.Audioinfo;
import gamelogic.*;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import uicomponents.Sprite;
import uicomponents.iteamSprite;
import uicomponents.playerSprite;
import uicomponents.waveSprite;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.RED;

public class GameController {
	private Pane root;
	private Game game;
	player playerObject;
	int i= 0;
	Audioinfo audioinfo ;
	private double[]array;
	double gameSpeed = 1;
	playerSprite playerSprites = new playerSprite();
	private List<iteam>iteams = new ArrayList<>();
	private List<Sprite> Upsprites = new ArrayList<>();
	private List<Sprite> Iteamsprites = new ArrayList<>();
	private List<Sprite> iteamsSprites = new ArrayList<>();
	private playerSprite playerSprite;
	private boolean gameIsRunning;

	public GameController(Game game) {
		GameView view = new GameView();
		
		root = view;
		
		this.game = game;
		
		initialize();
	}
	public player getPlayerObject(){

		return playerObject;
	}

	public <pixels, i> void initialize() {

		playerObject= new player();
		playerSprite playerSprites = new playerSprite();
		playerObject.setgamespeed(1);
		playerObject.setX(400);
		playerObject.setY(500);
		playerObject.setRadius(40);
		playerObject.setSpeedModfer(0.08);
		game.add(playerObject);
		playerSprites = new playerSprite();
		playerSprites.setRadius(playerObject.getRadius());
		playerSprites.setCenterX(playerObject.getX());
		playerSprites.setCenterX(playerObject.getY());
		playerSprites.setFill(RED);
		addSprite(playerSprites);
		root.getChildren().add(playerSprites);
		playerSprites.gameObjectProperty().set(playerObject);
		game.update(gameSpeed);
		audioinfo = new Audioinfo();
		array =audioinfo.getLeft("D:\\Studies\\Semesterr 3\\play-a-song-02\\src\\game\\hallo.mp3");

		gameIsRunning= true;
		setIteams();
		SpawnIteam();

		Thread thread = new Thread() {

			@Override
			public void run() {

			int pixels = 0;

			for(i = 0;i< array.length;i++) {
						wave gameObject = null;
						waveSprite sprite;
						gameObject = new wave();
						gameObject.setgamespeed(1);
						gameObject.setX(1000);
						gameObject.setY(200 + 10 * array[i]);
						sprite = new waveSprite();
						sprite.setStartY(-100);
						sprite.setStartX(gameObject.getX());
						sprite.setEndX(gameObject.getX());
						sprite.setEndY(gameObject.getY());
						sprite.setFill(Color.BLUEVIOLET);
						game.add(gameObject);
						addSprite(sprite);
						Platform.runLater(() -> root.getChildren().add(sprite));
						sprite.gameObjectProperty().set(gameObject);
						game.update(gameSpeed);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
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
						e.render();
						System.out.println("worked fine");
					}
					lastRendered = now;
				}
				
				if (lastUpdated + UPNS_DELTA < now) {
					double delta = lastUpdated == 0 ? 0 : (now - lastUpdated) / (double)SECONDS2NANO_SECONDS;

					lastUpdated = now;
				}
			}
			
		};
		
		gameThread.start();
		
	}
	private void addSprite(Sprite sprite) {
		if(Upsprites.size() == 999){

			//Upsprites.remove(1);
		}
		Upsprites.add(sprite);



	}

	public void collision(Sprite e) throws Exception {
		System.out.println(playerSprite.player().getBoundsInParent());
		/*if(e.getLine().getBoundsInParent().intersects(playerSprite.getBoundsInParent())){
			System.out.println("HELP collusion");
		}*/


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
						Platform.runLater(() -> root.getChildren().add(sprite));
						sprite.gameObjectProperty().set(iteamObject);
						iteamsSprites.add(sprite);

						/*playerObject.setSpeedModfer(iteamobject.getSpeedModifer());
						playerObject.setSizeModifer(iteamobject.getSizeModifer());*/
						iteamObject.setSpeed(playerObject.getSpeedModfer());
						iteamRemover(iteamObject,sprite);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}


				}
			}
		}; iteamThread.start();
	}

	public void setIteams(){
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

	public void iteamRemover(iteam thisiteam, iteamSprite index){
		Thread iteamRemoverThread = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(thisiteam.getDuration());
					/*playerObject.setSizeModifer(-thisiteam.getSizeModifer());
					playerObject.setSpeedModfer(-thisiteam.getSizeModifer());*/
					Platform.runLater(()->root.getChildren().remove(thisiteam));
					iteamsSprites.remove(index);
					game.removeIteam(thisiteam);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}; iteamRemoverThread.start();
	}



	public Pane getRoot() {
		return root;
	}

	public void newgameObject(int i, int pixels){
	}


	}





