package game.Inputs;

public interface GameEventObserver {
    public void listenStartMenu();
    public void listenLevelSelectMenu();
    public void listenLoadLevel(String levelId);
    public void listenReloadLevel();
    public void listenPause();
    public void listenUnPause();
}
