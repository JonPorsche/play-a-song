package game;

public enum GameIteam {
    SPEED, SLOW;

    public static GameIteam getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
