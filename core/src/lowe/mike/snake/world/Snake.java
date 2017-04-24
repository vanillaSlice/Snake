package lowe.mike.snake.world;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
    public Array<Image> bodyparts = new Array<Image>();

    public Snake(TextureRegion body, Stage stage) {
        super(body);
        setOrigin(getWidth() / 2, getHeight() / 2);

        for (int i = 0; i < 10; i++) {
            Image bodyPart = new Image(body);
            bodyparts.add(bodyPart);
            stage.addActor(bodyPart);
        }
    }

    public void update(float delta) {
        for (int i = 9; i >= 1; i--) {
            int x = (int) bodyparts.get(i - 1).getX();
            int y = (int) bodyparts.get(i - 1).getY();
            if (bodyparts.get(i).getX() != x || bodyparts.get(i).getY() != y) {
                bodyparts.get(i).setPosition(x, y);
            }
        }
        bodyparts.first().setPosition((int) getX(), (int) getY());

        switch (direction) {
            case UP:
                setY((int) (getY() + getHeight()));
                setRotation(0);
                lastDirection = Direction.UP;
                break;
            case RIGHT:
                setX((int) (getX() + getWidth()));
                setRotation(270);
                lastDirection = Direction.RIGHT;
                break;
            case DOWN:
                setY((int) (getY() - getHeight()));
                setRotation(180);
                lastDirection = Direction.DOWN;
                break;
            case LEFT:
                setX((int) (getX() - getWidth()));
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
}