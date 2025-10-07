package game;

import game.Entities.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import game.Entities.SpriteUtil;
import game.Inputs.GameEventObserver;
import game.Inputs.InputHandler;
import game.Loading.LevelLoader;
import game.Loading.UILoader;

// Singleton
public class GameEngine implements GameEventObserver {
    private static GameEngine instance = null;
    private GameState gameState;
    private LevelLoader levelLoader;
    private UILoader uiLoader;
    private Player player;
    //framerate restriction
    private long lastFrame = 0;
    private final long interval = 1000000000 / 60;

    public enum GameState {
        START_MENU, LEVEL_SELECT, LEVEL, PAUSED
    }

    private GameEngine(StackPane sp, Canvas backgroundCanvas, Canvas entityCanvas, Canvas uiCanvas, Scene scene) {
        levelLoader = new LevelLoader(entityCanvas, new InputHandler(scene));
        uiLoader = new UILoader(backgroundCanvas, sp, this);
        player = new Player(3);
        setState(GameState.START_MENU);
    }

    public static GameEngine getInstance(StackPane sp, Canvas backgroundCanvas, Canvas entityCanvas, Canvas uiCanvas, Scene scene) {
        if (instance == null) instance = new GameEngine(sp, backgroundCanvas, entityCanvas, uiCanvas, scene);
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
                    switch (gameState) {
                        case GameState.START_MENU -> {}
                        case GameState.LEVEL -> levelLoader.updateLevel();
                    }
                }
            }
        };
        timer.start();
    }

    public void setState(GameState gameState) {
        this.gameState = gameState;
        uiLoader.loadMenu(gameState);
    }

    @Override
    public void listenStartMenu() {
        setState(GameState.START_MENU);
        levelLoader.clean();
    }

    @Override
    public void listenLoadLevel(String levelId) {
        setState(GameState.LEVEL);
        levelLoader.loadLevel(levelId);
        uiLoader.loadBackground(SpriteUtil.BACKGROUND);
    }

    @Override
    public void listenReloadLevel() {
        listenLoadLevel(levelLoader.getCurrentLevelId());
    }

    @Override
    public void listenLevelSelectMenu() {
        setState(GameState.LEVEL_SELECT);
    }

    @Override
    public void listenPause() {
        setState(GameState.PAUSED);
    }

    @Override
    public void listenUnPause() {
        this.gameState = GameState.LEVEL;
    }
}
