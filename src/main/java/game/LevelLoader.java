package game;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import game.Entities.*;
import game.Renderering.Renderer;

public class LevelLoader {
    private ArrayList<Entity> staticEntityList;
    private ArrayList<Entity> movingEntityList;
    private Renderer entityRenderer;
    private class JsonInputUtil {
        // phải giống với các trường trong file json
        String type;
        double x, y, w, h;
    }

    public LevelLoader(Canvas entityCanvas) {
        this.entityRenderer = new Renderer(entityCanvas);
        this.staticEntityList = new ArrayList<>();
        this.movingEntityList = new ArrayList<>();
    }

    private JsonInputUtil[] getLevelData(String levelId) {
        Gson gson = new Gson();
        InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/Level_data.json"));
        return gson.fromJson(reader, JsonInputUtil[].class);
    }

    public void loadLevel(String levelId) {
        JsonInputUtil[] data = getLevelData(levelId);
        for (JsonInputUtil d : data) {
            Entity current;
            if (d.type.equals("ball")) {
                current = new Ball(d.x, d.y, d.w, d.h);
                movingEntityList.add(current);
            }
            else {
                current = new Wall(d.x, d.y, d.w, d.h);
                staticEntityList.add(current);
            }
        }
    }

    public void updateLevel() {
        entityRenderer.clearCanvas();
        handleCollision();
        for (Entity e : movingEntityList) {
            e.update(1);
            entityRenderer.render(e);
        }
        for (Entity e : staticEntityList) {
            entityRenderer.render(e);
        }
    }

    private void handleCollision() {
        for (int t = 0; t < 2; t++) {
            for (Entity ball : movingEntityList) {
                for (Entity wall : staticEntityList) {
                    if (CollisionHandler.overlaps(ball, wall) == true) {
                        CollisionHandler.resolveCollision((Ball) ball, wall);
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
