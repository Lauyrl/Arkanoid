package game.Entities;

public abstract class MovingEntity extends Entity {
    private double velX, velY;

    public MovingEntity(double x, double y, double w, double h, double velX, double velY) {
        super(x, y, w, h);
        this.velX = velX;
        this.velY = velY;
    }

    public void updatePosition() {
        setX(getX() + velX);
        setY(getY() + velY);
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelX(double v) {
        velX = v;
    }

    public void setVelY(double v) {
        velY = v;
    }
}
