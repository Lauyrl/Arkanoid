package game.Entities;

public class Player {
    private int lives, score = 0;
    public Player(int lives) {
        this.lives = lives;
    }

    // getter và setter
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int value) {
        this.score += value;
    }

    public void loseLife() {
        if (lives > 0) lives--;
    }

    public boolean isAlive() {
        return lives > 0;
    }
}
