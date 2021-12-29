package gamelogic;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<GameObject> gameObjects = new ArrayList<>();
	private List<GameObject> iteamObjects = new ArrayList<>();
	private  GameObject player;
	private  Collison collisonChecker;

	public void update(double delta) {
		double playerX = player.getX();
		double playerY = player.getY();
		double playerRadius = player.getRadius();
		for(GameObject gameObject : gameObjects) {
			gameObject.update(delta);
		}
		for (GameObject iteamObject : iteamObjects){

			iteamObject.update(delta);
			collisonChecker.iteamPlayerCollison(delta,playerRadius,playerX,playerY,iteamObject.getX(),iteamObject.getY(),iteamObject.getWidth(), iteamObject.getHeight(), iteamObject.getIsVissable(), (iteam)iteamObject, this);
		}
		player.update(delta);
	}

	public void setPlayer(GameObject player){
		collisonChecker = new Collison();
		this.player = player;

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
