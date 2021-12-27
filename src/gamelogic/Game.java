package gamelogic;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<GameObject> gameObjects = new ArrayList<>();
	private List<GameObject> iteamObjects = new ArrayList<>();

	public void update(double delta) {
		for(GameObject gameObject : gameObjects) {
			gameObject.update(delta);
		}
		for (GameObject iteamObject : iteamObjects){
			iteamObject.update(delta);
		}
	}
	
	public void add(GameObject object) {
		if(gameObjects.size() == 999){
			//gameObjects.remove(1);
		}
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
