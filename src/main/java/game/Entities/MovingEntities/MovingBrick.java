package game.Entities.MovingEntities;

import game.Entities.StaticEntities.Brick;

public class MovingBrick extends Brick implements LimitedMovingObject {
    private int stepCounter = 0;
    private double movingRange = 200;
    private double movingSpeed = 2;
    private double startX;

    public MovingBrick(double x, double y, double width, double height, int hp) {
        super(x,y,width,height,hp);
        this.startX = x;
    }

    @Override
    public void moveInLine() {
        stepCounter++;
        double offset = Math.sin(stepCounter * 0.05) * movingRange/2;
        setX(startX + offset);

    }

    @Override
    public void update() {
        moveInLine();
        super.update();
    }
}