package game.Inputs;

import game.GameEngine.GameState;

public interface GameEventObserver {
    public void listen(GameState state);
}
