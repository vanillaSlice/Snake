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

import lowe.mike.snake.Constants;
import lowe.mike.snake.state.GameSettings;
import lowe.mike.snake.state.GameState;
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

    private final Label scoreLabel;
    private final World world;

    /**
     * Creates a new {@code GameScreen} given the {@link Assets}, the {@link SpriteBatch},
     * the {@link ScreenManager} and the {@link GameState}.
     *
     * @param assets        the {@link Assets} containing assets used in the {@link Screen}
     * @param spriteBatch   the {@link SpriteBatch} to add sprites to
     * @param screenManager the {@link ScreenManager} used to manage game {@link Screen}s
     * @param gameState     the {@link GameState} containing the current game state
     */
    GameScreen(Assets assets, SpriteBatch spriteBatch, ScreenManager screenManager,
               final GameState gameState) {
        super(assets, spriteBatch, screenManager, gameState);
        setBackground();
        this.scoreLabel = createScoreLabel();
        this.world = new World(assets, COMPONENT_SPACING, 240f, 320f, 320f, stage);
        this.stage.addActor(this.world.getSnake());
        this.stage.addActor(this.world.food);
        this.stage.addActor(new Image(assets.getGameFrame()));

        this.stage.addActor(this.scoreLabel);

        ImageButton rightButton = Utils.createImageButton(assets.getLargeRightArrow(),
                assets.getLargeRightArrowPressed());
        rightButton.setPosition(230f, 100f);
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
        leftButton.setPosition(66f, 100f);
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
                if (gameState.getCurrentState() == GameState.State.PAUSED) {
                    gameState.setCurrentState(GameState.State.RUNNING);
                } else {
                    gameState.setCurrentState(GameState.State.PAUSED);
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        this.stage.addActor(pauseButton);
        world.setLevel(GameSettings.getLevel());
    }

    private void setBackground() {
        stage.addActor(new Image(assets.getBackground()));
    }

    private Label createScoreLabel() {
        Label scoreLabel = Utils.createTextLabel(assets.getSmallFont(), "0000");
        float x = COMPONENT_SPACING;
        float y = Constants.GAME_HEIGHT - COMPONENT_SPACING * 2.5f - (scoreLabel.getHeight() / 2);
        scoreLabel.setPosition(x, y);
        return scoreLabel;
    }

    @Override
    void update(float delta) {
        switch (gameState.getCurrentState()) {
            case RUNNING:
                updateInRunningState(delta);
                break;
            case ENTERING_GAME_OVER:
                updateInEnteringGameOverState();
                break;
            case GAME_OVER:
                updateInGameOverState();
                break;
            case PAUSED:
                updateInPausedState();
                break;
        }
    }

    private void updateInRunningState(float delta) {
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

    private void updateInEnteringGameOverState() {
    }

    private void updateInGameOverState() {
    }

    private void updateInPausedState() {
    }

}