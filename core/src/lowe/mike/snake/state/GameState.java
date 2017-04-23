package lowe.mike.snake.state;

import lowe.mike.snake.util.Utils;

/**
 * {@code GameState} contains current state information about the game.
 *
 * @author Mike Lowe
 */
public final class GameState {

    public enum State {

        RUNNING, ENTERING_GAME_OVER, GAME_OVER, PAUSED

    }

    private int currentScore;
    private State currentState = State.RUNNING;

    /**
     * @return the high score
     */
    public int getHighScore() {
        return Utils.getPreferences().getInteger("high-score");
    }

    /**
     * @param highScore the high score
     */
    public void setHighScore(int highScore) {
        Utils.getPreferences().putInteger("high-score", highScore).flush();
    }

    /**
     * @return the current score
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * @param currentScore the current score
     */
    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    /**
     * @return the current {@link State}
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     * @param currentState the current {@link State}
     */
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

}