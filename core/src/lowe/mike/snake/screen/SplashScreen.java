package lowe.mike.snake.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import lowe.mike.snake.state.GameState;
import lowe.mike.snake.util.Assets;
import lowe.mike.snake.util.ScreenManager;

/**
 * Splash screen to show while assets are being loaded.
 *
 * @author Mike Lowe
 */
public final class SplashScreen extends BaseScreen {

    /**
     * Creates a new {@code SplashScreen} given the {@link Assets}, the {@link SpriteBatch},
     * the {@link ScreenManager} and the {@link GameState}.
     *
     * @param assets        the {@link Assets} containing assets used in the {@link Screen}
     * @param spriteBatch   the {@link SpriteBatch} to add sprites to
     * @param screenManager the {@link ScreenManager} used to manage game {@link Screen}s
     * @param gameState     the {@link GameState} containing the current game state
     */
    public SplashScreen(Assets assets, SpriteBatch spriteBatch, ScreenManager screenManager,
                        GameState gameState) {
        super(assets, spriteBatch, screenManager, gameState);
        setBackground();
    }

    private void setBackground() {
        stage.addActor(new Image(assets.getSplashBackground()));
    }

    @Override
    void update(float delta) {
        if (assets.isFinishedLoading()) {
            switchToMainMenuScreen();
        }
    }

    private void switchToMainMenuScreen() {
        // dispose this screen and all previous screens because we won't be able to return from the
        // next screen
        screenManager.disposeAndClearAllScreens();
        screenManager.setScreen(new MainMenuScreen(assets, spriteBatch, screenManager, gameState));
    }

    @Override
    public void onDispose() {
        // dispose this because it won't be used again
        assets.disposeSplashBackground();
    }

}