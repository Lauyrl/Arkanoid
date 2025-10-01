package game.Entities;

import javafx.scene.image.Image;

public class Ball extends Entity {
    private double velX, velY;
    private static Image[] movingSprites = {new Image(Entity.class.getResourceAsStream("/assets/Ball.png")), new Image(Entity.class.getResourceAsStream("/assets/Ball1.png"))};
    private static Image[][] spriteArrays = {movingSprites};
    private BallState ballState;

    private enum BallState {
        MOVING(0), TEST(1);
        public final int value;
        private BallState(int i) {
            value = i;
        }
    }

    public Ball(double x, double y, double w, double h) {
        super(x, y, w, h);
        changeState(BallState.MOVING);
        setVelX(5);
        setVelY(5);
    }

    @Override
    public void update() {
        setStateSprite();
        setX(getX() + velX);
        setY(getY() + velY);
    }

    @Override
    public void changeState(Object ballState) {
        this.ballState = (BallState) ballState;
    }

    @Override
    public void setStateSprite() {
        setSprite(spriteArrays[ballState.value][getFrameCounter()/20]);
        setFrameCounter((getFrameCounter() + 1) % (movingSprites.length*20));
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelX(double v) {
        velX = v;
    }

    public void setVelY(double v) {
        velY = v;
    }
}
