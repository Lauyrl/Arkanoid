package game.Loading;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import game.InputHandler;
import game.Entities.Destructible;
import game.Entities.MovingEntities.*;
import game.Entities.StaticEntities.*;
import game.Renderering.Renderer;

public class LevelLoader {
    private ArrayList<MovingEntity> movingEntityList = new ArrayList<>();
    private ArrayList<StaticEntity> staticEntityList = new ArrayList<>();
    private InputHandler inputHandler;
    private Renderer entityRenderer;

    public LevelLoader(Canvas entityCanvas, InputHandler inputHandler) {
        this.entityRenderer = new Renderer(entityCanvas);
        this.inputHandler = inputHandler;
    }

    public void loadLevel(String levelId) {
        staticEntityList = new ArrayList<>();
        movingEntityList = new ArrayList<>();
        EntityFactory.produceEntities(levelId, movingEntityList, staticEntityList);
    }

    public void updateLevel() {
        entityRenderer.clearCanvas();
        CollisionHandler.handleCollision(movingEntityList, staticEntityList, 2);
        removeDestroyedEntities();
        for (MovingEntity e : movingEntityList) {
            if (e instanceof Paddle) {
                ((Paddle) e).respondToInput(inputHandler.getKeysPressed()); 
            }
            e.update();
            System.out.println("hello?");
            entityRenderer.render(e);
        }
        for (StaticEntity e : staticEntityList) {
            e.update();
            entityRenderer.render(e);
        }
    }

    private void removeDestroyedEntities() {
        for (int i = movingEntityList.size() - 1; i >= 0; i--) {
            if (movingEntityList.get(i) instanceof Destructible && ((Destructible) movingEntityList.get(i)).isDestroyed()) {
                movingEntityList.remove(i);
            }
        }
        for (int i = staticEntityList.size() - 1; i >= 0; i--) {
            if (staticEntityList.get(i) instanceof Destructible && ((Destructible) staticEntityList.get(i)).isDestroyed()) {
                staticEntityList.remove(i);
            }
        }
    }
}
