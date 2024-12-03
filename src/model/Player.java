package model;

public class Player {
    private final int id;
    private final String name;
    private int score;
    private int lives;
    private int level;

    public Player(int id, String name, int score, int lives, int level) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.lives = lives;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public int getLevel() {
        return level;
    }


    public void setScore(int score) {
        this.score = score;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
