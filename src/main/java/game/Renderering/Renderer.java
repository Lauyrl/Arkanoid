package game.Renderering;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Renderer {
    private Canvas canvas;
    private GraphicsContext gc;

    public Renderer(Rectangle2D screenBorder) {
        canvas = new Canvas(screenBorder.getWidth(), screenBorder.getHeight());
        gc = canvas.getGraphicsContext2D();
    }
    
    public void render(Image image, double x, double y, double w, double h) {
        gc.drawImage(image, x, y, w, h);
    }

    public void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    // for testing
    public void renderStrokeRect() {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);
        gc.strokeRect(200, 200, 400, 600);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
