package gamelogic;

import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.List;

public class player implements GameObject{
    private final Game game;
    private double x;
    private double y;
    private double radius;
    private double sizeModifer;
    private double speedModfer;
    private double gamespeed = 1;
    private double pixelpermiliscond;
    private boolean hasIteam;
    private List<IteamTaken> listeners;
    private Collison collsion;
    private SimpleBooleanProperty collisonWave;
    public player(Game game){
        this.game = game;
        this.listeners = new ArrayList<>();
        collsion = new Collison();
        collisonWave = new SimpleBooleanProperty();
        collisonWave.setValue(false);
    }
    public void addIteamTakenListner(IteamTaken listener){
        listeners.add(listener);

    }
    public SimpleBooleanProperty getCollisonWave() {
        return collisonWave;
    }
    @Override
    public void update(double delta) {
        y = y+speedModfer * delta;
        if (collsion.wavePlayerCollsion(y,x, radius)){
            collisonWave.setValue(Boolean.TRUE);



        }else {
            collisonWave.setValue(false);
        }

    }

    @Override
    public void update(double gamespeed, double Radius, double playerX, double playerY) {
        y = y+speedModfer * gamespeed;

    }

    public void updateHeigt(double value){

        y = y + value;
        if (collsion.wavePlayerCollsion(y,x, radius)){
            collisonWave.setValue(Boolean.TRUE);
            //y= wave.getBorderX - radius;


        }else {
            collisonWave.setValue(false);
        }

    }
    public void setgamespeed(double pixelpermiliscond){
        this.pixelpermiliscond= pixelpermiliscond;

    }

    public void setRadius(double radius){
        this.radius = radius;

    }

    public double getRadius() {
        return radius;
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
    public void setIsUsed(boolean isUseD) {

    }

    @Override
    public SimpleBooleanProperty isUseDProperty() {
        return null;
    }

    @Override
    public int getPoints() {
        return 0;
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

    public boolean gethasIteam() {
        return hasIteam;
    }

    public double getSpeedModfer() {
        return speedModfer;
    }

    public void setSpeedModfer(double speedModifer) {

        this.speedModfer = speedModifer+speedModfer;
        for (IteamTaken listner : listeners) {

        }
    }

    public double getSizeModifer() {
        return sizeModifer;
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

    public void setSizeModifer(double sizeModifer) {
        this.sizeModifer = this.sizeModifer +sizeModifer;
    }

    public void setHasIteam(boolean hasIteam) {
        this.hasIteam = hasIteam;
    }

    public double getPixelpermiliscond() {
        return pixelpermiliscond;
    }

    public void setPixelpermiliscond(double pixelpermiliscond) {
        this.pixelpermiliscond = pixelpermiliscond;
    }

    public void handleSpeedEvent(CollisionEvent event ) {
    }
}
