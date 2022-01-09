package scene;

import gamelogic.*;
import gamelogic.sprites._old_player;
import javafx.scene.layout.Pane;

public class GameController {
	private GameView gameDisplayPane;
	private Game game;

	public GameController(Game game) {
		GameView view = new GameView();
		
		gameDisplayPane = view;
		
		this.game = game;
		
		//initialize();
	}

	public _old_player getPlayerObject( ) {
		return playerObject;
	}

	/*private void addSprite( Sprite sprite ) {
		if(Upsprites.size() == 999){
			Platform.runLater( ( )-> gameDisplayPane.getChildren().remove( Upsprites.get(1)) );
			Upsprites.remove(1);
		}
		Upsprites.add(sprite);

	}*/

	/*public void collision(Sprite e) throws Exception {

		if(e.getRectangle().getBoundsInParent().intersects(playerSpritesobject.getBoundsInParent())){
			System.out.println("HELP collusion");
		}


	}*/

	/*public void iteamCollison(Sprite iteam){
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


	}*/



	/*public void SpawnIteam(){
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
	}*/

	/*public void setIteams(){
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
		iteams.add(iteamObject);*-/

	}*/

	public Pane getGameDisplayPane() {
		return gameDisplayPane;
	}

	public void newgameObject(int i, int pixels){
	}


	}





