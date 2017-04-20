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
        label.setPosition(COMPONENT_SPACING, stage.getHeight() - COMPONENT_SPACING * 2.5f - (label.getHeight() / 2));
        this.world = new World(assets, COMPONENT_SPACING, 240f, 360f, 360f);
        this.stage.addActor(this.world.getSnake());
        this.stage.addActor(new Image(assets.getGameFrame()));

        this.stage.addActor(label);

        ImageButton rightButton = Utils.createImageButton(assets.getLargeRightArrow(),
                assets.getLargeRightArrowPressed());
        rightButton.setPosition(220f, 100f);
        this.stage.addActor(rightButton);
        rightButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                world.getSnake().setDirection(Snake.Direction.RIGHT);
                return true;
            }
        });

        ImageButton leftButton = Utils.createImageButton(assets.getLargeLeftArrow(),
                assets.getLargeLeftArrowPressed());
        leftButton.setPosition(76f, 100f);
        this.stage.addActor(leftButton);
        leftButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                world.getSnake().setDirection(Snake.Direction.LEFT);
                return true;
            }
        });

        ImageButton upButton = Utils.createImageButton(assets.getLargeUpArrow(),
                assets.getLargeUpArrowPressed());
        upButton.setPosition(148f, 164f);
        this.stage.addActor(upButton);
        upButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                world.getSnake().setDirection(Snake.Direction.UP);
                return true;
            }
        });

        ImageButton downButton = Utils.createImageButton(assets.getLargeDownArrow(),
                assets.getLargeDownArrowPressed());
        downButton.setPosition(148f, 36f);
        this.stage.addActor(downButton);
        downButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                world.getSnake().setDirection(Snake.Direction.DOWN);
                return true;
            }
        });

        ImageButton pauseButton = Utils.createImageButton(assets.getPause(),
                assets.getPausePressed());
        pauseButton.setPosition(304f, 200f);
        pauseButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Paused");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        this.stage.addActor(pauseButton);
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
           snake.setDirection(Snake.Direction.UP);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            snake.setDirection(Snake.Direction.RIGHT);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            snake.setDirection(Snake.Direction.DOWN);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            snake.setDirection(Snake.Direction.LEFT);
        }
    }

}