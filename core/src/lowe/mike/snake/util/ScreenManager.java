package lowe.mike.snake.util;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * {@code ScreenManager} is used to manage {@link Screen}s in
 * {@link Game} instances.
 *
 * @author Mike Lowe
 */
public final class ScreenManager implements Disposable {

    private final Game game;
    private final Array<Screen> screens = new Array<Screen>();

    /**
     * Creates a new {@code ScreenManager} with a reference to the
     * {@link Game}.
     *
     * @param game reference to the {@link Game}
     */
    public ScreenManager(Game game) {
        this.game = game;
    }

    /**
     * Sets the {@link Screen} to display. Note that any existing
     * {@link Screen}s are NOT disposed.
     *
     * @param screen the {@link Screen} to display
     */
    public void setScreen(Screen screen) {
        screens.add(screen);
        game.setScreen(screen);
    }

    /**
     * Switches to the previous {@link Screen}, if one exists. Note that
     * this removes and disposes the current {@link Screen}, if one exists.
     */
    public void switchToPreviousScreen() {
        // remove and dispose current screen
        if (screens.size != 0) {
            screens.pop().dispose();
            // switch to previous screen
            if (screens.size != 0) {
                game.setScreen(screens.peek());
            }
        }
    }

    /**
     * Disposes and clears all {@link Screen}s.
     */
    public void disposeAndClearAllScreens() {
        for (Screen screen : screens) {
            screen.dispose();
        }
        screens.clear();
    }

    @Override
    public void dispose() {
        disposeAndClearAllScreens();
    }

}