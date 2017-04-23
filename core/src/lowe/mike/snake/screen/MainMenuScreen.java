package lowe.mike.snake.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import lowe.mike.snake.Constants;
import lowe.mike.snake.state.GameSettings;
import lowe.mike.snake.state.GameState;
import lowe.mike.snake.util.Assets;
import lowe.mike.snake.util.ScreenManager;
import lowe.mike.snake.util.Utils;

/**
 * Main menu screen to show when the game is first opened.
 *
 * @author Mike Lowe
 */
final class MainMenuScreen extends BaseScreen {

    /**
     * Creates a new {@code MainMenuScreen} given the {@link Assets}, the {@link SpriteBatch},
     * the {@link ScreenManager} and the {@link GameState}.
     *
     * @param assets        the {@link Assets} containing assets used in the {@link Screen}
     * @param spriteBatch   the {@link SpriteBatch} to add sprites to
     * @param screenManager the {@link ScreenManager} used to manage game {@link Screen}s
     * @param gameState     the {@link GameState} containing the current game state
     */
    MainMenuScreen(Assets assets, SpriteBatch spriteBatch, ScreenManager screenManager,
                   GameState gameState) {
        super(assets, spriteBatch, screenManager, gameState);
        setBackground();
        addMenu();
        playMusic();
        addWorld();
    }

    private void setBackground() {
        stage.addActor(new Image(assets.getBackground()));
    }

    private void addMenu() {
        Table menu = Utils.createMenu();
        addTitleLabel(menu);
        addPlayButton(menu);
        addSettingsButton(menu);
        stage.addActor(menu);
    }

    private void addTitleLabel(Table menu) {
        menu.row().padBottom(COMPONENT_SPACING);
        Label titleLabel = Utils.createTextLabel(assets.getLargeFont(), Constants.GAME_TITLE);
        menu.add(titleLabel).expandX();
    }

    private void addPlayButton(Table menu) {
        menu.row().padTop(COMPONENT_SPACING);
        TextButton playButton = createPlayButton();
        menu.add(playButton).expandX();
    }

    private TextButton createPlayButton() {
        TextButton playButton = Utils.createTextButton(assets.getMediumFont(), "Play");
        addPlayButtonListener(playButton);
        return playButton;
    }

    private void addPlayButtonListener(TextButton playButton) {
        playButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switchToGameScreen();
            }

        });
    }

    private void switchToGameScreen() {
        // dispose this screen and all previous screens because we won't be able to return from the
        // next screen
        screenManager.disposeAndClearAllScreens();
        screenManager.setScreen(new GameScreen(assets, spriteBatch, screenManager, gameState));
    }

    private void addSettingsButton(Table menu) {
        menu.row().padTop(COMPONENT_SPACING);
        TextButton settingsButton = createSettingsButton();
        menu.add(settingsButton).expandX();
    }

    private TextButton createSettingsButton() {
        TextButton settingsButton = Utils.createTextButton(assets.getMediumFont(), "Settings");
        addSettingsButtonListener(settingsButton);
        return settingsButton;
    }

    private void addSettingsButtonListener(TextButton settingsButton) {
        settingsButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switchToSettingsScreen();
            }

        });
    }

    private void switchToSettingsScreen() {
        // don't dispose this screen because we want to be able to return to it
        // from the next screen
        screenManager.setScreen(new SettingsScreen(assets, spriteBatch, screenManager, gameState));
    }

    private void playMusic() {
        Utils.playMusic(assets.getMusic(), GameSettings.shouldPlayMusic());
    }

    private void addWorld() {
        // interact with world here
    }

}