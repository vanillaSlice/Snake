package lowe.mike.snake.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import lowe.mike.snake.SnakeGame;
import lowe.mike.snake.world.Level;

/**
 * {@code GamePreferences} provides access to preferences for the game.
 * <p>
 * Instances of {@code GamePreferences} cannot be created.
 *
 * @author Mike Lowe
 */
public final class GamePreferences {

    private static final String PLAY_SOUNDS_KEY = "play-sounds";
    private static final boolean PLAY_SOUNDS_DEFAULT = true;
    private static final String LEVEL_KEY = "level";
    private static final int LEVEL_DEFAULT = Level.MIN;
    private static final String HIGH_SCORE_KEY = "high-score";

    // don't want instances
    private GamePreferences() {
    }

    /**
     * @return if sounds should be played when playing the game
     */
    public static boolean shouldPlaySounds() {
        return getPreferences().getBoolean(PLAY_SOUNDS_KEY, PLAY_SOUNDS_DEFAULT);
    }

    private static Preferences getPreferences() {
        return Gdx.app.getPreferences(SnakeGame.TITLE);
    }

    /**
     * @param playSounds if sounds should be played when playing the game
     */
    public static void setPlaySounds(boolean playSounds) {
        getPreferences().putBoolean(PLAY_SOUNDS_KEY, playSounds).flush();
    }

    /**
     * @return the level to play the game in
     */
    public static int getLevel() {
        int level = getPreferences().getInteger(LEVEL_KEY);
        return Level.isValid(level) ? level : LEVEL_DEFAULT;
    }

    /**
     * @param level the level to play the game in
     */
    public static void setLevel(int level) {
        if (Level.isValid(level)) {
            getPreferences().putInteger(LEVEL_KEY, level).flush();
        }
    }

    /**
     * @return the high score
     */
    public static int getHighScore() {
        return getPreferences().getInteger(HIGH_SCORE_KEY);
    }

    /**
     * @param highScore the high score
     */
    public static void setHighScore(int highScore) {
        getPreferences().putInteger(HIGH_SCORE_KEY, highScore).flush();
    }

}