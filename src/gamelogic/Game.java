package gamelogic;

import game.Audioinfo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<GameObject> gameObjects = new ArrayList<>();
	private List<GameObject> iteamObjects = new ArrayList<>();
	private  player player;
	private  Collison collisonChecker;



	public void update(double delta) {
		double playerX = player.getX();
		double playerY = player.getY();
		double playerRadius = player.getRadius();
		for(GameObject gameObject : gameObjects) {
			gameObject.update(delta);
		}
		try{
		for (GameObject iteamObject : iteamObjects){

			iteamObject.update(delta);
			if (collisonChecker.iteamPlayerCollison(delta,playerRadius,playerX,playerY,iteamObject.getX(),iteamObject.getY(),iteamObject.getWidth(), iteamObject.getHeight(), iteamObject.getIsVissable())){
				iteamObject.setIsUsed(true);
				Audioinfo sound = new Audioinfo();
				sound.play( new File( "src/assets/FFXIV_Remove_Item.mp3" ).getAbsolutePath());
				Thread thread = new Thread() {

					public void run(){
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						removeIteam(iteamObject);}


				};thread.start();
			}
		}}catch (Exception E){

		}
		player.update(delta);
	}

	public void setPlayer(player player){
		collisonChecker = new Collison();
		this.player = player;
		player.addIteamTakenListner(new IteamTakenListner());

	}

	public void add(GameObject object) {

		gameObjects.add(object);




	}
	public void addIteam(GameObject object) {

		iteamObjects.add(object);


	}
	public void removeIteam(GameObject object){
		iteamObjects.remove(object);
	}

	
	public void start() {
		// hier würde der Update-Thread gestartet
		
	}

}
