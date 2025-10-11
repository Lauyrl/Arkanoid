package game.Entities.StaticEntities;

import game.Entities.Entity;
import game.Entities.SpriteUtil;

public class Wall extends StaticEntity {
    public Wall(double x, double y, double w, double h) {
        super(x, y, w, h);
    }
    
    @Override
    public void update() {
        setCurrentSprite(new SpriteUtil(SpriteUtil.BRICK_NORMAL_1, 1));
    }

    @Override
    public void setState(Object wallState) {}

    @Override
    public void relayCollision(Entity e, double oldLeft, double oldRight, double oldTop, double oldBot, double tX, double tY) {
        e.respondToCollisionWithWall(this, oldLeft, oldRight, oldTop, oldBot, tX, tY);
    }
}
