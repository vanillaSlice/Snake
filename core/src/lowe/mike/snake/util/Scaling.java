package lowe.mike.snake.util;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

import lowe.mike.snake.SnakeGame;

/**
 * {@code Scaling} is used to scale the different components in the game.
 * <p>
 * Instances of {@code Scaling} cannot be created.
 *
 * @author Mike Lowe
 */
public final class Scaling {

    // don't want instances
    private Scaling() {
    }

    // use actual dimensions of background to scale everything else in the game
    private static final float X_SCALE = SnakeGame.VIRTUAL_WIDTH / 360f;
    private static final float Y_SCALE = SnakeGame.VIRTUAL_HEIGHT / 640f;

    /**
     * @param font the {@link BitmapFont} to scale
     */
    static void scaleFont(BitmapFont font) {
        font.getData().setScale(X_SCALE, Y_SCALE);
    }

    /**
     * @param actor the {@link Actor} to scale
     */
    public static void scaleActor(Actor actor) {
        actor.setScale(X_SCALE, Y_SCALE);
    }

}