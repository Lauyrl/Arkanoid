package game;

import game.Renderering.Renderer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

// class Main
public class App extends Application {
    @Override
    public void start(Stage stage) { //Stage = cửa sổ
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        //Các renderer có canvas khác nhau (ở 3 tầng khác nhau trong stackPane)
        Renderer backgroundRenderer = new Renderer(screenBounds);
        Renderer entityRenderer = new Renderer(screenBounds);
        Renderer uiRenderer = new Renderer(screenBounds);
        StackPane root = new StackPane();
        //StackPane  định nghĩa thứ tự render các Canvas trong mainScene 
        root.getChildren().addAll(backgroundRenderer.getCanvas(), entityRenderer.getCanvas(), uiRenderer.getCanvas());

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
        GameEngine engine = GameEngine.getInstance(entityRenderer);
        engine.run();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
