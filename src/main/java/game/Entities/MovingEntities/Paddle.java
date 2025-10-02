package game.Entities.MovingEntities;

import game.Entities.SpriteUtil;

public class Paddle extends MovingEntity {
    public Paddle(double x, double y, double w, double h) {
        super(x, y, w, h, 0, 0);
    }

    @Override
    public void update() {
        setCurrentSprite(new SpriteUtil(SpriteUtil.BRICK_NORMAL, 1));
        updatePosition();
        setVelX(0);
    }

    @Override
    public void setState(Object ballState) {}
}
