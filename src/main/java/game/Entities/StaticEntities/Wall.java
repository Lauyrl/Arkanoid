package game.Entities.StaticEntities;

import game.Entities.Entity;
import javafx.scene.image.Image;

public class Wall extends StaticEntity {
    private static Image[] normalSprites = {new Image(Entity.class.getResourceAsStream("/assets/Brick.png"))};

    public Wall(double x, double y, double w, double h) {
        super(x, y, w, h);
        setSpriteArrays(normalSprites);
    }

    @Override
    public void update() {
        setCurrentSprite(0, 1);
    }

    @Override
    public void setState(Object wallState) {}
}
