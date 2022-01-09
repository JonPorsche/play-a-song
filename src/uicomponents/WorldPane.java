package uicomponents;

import application.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class WorldPane extends Canvas {

    public WorldPane( ) {
        super( Main.WINDOW_WIDTH *15, Main.WINDOW_HEIGHT);
        //this.setLayoutX();
    }

    private void drawWall(List<Number> wallSideSteps, int yStartPos ) {
        GraphicsContext gc = this.getGraphicsContext2D();

        //gc.fillOval(130, 30, 80, 80);

        gc.beginPath();
        gc.setFill( Color.BLACK );
        gc.setStroke( Color.BLACK );

        gc.moveTo( 0, yStartPos );

        /*gc.setStroke(Color.FORESTGREEN.brighter());
        gc.setLineWidth(5);
        gc.setFill(Color.FORESTGREEN);*/

        /*gc.setStroke(Color.FORESTGREEN.brighter());
        gc.setLineWidth(5);
        gc.strokeOval(30, 30, 80, 80);
        gc.setFill(Color.FORESTGREEN);
        gc.fillOval(130, 30, 80, 80);*/

        for (int curDrawIndex = 0; curDrawIndex < wallSideSteps.size( ); curDrawIndex++) {
            int curDisplayAmp = wallSideSteps.get( curDrawIndex ).intValue( );
            double curDisplayPos = curDrawIndex * 100;

            gc.lineTo( curDisplayPos, curDisplayAmp );
        }
        gc.stroke();
        gc.fill();
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
            float curDisplayAmpPercent = (float)( curAmpValue / 100 * maxAmplitude );

            if (curDisplayAmpPercent > 95 || curAmpValue < 0) curDisplayAmpPercent = 95;

            double curDisplayAmp = curDisplayAmpPercent * 2.5;

            generatedWorldTopPath.add( curDisplayAmp );
            generatedWorldBottomPath.add( Main.WINDOW_HEIGHT -curDisplayAmp );
        }

        this.drawWall( generatedWorldTopPath );
        this.drawWall( generatedWorldBottomPath, Main.WINDOW_HEIGHT );

        /*WorldPane WPScope = this;

        Path worldTopCornerPath = new Path( );
        worldTopCornerPath.setStroke( Color.BLACK );
        worldTopCornerPath.setStrokeWidth( 2.0 );

        Path worldBottomCornerPath = new Path( );
        worldBottomCornerPath.setStroke( Color.BLACK );
        worldBottomCornerPath.setStrokeWidth(2.0);

        MoveTo worldTopCornerPath_fPos = new MoveTo( 0, 0 );
        MoveTo worldBottomCornerPath_fPos = new MoveTo( 0, Main.WINDOW_HEIGHT );

        worldTopCornerPath.getElements().add( worldTopCornerPath_fPos );
        worldBottomCornerPath.getElements().add( worldBottomCornerPath_fPos );

        for (int curAmpPos = 0; curAmpPos < allWorldSteps.size( ); curAmpPos++) {
            double curAmpValue = allWorldSteps.get( curAmpPos );
            double curDisplayPos = curAmpPos * 25;
            float curDisplayAmpPercent = (float)( curAmpValue / 100 * maxAmplitude );
            double curDisplayAmp = curDisplayAmpPercent * 475;

            worldTopCornerPath.getElements( ).add( new LineTo(
                curDisplayPos, 0 -curDisplayAmp
            ) );
            worldBottomCornerPath.getElements( ).add( new LineTo(
                curAmpPos * 25, curDisplayAmp
            ) );
        }

        worldTopCornerPath.getElements( ).add( new ClosePath() );
        worldBottomCornerPath.getElements( ).add( new ClosePath() );

        //Group worldCornersGroup = new Group( worldTopCornerPath, worldBottomCornerPath );

        this.getChildren().addAll( worldTopCornerPath, worldBottomCornerPath );*/
    }
}
