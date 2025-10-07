package game.Entities.StaticEntities;

import game.Entities.Entity;

public abstract class StaticEntity extends Entity {
    public static final int NEGATIVE = -1;
    public static final int POSITIVE = 1;
    private int direction;
    private double vel; 
    private double movementLeftBound, movementRightBound, movementUpperBound, movementLowerBound;  
    
    public StaticEntity(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    public StaticEntity(double x, double y, double w, double h, int initialDirection, double vel, double movementLeftBound, double movementRightBound, double movementUpperBound, double movementLowerBound) {
        super(x, y, w, h);
        this.direction = initialDirection;
        this.vel = vel;
        this.movementLeftBound = movementLeftBound;
        this.movementRightBound = movementRightBound;
        this.movementUpperBound = movementUpperBound;
        this.movementLowerBound = movementLowerBound;
    }

    public void horizontalOscillate() {
        setX(getX() + vel * direction);
        if (getX() > movementRightBound) {
            setX(movementRightBound); //adjust to bound
            direction = NEGATIVE;
        } else if (getX() < movementLeftBound) {
            setX(movementLeftBound); //adjust to bound
            direction = POSITIVE;
        }
    }

    public void drop() {
        setY(getY() + vel * direction);
    }
}
