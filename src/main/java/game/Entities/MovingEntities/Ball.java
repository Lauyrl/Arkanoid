package game.Entities.MovingEntities;

import game.Entities.Entity;
import javafx.scene.image.Image;

public class Ball extends MovingEntity {
    private BallState ballState;
    private static Image[] movingSprites = {new Image(Entity.class.getResourceAsStream("/assets/Ball.png")), 
                                            new Image(Entity.class.getResourceAsStream("/assets/Ball1.png"))};

    private enum BallState {
        MOVING(0, 20), TEST(1, 20);

        public final int index, spriteInterval;
        private BallState(int v, int i) {
            index = v;
            spriteInterval = i;
        }
    }

    public Ball(double x, double y, double w, double h) {
        super(x, y, w, h, 5, 5);
        setSpriteArrays(movingSprites);
        setState(BallState.MOVING);
    }

    @Override
    public void update() {
        setStateSprite(ballState.index, ballState.spriteInterval);
        updatePosition();
    }

    @Override
    public void setState(Object ballState) {
        this.ballState = (BallState) ballState;
    }
}
