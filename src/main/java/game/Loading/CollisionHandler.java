package game.Loading;

import game.Entities.*;
import game.Entities.MovingEntities.*;
import game.Entities.StaticEntities.Collidable;
import game.Entities.StaticEntities.StaticEntity;

public class CollisionHandler {
    public static boolean overlaps(Entity a, Entity b) {
        return (a.getLeftBound() < b.getRightBound()
            && a.getRightBound() > b.getLeftBound()
            && a.getBottomBound() > b.getTopBound()
            && a.getTopBound() < b.getBottomBound());
    }

    public static void resolveCollision(MovingEntity a, Entity b) {
        double xOverlap = Math.min(a.getRightBound(), b.getRightBound()) - Math.max(a.getLeftBound(), b.getLeftBound());
        double yOverlap = Math.min(a.getBottomBound(), b.getBottomBound()) - Math.max(a.getTopBound(), b.getTopBound());
        double aRelativeXb = a.getCenter()[0] - b.getCenter()[0];
        double aRelativeYb = a.getCenter()[1] - b.getCenter()[1];

        if (a instanceof Paddle && b instanceof Bouncy) {
            if (xOverlap < yOverlap) {
                b.setX(b.getX() - Math.copySign(xOverlap, aRelativeXb));
                ((Bouncy) b).bounceX();
            } else {
                b.setY(b.getY() - Math.copySign(yOverlap, aRelativeYb));
                ((Bouncy) b).bounceY();
            }
        } 

        else if (a instanceof Paddle && b instanceof Collidable) {
            a.setX(a.getX() + Math.copySign(xOverlap, aRelativeXb));
            a.setVelX(0);
        } 

        else if (a instanceof Bouncy && b instanceof Paddle) {
            if (xOverlap < yOverlap) {
                a.setX(a.getX() + Math.copySign(xOverlap, aRelativeXb));
                ((Bouncy) a).bounceX();
            } else {
                a.setY(a.getY() + Math.copySign(yOverlap, aRelativeYb));
                ((Bouncy) a).bounceY();
            }
        } 

        else if (a instanceof Ball && b instanceof StaticEntity && b instanceof Collidable) {
            if (xOverlap < yOverlap) {
                a.setX(a.getX() + Math.copySign(xOverlap, aRelativeXb));
                ((Bouncy) a).bounceX();
            } else {
                a.setY(a.getY() + Math.copySign(yOverlap, aRelativeYb));
                ((Bouncy) a).bounceY();
            }
            ((Collidable) b).respondToCollision(a);
        } 

        else if (a instanceof Bouncy && b instanceof Bouncy) {
            if (xOverlap < yOverlap) {
                a.setX(a.getX() + Math.copySign(xOverlap/2, aRelativeXb));
                b.setX(b.getX() - Math.copySign(xOverlap/2, aRelativeXb));
                ((Bouncy) a).bounceX();
                ((Bouncy) b).bounceX();
            } else {
                a.setY(a.getY() + Math.copySign(yOverlap/2, aRelativeYb));
                b.setY(b.getY() - Math.copySign(yOverlap/2, aRelativeYb));
                ((Bouncy) a).bounceY();
                ((Bouncy) b).bounceY();
            }
        }
    }
}
