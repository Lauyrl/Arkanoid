package game.Entities;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ball extends Entity {
    private double velX, velY;
    
    public Ball(double x, double y) {
        setSprite(new Image(getClass().getResourceAsStream("/assets/Ball.png")));
        setX(x);
        setY(y);
        setWidth(20);
        setHeight(20);
        velX = 10;
        velY = 10;
    }

    public void update() {
        setX(getX() + velX);
        setY(getY() + velY);
        // đâm vào 2 bên thì đảo vận tốc x, đâm vào trên hoặc dưới thì đảo vận tốc y
        if (getX() < 200)               velX = -velX;
        if (getX() + getWidth() > 600)  velX = -velX;
        if (getY() < 200)               velY = -velY;
        if (getY() + getHeight() > 800) velY = -velY;
        System.out.printf("(%f, %f)", getX(), getY());
    }
}
