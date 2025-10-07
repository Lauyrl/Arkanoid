package game.Entities.DynamicEntities;

import game.Entities.SpriteUtil;
import game.Entities.StaticEntities.PowerUp.PowerUpType;
import java.util.Set;
import javafx.scene.input.KeyCode;

public class Paddle extends DynamicEntity {
    private static final double DEFAULT_WIDTH = 200;
    private static final double DEFAULT_HEIGHT = 40;
    private PowerUpType currentPowerUp;
    private int powerUpDuration = 0;
    public Paddle(double x, double y, double w, double h) {
        super(x, y, w, h, 0, 0);
    }

    @Override
    public void update() {
        setCurrentSprite(new SpriteUtil(SpriteUtil.PADDLE_NORMAL, 1));
        updatePosition();
        setVelX(0);
        if (powerUpDuration > 0) {
            updatePowerUp();
        } 
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

    public void consumePowerUp(PowerUpType type) {
        resetPowerUp();
        currentPowerUp = type;
        switch (type) {
            case PowerUpType.LENGTH -> {
                setX(getX() - 40);
                setWidth(getWidth() + 80);
                powerUpDuration = 240;
            }
        }
    }

    public void updatePowerUp() {
        powerUpDuration--;
        if (powerUpDuration == 0) resetPowerUp();
    }

    private void resetPowerUp() {
        setX(getX() + 40);
        setWidth(DEFAULT_WIDTH);
    }
}
