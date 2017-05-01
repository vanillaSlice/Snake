package lowe.mike.snake.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import lowe.mike.snake.SnakeGame;
import lowe.mike.snake.util.Assets;
import lowe.mike.snake.util.ScreenManager;
import lowe.mike.snake.util.State;
import lowe.mike.snake.util.Utils;
import lowe.mike.snake.world.World;

/**
 * Screen to show when the game is being played.
 *
 * @author Mike Lowe
 */
final class GameScreen extends BaseScreen {

    private static final float X = COMPONENT_SPACING;
    private static final float Y = 240f;

    private final Label scoreLabel;
    private final World world;
    private final ImageButton upButton;
    private final ImageButton rightButton;
    private final ImageButton downButton;
    private final ImageButton leftButton;
    private final ImageButton pauseButton;
    private final TextButton resumeButton;
    private final TextButton settingsButton;
    private final TextButton exitButton;
    private final TextButton newGameButton;
    private final Label gameOverLabel;
    private final Label highScoreLabel;
    private final Label currentScoreLabel;

    /**
     * Creates a new {@code GameScreen} given the {@link Assets}, the {@link SpriteBatch},
     * the {@link ScreenManager}.
     *
     * @param spriteBatch   the {@link SpriteBatch} to add sprites to
     */
    GameScreen(SpriteBatch spriteBatch) {
        super(spriteBatch);
        setBackground();
        Rectangle bounds = new Rectangle(X, Y, World.WIDTH, World.HEIGHT);
        this.world = new World(bounds, this.stage);
        addGameFrame();
        this.scoreLabel = createScoreLabel();
        this.stage.addActor(this.scoreLabel);
        this.upButton = createUpButton();
        this.rightButton = createRightButton();
        this.downButton = createDownButton();
        this.leftButton = createLeftButton();
        this.pauseButton = createPauseButton();
        this.resumeButton = createResumeButton();
        this.settingsButton = createSettingsButton();
        this.exitButton = createExitButton();
        this.newGameButton = createNewGameButton();
        this.gameOverLabel = Utils.createTextLabel(Assets.getSmallFont(), "Game Over");
        this.highScoreLabel = Utils.createTextLabel(Assets.getSmallFont(), "High Score : ");
        this.currentScoreLabel = Utils.createTextLabel(Assets.getSmallFont(), "Current Score : ");
        newGame();
    }

    private void setBackground() {
        stage.addActor(new Image(Assets.getBackground()));
    }

    private Label createScoreLabel() {
        Label scoreLabel = Utils.createTextLabel(Assets.getSmallFont(), "0000");
        float x = COMPONENT_SPACING;
        float y = SnakeGame.HEIGHT - COMPONENT_SPACING * 2.5f - (scoreLabel.getHeight() / 2);
        scoreLabel.setPosition(x, y);
        return scoreLabel;
    }

    private void addGameFrame() {
        stage.addActor(new Image(Assets.getGameFrame()));
    }

