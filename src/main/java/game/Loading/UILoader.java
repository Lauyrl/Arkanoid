package game.Loading;

import game.GameEngine.GameState;
import game.Inputs.GameEventObserver;
import game.Renderering.Renderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class UILoader {
    private StackPane root;
    private Renderer uiRenderer;
    private GameEventObserver subscriber;

    public UILoader(Canvas backgroundCanvas, StackPane root, GameEventObserver subscriber) {
        this.uiRenderer = new Renderer(backgroundCanvas);
        this.root = root;
        this.subscriber = subscriber;
    }

    public void loadMenu(GameState gameState) {
        switch (gameState) {
            case GameState.START_MENU -> {
                Button start = new Button("START");
                Pane menu = new Pane(start);
                setButtonBounds(start, 610, 400, 300, 80);
                root.getChildren().add(menu);
                start.setOnMouseClicked(e -> {
                    subscriber.listenLevelSelectMenu();
                    root.getChildren().remove(menu);
                });
            }
            case GameState.LEVEL -> {
                Button reset = new Button("RESET");
                Button exit = new Button("EXIT");
                Button pause = new Button("PAUSE");
                Pane options = new Pane(reset, exit, pause);
                setButtonBounds(exit, 100, 100, 100, 100);
                setButtonBounds(reset, 100, 250, 100, 100);
                setButtonBounds(pause,100, 400,100,100);
                root.getChildren().add(options);

                pause.setOnMouseClicked(e->{
                    subscriber.listenPause();
                });
                reset.setOnMouseClicked(e -> {
                    cleanBackground();
                    subscriber.listenReloadLevel();
                    root.getChildren().remove(options);
                });
                exit.setOnMouseClicked(e -> {
                    cleanBackground();
                    subscriber.listenStartMenu();
                    root.getChildren().remove(options);
                });
            }
            case GameState.LEVEL_SELECT -> {
                Pane selectMenu = new Pane();

                // các nút để chọn level 0 hoặc 1, có thêm nút back để quay trở lại màn hình ban đầu
                Button level0 = new Button("Level 0");
                Button level1 = new Button("Level 1");
                Button back = new Button("Back");

                setButtonBounds(level0, 610, 300, 300, 80);
                setButtonBounds(level1, 610, 400, 300, 80);
                setButtonBounds(back, 610, 500, 300, 80);

                // thêm nút vaò giao diện
                selectMenu.getChildren().addAll(level0, level1, back);
                root.getChildren().add(selectMenu);

                // xử lý khi nhấn nút
                level0.setOnMouseClicked(e -> {
                    root.getChildren().remove(selectMenu);
                    subscriber.listenLoadLevel("Level_0");
                });

                level1.setOnMouseClicked(e -> {
                    root.getChildren().remove(selectMenu);
                    subscriber.listenLoadLevel("Level_1");
                });

                back.setOnMouseClicked(e -> {
                    root.getChildren().remove(selectMenu);
                    subscriber.listenStartMenu();
                });
            }
            //game pause
            case GameState.PAUSED -> {
                Pane pauseMenu = new Pane();
                Button unPause = new Button("Continue");
                setButtonBounds(unPause,610, 400, 300, 80);
                pauseMenu.getChildren().add(unPause);
                root.getChildren().add(pauseMenu);
                //when click Continue
                unPause.setOnMouseClicked(e ->{
                    root.getChildren().remove(pauseMenu);
                    subscriber.listenUnPause();
                });
            }
        }
    }

    public void loadBackground(Image image) {
        uiRenderer.render(image, 0, 45, 1920, 1080);
    }

    public void cleanBackground() {
        uiRenderer.clearCanvas();
    }

    public static void setButtonBounds(Button button, double x, double y, double w, double h) {
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefSize(w, h);
    }
}
