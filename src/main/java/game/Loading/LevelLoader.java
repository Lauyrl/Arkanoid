package game.Loading;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import game.Entities.*;
import game.Entities.MovingEntities.Ball;
import game.Entities.MovingEntities.MovingEntity;
import game.Entities.StaticEntities.Brick;
import game.Entities.StaticEntities.StaticEntity;
import game.Entities.StaticEntities.Wall;
import game.Renderering.Renderer;

public class LevelLoader {
    private ArrayList<StaticEntity> staticEntityList = new ArrayList<>();
    private ArrayList<MovingEntity> movingEntityList = new ArrayList<>();
    private Renderer entityRenderer;
    private class JsonInputUtil {
        // phải giống với các trường trong file json
        String type;
        double x, y, w, h;
    }

    public LevelLoader(Canvas entityCanvas) {
        this.entityRenderer = new Renderer(entityCanvas);
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
            } else {
                StaticEntity current = new Wall(d.x, d.y, d.w, d.h);
                staticEntityList.add(current);
            }
        }
    }

    public void updateLevel() {
        entityRenderer.clearCanvas();
        handleCollision();
        for (Entity e : movingEntityList) {
            e.update();
            entityRenderer.render(e);
        }
        for (Entity e : staticEntityList) {
            e.update();
            entityRenderer.render(e);
        }
    }

    private void handleCollision() {
        for (int t = 0; t < 2; t++) {
            for (MovingEntity ball : movingEntityList) {
                for (StaticEntity staticEntity : staticEntityList) {
                    if (CollisionHandler.overlaps(ball, staticEntity) == true) {
                        CollisionHandler.resolveCollision((Ball) ball, staticEntity);
                    }
                }
            }
            for (int i = 0; i < movingEntityList.size(); i++) {
                for (int j = i+1; j < movingEntityList.size(); j++) {
                    if (CollisionHandler.overlaps(movingEntityList.get(i), movingEntityList.get(j)) == true) {
                        CollisionHandler.resolveCollision((Ball) movingEntityList.get(i), (Ball) movingEntityList.get(j));
                    }
                }
            }
        }
    }
}
