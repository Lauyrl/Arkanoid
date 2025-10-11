package game.Entities.DynamicEntities;

import game.Entities.Entity;
import game.Entities.SpriteUtil;
import game.Entities.StaticEntities.Brick;
import game.Entities.StaticEntities.Wall;
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

    @Override
    public void update() {
        setCurrentSprite(spriteArrayMap.get(ballState));
        updatePosition();
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
        double delta = (getXCenter() - paddleCenterX) / (paddleWidth / 2); 
        double maxAngle = Math.toRadians(60);
        double theta = delta * maxAngle;
        double vel = Math.sqrt(getVelX() * getVelX() + getVelY() * getVelY());
        if (!Double.isFinite(vel)) return;
        setVelX(vel * Math.sin(theta));
        setVelY(-vel * Math.cos(theta));
    }

    @Override
    public void relayCollision(Entity e, double oldLeft, double oldRight, double oldTop, double oldBot, double tX, double tY) {
        e.respondToCollisionWithBall(this, oldLeft, oldRight, oldTop, oldBot, tX, tY);
    }

    @Override
    public void respondToCollisionWithWall(Wall e, double oldLeft, double oldRight, double oldTop, double oldBot, double tX, double tY) {
        respondToStandardCollision(oldLeft, oldRight, oldTop, oldBot, tX, tY);
    }

    @Override
    public void respondToCollisionWithBrick(Brick e, double oldLeft, double oldRight, double oldTop, double oldBot, double tX, double tY) {
        respondToStandardCollision(oldLeft, oldRight, oldTop, oldBot, tX, tY);
        e.loseHp();
    }

    @Override
    public void respondToCollisionWithPaddle(Paddle e, double oldLeft, double oldRight, double oldTop, double oldBot, double tX, double tY) {
        moveToCollisionPoint(tX, tY, oldLeft, oldTop);
        if (tX < tY) {
            bounceX();
        } else {
            bounceOffPaddle(e.getXCenter(), e.getWidth());
        }
        moveToPostCollisionPoint(tX, tY);
    }

    private void respondToStandardCollision(double oldLeft, double oldRight, double oldTop, double oldBot, double tX, double tY) {
        moveToCollisionPoint(tX, tY, oldLeft, oldTop);
        if (tX < tY) {
            bounceX();
        } else {
            bounceY();
        }
        moveToPostCollisionPoint(tX, tY);
    }

    private void moveToCollisionPoint(double tX, double tY, double oldLeft, double oldTop) {
        double t = Math.min(tX, tY);
        setX(oldLeft + getVelX() * t);
        setY(oldTop  + getVelY() * t);
    }

    private void moveToPostCollisionPoint(double tX, double tY) {
        double t = Math.min(tX, tY);
        setX(getX() + getVelX() * (1 - t + 1e-6));
        setY(getY() + getVelY() * (1 - t + 1e-6));
    }

    @Override
    public void setState(Object ballState) {
        this.ballState = (BallState) ballState;
    }
}
