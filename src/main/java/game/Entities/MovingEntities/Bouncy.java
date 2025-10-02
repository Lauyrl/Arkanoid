package game.Entities.MovingEntities;

public interface Bouncy { 
    public void bounceX();
    public void bounceY();
    public void bounceOffPaddle(double paddleCenterX, double paddleWidth);
}
