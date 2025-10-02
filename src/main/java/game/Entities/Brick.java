package game.Entities;

import javafx.scene.image.Image;

public class Brick extends StaticEntity {
    private int hp;
    private BrickState brickState;
    private static Image[] normalSprites = {new Image(Entity.class.getResourceAsStream("/assets/Brick.png"))};

    private enum BrickState {
        NORMAL(0, 20), BROKEN(1, 20);

        public final int index, spriteInterval;
        private BrickState(int v, int i) {
            index = v;
            spriteInterval = i;
        }
    }

    public Brick(double x, double y, double w, double h) {
        super(x, y, w, h);
        setSpriteArrays(normalSprites);
        setState(BrickState.NORMAL);
        hp = 1;
    }

    @Override
    public void update() {
       setStateSprite(brickState.index, brickState.spriteInterval);
    }

    @Override
    public void setState(Object brickState) {
        this.brickState = (BrickState) brickState;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }
}
