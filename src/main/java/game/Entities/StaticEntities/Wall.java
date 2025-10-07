package game.Entities.StaticEntities;

import game.Entities.SpriteUtil;
import game.Entities.DynamicEntities.DynamicEntity;

public class Wall extends StaticEntity implements Collidable {
    public Wall(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    @Override
    public void respondToCollision(DynamicEntity e) {}
    
    @Override
    public void update() {
        setCurrentSprite(new SpriteUtil(SpriteUtil.BRICK_NORMAL_1, 1));
    }

    @Override
    public void setState(Object wallState) {}
}
