package lowe.mike.snake.world;

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

    public World(Assets assets, float x, float y, float width, float height) {
        this.snake = new Snake(assets.getSnakeBodyTexture());
        this.snake.setPosition(x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Snake getSnake() {
        return snake;
    }

    public void update(float delta) {
        if (tick > tickInterval) {
            tick = 0f;
            snake.update(delta);
            // rough calculations -- turn into grid
            if (snake.getX() < x - snake.getWidth()) {
                snake.setX(width + x);
            }
            if (snake.getX() > width + x) {
                snake.setX(x);
            }
            if (snake.getY() < y) {
                snake.setY(height + y);
            }
            if (snake.getY() > height + y) {
                snake.setY(y);
            }
        } else {
            tick += delta;
        }
    }

}
