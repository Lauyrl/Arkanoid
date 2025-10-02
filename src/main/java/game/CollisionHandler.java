package game;

import game.Entities.*;

public class CollisionHandler {
    public static boolean overlaps(Entity a, Entity b) {
        return (a.getLeftBound() < b.getRightBound()
            && a.getRightBound() > b.getLeftBound()
            && a.getBottomBound() > b.getTopBound()
            && a.getTopBound() < b.getBottomBound());
    }

    public static void resolveCollision(Ball a, StaticEntity b) {
        double xOverlap = Math.min(a.getRightBound(), b.getRightBound()) - Math.max(a.getLeftBound(), b.getLeftBound());
        double yOverlap = Math.min(a.getBottomBound(), b.getBottomBound()) - Math.max(a.getTopBound(), b.getTopBound());
        if (xOverlap < yOverlap) {
            if (a.getCenter()[0] < b.getCenter()[0]) {
                a.setX(a.getX() - Math.abs(xOverlap));
            } else {
                a.setX(a.getX() + Math.abs(xOverlap));
            }
            a.setVelX(-a.getVelX());
        } else {
            if (a.getCenter()[1] < b.getCenter()[1]) {
                a.setY(a.getY() - Math.abs(yOverlap));
            } else {
                a.setY(a.getY() + Math.abs(yOverlap));
            }
            a.setVelY(-a.getVelY());
        }
    }

    public static void resolveCollision(Ball a, Ball b) {
        double xOverlap = Math.min(a.getRightBound(), b.getRightBound()) - Math.max(a.getLeftBound(), b.getLeftBound());
        double yOverlap = Math.min(a.getBottomBound(), b.getBottomBound()) - Math.max(a.getTopBound(), b.getTopBound());
        if (xOverlap < yOverlap) {
            if (a.getCenter()[0] < b.getCenter()[0]) {
                a.setX(a.getX() - Math.abs(xOverlap)/2);    
                b.setX(b.getX() + Math.abs(xOverlap)/2);
            }
            else {
                a.setX(a.getX() + Math.abs(xOverlap)/2);    
                b.setX(b.getX() - Math.abs(xOverlap)/2);
            }
            a.setVelX(-a.getVelX());
            b.setVelX(-b.getVelX());
        } else {
            if (a.getCenter()[1] < b.getCenter()[1]) {
                a.setY(a.getY() - Math.abs(yOverlap)/2);
                b.setY(b.getY() + Math.abs(yOverlap)/2);
            }
            else {
                a.setY(a.getY() + Math.abs(yOverlap)/2);
                b.setY(b.getY() - Math.abs(yOverlap)/2);
            }
            a.setVelY(-a.getVelY());
            b.setVelY(-b.getVelY());
        }
    }
}
