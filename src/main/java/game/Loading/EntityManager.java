package game.Loading;

import game.Entities.Destructible;
import game.Entities.DynamicEntities.*;
import game.Entities.StaticEntities.Brick;
import game.Entities.StaticEntities.StaticEntity;
import java.util.ArrayList;
import java.util.Set;
import javafx.scene.input.KeyCode;

public class EntityManager {
    private ArrayList<DynamicEntity> dynamicEntities = new ArrayList<>();
    private ArrayList<StaticEntity> staticEntities = new ArrayList<>();
    public Paddle paddle;

    public EntityManager(ArrayList<DynamicEntity> dynamicEntities, ArrayList<StaticEntity> staticEntities) {
        this.dynamicEntities = dynamicEntities;
        this.staticEntities = staticEntities;
    }

    public void removeDestroyedEntities() {
        for (int i = dynamicEntities.size() - 1; i >= 0; i--) {
            if (dynamicEntities.get(i) instanceof Destructible && ((Destructible) dynamicEntities.get(i)).isDestroyed()) {
                dynamicEntities.remove(i);   
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

    public void propagateInput(Set<KeyCode> keySet) {
        paddle.respondToInput(keySet);
    }

    public void updateEntities() {
        for (DynamicEntity e : dynamicEntities) {
            if (e instanceof Ball && ((Ball) e).isOutOfBounds()) {
                e.setX(paddle.getX() + paddle.getWidth() / 2);
                e.setY(paddle.getY() - e.getWidth() - 1);
            }
            e.update();
        }
        for (StaticEntity e : staticEntities) {
            e.update();
        }
        CollisionHandler.handleCollision(dynamicEntities, staticEntities, 2);
    }

    public void clean() {
        dynamicEntities.clear();
        staticEntities.clear();
        paddle = null;
    }

    public ArrayList<DynamicEntity> getDynamicEntities() {
        return dynamicEntities;
    }

    public ArrayList<StaticEntity> getStaticEntities() {
        return staticEntities;
    }
}
