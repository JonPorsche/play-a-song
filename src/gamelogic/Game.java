package gamelogic;

import game.Audioinfo;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<GameObject> gameObjects = new ArrayList<>();
	private List<GameObject> iteamObjects = new ArrayList<>();
	private List<GameObject> coinObjects = new ArrayList<>();
	private  player player;
	private  Collison collisonChecker;
	private SimpleIntegerProperty  health;
	private SimpleIntegerProperty score;
	private IteamTakenListner iteamTaken;
	private SimpleDoubleProperty speedModifer;
	private CollisionWaveListner collsionWaveListner;
	private CoinCollcted coinCollcted;

	public Game(){
		iteamTaken = new IteamTakenListner();
		collsionWaveListner  = new CollisionWaveListner();
		coinCollcted = new CoinCollcted();
		speedModifer = new SimpleDoubleProperty();
		score = new SimpleIntegerProperty();
		health = new SimpleIntegerProperty();
		speedModifer.setValue(1);


	}
	public void setHealth(int health) {
		this.health.set(health);
	}

	public void setScore(int score) {
		this.score.set(score);
	}
	public void updateScore(int score){
		this.score.set(this.score.getValue()+score);
		System.out.println(this.score);
	}

	public int getHealth() {
		return health.getValue();
	}
	public int getScore(){
		return score.getValue();
	}
	public SimpleIntegerProperty scorePropery(){
		return scorePropery();
	}

	public void update(double deltaC) {
		double delta = deltaC * speedModifer.getValue();
		updateScore(1);
		double playerX = player.getX();
		double playerY = player.getY();
		double playerRadius = player.getRadius();
		for(GameObject gameObject : gameObjects) {
			gameObject.update(delta);
		}
	try {
		for(GameObject coinObject : coinObjects) {
			if(coinObject != null){
			coinObject.update(delta);
			if (collisonChecker.coinCollsion(playerRadius,playerX,playerY,coinObject.getX(),coinObject.getY(),coinObject.getRadius())){
				coinObject.setIsUsed(true);
				coinCollcted.coinCollsion(coinObject.getPoints(),player,this);
				coinObjects.remove(coinObject);
			}
		}}}catch (Exception E){

}
		try{
		for (GameObject iteamObject : iteamObjects){

			iteamObject.update(delta);
			if (collisonChecker.iteamPlayerCollison(delta,playerRadius,playerX,playerY,iteamObject.getX(),iteamObject.getY(),iteamObject.getWidth(), iteamObject.getHeight(), iteamObject.getIsVissable())){
				iteamTaken.IteamTaken(player,this, iteamObject.getSizeModifer(),iteamObject.getSpeedModifer(),iteamObject.getDuration());
				iteamObject.setIsUsed(true);
				removeIteam(iteamObject);


			}
		}}catch (Exception E){

		}

		player.update(delta);
	}
 	public SimpleDoubleProperty speedModiferProperty(){
		return  speedModifer;
	}
	public void setPlayer(player player){
		collisonChecker = new Collison();
		this.player = player;
		player.addIteamTakenListner(iteamTaken);


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


	public void updateHealth(int i) {
		health.add(i);
	}

	public void addCoin(Coin coinObject) {
		coinObjects.add(coinObject);

	}
}
