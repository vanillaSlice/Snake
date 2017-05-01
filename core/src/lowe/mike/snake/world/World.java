package lowe.mike.snake.world;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import lowe.mike.snake.util.State;

/**
 * {@code World} represents the world in the game containing the snake, food, etc.
 *
 * @author Mike Lowe
 */
public final class World {

    public static final int WIDTH = 320;
    public static final int HEIGHT = 320;
    public static final int GRID_CELL_WIDTH = 16;
    public static final int GRID_CELL_HEIGHT = 16;
    public static final float TICK_INTERVAL_INCREMENT = .075f;
    public static final int BONUS_FOOD_APPEARANCE_INTERVAL = 5;
    public static final int BONUS_FOOD_TICKS = 20;

    private final Rectangle bounds;
    private final int rows;
    private final int columns;
    private final Stage stage;
    private final Snake snake;
    private final Food food;
    private final BonusFood bonusFood;
    private float tickInterval;
    private float tick;
    private int level;
    private int eaten;
    private int nextBonusRound;
    private int bonusTicks;

    /**
     * Creates a new {@code World} instance.
     *
     * @param bounds the {@link Rectangle} bounds that describe the world
     * @param stage  the {@link Stage} to add {@link Actor}s to
     */
    public World(Rectangle bounds, Stage stage) {
        this.bounds = bounds;
        this.rows = MathUtils.ceil(this.bounds.height / GRID_CELL_HEIGHT);
        this.columns = MathUtils.ceil(this.bounds.width / GRID_CELL_WIDTH);
        this.stage = stage;
        this.snake = new Snake(this.bounds);
        this.food = new Food();
        this.bonusFood = new BonusFood();
        this.stage.addActor(this.snake);
        this.stage.addActor(this.food);
        initialise();
    }

    private void initialise() {
        setRandomPosition(food);
        tick = 0f;
        eaten = 0;
        nextBonusRound = BONUS_FOOD_APPEARANCE_INTERVAL;
        bonusTicks = 0;
    }

    private void setRandomPosition(Food food) {
        float x;
        float y;
        do {
            x = (MathUtils.random(columns - 1) * GRID_CELL_WIDTH) + bounds.x;
            y = (MathUtils.random(rows - 1) * GRID_CELL_HEIGHT) + bounds.y;
        } while (isPositionOccupied(x, y));
        food.setPosition(x, y);
    }

    private boolean isPositionOccupied(float x, float y) {
        return snake.isBodyPartOccupyingPosition(x, y) || snake.isHeadOccupyingPosition(x, y) ||
                isFoodOccupyingPosition(x, y) || isBonusFoodOccupyingPosition(x, y);
    }

    private boolean isFoodOccupyingPosition(float x, float y) {
        return food.getX() == x && food.getY() == y;
    }

    private boolean isBonusFoodOccupyingPosition(float x, float y) {
        return isBonusFoodShowing() && bonusFood.getX() == x && bonusFood.getY() == y;
    }

    /**
     * @return if the {@link BonusFood} is showing
     */
    public boolean isBonusFoodShowing() {
        return bonusFood.getStage() != null;
    }

    /**
     * Resets the world to its initial state.
     */
    public void reset() {
        initialise();
        snake.reset();
        bonusFood.remove();
    }

    /**
     * @return the number of bonus ticks
     */
    public int getBonusTicks() {
        return bonusTicks;
    }

    /**
     * @param level the level to play the game in
     */
    public void setLevel(int level) {
        this.level = level;
        tickInterval = (Level.MAXIMUM + 1 - this.level) *
                TICK_INTERVAL_INCREMENT;
    }

    /**
     * Set the snake's direction to go up, if possible.
     */
    public void setSnakeDirectionUp() {
        snake.setCurrentDirection(Snake.Direction.UP);
    }

    /**
     * Set the snake's direction to go right, if possible.
     */
    public void setSnakeDirectionRight() {
        snake.setCurrentDirection(Snake.Direction.RIGHT);
    }

    /**
     * Set the snake's direction to go down, if possible.
     */
    public void setSnakeDirectionDown() {
        snake.setCurrentDirection(Snake.Direction.DOWN);
    }

    /**
     * Set the snake's direction to go left, if possible.
     */
    public void setSnakeDirectionLeft() {
        snake.setCurrentDirection(Snake.Direction.LEFT);
    }

    /**
     * Updates the world's state.
     *
     * @param delta time in seconds since the last frame
     */
    public void update(float delta) {
        if (tick >= tickInterval) {
            tick -= tickInterval;
            snake.updatePositionAndState();
            checkSnakeAteFood();
            checkSnakeAteBonusFood();
            checkBonusFood();
        } else {
            tick += delta;
        }

        if (snake.isDying()) {
            snake.updateDeathSequence(delta);
        } else if (snake.isDead()) {
            State.setGameOver(true);
        }

        if (isBonusFoodShowing()) {
            bonusFood.updateFlash(delta);
        }
    }

    private void checkSnakeAteFood() {
        if (isFoodOccupyingPosition(snake.getHead().getX(), snake.getHead().getY())) {
            setRandomPosition(food);
            State.setCurrentScore(State.getCurrentScore() + level);
            snake.addBodyPart();
            eaten++;
        }
    }

    private void checkSnakeAteBonusFood() {
        if (isBonusFoodOccupyingPosition(snake.getHead().getX(), snake.getHead().getY())) {
            State.setCurrentScore(State.getCurrentScore() + bonusTicks);
            snake.addBodyPart();
            bonusFood.remove();
            nextBonusRound += BONUS_FOOD_APPEARANCE_INTERVAL;
        }
    }

    private void checkBonusFood() {
        if (isBonusFoodShowing() && bonusTicks <= 0) {
            bonusFood.remove();
            nextBonusRound += BONUS_FOOD_APPEARANCE_INTERVAL;
        } else if (isBonusFoodShowing()) {
            bonusTicks--;
        } else if (!isBonusFoodShowing() && eaten == nextBonusRound) {
            stage.addActor(bonusFood);
            bonusTicks = BONUS_FOOD_TICKS;
            setRandomPosition(bonusFood);
        }
    }

}