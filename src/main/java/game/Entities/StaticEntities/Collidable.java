package game.Entities.StaticEntities;

import game.Entities.DynamicEntities.DynamicEntity;

public interface Collidable {
    public void respondToCollision(DynamicEntity e);
}
