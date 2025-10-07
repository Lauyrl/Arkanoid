package game.Loading;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import game.Entities.DynamicEntities.*;
import game.Entities.StaticEntities.*;
import game.Inputs.InputHandler;
import game.Renderering.Renderer;

public class LevelLoader {
    private ArrayList<DynamicEntity> movingEntityList = new ArrayList<>();
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
        EntityManager.removeDestroyedEntities(movingEntityList, staticEntityList);
        for (DynamicEntity e : movingEntityList) {
            if (e instanceof Paddle) {
                ((Paddle) e).respondToInput(inputHandler.getKeysPressed()); 
            }
            else if (e instanceof Ball && ((Ball) e).isOutOfBounds()) {
                e.setX(paddle.getX() + paddle.getWidth() / 2);
                e.setY(paddle.getY() - e.getWidth() - 1);
            }
            e.update();
            entityRenderer.render(e);
        }
        for (StaticEntity e : staticEntityList) {
            e.update();
            entityRenderer.render(e);
        }
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
