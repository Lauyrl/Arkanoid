package game.Entities;

public class Brick extends Entity {
    private int hp;
    private BrickState brickState;

    private enum BrickState {
        NORMAL(0), BROKEN(1);
        public final int value;
        private BrickState(int i) {
            value = i;
        }
    }

    public Brick(double x, double y, double w, double h) {
        super(x, y, w, h);
        hp = 1;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public void update() {
       setStateSprite();
    }

    @Override
    public void changeState(Object brickState) {
        this.brickState = (BrickState) brickState;
    }

    @Override
    public void setStateSprite() {

    }

}
