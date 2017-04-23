package lowe.mike.snake.world;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import lowe.mike.snake.Constants;
import lowe.mike.snake.util.Assets;

/**
 * Created by mikelowe on 14/04/2017.
 */

public class World {

    private final Snake snake;
    private float tickInterval = .05f;
    private float tick;
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    public final Image food;

    public World(Assets assets, float x, float y, float width, float height) {
        this.snake = new Snake(assets.getSnakeHeadOpen(), assets.getSnakeHeadClosed(), assets.getSnakeBody());
        this.snake.setPosition(x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.food = new Image(assets.getFood());
        this.food.setAlign(Align.center);
        setFoodPosition();
    }

    public void setLevel(int level) {
        tickInterval = Math.abs(Constants.MAX_LEVEL + 1 - level) * Constants.TICK_INTERVAL_INCREMENT;
    }

    public Snake getSnake() {
        return snake;
    }

    public void update(float delta) {
        if (tick > tickInterval) {
            tick = 0f;
            snake.update(delta);
            if (snake.getX() < x) {
                snake.setX(width + x - snake.getWidth());
            }
            if (snake.getX() >= width + x) {
                snake.setX(x);
            }
            if (snake.getY() < y) {
                snake.setY(height + y - snake.getHeight());
            }
            if (snake.getY() >= height + y) {
                snake.setY(y);
            }
            if (snake.getY() == food.getY() && snake.getX() == food.getX()) {
                setFoodPosition();
            }
        } else {
            tick += delta;
        }
    }

    private void setFoodPosition() {
        food.setPosition((MathUtils.random(1, 19) * snake.getWidth()) + x,
                (MathUtils.random(1, 19) * snake.getHeight()) + y);
    }

}