    private ImageButton createUpButton() {
        ImageButton upButton = Utils.createImageButton(Assets.getLargeUpArrow(),
                Assets.getLargeUpArrowPressed());
        upButton.setPosition(148f, 164f);
        upButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                world.setSnakeDirectionUp();
                return true;
            }
        });
        return upButton;
    }

    private ImageButton createRightButton() {
        ImageButton rightButton = Utils.createImageButton(Assets.getLargeRightArrow(),
                Assets.getLargeRightArrowPressed());
        rightButton.setPosition(230f, 100f);
        rightButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                world.setSnakeDirectionRight();
                return true;
            }
        });
        return rightButton;
    }

    private ImageButton createDownButton() {
        ImageButton downButton = Utils.createImageButton(Assets.getLargeDownArrow(),
                Assets.getLargeDownArrowPressed());
        downButton.setPosition(148f, 36f);
        downButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                world.setSnakeDirectionDown();
                return true;
            }
        });
        return downButton;
    }

    private ImageButton createLeftButton() {
        ImageButton leftButton = Utils.createImageButton(Assets.getLargeLeftArrow(),
                Assets.getLargeLeftArrowPressed());
        leftButton.setPosition(66f, 100f);
        leftButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                world.setSnakeDirectionLeft();
                return true;
            }
        });
        return leftButton;
    }

    private ImageButton createPauseButton() {
        ImageButton pauseButton = Utils.createImageButton(Assets.getPause(),
                Assets.getPausePressed());
        pauseButton.setPosition(304f, 200f);
        pauseButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        return pauseButton;
    }

    private TextButton createResumeButton() {
        TextButton resumeButton = Utils.createTextButton(Assets.getSmallFont(), "Resume");
        resumeButton.setPosition(100f, 150f);
        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                State.setGameOver(false);
            }
        });
        return resumeButton;
    }

    private TextButton createSettingsButton() {
        final TextButton settingsButton = Utils.createTextButton(Assets.getSmallFont(), "Settings");
        settingsButton.setPosition(100f, 100f);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.setScreen(new SettingsScreen(spriteBatch));
            }
        });
        return settingsButton;
    }

    private TextButton createExitButton() {
        TextButton exitButton = Utils.createTextButton(Assets.getSmallFont(), "Exit");
        exitButton.setPosition(100f, 50f);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.disposeAndClearAllScreens();
                ScreenManager.setScreen(new MainMenuScreen(spriteBatch));
            }
        });
        return exitButton;
    }

    private TextButton createNewGameButton() {
        TextButton newGameButton = Utils.createTextButton(Assets.getSmallFont(), "New Game");
        newGameButton.setPosition(100f, 100f);
        newGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                newGame();
            }
        });
        return newGameButton;
    }

    private void newGame() {
        State.setGameOver(false);
        State.setCurrentScore(0);
        world.reset();
    }

    @Override
    void onShow() {
        world.setLevel(State.getLevel());
    }

    @Override
    void update(float delta) {
        showState();
        if (State.isGameOver()) {
            updateInGameOverState();
        } else {
            updateInRunningState(delta);
        }
    }

    private void showState() {
        boolean isGameOver = State.isGameOver();
        showRunningState(!isGameOver);
        showGameOverState(isGameOver);
        //showPausedState(!isGameOver);

    }

    private void showRunningState(boolean show) {
        if (show) {
            stage.addActor(upButton);
            stage.addActor(rightButton);
            stage.addActor(downButton);
            stage.addActor(leftButton);
            stage.addActor(pauseButton);
            exitButton.remove();
            newGameButton.remove();
        } else {
            upButton.remove();
            rightButton.remove();
            downButton.remove();
            leftButton.remove();
            pauseButton.remove();
        }
    }

    private void showGameOverState(boolean show) {
        if (show) {
            stage.addActor(newGameButton);
            stage.addActor(exitButton);

            if (State.getCurrentScore() > State.getHighScore()) {
                State.setHighScore(State.getCurrentScore());
            }
            stage.addActor(gameOverLabel);
            highScoreLabel.setText(String.format("High Score : %04d", State.getHighScore()));
            highScoreLabel.setPosition(100f, 300f);
            currentScoreLabel.setText(String.format("Current Score : %04d", State.getCurrentScore()));
            currentScoreLabel.setPosition(100f, 200f);
            stage.addActor(highScoreLabel);
            stage.addActor(currentScoreLabel);

        } else {
            gameOverLabel.remove();
            highScoreLabel.remove();
            currentScoreLabel.remove();
        }
    }

    private void showPausedState(boolean show) {
        if (show) {
            stage.addActor(resumeButton);
            stage.addActor(settingsButton);
            stage.addActor(exitButton);
        } else {
            resumeButton.remove();
            settingsButton.remove();
        }
    }

    private void updateInRunningState(float delta) {
        world.update(delta);
        handleUserInput();
        scoreLabel.setText(String.format("%04d", State.getCurrentScore()));
        scoreLabel.pack();
    }

    private void handleUserInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            world.setSnakeDirectionUp();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            world.setSnakeDirectionRight();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            world.setSnakeDirectionDown();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            world.setSnakeDirectionLeft();
        }
    }

    private void updateInGameOverState() {
        //    world.reset();
        //    gameState.setCurrentState(GameState.State.RUNNING);
        //    gameState.setCurrentScore(0);
    }

    private void updateInPausedState() {
    }

}