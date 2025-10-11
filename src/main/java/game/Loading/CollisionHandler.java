package game.Loading;

import game.Entities.*;
import game.Entities.DynamicEntities.*;
import game.Entities.StaticEntities.StaticEntity;
import java.util.ArrayList;

public class CollisionHandler {
    public static boolean overlaps(Entity a, Entity b) {
        return (a.getLeftBound() < b.getRightBound() && a.getRightBound() > b.getLeftBound()
             && a.getBottomBound() > b.getTopBound() && a.getTopBound() < b.getBottomBound());
    }

    public static void handleCollision(ArrayList<DynamicEntity> movingEntityList, ArrayList<StaticEntity> staticEntityList, int iterations) {
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

    public static void resolveCollision(DynamicEntity a, Entity b) {
        double aOldLeft  = a.getLeftBound()   - a.getVelX();
        double aOldRight = a.getRightBound()  - a.getVelX();
        double aOldTop   = a.getTopBound()    - a.getVelY();
        double aOldBot   = a.getBottomBound() - a.getVelY();
        double tX = computeTimeOfCollision(a.getVelX(), aOldLeft, aOldRight, b.getLeftBound(), b.getRightBound());
        double tY = computeTimeOfCollision(a.getVelY(), aOldTop , aOldBot  , b.getTopBound() , b.getBottomBound());
        b.relayCollision(a, aOldLeft, aOldRight, aOldTop, aOldBot, tX, tY);
    }

    public static double computeTimeOfCollision(double vel, double aLo, double aHi, double bLo, double bHi) {
        double t;
        if (vel > 0 && bLo > aHi) {
            t = (bLo - aHi) / vel;
        } else if (vel < 0 && bHi < aLo) {
            t = (bHi - aLo) / vel;
        } else {
            t = 1;
        }
        return Math.max(0, Math.min(t, 1));
    }

    // @Deprecated
    // public static void resolveCollisionOld(DynamicEntity a, Entity b) {
    //     if (a instanceof Paddle && b instanceof Bouncy) {
    //         Entity paddle = a;
    //         a = (DynamicEntity) b;
    //         b = paddle;
    //     }
    //     double aOldLeft  = a.getLeftBound()   - a.getVelX();
    //     double aOldRight = a.getRightBound()  - a.getVelX();
    //     double aOldTop   = a.getTopBound()    - a.getVelY();
    //     double aOldBot   = a.getBottomBound() - a.getVelY();
    //     double tX = computeTimeOfCollision(a.getVelX(), aOldLeft, aOldRight, b.getLeftBound(), b.getRightBound());
    //     double tY = computeTimeOfCollision(a.getVelY(), aOldTop , aOldBot  , b.getTopBound() , b.getBottomBound());

    //     if (a instanceof Paddle && b instanceof Collidable) {
    //         a.setX(a.getX() + ((a.getX() < b.getX()) ? (b.getLeftBound() - a.getRightBound()) : (b.getRightBound() - a.getLeftBound())));
    //         a.setVelX(0);
    //     } 

    //     else if ((a instanceof Ball && b instanceof StaticEntity && b instanceof Collidable)
    //           || (a instanceof Bouncy && b instanceof Paddle)) {
    //         double t = Math.min(tX, tY);
    //         a.setX(aOldLeft + a.getVelX() * t);
    //         a.setY(aOldTop  + a.getVelY() * t);
    //         if (tX < tY) {
    //             ((Bouncy) a).bounceX();
    //         } else {
    //             if (b instanceof Paddle && a.getY() <= b.getY()) {
    //                 ((Bouncy) a).bounceOffPaddle(b.getXCenter(), b.getWidth());
    //             } else if (!(b instanceof Paddle)) {
    //                 ((Bouncy) a).bounceY();
    //             }
    //         }
    //         a.setX(a.getX() + a.getVelX() * (1 - t + 1e-6));
    //         a.setY(a.getY() + a.getVelY() * (1 - t + 1e-6));
    //         ((Collidable) b).respondToCollision(a);
    //     } 

    //     else if (a instanceof Paddle && b instanceof PowerUp) {
    //         ((Paddle) a).consumePowerUp(((PowerUp) b).getPowerUpType());
    //         ((PowerUp) b).setConsumed();
    //     } 
    // }

    // @Deprecated
    // public static void resolveCollisionOldOld(DynamicEntity a, Entity b) {
    //     double xOverlap = Math.min(a.getRightBound(), b.getRightBound()) - Math.max(a.getLeftBound(), b.getLeftBound());
    //     double yOverlap = Math.min(a.getBottomBound(), b.getBottomBound()) - Math.max(a.getTopBound(), b.getTopBound());
    //     double aRelativeXb = a.getXCenter() - b.getXCenter();
    //     double aRelativeYb = a.getYCenter() - b.getYCenter();

    //     if (a instanceof Paddle && b instanceof Collidable) {
    //         a.setX(a.getX() + Math.copySign(xOverlap, aRelativeXb));
    //         a.setVelX(0);
    //     } 

    //     else if (a instanceof Paddle && b instanceof PowerUp) {
    //         ((Paddle) a).consumePowerUp(((PowerUp) b).getPowerUpType());
    //         ((PowerUp) b).setConsumed();
    //     } 

    //     else if ((a instanceof Ball && b instanceof StaticEntity && b instanceof Collidable)
    //           || (a instanceof Bouncy && b instanceof Paddle) 
    //           || (a instanceof Paddle && b instanceof Bouncy)) {
    //         if (a instanceof Paddle) {
    //             Entity paddle = a;
    //             a = (DynamicEntity) b;
    //             b = paddle;
    //         }
    //         if (xOverlap < yOverlap) {
    //             a.setX(a.getX() + Math.copySign(xOverlap, aRelativeXb));
    //             ((Bouncy) a).bounceX();
    //         } else {
    //             a.setY(a.getY() + Math.copySign(yOverlap, aRelativeYb));
    //             if (b instanceof Paddle && a.getY() <= b.getY()) {
    //                 ((Bouncy) a).bounceOffPaddle(b.getXCenter(), b.getWidth());
    //             } else if (!(b instanceof Paddle)) {
    //                 ((Bouncy) a).bounceY();
    //             }
    //         }
    //         ((Collidable) b).respondToCollision(a);
    //     } 

    //     else if (a instanceof Bouncy && b instanceof Bouncy) {
    //         if (xOverlap < yOverlap) {
    //             a.setX(a.getX() + Math.copySign(xOverlap/2, aRelativeXb));
    //             b.setX(b.getX() - Math.copySign(xOverlap/2, aRelativeXb));
    //             ((Bouncy) a).bounceX();
    //             ((Bouncy) b).bounceX();
    //         } else {
    //             a.setY(a.getY() + Math.copySign(yOverlap/2, aRelativeYb));
    //             b.setY(b.getY() - Math.copySign(yOverlap/2, aRelativeYb));
    //             ((Bouncy) a).bounceY();
    //             ((Bouncy) b).bounceY();
    //         }
    //     }
    // }
}
