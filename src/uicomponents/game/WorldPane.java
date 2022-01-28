package uicomponents.game;

import application.Main;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.*;

import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.rgb;

public class WorldPane extends Canvas {

  // Attribute
  private List<Number> generatedWorldTopPath;
  private List<Number> generatedWorldBottomPath;
  private File imgFile = new File("src/resources/wave.png");
  private Image img = new Image(imgFile.toURI().toString());
  private ImagePattern ImgPatern = new ImagePattern(img);

  public List<Point2D> UpperCordinatesArray = new ArrayList<>();
  public List<Point2D> BottomCordinatesArray = new ArrayList<>();
  public List<Double> allXYUpperArray = new ArrayList<>();
  public List<Double> allXYBottomArray = new ArrayList<>();

  // Propertys
  public SimpleBooleanProperty isDoneLoadingLevel = new SimpleBooleanProperty(false);

  public WorldPane( ) {
    super( Main.WINDOW_WIDTH +200, Main.WINDOW_HEIGHT );
  }

  public List<Double> getAllXYUpperArray( ) {
    return allXYUpperArray;
  }

  public List<Double> getAllXYBottomArray( ) {
    return allXYBottomArray;
  }

  public void setCenterViewFrame( double playerPos ) {
    Platform.runLater(
      ( ) -> updateCanvasWall( (int)playerPos )
    );
  }

  private void updateCanvasSingleWall( List<Point2D> wallpoints, int yStartPos, int playerPosX ) {
    int drawStartPos = playerPosX;
    int centerChunkStep = drawStartPos / Main.MAP_CHUNK_WIDTH_PX;
    int drawingLoopIndex;

    GraphicsContext gc = this.getGraphicsContext2D();
    gc.setFill(ImgPatern);
    gc.setStroke(rgb(187, 134, 252));
    gc.beginPath();
    gc.moveTo(0, yStartPos);

    for (drawingLoopIndex = centerChunkStep; drawingLoopIndex < centerChunkStep +15; drawingLoopIndex++) {
      int x = (int)wallpoints.get(drawingLoopIndex).getX( ) - playerPosX;
      int y = (int)wallpoints.get(drawingLoopIndex).getY( );
      gc.lineTo( x, y );
    }

    gc.lineTo( playerPosX+ wallpoints.get( drawingLoopIndex ).getX( ),yStartPos );
    gc.closePath( );
    gc.fill( );
  }

  private void updateCanvasWall( int centerDrawIndex ) {
    GraphicsContext gt = this.getGraphicsContext2D( );
    gt.clearRect(0, 0,this.getWidth(), this.getHeight());

    updateCanvasSingleWall( UpperCordinatesArray, 0, centerDrawIndex );
    updateCanvasSingleWall( BottomCordinatesArray, Main.WINDOW_HEIGHT, centerDrawIndex );
  }

  private void drawWall( List<Number> wallSideSteps, List <Point2D>CordinatesArray ) {
    double modifer = 3;

    for (int curDrawIndex = 0; curDrawIndex < wallSideSteps.size( ); curDrawIndex++) {
      int curDisplayAmp = wallSideSteps.get( curDrawIndex ).intValue( );
      double curDisplayPos = curDrawIndex * Main.MAP_CHUNK_WIDTH_PX;

      CordinatesArray.add(
        new Point2D.Double(curDisplayPos,curDisplayAmp)
      );
    }
  }

  public void setWorldSteps( List<Double> allWorldSteps, double maxAmplitude) throws InterruptedException {
    int sampleCount = allWorldSteps.size( );

    this.generatedWorldTopPath = new ArrayList<>( );
    this.generatedWorldBottomPath = new ArrayList<>( );

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

    Thread t1 = new Thread(()->this.calculateAllPointsX(UpperCordinatesArray, allXYUpperArray)); t1.start();
    Thread t2 = new Thread(()->this.calculateAllPointsX(BottomCordinatesArray, allXYBottomArray)); t2.start();

    t1.join();
    t2.join();

    this.isDoneLoadingLevel.setValue(true);
  }

  private void calculateAllPointsX( List<Point2D> cordinatesArray, List<Double> allXYArray ) {
    int length = cordinatesArray.size();
    int last = (int) cordinatesArray.get(length-1).getX();
    double point1X = cordinatesArray.get(0).getX();
    double point2X = cordinatesArray.get(1).getX();
    double point1Y = cordinatesArray.get(0).getY();
    double point2Y = cordinatesArray.get(1).getY();
    double point3X = cordinatesArray.get(2).getX();
    int index = 2;
    double m;
    double c;
    double y;

    GraphicsContext gc = this.getGraphicsContext2D();

    m = (point1Y-point2Y)/(point1X-point2X);
    c = point1Y-(point1X*m);

    for(int x = 0; x < last;x++ ){
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
  }

  public SimpleBooleanProperty isDoneLoadingLevelProperty( ) {
    return isDoneLoadingLevel;
  }

  public double getLength( ) {
    return (int)UpperCordinatesArray.get(
      UpperCordinatesArray.size( ) -1
    ).getX( );
  }
}

