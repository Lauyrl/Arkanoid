package game.Entities.DynamicEntities;

import game.Entities.SpriteUtil;
import java.util.Map;

public class Ball extends DynamicEntity implements Bouncy {
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

    public boolean isOutOfBounds(){
        return (getY() > 1300);
    }

    @Override
    public void bounceX() {
        setVelX(-getVelX());
    }

    @Override
    public void bounceY() {
        setVelY(-getVelY());
    }  

    @Override
    public void bounceOffPaddle(double paddleCenterX, double paddleWidth) {
        double delta = (getCenter()[0] - paddleCenterX) / (paddleWidth / 2); 
        double maxAngle = Math.toRadians(60);
        double theta = delta * maxAngle;
        double vel = Math.sqrt(getVelX() * getVelX() + getVelY() * getVelY());
        setVelX(vel * Math.sin(theta));
        setVelY(-vel * Math.cos(theta));
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
