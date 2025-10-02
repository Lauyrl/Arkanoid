package game.Entities;

import javafx.scene.image.Image;

public abstract class Entity {
    private Image sprite;
    private double x, y, w, h;
    private int frameCounter = 0;

    public Entity(double x, double y, double w, double h) {
        setX(x);
        setY(y);
        setWidth(w);
        setHeight(h);
    }

    public abstract void update();
    public abstract void setState(Object entityState);

    public Image getSprite() {
        return sprite;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return w;
    }

    public double getHeight() {
        return h;
    }

    public double getLeftBound() {
        return x;
    }

    public double getRightBound() {
        return x + w;
    }
    
    public double getTopBound() {
        return y;
    }

    public double getBottomBound() {
        return y + h;
    }

    public double[] getCenter() {
        double[] center = new double[2];
        center[0] = (x + w) / 2;
        center[1] = (y + h) / 2;
        return center;
    }

    public int getFrameCounter() {
        return frameCounter;
    }

    public void setSprite(Image image){
        sprite = image;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(double w) {
        this.w = w;
    }

    public void setHeight(double h) {
        this.h = h;
    }

    public void setFrameCounter(int frameCounter) {
        this.frameCounter = frameCounter;
    }

    public void setCurrentSprite(SpriteUtil u) {
        setSprite(u.sprites[frameCounter / u.duration]);
        incrementFrameCounter(u.sprites.length * u.duration);
    }

    public void incrementFrameCounter(int limit) {
        frameCounter = (frameCounter + 1) % limit;
    }
}
