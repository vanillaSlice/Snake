package lowe.mike.snake.state;

import lowe.mike.snake.Constants;
import lowe.mike.snake.util.Utils;

/**
 * {@code GameSettings} stores the game's settings which can be changed by the user.
 * <p>
 * Instances of {@code GameSettings} cannot be created.
 *
 * @author Mike Lowe
 */
public final class GameSettings {

    // don't want instances
    private GameSettings() {
    }

    /**
     * @param shouldPlayMusic if music should be played when playing the game
     */
    public static void setShouldPlayMusic(boolean shouldPlayMusic) {
        Utils.getPreferences().putBoolean("play-music", shouldPlayMusic).flush();
    }

    /**
     * @return if music should be played when playing the game
     */
    public static boolean shouldPlayMusic() {
        return Utils.getPreferences().getBoolean("play-music", true);
    }

    /**
     * @return the level to play the game in
     */
    public static int getLevel() {
        return Utils.getPreferences().getInteger("level", Constants.MIN_LEVEL);
    }

    /**
     * @param level the level to play the game in
     */
    public static void setLevel(int level) {
        Utils.getPreferences().putInteger("level", level).flush();
    }

}