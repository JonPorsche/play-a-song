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
  List<Double> allXYUpperArray = new ArrayList<>();
  List<Double> allXYBottomArray = new ArrayList<>();
  public SimpleBooleanProperty isDone = new SimpleBooleanProperty(false);
  public SimpleIntegerProperty isLoaded = new SimpleIntegerProperty(0);
  public WorldPane( int canWidth, int canHeight ) {
    super(
            canWidth , //Main.gameManager.getCanvasWidthPx( ),
            canHeight
    );

    System.out.println(this.isResizable());
    System.out.println(canHeight);
    this.setStyle("-fx-background-color:rgb(0, 100, 0);");
    isLoaded.addListener((o, oldP, newP) -> {
      if (newP == (Number) 2) {
        isDone.set(true);
        System.out.println("done");

      }});
  }

  public List<Double> getAllXYUpperArray() {
    return allXYUpperArray;
  }

  public List<Double> getAllXYBottomArray() {
    return allXYBottomArray;
  }

  public void setCenterViewFrame(double playerPos ) {

    Platform.runLater(
      ( ) -> this.setTranslateX( 0 - playerPos )
    );
  }
  private void drawWall(List<Number> wallSideSteps, int yStartPos, List <Point2D>CordinatesArray, Boolean isTop) {
    GraphicsContext gc = this.getGraphicsContext2D();
    gc.beginPath( );
    gc.setFill( Color.BLACK );
    gc.setStroke( Color.BLACK );
    gc.moveTo( 0, yStartPos );
    double heigth = 640;
    double topheight = heigth/2;

    double modifer = 3;
    for (int curDrawIndex = 0; curDrawIndex < wallSideSteps.size( ); curDrawIndex++) {
      int curDisplayAmp = (wallSideSteps.get( curDrawIndex ).intValue( ));
      double curDisplayPos = curDrawIndex * Main.MAP_CHUNK_WIDTH_PX;
      if(curDisplayAmp >heigth){
        curDisplayPos = 640;
      }
      if (curDisplayAmp<0){
        curDisplayPos = 1;
      }
      gc.lineTo( curDisplayPos, curDisplayAmp);
      CordinatesArray.add(new Point2D.Double(curDisplayPos,curDisplayAmp));




    }

    gc.stroke( );
    gc.fill( );
    gc.closePath( );
  }
  private void drawWall(List<Number> wallSideSteps, List <Point2D>CordinatesArray,boolean isTop ) {
    this.drawWall( wallSideSteps, 0,CordinatesArray, isTop);
  }

  public void setWorldSteps( List<Double> allWorldSteps, double maxAmplitude) {
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
    this.drawWall( generatedWorldTopPath,UpperCordinatesArray,true);
    this.drawWall( generatedWorldBottomPath, Main.WINDOW_HEIGHT,BottomCordinatesArray,false);
    Thread t1 = new Thread(()->this.calculateallXpoints(UpperCordinatesArray, allXYUpperArray)); t1.start();
    Thread t2 = new Thread(()->this.calculateallXpoints(BottomCordinatesArray, allXYBottomArray)); t2.start();
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
    isLoaded.set(isLoaded.getValue()+1);




  }

  public SimpleBooleanProperty isDoneProperty() {
    return isDone;
  }

  public double getLength() {
   return this.getWidth();
  }
}

