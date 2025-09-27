package game;

import game.GameEngine.GameState;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

// class Main
public class App extends Application {
    @Override
    public void start(Stage stage) { //Stage = cửa sổ
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        //Canvas khác nhau (ở 3 tầng khác nhau trong stackPane)
        Canvas backgroundCanvas = new Canvas(screenBounds.getWidth(), screenBounds.getHeight());
        Canvas entityCanvas = new Canvas(screenBounds.getWidth(), screenBounds.getHeight());
        Canvas uiCanvas = new Canvas(screenBounds.getWidth(), screenBounds.getHeight());
        StackPane root = new StackPane();
        //StackPane  định nghĩa thứ tự render các Canvas trong mainScene 
        root.getChildren().addAll(backgroundCanvas, entityCanvas, uiCanvas);

        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        //mainScene là root của scene graph: https://docs.oracle.com/javafx/2/scenegraph/jfxpub-scenegraph.htm
        Scene mainScene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        stage.setScene(mainScene);
        stage.setTitle("Arkanoid");
        stage.show();
        //GameEngine quản lí logic game
        GameEngine engine = GameEngine.getInstance(backgroundCanvas, entityCanvas, uiCanvas);
        engine.changeState(GameState.LEVEL);
        engine.run();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
