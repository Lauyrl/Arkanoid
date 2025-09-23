package game.Entities;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ball {
    private Image sprite;
    private double x, y, w, h;
    private double velX, velY;
    
    public Ball() {
        sprite = new Image(getClass().getResourceAsStream("/assets/Ball.png"));
        x = 300;
        y = 300;
        w = 20;
        h = 20;
        velX = 10;
        velY = 10;
    }

    public void update() {
        x += velX;
        y += velY;
        // đâm vào 2 bên thì đảo vận tốc x, đâm vào trên hoặc dưới thì đảo vận tốc y
        if (x < 200)     velX = -velX;
        if (x + w > 600) velX = -velX;
        if (y < 200)     velY = -velY;
        if (y + h > 800) velY = -velY;
        System.out.printf("(%f, %f)", x, y);
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

}
