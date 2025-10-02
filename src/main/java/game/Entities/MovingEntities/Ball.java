package game.Entities.MovingEntities;

import game.Entities.SpriteUtil;
import java.util.Map;

public class Ball extends MovingEntity {
    private BallState ballState;
    private static final Map<BallState, SpriteUtil> spriteArrayMap = Map.of(
        BallState.MOVING, new SpriteUtil(SpriteUtil.BALL_MOVING, 20),
        BallState.TEST, new SpriteUtil(SpriteUtil.BALL_MOVING, 20)
    );

    private enum BallState {
        MOVING, TEST;
    }

    public Ball(double x, double y, double w, double h) {
        super(x, y, w, h, 10, 10);
        setState(BallState.MOVING);
    }

    @Override
    public void update() {
        setCurrentSprite(spriteArrayMap.get(ballState));
        updatePosition();
    }

    @Override
    public void setState(Object ballState) {
        this.ballState = (BallState) ballState;
    }
}
