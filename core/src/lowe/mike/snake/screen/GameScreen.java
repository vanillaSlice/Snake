package lowe.mike.snake.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import lowe.mike.snake.util.Assets;
import lowe.mike.snake.util.ScreenManager;
import lowe.mike.snake.util.Utils;
import lowe.mike.snake.world.Snake;
import lowe.mike.snake.world.World;

/**
 * Screen to show when the game is being played.
 *
 * @author Mike Lowe
 */
final class GameScreen extends BaseScreen {

    private final World world;

    /**
     * Creates a new {@code GameScreen} given {@link Assets}, a {@link SpriteBatch}
     * and a {@link ScreenManager}.
     *
     * @param assets        {@link Assets} containing assets used in the {@link Screen}
     * @param spriteBatch   {@link SpriteBatch} to add sprites to
     * @param screenManager the {@link ScreenManager} used to manage game {@link Screen}s
     */
    GameScreen(Assets assets, SpriteBatch spriteBatch, ScreenManager screenManager) {
        super(assets, spriteBatch, screenManager);
        setBackground();
        Label label = Utils.createLabel(assets.getSmallFont(), "0000");
        label.setPosition(COMPONENT_SPACING, stage.getHeight() - COMPONENT_SPACING * 2.5f
                - (label.getHeight() / 2));
        Image gridFrame = new Image(assets.getGridFrame());
        gridFrame.setPosition(COMPONENT_SPACING, label.getY() - (gridFrame.getHeight() / 2));
        this.stage.addActor(label);
        this.stage.addActor(gridFrame);

        ImageButton rightButton = Utils.createImageButton(assets.getLargeRightArrow(),
                assets.getLargeRightArrowPressed());
        rightButton.setPosition(110f, 50f);
        this.stage.addActor(rightButton);

        ImageButton leftButton = Utils.createImageButton(assets.getLargeLeftArrow(),
                assets.getLargeLeftArrowPressed());
        leftButton.setPosition(38f, 50f);
        this.stage.addActor(leftButton);

        ImageButton upButton = Utils.createImageButton(assets.getLargeUpArrow(),
                assets.getLargeUpArrowPressed());
        upButton.setPosition(74f, 82f);
        this.stage.addActor(upButton);

        ImageButton downButton = Utils.createImageButton(assets.getLargeDownArrow(),
                assets.getLargeDownArrowPressed());
        downButton.setPosition(74f, 18f);
        this.stage.addActor(downButton);

        ImageButton pauseButton = Utils.createImageButton(assets.getPause(),
                assets.getPausePressed());
        pauseButton.setPosition(152f, 100f);
        pauseButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Paused");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        this.stage.addActor(pauseButton);
        this.world = new World(assets, gridFrame.getX(), gridFrame.getY(), gridFrame.getWidth() / 2, gridFrame.getHeight() / 2);
        this.stage.addActor(this.world.getSnake());
    }

    private void setBackground() {
        stage.addActor(new Image(assets.getBackground()));
    }

    @Override
    void update(float delta) {
        world.update(delta);
        handleUserInput();
    }

    private void handleUserInput() {
        Snake snake = world.getSnake();
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && snake.direction != Snake.Direction.DOWN) {
            snake.direction = Snake.Direction.UP;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && snake.direction != Snake.Direction.LEFT) {
            snake.direction = Snake.Direction.RIGHT;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && snake.direction != Snake.Direction.UP) {
            snake.direction = Snake.Direction.DOWN;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && snake.direction != Snake.Direction.RIGHT) {
            snake.direction = Snake.Direction.LEFT;
        }
    }

}