package lowe.mike.snake.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import lowe.mike.snake.SnakeGame;
import lowe.mike.snake.util.Assets;
import lowe.mike.snake.util.Scaling;
import lowe.mike.snake.util.ScreenManager;
import lowe.mike.snake.util.Utils;

/**
 * Screen to show when the game is being played.
 *
 * @author Mike Lowe
 */
final class GameScreen extends BaseScreen {

    /**
     * Creates a new {@code GameScreen} given {@link Assets}, a {@link SpriteBatch}
     * and a {@link ScreenManager}.
     *
     * @param assets        {@link Assets} containing assets used in the {@link Screen}
     * @param spriteBatch   {@link SpriteBatch} to add sprites to
     * @param screenManager the {@link ScreenManager} used to manage game {@link Screen}s
     */
    GameScreen(Assets assets, SpriteBatch spriteBatch, ScreenManager screenManager) {
        super(assets, spriteBatch, screenManager);
        Label label = Utils.createLabel(assets.getSmallFont(), "0000");
        label.setPosition(COMPONENT_SPACING, stage.getHeight() - COMPONENT_SPACING * 2.5f
                - (label.getHeight() / 2));
        Image gridFrame = new Image(assets.getGridFrameTexture());
        Scaling.scaleActor(gridFrame);
        gridFrame.setPosition(COMPONENT_SPACING, label.getY() - (gridFrame.getHeight() / 2));
        this.stage.addActor(label);
        this.stage.addActor(gridFrame);
    }

}

//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.ScreenAdapter;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//
//import lowe.mike.snake.SnakeGame;
//import lowe.mike.snake.util.Assets;
//import lowe.mike.snake.world.Snake;
//
///**
// * Screen to show when the game is being played.
// *
// * @author Mike Lowe
// */
//public final class GameScreen extends ScreenAdapter {
//
//
//    private final Assets assets;
//    private final SpriteBatch spriteBatch;
//    private final Stage stage;
//    private final OrthographicCamera camera = new OrthographicCamera();
//    private final Viewport viewport;
//
//
//    //sort this
//    private int width;
//    private int height;
//    private final Snake snake;
//    private float tickInterval = .01f;
//    private float tick;
//
//    public GameScreen(Assets assets, SpriteBatch spriteBatch) {
//        this.assets = assets;
//        this.spriteBatch = spriteBatch;
//        this.camera.setToOrtho(false);
//        this.viewport = new FitViewport(SnakeGame.VIRTUAL_WIDTH, SnakeGame.VIRTUAL_HEIGHT,
//                this.camera);
//        this.stage = new Stage(this.viewport, this.spriteBatch);
//
//
//        this.snake = new Snake();
//        this.width = Gdx.graphics.getWidth() / snake.texture.getWidth();
//        this.height = Gdx.graphics.getHeight() / snake.texture.getHeight();
//
//
//
//        addBackground(backgroundTexture);
//    }
//
//    private void addBackground(Texture backgroundTexture) {
//        Image background = new Image(backgroundTexture);
//        Scaling.scaleActor(background);
//        stage.addActor(background);
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        handleUserInput();
//        if (tick > tickInterval) {
//            tick = 0f;
//            switch (snake.direction) {
//                case UP:
//                    snake.y++;
//                    break;
//                case RIGHT:
//                    snake.x++;
//                    break;
//                case DOWN:
//                    snake.y--;
//                    break;
//                case LEFT:
//                    snake.x--;
//                    break;
//            }
//            // rough calculations -- turn into grid
//            if (snake.x < 0) {
//                snake.x = width - 1;
//            }
//            if (snake.x > width - 1) {
//                snake.x = 0;
//            }
//            if (snake.y < 0) {
//                snake.y = height - 1;
//            }
//            if (snake.y > height - 1) {
//                snake.y = 0;
//            }
//        } else {
//            tick += delta;
//        }
//
//        spriteBatch.begin();
//        if (assets.isFinishedLoading()) {
//            spriteBatch.draw(assets.getBackgroundTexture(), 0, 0);
//        } else {
//            spriteBatch.draw(assets.getSplashBackgroundTexture(), 0, 0);
//        }
//        spriteBatch.draw(snake.texture, snake.x * snake.texture.getWidth(), snake.y * snake.texture.getHeight());
//        spriteBatch.end();
//    }
//
//    private void handleUserInput() {
//        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && snake.direction != Snake.Direction.DOWN) {
//            snake.direction = Snake.Direction.UP;
//        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && snake.direction != Snake.Direction.LEFT) {
//            snake.direction = Snake.Direction.RIGHT;
//        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && snake.direction != Snake.Direction.UP) {
//            snake.direction = Snake.Direction.DOWN;
//        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && snake.direction != Snake.Direction.RIGHT) {
//            snake.direction = Snake.Direction.LEFT;
//        }
//    }
//
//}