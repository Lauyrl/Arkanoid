package game.Loading;

import com.google.gson.Gson;
import game.Entities.MovingEntities.*;
import game.Entities.StaticEntities.*;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class EntityFactory {
    private class JsonInputUtil {
        String type;
        double x, y, w, h;
    }

    private static JsonInputUtil[] getLevelData(String levelId) {
        Gson gson = new Gson();
        InputStreamReader reader = new InputStreamReader(Gson.class.getResourceAsStream("/Level_" + levelId + ".json"));
        return gson.fromJson(reader, JsonInputUtil[].class);
    }

    public static void produceEntities(String levelId, ArrayList<MovingEntity> movingEntityList, ArrayList<StaticEntity> staticEntityList) {
        JsonInputUtil[] data = getLevelData(levelId);
        for (JsonInputUtil d : data) {
            switch (d.type) {
                case "ball"   -> movingEntityList.add(new Ball(d.x, d.y, d.w, d.h));
                case "paddle" -> movingEntityList.add(new Paddle(d.x, d.y, d.w, d.h));
                case "brick"  -> staticEntityList.add(new Brick(d.x, d.y, d.w, d.h));
                case "wall"   -> staticEntityList.add(new Wall(d.x, d.y, d.w, d.h));
            }
        }
    }
}
