package game;
import game.Entities.Ball;
import game.Renderering.Renderer;
import java.util.logging.Level;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
// Singleton
public class GameEngine {
    private static GameEngine instance = null;
    private GameState gameState;
    private Renderer backgroundRenderer;
    private Renderer entityRenderer;
    private Renderer uiRenderer;
    private LevelLoader levelLoader;
    //framerate restriction
    private long lastFrame;
    private final long interval = 1000000000 / 60;
    // test
    private boolean ballSpawned;
    private Ball ball; 

    public enum GameState {
        START_MENU, MODE_SELECT, LEVEL_SELECT, LEVEL, BALL_TEST
    }

    private GameEngine(Canvas backgroundCanvas, Canvas entityCanvas, Canvas uiCanvas) {
        gameState = GameState.BALL_TEST;
        backgroundRenderer = new Renderer(backgroundCanvas);
        entityRenderer = new Renderer(entityCanvas);
        uiRenderer = new Renderer(uiCanvas);
        levelLoader = new LevelLoader();
        ballSpawned = false;
        lastFrame = 0;
    }

    public static GameEngine getInstance(Canvas backgroundCanvas, Canvas entityCanvas, Canvas uiCanvas) {
        if (instance == null) instance = new GameEngine(backgroundCanvas, entityCanvas, uiCanvas);
        return instance;
    }
    // vongf lặp chính
    public void run() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // vòng lặp không chạy quá 60 lần/giây
                if (now - lastFrame >= interval) {
                    lastFrame = now;
                    if      (gameState == GameState.BALL_TEST) {
                        runTest();
                    }
                    //else if (gameState == GameState.LEVEL) {}
                }    
                    
            }
        };
        timer.start();
    }

    public void changeState(GameState gameState) {
        if (gameState == GameState.LEVEL) {
            levelLoader.loadLevel();
        }
    }
    //test
    private void runTest() {
        if (ballSpawned == false) {
            ball = new Ball();
            ballSpawned = true;
        }
        ball.update();
        entityRenderer.clearCanvas();
        entityRenderer.renderStrokeRect();
        entityRenderer.render(ball.getSprite(), ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
    }
}
