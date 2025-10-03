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
                    subscriber.listen(GameState.LEVEL);
                    root.getChildren().remove(menu);
                });
            }
            case GameState.LEVEL -> {
                Button reset = new Button("RESET");
                Button exit = new Button("EXIT");
                Pane options = new Pane(reset, exit);
                setButtonBounds(exit, 100, 100, 100, 100);
                setButtonBounds(reset, 100, 250, 100, 100);
                root.getChildren().add(options);
                reset.setOnMouseClicked(e -> {
                    root.getChildren().remove(options);
                    subscriber.listen(GameState.LEVEL);
                });
                exit.setOnMouseClicked(e -> {
                    cleanBackground();
                    root.getChildren().remove(options);
                    subscriber.listen(GameState.START_MENU);
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
