package game.Entities.StaticEntities;

import game.Entities.Destructible;
import game.Entities.SpriteUtil;
import java.util.Map;

public class PowerUp extends StaticEntity implements Destructible {
    private PowerUpType type;
    private boolean consumed = false;
    int duration;
    private static final Map<PowerUpType, SpriteUtil> spriteArrayMap = Map.of(
        PowerUpType.LENGTH, new SpriteUtil(SpriteUtil.LENGTH, 1)
    );

    public enum PowerUpType {
        LENGTH
    }
    
    public PowerUp(double x, double y) {
        super(x, y, 70, 40, StaticEntity.POSITIVE, 10, 0, 0, 0, 0);
        type = PowerUpType.LENGTH;
        setCurrentSprite(spriteArrayMap.get(type));
    }

    @Override
    public boolean isDestroyed() {
        return (consumed || getY() > 1080);
    }

    @Override
    public void update() {
        drop();
    }

    @Override
    public void setState(Object entityState) {}

    public void setConsumed() {
        consumed = true;
    }

    public PowerUpType getPowerUpType() {
        return type;
    }
}
