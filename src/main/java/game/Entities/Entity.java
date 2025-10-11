package game.Entities;

import game.Entities.DynamicEntities.Ball;
import game.Entities.DynamicEntities.Paddle;
import game.Entities.StaticEntities.Brick;
import game.Entities.StaticEntities.PowerUp;
import game.Entities.StaticEntities.Wall;
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
    public abstract void relayCollision(Entity e, double oldLeft, double oldRight, double oldTop, double oldBot, double tX, double tY);
    public void respondToCollisionWithBall(Ball e, double oldLeft, double oldRight, double oldTop, double oldBot, double tX, double tY) {}
    public void respondToCollisionWithWall(Wall e, double oldLeft, double oldRight, double oldTop, double oldBot, double tX, double tY) {}
    public void respondToCollisionWithBrick(Brick e, double oldLeft, double oldRight, double oldTop, double oldBot, double tX, double tY) {}
    public void respondToCollisionWithPaddle(Paddle e, double oldLeft, double oldRight, double oldTop, double oldBot, double tX, double tY) {}
    public void respondToCollisionWithPowerUp(PowerUp e, double oldLeft, double oldRight, double oldTop, double oldBot, double tX, double tY) {}

    public void incrementFrameCounter(int limit) {
        frameCounter = (frameCounter + 1) % limit;
    }

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

    public double getXCenter() {
        return x + w / 2;
    }

    public double getYCenter() {
        return y + h / 2;
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
}
