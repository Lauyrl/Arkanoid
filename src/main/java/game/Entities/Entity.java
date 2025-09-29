package game.Entities;

import javafx.scene.image.Image;

public abstract class Entity {
    private Image sprite;
    private double x, y, w, h;
    
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

    public abstract void update(double frameTime);
}
