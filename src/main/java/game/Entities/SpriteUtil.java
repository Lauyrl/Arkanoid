package game.Entities;

import game.Renderering.Renderer;
import javafx.scene.image.Image;

public class SpriteUtil {
    public static final Image[] BALL_MOVING = {Renderer.loadImage("/assets/Ball.png"), Renderer.loadImage("/assets/Ball1.png")};
    public static final Image[] BRICK_NORMAL_1 = {Renderer.loadImage("/assets/Brick.png")};
    public static final Image[] BRICK_NORMAL_2 = {Renderer.loadImage("/assets/bluebrick.png")};
    public static final Image[] PADDLE_NORMAL = {Renderer.loadImage("/assets/Paddle.png")};
    public static final Image[] LENGTH = {Renderer.loadImage("/assets/poweuptest.png")};
    public static final Image BACKGROUND = Renderer.loadImage("/assets/yep.png");
    public final Image[] sprites;
    public final int duration; 

    public SpriteUtil(Image[] sprites, int duration) {
        this.sprites = sprites;
        this.duration = duration;
    }
}

