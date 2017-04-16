package lowe.mike.snake.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import lowe.mike.snake.SnakeGame;

/**
 * {@code Utils} provides useful helper methods that are
 * repeatedly used in the game.
 * <p>
 * Instances of {@code Utils} cannot be created.
 *
 * @author Mike Lowe
 */
public final class Utils {

    private static final Color PRIMARY_FONT_COLOR = new Color(0x181818ff);
    private static final Color SECONDARY_FONT_COLOR = new Color(0x4a4a4aff);

    // don't want instances
    private Utils() {
    }

    /**
     * Creates a {@link Label} with the given {@link BitmapFont} and text.
     *
     * @param font the {@link BitmapFont}
     * @param text text to initialise the {@link Label} with
     * @return the {@link Label}
     */
    public static Label createLabel(BitmapFont font, String text) {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        style.fontColor = PRIMARY_FONT_COLOR;
        Label label = new Label(text, style);
        label.setAlignment(Align.center);
        return label;
    }

    /**
     * Creates a {@link TextButton} with the given {@link BitmapFont} and text.
     *
     * @param font the {@link BitmapFont}
     * @param text text to initialise the {@link TextButton} with
     * @return the {@link TextButton}
     */
    public static TextButton createTextButton(BitmapFont font, String text) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.fontColor = PRIMARY_FONT_COLOR;
        style.downFontColor = SECONDARY_FONT_COLOR;
        style.overFontColor = style.downFontColor;
        style.checkedFontColor = style.downFontColor;
        style.font = font;
        TextButton button = new TextButton(text, style);
        button.align(Align.center);
        return button;
    }

    public static ImageButton createImageButton(Assets assets) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(assets.getRightArrowTexture()));
        style.imageOver = new TextureRegionDrawable(new TextureRegion(assets.getRightArrowDownTexture()));
        ImageButton button = new ImageButton(style);
       // button.setTransform(true);
       // button.setScale(SnakeGame.VIRTUAL_WIDTH / 360f, SnakeGame.VIRTUAL_HEIGHT/ 640f);
        button.align(Align.center);
        return button;
    }

}