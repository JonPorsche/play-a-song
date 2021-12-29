package gamelogic;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;
import uicomponents.Sprite;
import uicomponents.iteamSprite;

import static javafx.scene.paint.Color.BLUE;

public class iteam implements GameObject{
    private double x;
    private double y;
    private double sizeModifer;
    private double speedModifer;
    private double height;
    private double width;
    private double speed;
    private long duration;
    private SimpleBooleanProperty isUsed;
    private double pixelpermiliscond;
    public Color iteamColor = BLUE;
    private iteamSprite sprite;

    public iteam(){
        isUsed = new SimpleBooleanProperty();
    }

    @Override
    public void update(double delta) {
        x = x-(pixelpermiliscond *delta+2);

    }
    public Color getIteamColor(){
        return iteamColor;
    }

    @Override
    public void update(double gamespeed, double Radius, double playerX, double playerY) {




    }
    public void setgamespeed(double pixelpermiliscond){
        this.pixelpermiliscond= pixelpermiliscond;

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

    @Override
    public double getRadius() {
        return 0;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public boolean getIsVissable() {
        return false;
    }

    public double getSizeModifer() {
        return sizeModifer;
    }

    public void setSizeModifer(double sizeModifer) {
        this.sizeModifer = sizeModifer;
    }

    public double getSpeedModifer() {
        return speedModifer;
    }

    public void setSpeedModifer(double speedModifer) {
        this.speedModifer = speedModifer;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setSpeed(double speedModfer) {
        this.speed = speedModfer;
    }



    public boolean isIsUseD() {
        return isUsed.get();
    }
    @Override
    public SimpleBooleanProperty isUseDProperty() {
        return isUsed;
    }
    @Override
    public void setIsUsed(boolean isUseD) {
        this.isUsed.set(isUseD);
    }

    public void setSprite(iteamSprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
