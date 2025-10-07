package game.Loading;

import game.Entities.*;
import game.Entities.MovingEntities.*;
import game.Entities.StaticEntities.Collidable;
import game.Entities.StaticEntities.PowerUp;
import game.Entities.StaticEntities.StaticEntity;
import java.util.ArrayList;

public class CollisionHandler {
    public static boolean overlaps(Entity a, Entity b) {
        return (a.getLeftBound() < b.getRightBound() && a.getRightBound() > b.getLeftBound()
             && a.getBottomBound() > b.getTopBound() && a.getTopBound() < b.getBottomBound());
    }

    public static void handleCollision(ArrayList<MovingEntity> movingEntityList, ArrayList<StaticEntity> staticEntityList, int iterations) {
        for (int t = 0; t < iterations; t++) {
            for (int i = 0; i < movingEntityList.size(); i++) {
                for (int j = 0; j < staticEntityList.size(); j++) {
                    if (overlaps(movingEntityList.get(i), staticEntityList.get(j))) {
                        resolveCollision(movingEntityList.get(i), staticEntityList.get(j));
                    }
                }
                for (int j = i+1; j < movingEntityList.size(); j++) {
                    if (overlaps(movingEntityList.get(i), movingEntityList.get(j))) {
                        resolveCollision(movingEntityList.get(i), movingEntityList.get(j));
                    }
                }
            }
        }
    }

    public static void resolveCollision(MovingEntity a, Entity b) {
        double xOverlap = Math.min(a.getRightBound(), b.getRightBound()) - Math.max(a.getLeftBound(), b.getLeftBound());
        double yOverlap = Math.min(a.getBottomBound(), b.getBottomBound()) - Math.max(a.getTopBound(), b.getTopBound());
        double aRelativeXb = a.getCenter()[0] - b.getCenter()[0];
        double aRelativeYb = a.getCenter()[1] - b.getCenter()[1];

        if (a instanceof Paddle && b instanceof Collidable) {
            a.setX(a.getX() + Math.copySign(xOverlap, aRelativeXb));
            a.setVelX(0);
        } 

        if (a instanceof Paddle && b instanceof PowerUp) {
            ((Paddle) a).consumePowerUp(((PowerUp) b).getPowerUpType());
            ((PowerUp) b).setConsumed();
        } 

        else if (a instanceof Paddle && b instanceof Bouncy) {
            if (xOverlap < yOverlap) {
                b.setX(b.getX() - Math.copySign(xOverlap, aRelativeXb));
                ((Bouncy) b).bounceX();
            } else {
                b.setY(b.getY() - Math.copySign(yOverlap, aRelativeYb));
                ((Bouncy) b).bounceOffPaddle(a.getCenter()[0], a.getWidth());
            }
        } 

        else if (a instanceof Bouncy && b instanceof Paddle) {
            if (xOverlap < yOverlap) {
                a.setX(a.getX() + Math.copySign(xOverlap, aRelativeXb));
                ((Bouncy) a).bounceX();
            } else {
                a.setY(a.getY() + Math.copySign(yOverlap, aRelativeYb));
                ((Bouncy) a).bounceOffPaddle(b.getCenter()[0], b.getWidth());
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
