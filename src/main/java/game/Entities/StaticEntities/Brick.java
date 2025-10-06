package game.Entities.StaticEntities;

import game.Entities.Destructible;
import game.Entities.SpriteUtil;
import game.Entities.MovingEntities.MovingEntity;
import java.util.Map;

public class Brick extends StaticEntity implements Collidable, Destructible {
    private int hp;
    private BrickState brickState;
    private static final Map<BrickState, SpriteUtil> spriteArrayMap = Map.of(
        BrickState.BRICK_NORMAL_2, new SpriteUtil(SpriteUtil.BRICK_NORMAL_2, 20),
        BrickState.BRICK_NORMAL_1, new SpriteUtil(SpriteUtil.BRICK_NORMAL_1, 20),
        BrickState.BROKEN, new SpriteUtil(SpriteUtil.BRICK_NORMAL_1, 20)
    );

    private enum BrickState {
        BRICK_NORMAL_2, BRICK_NORMAL_1, BROKEN;
    }

    public Brick(double x, double y, double w, double h, int hp) {
        super(x, y, w, h);
        this.hp = hp;
        switch (hp) {
            case 1 -> setState(BrickState.BRICK_NORMAL_1);
            case 2 -> setState(BrickState.BRICK_NORMAL_2);
        }
    }

    @Override
    public void respondToCollision(MovingEntity e) {
        hp--;
        if      (hp == 2) setState(BrickState.BRICK_NORMAL_2);
        else if (hp == 1) setState(BrickState.BRICK_NORMAL_1);
        else if (hp <= 0) setState(BrickState.BROKEN); 
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
