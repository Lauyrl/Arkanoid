package game.Loading;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import game.Entities.Destructible;
import game.Entities.MovingEntities.*;
import game.Entities.StaticEntities.*;
import game.Inputs.InputHandler;
import game.Renderering.Renderer;

public class LevelLoader {
    private ArrayList<MovingEntity> movingEntityList = new ArrayList<>();
    private ArrayList<StaticEntity> staticEntityList = new ArrayList<>();
    private Paddle paddle;
    private InputHandler inputHandler;
    private Renderer entityRenderer;
    private String currentLevelId = "";

    public LevelLoader(Canvas entityCanvas, InputHandler inputHandler) {
        this.entityRenderer = new Renderer(entityCanvas);
        this.inputHandler = inputHandler;
    }

    public void loadLevel(String levelId) {
        clean();
        currentLevelId = levelId;
        staticEntityList = new ArrayList<>();
        movingEntityList = new ArrayList<>();
        EntityFactory.produceEntities(levelId, movingEntityList, staticEntityList);
        paddle = EntityFactory.getPaddle();
    }

    public void updateLevel() {
        entityRenderer.clearCanvas();
        CollisionHandler.handleCollision(movingEntityList, staticEntityList, 1);
        removeDestroyedEntities();
        for (MovingEntity e : movingEntityList) {
            if (e instanceof Paddle) {
                ((Paddle) e).respondToInput(inputHandler.getKeysPressed()); 
            }
            e.update();
            entityRenderer.render(e);
        }
        for (StaticEntity e : staticEntityList) {
            e.update();
            entityRenderer.render(e);
        }
    }

    private void removeDestroyedEntities() {
        movingEntityList.removeIf(entity -> entity instanceof Destructible && ((Destructible) entity).isDestroyed());
        staticEntityList.removeIf(entity -> entity instanceof Destructible && ((Destructible) entity).isDestroyed());
    }

    public void clean() {
        entityRenderer.clearCanvas();
        staticEntityList.clear();
        movingEntityList.clear();
    }

    public String getCurrentLevelId() {
        return currentLevelId;
    }
}
