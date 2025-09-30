package game.Entities;

import javafx.scene.image.Image;

public class Wall extends Entity {
    public Wall(double x, double y, double w, double h) {
        setSprite(new Image(getClass().getResourceAsStream("/assets/Ball.png")));
        setX(x);
        setY(y);
        setWidth(w);
        setHeight(h);
    }

    @Override
    public void update() {}

    @Override
    public void changeState(Object wallState) {}
    
    @Override
    public void setStateSprite() {}
}
