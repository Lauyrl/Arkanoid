package game.Loading;

import com.google.gson.Gson;
import game.Entities.MovingEntities.*;
import game.Entities.StaticEntities.*;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class EntityFactory {
    private class DataArray {
        OtherData[] other;
        BrickData[] bricks;
    }

    private class BrickData {
        String type;
        double x, y, w, h, level;
    }

    private class OtherData {
        String type;
        double x, y, w, h;
    }

    private static DataArray getLevelData(String levelId) {
        Gson gson = new Gson();
        InputStreamReader reader =
                new InputStreamReader(EntityFactory.class.getResourceAsStream("/" + levelId + ".json"));
        return gson.fromJson(reader, DataArray.class);
    }

    public static void produceEntities(String levelId, ArrayList<MovingEntity> movingEntityList, ArrayList<StaticEntity> staticEntityList) {
        DataArray data = getLevelData(levelId);
        for (BrickData b : data.bricks) {
            switch (b.type) {
                case "brick"  -> staticEntityList.add(new Brick(b.x, b.y, b.w, b.h));
            }
        }
        for (OtherData b : data.other) {
            switch (b.type) {
                case "ball"   -> movingEntityList.add(new Ball(b.x, b.y, b.w, b.h));
                case "paddle" -> movingEntityList.add(new Paddle(b.x, b.y, b.w, b.h));
                case "wall"   -> staticEntityList.add(new Wall(b.x, b.y, b.w, b.h));
            }
        }
    }
}
