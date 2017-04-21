package lowe.mike.snake.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import lowe.mike.snake.util.Assets;
import lowe.mike.snake.util.GamePreferences;
import lowe.mike.snake.util.ScreenManager;
import lowe.mike.snake.util.Utils;
import lowe.mike.snake.world.Level;

/**
 * Settings screen to show settings that the user can change.
 *
 * @author Mike Lowe
 */
final class SettingsScreen extends BaseScreen {

    private static final float SETTINGS_COMPONENT_SPACING = COMPONENT_SPACING * 2;

    private int level;

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
        setBackground();
        this.level = GamePreferences.getLevel();
        Table menu = createMenu();
        this.stage.addActor(menu);
    }

    private void setBackground() {
        stage.addActor(new Image(assets.getBackground()));
    }

    private Table createMenu() {
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        int colspan = 3;

        // add sound label
        table.row();
        Label soundsLabel = Utils.createLabel(assets.getMediumFont(), "Sounds");
        table.add(soundsLabel).expandX().colspan(colspan);

        // add sound buttons
        table.row();
        HorizontalGroup soundButtonGroup = createSoundButtonGroup();
        table.add(soundButtonGroup).expandX().colspan(colspan);

        // add level label
        table.row().padTop(SETTINGS_COMPONENT_SPACING);
        Label levelLabel = Utils.createLabel(assets.getMediumFont(), "Level");
        table.add(levelLabel).expandX().colspan(colspan);

        // add level buttons
        table.row();
        Label numberLabel = Utils.createLabel(assets.getMediumFont(), Integer.toString(level));
        ImageButton leftArrowButton = createLeftArrowButton(numberLabel);
        table.add(leftArrowButton).expandX().align(Align.right);
        table.add(numberLabel).expandX();
        ImageButton rightArrowButton = createRightArrowButton(numberLabel);
        table.add(rightArrowButton).expandX().align(Align.left);

        // add back button
        table.row().padTop(SETTINGS_COMPONENT_SPACING);
        TextButton backButton = createBackButton();
        table.add(backButton).expandX().colspan(colspan);

        return table;
    }

    private HorizontalGroup createSoundButtonGroup() {
        HorizontalGroup group = new HorizontalGroup();
        group.space(SETTINGS_COMPONENT_SPACING);

        // create the buttons
        boolean playSounds = GamePreferences.shouldPlaySounds();
        TextButton onButton = Utils.createTextButton(assets.getMediumFont(), "On");
        TextButton offButton = Utils.createTextButton(assets.getMediumFont(), "Off");
        onButton.setChecked(playSounds);
        offButton.setChecked(!playSounds);
        addSoundButtonListener(onButton, true);
        addSoundButtonListener(offButton, false);

        // add to button group
        ButtonGroup<TextButton> buttonGroup = new ButtonGroup<TextButton>();
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.add(onButton);
        buttonGroup.add(offButton);

        // add to horizontal group
        group.addActor(onButton);
        group.addActor(offButton);

        return group;
    }

    private void addSoundButtonListener(final TextButton button, final boolean playSounds) {
        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (button.isChecked()) {
                    GamePreferences.setPlaySounds(playSounds);
                }
            }

        });
    }

    private ImageButton createLeftArrowButton(Label numberLabel) {
        ImageButton button = Utils.createImageButton(assets.getMediumLeftArrow(),
                assets.getMediumLeftArrowPressed());
        addLeftArrowButtonListener(button, numberLabel);
        return button;
    }

    private void addLeftArrowButtonListener(final ImageButton button, final Label numberLabel) {
        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                updateLevel(level - 1, numberLabel);
            }

        });
    }

    private void updateLevel(int level, Label numberLabel) {
        if (Level.isValid(level)) {
            this.level = level;
            numberLabel.setText(Integer.toString(this.level));
            GamePreferences.setLevel(this.level);
        }
    }

    private ImageButton createRightArrowButton(Label numberLabel) {
        ImageButton button = Utils.createImageButton(assets.getMediumRightArrow(),
                assets.getMediumRightArrowPressed());
        addRightArrowButtonListener(button, numberLabel);
        return button;
    }

    private void addRightArrowButtonListener(final ImageButton button, final Label numberLabel) {
        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                updateLevel(level + 1, numberLabel);
            }

        });
    }

    private TextButton createBackButton() {
        TextButton button = Utils.createTextButton(assets.getMediumFont(), "Back");
        addBackButtonListener(button);
        return button;
    }

    private void addBackButtonListener(final TextButton button) {
        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (button.isChecked()) {
                    screenManager.switchToPreviousScreen();
                    button.setChecked(false);
                }
            }

        });
    }

}