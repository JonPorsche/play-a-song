package scene;

import game.Audioinfo;
import gamelogic.Game;
import gamelogic.player;
import gamelogic.wave;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import uicomponents.Sprite;
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
	
	private List<Sprite> Upsprites = new ArrayList<>();
	private List<Sprite> Downsprites = new ArrayList<>();
	private uicomponents.playerSprite playerSprite;

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
		game.add(playerObject);
		playerSprites = new playerSprite();
		playerSprites.setRadius(40);
		playerSprites.setCenterX(playerObject.getX());
		playerSprites.setCenterX(playerObject.getY());
		playerSprites.setFill(RED);
		addSprite(playerSprites);
		root.getChildren().add(playerSprites);
		playerSprites.gameObjectProperty().set(playerObject);
		game.update(gameSpeed);
		audioinfo = new Audioinfo();
		array =audioinfo.getLeft("C:\\Users\\Johannes Weber\\Downloads\\10_3_BeispielAufbau_Simulation\\src\\game\\hallo.mp3");




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
						sprite.setStartY(0);
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
				if (lastRendered + FPNS_DELTA < now) {
					for(Sprite e : new ArrayList<Sprite>(Upsprites)) {
						e.render();
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

	public Pane getRoot() {
		return root;
	}

	public void newgameObject(int i, int pixels){
	}


	}





