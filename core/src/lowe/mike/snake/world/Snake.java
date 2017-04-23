package lowe.mike.snake.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

/**
 * Created by mikelowe on 11/04/2017.
 */

public class Snake extends Image {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    private TextureRegionDrawable open;
    private TextureRegionDrawable closed;

    private Direction lastDirection = Direction.RIGHT;
    private Direction direction = Direction.RIGHT;
    public float x;
    public float y;
    boolean isOpen;
    public Vector2 velocity = new Vector2();
    public Array<Image> bodyparts = new Array<Image>();
    private final TextureRegion body;

    public Snake(TextureRegion open, TextureRegion closed, TextureRegion body) {
        super(open);
        this.open = new TextureRegionDrawable(open);
        this.closed = new TextureRegionDrawable(closed);
        this.body = body;
        setOrigin(getWidth() / 2, getHeight() / 2);

        for (int i = 0; i < 10; i++) {
            bodyparts.add(new Image(body));
        }
    }

    public void update(float delta) {
        for (int i = 9; i >= 1; i--) {
            float x = bodyparts.get(i - 1).getX();
            float y = bodyparts.get(i - 1).getY();
            if (bodyparts.get(i).getX() != x || bodyparts.get(i).getY() != y) {
                bodyparts.get(i).setPosition(x, y);
            }
        }
        bodyparts.first().setPosition(getX(), getY());

        switch (direction) {
            case UP:
                setY(getY() + getHeight());
                setRotation(0);
                lastDirection = Direction.UP;
                break;
            case RIGHT:
                setX(getX() + getWidth());
                setRotation(270);
                lastDirection = Direction.RIGHT;
                break;
            case DOWN:
                setY(getY() - getHeight());
                setRotation(180);
                lastDirection = Direction.DOWN;
                break;
            case LEFT:
                setX(getX() - getWidth());
                setRotation(90);
                lastDirection = Direction.LEFT;
                break;
        }
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        if (direction == Direction.UP && lastDirection != Snake.Direction.DOWN ||
                direction == Direction.RIGHT && lastDirection != Snake.Direction.LEFT ||
                direction == Direction.DOWN && lastDirection != Snake.Direction.UP ||
                direction == Direction.LEFT && lastDirection != Snake.Direction.RIGHT) {
            this.direction = direction;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for (Image image : bodyparts) {
            image.draw(batch, parentAlpha);
        }
    }
}