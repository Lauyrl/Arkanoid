package game.Loading;

import game.Entities.Destructible;
import game.Entities.MovingEntities.MovingEntity;
import game.Entities.StaticEntities.Brick;
import game.Entities.StaticEntities.StaticEntity;
import java.util.ArrayList;

public class EntityManager {
    public static void removeDestroyedEntities(ArrayList<MovingEntity> movingEntities, ArrayList<StaticEntity> staticEntities) {
        for (int i = movingEntities.size() - 1; i >= 0; i--) {
            if (movingEntities.get(i) instanceof Destructible && ((Destructible) movingEntities.get(i)).isDestroyed()) {
                movingEntities.remove(i);   
            }
        }
        for (int i = staticEntities.size() - 1; i >= 0; i--) {
            if (staticEntities.get(i) instanceof Destructible && ((Destructible) staticEntities.get(i)).isDestroyed()) {
                if (staticEntities.get(i) instanceof Brick) {
                    staticEntities.add(((Brick) staticEntities.get(i)).spawnPowerUp());
                }
                staticEntities.remove(i);
            };        
        }
    }
}
