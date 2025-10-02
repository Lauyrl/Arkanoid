package game.Entities.StaticEntities;

import game.Entities.MovingEntities.MovingEntity;

public interface Collidable {
    public void respondToCollision(MovingEntity e);
}
