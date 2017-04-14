package lowe.mike.snake.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mikelowe on 11/04/2017.
 */

public class Snake {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    public Direction direction = Direction.RIGHT;
    public final Texture texture = new Texture(Gdx.files.internal("body.png"));
    public float x;
    public float y;
    public Vector2 velocity = new Vector2();

}
