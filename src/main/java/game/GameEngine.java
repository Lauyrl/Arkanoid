package game;
import game.Entities.Ball;
import game.Renderering.Renderer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
// Singleton
public class GameEngine {
    private static GameEngine instance = null;
    private GameState gameState;
    private Renderer renderer;
    private long lastFrame;
    private final long interval = 1000000000 / 60;
    // test
    private boolean ballSpawned;
    private Ball ball; 

    private GameEngine(Renderer renderer) {
        gameState = GameState.BALL_TEST;
        this.renderer = renderer;
        lastFrame = 0;
        ballSpawned = false;
    }

    public static GameEngine getInstance(Renderer renderer) {
        if (instance == null) instance = new GameEngine(renderer);
        return instance;
    }
    // vongf lặp chính
    public void run() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastFrame >= interval) {
                    lastFrame = now;
                    if (gameState == GameState.BALL_TEST) {
                        runTest();
                    }
                }    
                    
            }
        };
        timer.start();
    }
    //test
    private void runTest() {
        if (ballSpawned == false) {
            ball = new Ball();
            ballSpawned = true;
        }
        ball.update();
        renderer.clearCanvas();
        renderer.renderStrokeRect();
        renderer.render(ball.getSprite(), ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
    }

    public enum GameState {
        START_MENU, MODE_SELECT, LEVEL_SELECT, LEVEL, BALL_TEST
    }
}
