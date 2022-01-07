package gamelogic;

import application.Main;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class Game {
	private Player playerObject = new Player( );

	// Listen
	private List<GameObject> mapChunks = new ArrayList<>();
	private HashMap<Number, GameObject> iteams = new HashMap<>();

	// Propertys
	private IntegerProperty curPlayerPosX = new SimpleIntegerProperty();
	private IntegerProperty curPlayerPosY = new SimpleIntegerProperty();
	private ObjectProperty<Boolean> gameIsPlaying = new SimpleObjectProperty<>();

	public void update( double delta ) {
		for (GameObject curMapChunk : mapChunks) {
			curMapChunk.update( delta );
		}
		for (GameObject iteamObject : iteams.values( )){
			iteamObject.update( delta );
		}
	}

	public IntegerProperty playerPosXProperty( ) {
		return this.curPlayerPosX;
	}

	public IntegerProperty playerPosYProperty( ) {
		return this.curPlayerPosY;
	}

	public int getPlayerPosX( ) {
		return this.curPlayerPosX.getValue( ).intValue( );
	}
	public int getPlayerPosY( ) {
		return this.curPlayerPosY.getValue( ).intValue( );
	}
	
	public void addMapChunk( WaveWorldObj waveChunk ) {
		this.mapChunks.add( waveChunk );
	}

	public void addIteam( GameObject newIteam ) {
		this.iteams.put( newIteam.getX( ), newIteam );
	}

	public void removeIteam( GameObject selectedIteam ){
		this.iteams.remove( selectedIteam );
	}
	public void clearOldIteams( int selectedPosX ){
		this.iteams.remove( selectedPosX );
	}
	public boolean levelIsFinished( ) {
		return this.curPlayerPosX.getValue( ).intValue( ) < this.mapChunks.size( ) * Main.MAP_CHUNK_WIDTH_PX;
	}
	public boolean gameIsPlaying( ) {
		return this.gameIsPlaying.getValue( ).booleanValue( );
	}

	private void worldSlideNext( ) {
		this.curPlayerPosX.setValue( this.getPlayerPosX() +1 );
	}

	
	public void start( ) {
		// hier würde der Update-Thread gestartet

		int curGamePos = 0 - Main.WINDOW_WIDTH /2;
		//for (; curGamePos < amplitudeArray.length*100; curGamePos++) {
		while (!this.levelIsFinished( )) {
			try {
				Thread.sleep( 5 );//17,5 );

				if (this.gameIsPlaying( )) {
					/*final int finalCurGamePos = curGamePos;
					Platform.runLater(
							() -> gameDisplayPane.setCenterViewFrame(finalCurGamePos)
					);*/
					this.worldSlideNext( );
				}

			} catch (InterruptedException e) {
				e.printStackTrace( );
			}
		}
	}

}
