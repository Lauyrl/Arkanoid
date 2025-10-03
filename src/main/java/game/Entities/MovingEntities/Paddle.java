package game.Entities.MovingEntities;

import game.Entities.SpriteUtil;
import java.util.Set;
import javafx.scene.input.KeyCode;

public class Paddle extends MovingEntity {
    public Paddle(double x, double y, double w, double h) {
        super(x, y, w, h, 0, 0);
    }

    @Override
    public void update() {
        setCurrentSprite(new SpriteUtil(SpriteUtil.PADDLE_NORMAL, 1));
        updatePosition();
        setVelX(0);
    }

    @Override
    public void setState(Object ballState) {}

    public void respondToInput(Set<KeyCode> set) {
        if (set.contains(KeyCode.A)) {
            setVelX(-20);
        } else if (set.contains(KeyCode.D)) {
            setVelX(20);
        }
    }
}
