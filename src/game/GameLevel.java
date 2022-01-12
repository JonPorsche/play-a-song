package game;

import game.sprites.Iteam;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static application.Main.PLAYER_RADIUS;


// @ToDo: SongMapGenerator, HashMap<int x, obj iteam>,

public class GameLevel {
  private String songsPath;
  private List<Number> mapChunks;
  private HashMap<Number, Iteam> sortedItemsByPosX; // +-10
  private IntegerProperty playerPosX = new SimpleIntegerProperty();

  public GameLevel( String newLevelSongPath ) {
    this.songsPath = newLevelSongPath;
  }

  private void generateMapchunks() {
    // this.mapChunks.add( currentAmp );
  }

  public List<Iteam> iteamsInDangerZoner() {
    int curPlayerPosX = this.playerPosX.getValue( ).intValue( );
    List<Iteam> allResults = new ArrayList<>();

    for (int areaIndex=curPlayerPosX-PLAYER_RADIUS; areaIndex < curPlayerPosX+PLAYER_RADIUS; areaIndex++) {
      Iteam result = sortedItemsByPosX.get( areaIndex );

      if (result != null) allResults.add( result );
    }

    return allResults;
  }
}
