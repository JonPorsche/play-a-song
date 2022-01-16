package uicomponents.game;

import application.Main;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class WorldPane extends Canvas {

  public WorldPane(double width, double height) {
    super( width*15, height);


  }
  public void setCenterViewFrame(double playerPos ) {
    Platform.runLater(
            ( ) -> this.setTranslateX( 0 - playerPos )
    );
  }





  private void drawWall(List<Number> wallSideSteps, int yStartPos ) {
    GraphicsContext gc = this.getGraphicsContext2D();

    gc.beginPath( );
    gc.setFill( Color.BLACK );
    gc.setStroke( Color.BLACK );

    gc.moveTo( 0, yStartPos );

    for (int curDrawIndex = 0; curDrawIndex < wallSideSteps.size( ); curDrawIndex++) {
      int curDisplayAmp = wallSideSteps.get( curDrawIndex ).intValue( );
      double curDisplayPos = curDrawIndex * 100;

      gc.lineTo( curDisplayPos, curDisplayAmp );
    }
    gc.stroke( );
    gc.fill( );
    gc.closePath( );
  }




  private void drawWall(List<Number> wallSideSteps ) {
    this.drawWall( wallSideSteps, 0 );
  }

  public void setWorldSteps( List<Double> allWorldSteps, double maxAmplitude ) {
    List<Number> generatedWorldTopPath = new ArrayList<>( );
    List<Number> generatedWorldBottomPath = new ArrayList<>( );
    int sampleCount = allWorldSteps.size( );

    for (int curAmpPos = 0; curAmpPos < sampleCount; curAmpPos++) {
      double curAmpValue = allWorldSteps.get( curAmpPos );
      if (curAmpValue < 0) curAmpValue = 0 -curAmpValue;

      double curDisplayAmpPercent = 100 /(maxAmplitude / curAmpValue);
      double t = (maxAmplitude / curAmpValue);
      if (t > 100) t = 0;

      if (curDisplayAmpPercent > 95 || curAmpValue < 0) curDisplayAmpPercent = 95;

      double curDisplayAmp = curDisplayAmpPercent * 2.5;

      generatedWorldTopPath.add( curDisplayAmp );
      generatedWorldBottomPath.add( Main.WINDOW_HEIGHT -curDisplayAmp );
    }

    this.drawWall( generatedWorldTopPath );
    this.drawWall( generatedWorldBottomPath, Main.WINDOW_HEIGHT );
  }


  public double getLength() {
   return this.getWidth();
  }
}

