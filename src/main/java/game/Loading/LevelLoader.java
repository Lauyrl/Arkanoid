package game.Loading;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import game.InputHandler;
import game.Entities.MovingEntities.*;
import game.Entities.StaticEntities.*;
import game.Renderering.Renderer;

public class LevelLoader {
    private ArrayList<StaticEntity> staticEntityList = new ArrayList<>();
    private ArrayList<MovingEntity> movingEntityList = new ArrayList<>();
    private InputHandler inputHandler;
    private Renderer entityRenderer;
    private class JsonInputUtil {
        String type;
        double x, y, w, h;
    }

    public LevelLoader(Canvas entityCanvas, InputHandler inputHandler) {
        this.entityRenderer = new Renderer(entityCanvas);
        this.inputHandler = inputHandler;
    }

    private JsonInputUtil[] getLevelData(String levelId) {
        Gson gson = new Gson();
        InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/Level_" + levelId + ".json"));
        return gson.fromJson(reader, JsonInputUtil[].class);
    }

    public void loadLevel(String levelId) {
        JsonInputUtil[] data = getLevelData(levelId);
        for (JsonInputUtil d : data) {
            if (d.type.equals("ball")) {
                MovingEntity current = new Ball(d.x, d.y, d.w, d.h);
                movingEntityList.add(current);
            } else if (d.type.equals("brick")) {
                StaticEntity current = new Brick(d.x, d.y, d.w, d.h);
                staticEntityList.add(current);
            } else if (d.type.equals("paddle")) {
                MovingEntity current = new Paddle(d.x, d.y, d.w, d.h);
                movingEntityList.add(current);
            } else {
                StaticEntity current = new Wall(d.x, d.y, d.w, d.h);
                staticEntityList.add(current);
            }
        }
    }

    public void updateLevel() {
        entityRenderer.clearCanvas();
        handleCollision();
        for (int i = movingEntityList.size() - 1; i >= 0; i--) {
            MovingEntity current = movingEntityList.get(i);
            if (current instanceof Paddle) {
                if (inputHandler.isKeyPressed(KeyCode.A)) {
                    current.setVelX(-20);
                } else if (inputHandler.isKeyPressed(KeyCode.D)) {
                    current.setVelX(20);
                }
            }
            current.update();
            entityRenderer.render(current);
        }
        for (int i = staticEntityList.size() - 1; i >= 0; i--) {
            StaticEntity current = staticEntityList.get(i);
            current.update();
            entityRenderer.render(current);
            if (current instanceof Brick && ((Brick) current).isBroken()) {
                staticEntityList.remove(i);
            }
        }
    }

    private void handleCollision() {
        for (int t = 0; t < 2; t++) {
            for (MovingEntity movingEntity : movingEntityList) {
                for (StaticEntity staticEntity : staticEntityList) {
                    if (CollisionHandler.overlaps(movingEntity, staticEntity)) {
                        CollisionHandler.resolveCollision(movingEntity, staticEntity);
                    }
                }
            }
            for (int i = 0; i < movingEntityList.size(); i++) {
                for (int j = i+1; j < movingEntityList.size(); j++) {
                    if (CollisionHandler.overlaps(movingEntityList.get(i), movingEntityList.get(j))) {
                        CollisionHandler.resolveCollision(movingEntityList.get(i), movingEntityList.get(j));
                    }
                }
            }
        }
    }
}
