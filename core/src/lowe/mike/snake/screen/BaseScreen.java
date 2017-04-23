package lowe.mike.snake.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import lowe.mike.snake.Constants;
import lowe.mike.snake.state.GameState;
import lowe.mike.snake.util.Assets;
import lowe.mike.snake.util.ScreenManager;

/**
 * Provides a base class for the {@link Screen}s in the game.
 *
 * @author Mike Lowe
 */
class BaseScreen extends ScreenAdapter {

    static final float COMPONENT_SPACING = 20f;

    final Assets assets;
    final SpriteBatch spriteBatch;
    final ScreenManager screenManager;
    final GameState gameState;
    final Stage stage;

    private final OrthographicCamera camera = new OrthographicCamera();
    private final Viewport viewport;

    /**
     * Creates a new {@code BaseScreen} given the {@link Assets}, the {@link SpriteBatch},
     * the {@link ScreenManager} and the {@link GameState}.
     *
     * @param assets        the {@link Assets} containing assets used in the {@link Screen}
     * @param spriteBatch   the {@link SpriteBatch} to add sprites to
     * @param screenManager the {@link ScreenManager} used to manage game {@link Screen}s
     * @param gameState     the {@link GameState} containing the current game state
     */
    BaseScreen(Assets assets, SpriteBatch spriteBatch, ScreenManager screenManager,
               GameState gameState) {
        this.assets = assets;
        this.spriteBatch = spriteBatch;
        this.screenManager = screenManager;
        this.gameState = gameState;
        this.camera.setToOrtho(false);
        this.viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, this.camera);
        this.stage = new Stage(this.viewport, this.spriteBatch);
    }

    @Override
    public final void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public final void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public final void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        spriteBatch.setProjectionMatrix(camera.combined);
        stage.act(delta);
        stage.draw();
    }

    /**
     * Method that subclasses can override to determine how to
     * update the {@link Screen} in each frame.
     *
     * @param delta time in seconds since the last frame
     */
    void update(float delta) {
    }

    @Override
    public final void dispose() {
        stage.dispose();
        onDispose();
    }

    /**
     * Method that subclasses can override to determine what to
     * dispose.
     */
    void onDispose() {
    }

}