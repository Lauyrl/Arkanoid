package game.Loading;

import com.google.gson.Gson;
import game.Entities.MovingEntities.*;
import game.Entities.StaticEntities.*;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class EntityFactory {
    public static Paddle paddle;
    private class DataArray {
        OtherData[] other;
        BrickData[] bricks;
    }

    private class BrickData {
        String type;
        double x, y, w, h;
        int hp;
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
                case "brick"  -> staticEntityList.add(new Brick(b.x, b.y, b.w, b.h, b.hp));
            }
        }
        for (OtherData b : data.other) {
            switch (b.type) {
                case "ball"   -> movingEntityList.add(new Ball(b.x, b.y, b.w, b.h));
                case "paddle" -> { 
                    paddle = new Paddle(b.x, b.y, b.w, b.h); 
                    movingEntityList.add(paddle);
                }
                case "wall"   -> staticEntityList.add(new Wall(b.x, b.y, b.w, b.h));
            }
        }
    }

    public static Paddle getPaddle() {
        return paddle;
    }
}
