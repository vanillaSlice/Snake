package lowe.mike.snake.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import lowe.mike.snake.SnakeGame;
import lowe.mike.snake.util.Assets;

/**
 * Created by mikelowe on 14/04/2017.
 */

public class World {

    public Vector2 food = new Vector2();

    private final Snake snake;
    private float tickInterval = .02f;
    private float tick;

    public World(Assets assets) {
        this.snake = new Snake(assets.getSnakeBodyTexture());
        setFoodPosition();
    }

    private void setFoodPosition() {
        int x = (int) (MathUtils.random(20) * (snake.getWidth()) / snake.getScaleX());
        int y = (int)(MathUtils.random(20) * (snake.getHeight()) / snake.getScaleY());
        food.set(x, y);
    }

    public Snake getSnake() {
        return snake;
    }

    public void update(float delta) {
        if (tick > tickInterval) {
            tick = 0f;
            snake.update(delta);
            // rough calculations -- turn into grid
            if (snake.getX() < 0) {
                snake.setX(SnakeGame.VIRTUAL_WIDTH);
            }
            if (snake.getX() > SnakeGame.VIRTUAL_WIDTH) {
                snake.setX(-snake.getWidth());
            }
            if (snake.getY() < 0) {
                snake.setY(SnakeGame.VIRTUAL_HEIGHT);
            }
            if (snake.getY() > SnakeGame.VIRTUAL_HEIGHT) {
                snake.setY(-snake.getHeight());
            }
            System.out.println(food + " " + snake.getX() + "," + snake.getY());
        } else {
            tick += delta;
        }
        if ((int) snake.getY() == (int) food.y && (int) snake.getX() == (int) food.x) {
            setFoodPosition();
        }
    }

}
