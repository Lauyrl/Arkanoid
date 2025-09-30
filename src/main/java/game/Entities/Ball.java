package game.Entities;

import javafx.scene.image.Image;

public class Ball extends Entity {
    private double velX, velY;
    
    public Ball(double x, double y, double w, double h) {
        setSprite(new Image(getClass().getResourceAsStream("/assets/Ball.png")));
        setX(x);
        setY(y);
        setWidth(w);
        setHeight(h);
        velX = 5;
        velY = 5;
    }

    @Override
    public void update(double frameTime) {
        setX(getX() + velX * frameTime);
        setY(getY() + velY * frameTime);
        //System.out.printf("(%f, %f)%n", getX(), getY());
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelX(double v) {
        velX = v;
    }

    public void setVelY(double v) {
        velY = v;
    }
}
