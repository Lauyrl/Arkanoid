package game.Renderering;

import game.Entities.Entity;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Renderer {
    private Canvas canvas;
    private GraphicsContext gc;

    public Renderer(Canvas canvas) {
        this.canvas = canvas;
        this.gc = this.canvas.getGraphicsContext2D();
    }
    
    public void render(Image image, double x, double y, double w, double h) {
        gc.drawImage(image, x, y, w, h);
    }

    public void render(Entity e) {
        gc.drawImage(e.getSprite(), e.getX(), e.getY(), e.getWidth(), e.getHeight());
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
