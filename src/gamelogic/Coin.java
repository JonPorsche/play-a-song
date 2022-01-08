package gamelogic;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import uicomponents.CoinSprite;
import uicomponents.Sprite;

import java.util.List;

public class Coin implements GameObject{

    private final Game game;
    private double x;
    private double y;
    private double radius;
    private int points = 1000;
    private double gamespeed = 1;
    private double pixelpermiliscond;
    private boolean hasIteam;
    private List<IteamTaken> listeners;
    private double speedModifer;
    private Collison collsion;
    private SimpleBooleanProperty collisonWave;
    private SimpleBooleanProperty isUsed;
    private CoinSprite sprite;


    public Coin(Game game) {
        this.game = game;
        speedModifer = 1;
        isUsed = new SimpleBooleanProperty();
        isUsed.set(false);
        game.speedModiferProperty().addListener(new javafx.beans.value.ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                speedModifer = (double) newValue;
            }
        });

    }
    public void setPoints(int points){
        this.points = points;

    }

    @Override
    public void update(double delta) {
        x = x-delta;
        System.out.println("worked");

    }


    @Override
    public void update(double gamespeed, double Radius, double playerX, double playerY) {

    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setX(double x) {
    this.x = x;
    }

    @Override
    public void setY(double y) {
    this.y = y;
    }

    @Override
    public double getAngle() {
        return 0;
    }
    public void setRadius(double radius){
        this.radius = radius;
    }
    @Override
    public double getRadius() {
        return  radius;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public boolean getIsVissable() {
        return false;
    }

    @Override
    public void setIsUsed(boolean isUsed) {
        this.isUsed.set(isUsed);

    }

    @Override
    public double getSizeModifer() {
        return 0;
    }

    @Override
    public double getSpeedModifer() {
        return 0;
    }

    @Override
    public void setSpeedModifer(double speedModifer) {

    }

    @Override
    public long getDuration() {
        return 0;
    }

    @Override
    public SimpleBooleanProperty isUseDProperty() {
        return isUsed;
    }
    @Override
    public int getPoints(){
        return points;
    }

    public void setSprite(CoinSprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
