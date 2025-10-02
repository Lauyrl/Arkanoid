package game.Entities.StaticEntities;

import game.Entities.SpriteUtil;
import game.Entities.MovingEntities.MovingEntity;

public class Wall extends StaticEntity implements Collidable {
    public Wall(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    @Override
    public void respondToCollision(MovingEntity e) {}
    
    @Override
    public void update() {
        setCurrentSprite(new SpriteUtil(SpriteUtil.BRICK_NORMAL, 1));
    }

    @Override
    public void setState(Object wallState) {}
}
