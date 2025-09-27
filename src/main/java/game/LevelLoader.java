package game;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import game.Entities.*;
import game.Renderering.Renderer;

public class LevelLoader {
    private ArrayList<Entity> entityList;
    private Renderer entityRenderer;
    private class JsonInputUtil {
        // phải giống với các trường trong file json
        String type;
        double x, y;
    }

    public LevelLoader(Canvas entityCanvas) {
        this.entityRenderer = new Renderer(entityCanvas);
        this.entityList = new ArrayList<>();
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
                current = new Ball(d.x, d.y);
            }
            else current = new Ball(d.x, d.y);
            entityList.add(current);
        }
    }

    public void updateLevel() {
        entityRenderer.clearCanvas();
        entityRenderer.renderStrokeRect();
        for (Entity e : entityList) {
            e.update();
            entityRenderer.render(e.getSprite(), e.getX(), e.getY(), e.getWidth(), e.getHeight());
        }
    }
}
