package lowe.mike.snake.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import lowe.mike.snake.Constants;
import lowe.mike.snake.state.GameSettings;
import lowe.mike.snake.state.GameState;
import lowe.mike.snake.util.Assets;
import lowe.mike.snake.util.ScreenManager;
import lowe.mike.snake.util.Utils;

/**
 * Settings screen to show settings that the user can change.
 *
 * @author Mike Lowe
 */
final class SettingsScreen extends BaseScreen {

    private static final int COL_SPAN = 3;

    /**
     * Creates a new {@code SettingsScreen} given the {@link Assets}, the {@link SpriteBatch},
     * the {@link ScreenManager} and the {@link GameState}.
     *
     * @param assets        the {@link Assets} containing assets used in the {@link Screen}
     * @param spriteBatch   the {@link SpriteBatch} to add sprites to
     * @param screenManager the {@link ScreenManager} used to manage game {@link Screen}s
     * @param gameState     the {@link GameState} containing the current game state
     */
    SettingsScreen(Assets assets, SpriteBatch spriteBatch, ScreenManager screenManager,
                   GameState gameState) {
        super(assets, spriteBatch, screenManager, gameState);
        setBackground();
        addMenu();
    }

    private void setBackground() {
        stage.addActor(new Image(assets.getBackground()));
    }

    private void addMenu() {
        Table menu = Utils.createMenu();
        addMusicLabel(menu);
        addMusicButtons(menu);
        addLevelLabel(menu);
        addLevelButtons(menu);
        addBackButton(menu);
        stage.addActor(menu);
    }

    private void addMusicLabel(Table menu) {
        menu.row();
        Label musicLabel = Utils.createTextLabel(assets.getMediumFont(), "Music");
        menu.add(musicLabel).expandX().colspan(COL_SPAN);
    }

    private void addMusicButtons(Table menu) {
        menu.row().padBottom(COMPONENT_SPACING);
        Group musicButtonGroup = createMusicButtonGroup();
        menu.add(musicButtonGroup).expandX().colspan(COL_SPAN);
    }

    private Group createMusicButtonGroup() {
        HorizontalGroup horizontalGroup = new HorizontalGroup();
        horizontalGroup.space(COMPONENT_SPACING * 2);

        // create the buttons
        boolean shouldPlayMusic = GameSettings.shouldPlayMusic();
        TextButton onButton = Utils.createCheckableTextButton(assets.getMediumFont(), "On");
        TextButton offButton = Utils.createCheckableTextButton(assets.getMediumFont(), "Off");

        // add to button group
        ButtonGroup<TextButton> buttonGroup = new ButtonGroup<TextButton>();
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.add(onButton);
        buttonGroup.add(offButton);

        // add to horizontal group
        horizontalGroup.addActor(onButton);
        horizontalGroup.addActor(offButton);

        // check which button should be checked
        onButton.setChecked(shouldPlayMusic);
        offButton.setChecked(!shouldPlayMusic);

        // add button listeners
        addMusicButtonListener(onButton, true);
        addMusicButtonListener(offButton, false);

        return horizontalGroup;
    }

    private void addMusicButtonListener(final TextButton musicButton,
                                        final boolean shouldPlayMusic) {
        musicButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (musicButton.isChecked()) {
                    GameSettings.setShouldPlayMusic(shouldPlayMusic);
                    Utils.playMusic(assets.getMusic(), shouldPlayMusic);
                }
            }

        });
    }

    private void addLevelLabel(Table menu) {
        menu.row().padTop(COMPONENT_SPACING);
        Label levelLabel = Utils.createTextLabel(assets.getMediumFont(), "Level");
        menu.add(levelLabel).expandX().colspan(COL_SPAN);
    }

    private void addLevelButtons(Table menu) {
        menu.row().padBottom(COMPONENT_SPACING);
        Label numberLabel = Utils.createNumberLabel(assets.getMediumFont(),
                GameSettings.getLevel());

        float width = Constants.GAME_WIDTH / COL_SPAN;

        // create left arrow
        ImageButton leftArrowButton = createArrowButton(
                assets.getSmallLeftArrow(), assets.getSmallLeftArrowPressed(), numberLabel, -1);
        leftArrowButton.align(Align.right);
        menu.add(leftArrowButton).width(width);

        // add number label in the middle
        menu.add(numberLabel).width(width);

        // create right arrow
        ImageButton rightArrowButton = createArrowButton(
                assets.getSmallRightArrow(), assets.getSmallRightArrowPressed(), numberLabel, 1);
        rightArrowButton.align(Align.left);
        menu.add(rightArrowButton).width(width);
    }

    private ImageButton createArrowButton(TextureRegion up, TextureRegion down, Label numberLabel,
                                          int levelIncrement) {
        ImageButton arrowButton = Utils.createImageButton(up, down);
        addArrowButtonListener(arrowButton, numberLabel, levelIncrement);
        return arrowButton;
    }

    private void addArrowButtonListener(ImageButton arrowButton, final Label numberLabel,
                                        final int levelIncrement) {
        arrowButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int level = GameSettings.getLevel() + levelIncrement;
                if (level >= Constants.MIN_LEVEL && level <= Constants.MAX_LEVEL) {
                    GameSettings.setLevel(level);
                    Utils.updateNumberLabel(numberLabel, level);
                }
            }

        });
    }

    private void addBackButton(Table menu) {
        menu.row().padTop(COMPONENT_SPACING);
        TextButton backButton = createBackButton();
        menu.add(backButton).expandX().colspan(COL_SPAN);
    }

    private TextButton createBackButton() {
        TextButton backButton = Utils.createTextButton(assets.getMediumFont(), "Back");
        addBackButtonListener(backButton);
        return backButton;
    }

    private void addBackButtonListener(TextButton backButton) {
        backButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screenManager.switchToPreviousScreen();
            }

        });
    }

}