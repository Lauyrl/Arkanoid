package game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import game.InputHandler;
import game.Loading.LevelLoader;
import javafx.scene.input.KeyCode;

// Singleton
public class GameEngine {
    private static GameEngine instance = null;
    private GameState gameState;
    private LevelLoader levelLoader;
    private InputHandler inputHandler;

    //framerate restriction
    private long lastFrame = 0;
    private final long interval = 1000000000 / 60;

    public enum GameState {
        START_MENU, MODE_SELECT, LEVEL_SELECT, LEVEL, BALL_TEST
    }

    private GameEngine(Canvas backgroundCanvas, Canvas entityCanvas, Canvas uiCanvas, Scene scene) {
        gameState = GameState.BALL_TEST;
        levelLoader = new LevelLoader(entityCanvas);
        inputHandler = new InputHandler(scene);
    }

    public static GameEngine getInstance(Canvas backgroundCanvas, Canvas entityCanvas, Canvas uiCanvas, Scene scene) {
        if (instance == null) instance = new GameEngine(backgroundCanvas, entityCanvas, uiCanvas, scene);
        return instance;
    }

    // VÒNG LẶP CHÍNH
    public void run() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // vòng lặp không chạy quá 60 lần/giây
                if (now - lastFrame >= interval) {
                    lastFrame = now;
                    if (gameState == GameState.LEVEL) {
                        levelLoader.updateLevel();
                    }
                }    
                    
            }
        };
        timer.start();
    }

    public void setState(GameState gameState) {
        this.gameState = gameState;
        if (gameState == GameState.LEVEL) {
            levelLoader.loadLevel("");
        }
    }
}
