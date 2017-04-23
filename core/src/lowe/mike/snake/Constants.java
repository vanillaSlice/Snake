package lowe.mike.snake;

import com.badlogic.gdx.graphics.Color;

/**
 * {@code Constants} contains properties that are used throughout the game.
 * <p>
 * Instances of {@code Constants} cannot be created
 *
 * @author Mike Lowe
 */
public final class Constants {

    // don't want instances
    private Constants() {
    }

    public static final String GAME_TITLE = "Snake";
    public static final int GAME_WIDTH = 360;
    public static final int GAME_HEIGHT = 640;
    public static final int LARGE_FONT_SIZE = 62;
    public static final int MEDIUM_FONT_SIZE = 36;
    public static final int SMALL_FONT_SIZE = 28;
    public static final float MUSIC_VOLUME = .2f;
    public static final Color PRIMARY_FONT_COLOR = new Color(0x181818ff);
    public static final Color SECONDARY_FONT_COLOR = new Color(0x4a4a4aff);
    public static final int MIN_LEVEL = 1;
    public static final int MAX_LEVEL = 9;
    public static final float TICK_INTERVAL_INCREMENT = .05f;

}