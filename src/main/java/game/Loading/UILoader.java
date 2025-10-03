package game.Loading;

import game.Renderering.Renderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class UILoader {
    private Renderer uiRenderer;

    public UILoader(Canvas backgroundCanvas){
        uiRenderer = new Renderer(backgroundCanvas);
    }

    public void loadUI(Image image) {
        uiRenderer.render(image, 0, 40, 1920, 1080);
    }
}
