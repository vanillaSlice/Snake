package lowe.mike.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public final class SnakeGame extends Game {

    public static final String TITLE = "Snake";

    @Override
    public void create() {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(240f / 255f, 90f / 255f, 40f / 255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

}