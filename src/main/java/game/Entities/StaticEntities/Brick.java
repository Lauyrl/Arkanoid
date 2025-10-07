package game.Entities.StaticEntities;

import game.Entities.Destructible;
import game.Entities.SpriteUtil;
import game.Entities.MovingEntities.MovingEntity;
import java.util.Map;

public class Brick extends StaticEntity implements Collidable, Destructible {
    private int hp;
    private BrickState brickState;
    private MovementMode movementMode;
    private static final Map<BrickState, SpriteUtil> spriteArrayMap = Map.of(
        BrickState.BRICK_NORMAL_2, new SpriteUtil(SpriteUtil.BRICK_NORMAL_2, 20),
        BrickState.BRICK_NORMAL_1, new SpriteUtil(SpriteUtil.BRICK_NORMAL_1, 20),
        BrickState.BROKEN, new SpriteUtil(SpriteUtil.BRICK_NORMAL_1, 20)
    );

    private enum BrickState {
        BRICK_NORMAL_2, BRICK_NORMAL_1, BROKEN;
    }

    public enum MovementMode {
        STAGNANT, HORIZONTAL
    }

    public Brick(double x, double y, double w, double h, int hp, MovementMode movement, int initialDirection, double vel, double movementLeftBound, double movementRightBound, double movementUpperBound, double movementLowerBound) {
        super(x, y, w, h, initialDirection, vel, movementLeftBound, movementRightBound, movementUpperBound, movementLowerBound);
        this.movementMode = movement;
        setHp(hp);
    }

    public Brick(double x, double y, double w, double h, int hp) {
        super(x, y, w, h);
        this.movementMode = MovementMode.STAGNANT;
        setHp(hp);
    }

    @Override
    public void update() {
        setCurrentSprite(spriteArrayMap.get(brickState));
        switch (movementMode) {
            case STAGNANT -> {}
            case HORIZONTAL -> {horizontalOscillate();}
       }
    }

    @Override
    public void respondToCollision(MovingEntity e) {
        setHp(hp - 1);
    }

    public PowerUp spawnPowerUp() {
        return new PowerUp(getX() + getWidth() / 2, getY());
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
        switch (hp) {
            case 0 -> setState(BrickState.BROKEN); 
            case 1 -> setState(BrickState.BRICK_NORMAL_1);
            case 2 -> setState(BrickState.BRICK_NORMAL_2);
        }

    }

    public int getHp() {
        return hp;
    }
}
