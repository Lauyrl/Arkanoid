package game.Entities;

import javafx.scene.image.Image;

public class Wall extends StaticEntity {
    public Wall(double x, double y, double w, double h) {
        super(x, y, w, h);
        setSprite(new Image(getClass().getResourceAsStream("/assets/Ball.png")));
    }

    @Override
    public void update() {}

    @Override
    public void setState(Object wallState) {}
}
