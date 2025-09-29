package game;

import java.util.List;
import game.Entities.*;

public class CollisionHandler {
    public static boolean overlaps(Entity a, Entity b) {
        return (a.getLeftBound() < b.getRightBound()
            && a.getRightBound() > b.getLeftBound()
            && a.getBottomBound() > b.getTopBound()
            && a.getTopBound() < b.getBottomBound());
    }

    public static void resolveCollision(Ball a, Entity b) {
        double xOverlap = Math.min(a.getRightBound(), b.getRightBound()) - Math.max(a.getLeftBound(), b.getLeftBound());
        double yOverlap = Math.min(a.getBottomBound(), b.getBottomBound()) - Math.max(a.getTopBound(), b.getTopBound());
        if (b instanceof Ball) {
            
        } else {
            if (xOverlap < yOverlap) {
                if (a.getCenter()[0] < b.getCenter()[0]) {
                    a.setX(a.getX() - Math.abs(xOverlap));
                }
                else {
                    a.setX(a.getX() + Math.abs(xOverlap));
                }
                a.setVelX(-a.getVelX());
            } else {
                if (a.getCenter()[1] < b.getCenter()[1]) {
                    a.setY(a.getY() - Math.abs(yOverlap));
                }
                else {
                    a.setY(a.getY() + Math.abs(yOverlap));
                }
                a.setVelY(-a.getVelY());
            }
        }
    }
}
