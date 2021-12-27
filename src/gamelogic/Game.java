package gamelogic;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<GameObject> gameObjects = new ArrayList<>();

	public void update(double delta) {
		for(GameObject gameObject : gameObjects) {
			gameObject.update(delta);
		}
	}
	
	public void add(GameObject object) {
		if(gameObjects.size() == 999){
			gameObjects.remove(1);
		}
		gameObjects.add(object);


	}

	
	public void start() {
		// hier würde der Update-Thread gestartet
		
	}

}
