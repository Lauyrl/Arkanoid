package game.Loading;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import game.Entities.DynamicEntities.*;
import game.Entities.StaticEntities.*;
import game.Inputs.InputHandler;
import game.Renderering.Renderer;

public class LevelLoader {
    private EntityManager entityManager = new EntityManager(new ArrayList<>(), new ArrayList<>());
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
        entityManager = new EntityManager(new ArrayList<>(), new ArrayList<>());
        EntityFactory.produceEntities(levelId, entityManager.getDynamicEntities(), entityManager.getStaticEntities());
        entityManager.paddle = EntityFactory.getPaddle();
    }

    public void updateLevel() {
        entityRenderer.clearCanvas();
        entityManager.removeDestroyedEntities();
        entityManager.propagateInput(inputHandler.getKeysPressed());
        entityManager.updateEntities();
        for (DynamicEntity e : entityManager.getDynamicEntities()) {
            entityRenderer.render(e);
        }
        for (StaticEntity e : entityManager.getStaticEntities()) {
            entityRenderer.render(e);
        }
    }

    public void clean() {
        entityRenderer.clearCanvas();
        entityManager.clean();
    }

    public String getCurrentLevelId() {
        return currentLevelId;
    }
}
