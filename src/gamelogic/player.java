package gamelogic;

import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.List;

public class player implements GameObject{
    private double x;
    private double y;
    private double radius;
    private int painWidth;
    private int painHeight;
    private double sizeModifer;
    private double speedModfer;
    private double gamespeed = 1;
    private double pixelpermiliscond;
    private boolean hasIteam;
    private List<IteamTaken> listeners;
    public player(){
        this.listeners = new ArrayList<>();
    }
    public void addIteamTakenListner(IteamTaken listener){
        listeners.add(listener);

    }
    @Override
    public void update(double delta) {
        y = y+speedModfer * delta;

    }

    @Override
    public void update(double gamespeed, double Radius, double playerX, double playerY) {
        y = y+speedModfer * gamespeed;

    }

    public void updateHeigt(double value){
        y = y + value;
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
            listner.IteamTaken(this.speedModfer - speedModifer, speedModifer);
        }
    }

    public double getSizeModifer() {
        return sizeModifer;
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
