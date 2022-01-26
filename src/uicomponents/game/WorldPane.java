package uicomponents.game;

import application.Main;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorldPane extends Canvas {
  public List<Point2D> UpperCordinatesArray = new ArrayList<>();
  public List<Point2D> BottomCordinatesArray = new ArrayList<>();
  public List<Double> allXYUpperArray = new ArrayList<>();
  public List<Double> allXYBottomArray = new ArrayList<>();
  public SimpleBooleanProperty isDoneLoadingLevel = new SimpleBooleanProperty(false);
  //public SimpleIntegerProperty isLoaded = new SimpleIntegerProperty(0);
  private List<Number> generatedWorldTopPath;
  private List<Number> generatedWorldBottomPath;

  public WorldPane( ) {
    super( Main.WINDOW_WIDTH +200, Main.WINDOW_HEIGHT );
    this.setTranslateX( -100 );

    System.out.println(this.isResizable());

    //this.setStyle("-fx-background-color:rgb(0, 100, 0);");
    /*isLoaded.addListener((o, oldP, newP) -> {
      if (newP == (Number) 2) {
        isDoneLoadingLevel.setValue(true);
        System.out.println("done");
      }
    });*/
  }

  public List<Double> getAllXYUpperArray() {
    return allXYUpperArray;
  }

  public List<Double> getAllXYBottomArray() {
    return allXYBottomArray;
  }

  public void setCenterViewFrame(double playerPos ) {
    /*Platform.runLater(
      ( ) -> this.setTranslateX( 0 )
    );*/
    this.updateCanvasWall( (int)playerPos );
  }

  private void updateCanvasSingleWall( List<Number> wallSideSteps, int yStartPos, int playerPosX ) {
    GraphicsContext gc = this.getGraphicsContext2D( );
    gc.clearRect(0, 0, Main.WINDOW_WIDTH +200, Main.WINDOW_HEIGHT );
    gc.setFill( Color.DIMGREY );
    gc.fillRect(0, 0, Main.WINDOW_WIDTH +200, Main.WINDOW_HEIGHT);

    // Wall Color
    gc.setFill( Color.BLACK );
    gc.setStroke( Color.BLACK );

    double modifer = 3;
    boolean firstPoint = false;
    int canvasWidth = Main.WINDOW_WIDTH +200;
    int stepsPerFrame = canvasWidth / Main.MAP_CHUNK_WIDTH_PX;
    int drawStartPos = playerPosX - (canvasWidth /2);
    int drawStartStepIndex = drawStartPos / Main.MAP_CHUNK_WIDTH_PX;

    for (int curFrameStep = 0; curFrameStep < stepsPerFrame; curFrameStep++) {
      int curDrawIndex = drawStartStepIndex + curFrameStep;  //drawStartIndex + ( curFrameStep * Main.MAP_CHUNK_WIDTH_PX );
      if (curDrawIndex < 0
      ||  curDrawIndex >= wallSideSteps.size()
      ) {
        continue;
      }

      int curDisplayAmp = wallSideSteps.get( curDrawIndex ).intValue( );
      int curDisplayPos = 0 - (curDrawIndex * Main.MAP_CHUNK_WIDTH_PX) - (drawStartPos % Main.MAP_CHUNK_WIDTH_PX);

      if (!firstPoint) {
        gc.beginPath( );
        gc.moveTo( curDisplayPos, yStartPos );

        firstPoint = true;
      }
      gc.lineTo( curDisplayPos, curDisplayAmp);
    }

    /*for (int curDrawIndex = 0; curDrawIndex < wallSideSteps.size( ) && Main.WINDOW_WIDTH > curDrawIndex * Main.MAP_CHUNK_WIDTH_PX; curDrawIndex++) {
      int curDisplayAmp = (wallSideSteps.get( curDrawIndex ).intValue( ));
      double curDisplayPos = curDrawIndex * Main.MAP_CHUNK_WIDTH_PX;

      gc.lineTo( curDisplayPos, curDisplayAmp);
    }*/

    gc.lineTo( Main.WINDOW_WIDTH +200, yStartPos );
    gc.closePath( );
    gc.fill( );
  }

  private void updateCanvasWall( int centerDrawIndex ) {
    this.updateCanvasSingleWall( this.generatedWorldTopPath, 0, centerDrawIndex );
    //this.updateCanvasSingleWall( this.generatedWorldBottomPath, Main.WINDOW_HEIGHT, centerDrawIndex );;
  }

  private void drawWall(List<Number> wallSideSteps, List <Point2D>CordinatesArray) {
    double modifer = 3;
    for (int curDrawIndex = 0; curDrawIndex < wallSideSteps.size( ) && Main.WINDOW_WIDTH > curDrawIndex * Main.MAP_CHUNK_WIDTH_PX; curDrawIndex++) {
      int curDisplayAmp = (wallSideSteps.get( curDrawIndex ).intValue( ));
      double curDisplayPos = curDrawIndex * Main.MAP_CHUNK_WIDTH_PX;

      CordinatesArray.add(new Point2D.Double(curDisplayPos,curDisplayAmp));
    }
  }

  public void setWorldSteps( List<Double> allWorldSteps, double maxAmplitude) {
    this.generatedWorldTopPath = new ArrayList<>( );
    this.generatedWorldBottomPath = new ArrayList<>( );
    int sampleCount = allWorldSteps.size( );

    for (int curAmpPos = 0; curAmpPos < sampleCount; curAmpPos++) {
      double curAmpValue = allWorldSteps.get( curAmpPos );
      if (curAmpValue < 0) curAmpValue = 0 -curAmpValue;

      double curDisplayAmpPercent = 100 /(maxAmplitude / curAmpValue);
      double t = (maxAmplitude / curAmpValue);
      if (t > 100) t = 0;

      if (curDisplayAmpPercent > 95 || curAmpValue < 0) curDisplayAmpPercent = 95;

      double curDisplayAmp = Main.MAP_CHUNK_BASE_HEIGHT_PX + curDisplayAmpPercent * 2.5;

      this.generatedWorldTopPath.add( curDisplayAmp );
      this.generatedWorldBottomPath.add( Main.WINDOW_HEIGHT -curDisplayAmp );
    }
    this.drawWall( generatedWorldTopPath, UpperCordinatesArray );
    this.drawWall( generatedWorldBottomPath, BottomCordinatesArray );
    Thread t1 = new Thread(()->this.calculateallXpoints(UpperCordinatesArray, allXYUpperArray)); t1.start();
    Thread t2 = new Thread(()->this.calculateallXpoints(BottomCordinatesArray, allXYBottomArray)); t2.start();

    this.isDoneLoadingLevel.setValue( true );
  }
  private void calculateallXpoints(List<Point2D> cordinatesArray, List<Double> allXYArray ) {
    int length = cordinatesArray.size();
    int last = (int) cordinatesArray.get(length-1).getX();
    double point1X = cordinatesArray.get(0).getX();
    double point2X= cordinatesArray.get(1).getX();
    double point1Y= cordinatesArray.get(0).getY();
    double point2Y= cordinatesArray.get(1).getY();
    double point3X= cordinatesArray.get(2).getX();
    int index = 2;
    GraphicsContext gc = this.getGraphicsContext2D();
    double m;
    double c;
    double y;
    m = (point1Y-point2Y)/(point1X-point2X);
    c = point1Y-(point1X*m);
    for(int x = 0; x < this.getWidth();x++ ){
      if(x > point2X && index<length-1){
        point1X = point2X;
        point1Y = point2Y;
        point2X = point3X;
        point2Y = cordinatesArray.get(index).getY();
        index++;
        point3X =cordinatesArray.get(index).getX();

        m = (point2Y-point1Y)/(100);

        c = point1Y-(point1X*m);
      }

      y =(m * x)+c;
      String text = String.valueOf(x);


      allXYArray.add(y);
    }
    //isLoaded.set(isLoaded.getValue()+1);
  }

  public SimpleBooleanProperty isDoneLoadingLevelProperty() {
    return isDoneLoadingLevel;
  }

  public double getLength() {
   return this.getWidth();
  }
}

