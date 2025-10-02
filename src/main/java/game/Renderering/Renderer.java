package game.Renderering;

import game.Entities.Entity;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Renderer {
    private Canvas canvas;
    private GraphicsContext gc;

    public Renderer(Canvas canvas) {
        this.canvas = canvas;
        this.gc = this.canvas.getGraphicsContext2D();
    }
    
    public void render(Image image, double x, double y, double w, double h) {
        double scaleRatio = Math.min(canvas.getWidth() / 1920, canvas.getHeight() / 1080);
        gc.drawImage(image, x * scaleRatio, y * scaleRatio, w * scaleRatio, h * scaleRatio);
    }

    public void render(Entity e) {
        render(e.getSprite(), e.getX(), e.getY(), e.getWidth(), e.getHeight());
    }

    public void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
