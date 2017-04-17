package lowe.mike.snake.world;

/**
 * {@code Level} stores the maximum/minimum levels the game can be played
 * in and provides a helper method to validate a given level number.
 * <p>
 * Instances of {@code Level} cannot be created.
 *
 * @author Mike Lowe
 */
public final class Level {

    // don't want instances
    private Level() {
    }

    public static final int MIN = 1;
    public static final int MAX = 9;

    /**
     * @param level level number to check
     * @return {@code true} if the level number is valid
     */
    public static boolean isValid(int level) {
        return level >= MIN && level <= MAX;
    }

}