package game.Entities.StaticEntities;

import game.Entities.Destructible;
import game.Entities.SpriteUtil;
import game.Entities.MovingEntities.MovingEntity;
import java.util.Map;

public class Brick extends StaticEntity implements Collidable, Destructible {
    private int hp;
    private BrickState brickState;
    private static final Map<BrickState, SpriteUtil> spriteArrayMap = Map.of(
        BrickState.NORMAL, new SpriteUtil(SpriteUtil.BRICK_NORMAL, 20),
        BrickState.BROKEN, new SpriteUtil(SpriteUtil.BRICK_NORMAL, 20)
    );

    private enum BrickState {
        NORMAL, BROKEN;
    }

    public Brick(double x, double y, double w, double h) {
        super(x, y, w, h);
        setState(BrickState.NORMAL);
        hp = 1;
    }

    @Override
    public void respondToCollision(MovingEntity e) {
        hp--;
        if (hp <= 0) setState(BrickState.BROKEN); 
    }

    @Override
    public void update() {
       setCurrentSprite(spriteArrayMap.get(brickState));
    }

    @Override
    public void setState(Object brickState) {
        this.brickState = (BrickState) brickState;
    }

    public boolean isDestroyed() {
        return brickState.equals(BrickState.BROKEN);
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }
}
