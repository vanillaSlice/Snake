package lowe.mike.snake.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import lowe.mike.snake.SnakeGame;
import lowe.mike.snake.util.Assets;
import lowe.mike.snake.util.ScreenManager;
import lowe.mike.snake.util.Utils;

/**
 * Main menu screen to show when the game is first opened.
 *
 * @author Mike Lowe
 */
final class MainMenuScreen extends BaseScreen {

    private static final String PLAY_BUTTON_TEXT = "Play";
    private static final String SETTINGS_BUTTON_TEXT = "Settings";

    /**
     * Creates a new {@code MainMenuScreen} given {@link Assets}, a {@link SpriteBatch}
     * and a {@link ScreenManager}.
     *
     * @param assets        {@link Assets} containing assets used in the {@link Screen}
     * @param spriteBatch   {@link SpriteBatch} to add sprites to
     * @param screenManager the {@link ScreenManager} used to manage game {@link Screen}s
     */
    MainMenuScreen(Assets assets, SpriteBatch spriteBatch, ScreenManager screenManager) {
        super(assets, spriteBatch, screenManager);
        Table menu = createMenu();
        this.stage.addActor(menu);
    }

    private Table createMenu() {
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        // add title
        table.row().padBottom(COMPONENT_SPACING);
        Label titleLabel = Utils.createLabel(assets.getTitleFont(), SnakeGame.TITLE);
        table.add(titleLabel).expandX();

        // add play button
        table.row().padTop(COMPONENT_SPACING);
        TextButton playButton = createPlayButton();
        table.add(playButton).expandX();

        // add settings button
        table.row().padTop(COMPONENT_SPACING);
        TextButton settingsButton = createSettingsButton();
        table.add(settingsButton).expandX();

        return table;
    }

    private TextButton createPlayButton() {
        TextButton button = Utils.createTextButton(assets.getButtonFont(), PLAY_BUTTON_TEXT);
        addPlayButtonListener(button);
        return button;
    }

    private void addPlayButtonListener(final TextButton button) {
        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (button.isChecked()) {
                    switchToGameScreen();
                    button.setChecked(false);
                }
            }

        });

    }

    private void switchToGameScreen() {
        // dispose this screen and all previous screens because we won't be able to return from the
        // next screen
        screenManager.disposeAndClearAllScreens();
        screenManager.setScreen(new GameScreen(assets, spriteBatch, screenManager));
    }

    private TextButton createSettingsButton() {
        TextButton button = Utils.createTextButton(assets.getButtonFont(), SETTINGS_BUTTON_TEXT);
        addSettingsButtonListener(button);
        return button;
    }

    private void addSettingsButtonListener(final TextButton button) {
        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (button.isChecked()) {
                    switchToSettingsScreen();
                    button.setChecked(false);
                }
            }

        });
    }

    private void switchToSettingsScreen() {
        // don't dispose this screen because we want to be able to return to it
        // from the next screen
        screenManager.setScreen(new SettingsScreen(assets, spriteBatch, screenManager));
    }

}