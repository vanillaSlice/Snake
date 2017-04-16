package lowe.mike.snake.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import lowe.mike.snake.SnakeGame;
import lowe.mike.snake.util.Assets;
import lowe.mike.snake.util.Scaling;
import lowe.mike.snake.util.ScreenManager;
import lowe.mike.snake.util.Utils;
import lowe.mike.snake.world.Snake;

/**
 * Settings screen to show settings that the user can change.
 *
 * @author Mike Lowe
 */
final class SettingsScreen extends BaseScreen {

    /**
     * Creates a new {@code SettingsScreen} given {@link Assets}, a {@link SpriteBatch}
     * and a {@link ScreenManager}.
     *
     * @param assets        {@link Assets} containing assets used in the {@link Screen}
     * @param spriteBatch   {@link SpriteBatch} to add sprites to
     * @param screenManager the {@link ScreenManager} used to manage game {@link Screen}s
     */
    SettingsScreen(Assets assets, SpriteBatch spriteBatch, ScreenManager screenManager) {
        super(assets, spriteBatch, screenManager);
        Table menu = createMenu();
        this.stage.addActor(menu);
    }

    private Table createMenu() {
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        table.row();
        Label soundsLabel = Utils.createLabel(assets.getButtonFont(), "Sounds");
        table.add(soundsLabel).expandX().colspan(3);
        table.row().padBottom(COMPONENT_SPACING * 2);
        Label onOffLabel = Utils.createLabel(assets.getButtonFont(), "On Off");
        table.add(onOffLabel).expandX().colspan(3);

        table.row();
        Label levelLabel = Utils.createLabel(assets.getButtonFont(), "Level");
        table.add(levelLabel).expandX().colspan(3);
        table.row().padBottom(COMPONENT_SPACING * 2);
        ImageButton leftBut = Utils.createImageButton(assets);
        table.add(leftBut).expandX().align(Align.right)
                .size(leftBut.getWidth() * SnakeGame.VIRTUAL_WIDTH / 360f, leftBut.getHeight() * SnakeGame.VIRTUAL_HEIGHT/ 640f);
        Label numLabel = Utils.createLabel(assets.getButtonFont(), "9");
        table.add(numLabel).expandX();
        ImageButton rightBut = Utils.createImageButton(assets);
        table.add(rightBut).expandX().align(Align.left)
                .size(rightBut.getWidth() * SnakeGame.VIRTUAL_WIDTH / 360f, rightBut.getHeight() * SnakeGame.VIRTUAL_HEIGHT/ 640f);

        table.row();
        Label backLabel = Utils.createLabel(assets.getButtonFont(), "Back");
        table.add(backLabel).expandX().colspan(3);

        return table;
    }

}