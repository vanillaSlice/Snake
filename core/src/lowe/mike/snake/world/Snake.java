package lowe.mike.snake.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import lowe.mike.snake.util.Scaling;

/**
 * Created by mikelowe on 11/04/2017.
 */

public class Snake extends Image {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    public Direction direction = Direction.RIGHT;
    public float x;
    public float y;
    public Vector2 velocity = new Vector2();

    public Snake(Texture texture) {
        super(texture);
        Scaling.scaleActor(this);
    }

    public void update(float delta) {
        switch (direction) {
            case UP:
                setY(getY() + getHeight());
                break;
            case RIGHT:
                setX(getX() + getWidth());
                break;
            case DOWN:
                setY(getY() - getHeight());
                break;
            case LEFT:
                setX(getX() - getWidth());
                break;
        }
    }

}