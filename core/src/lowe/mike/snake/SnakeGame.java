package lowe.mike.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lowe.mike.snake.screen.SplashScreen;
import lowe.mike.snake.state.GameState;
import lowe.mike.snake.util.Assets;
import lowe.mike.snake.util.ScreenManager;

/**
 * Main class for <i>Snake</i> game.
 *
 * @author Mike Lowe
 */
public final class SnakeGame extends Game {

    private Assets assets;
    private SpriteBatch spriteBatch;
    private ScreenManager screenManager;

    @Override
    public void create() {
        assets = new Assets();
        spriteBatch = new SpriteBatch();
        screenManager = new ScreenManager(this);
        screenManager.setScreen(new SplashScreen(assets, spriteBatch, screenManager,
                new GameState()));
    }

    @Override
    public void dispose() {
        assets.dispose();
        spriteBatch.dispose();
        screenManager.dispose();
    }

}