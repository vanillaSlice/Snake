package lowe.mike.snake.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

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

    /**
     * Creates an {@link ImageButton} with the given up and down {@link Texture}s.
     *
     * @param up   the up {@link Texture}
     * @param down the down {@link Texture}
     * @return the {@link ImageButton}
     */
    public static ImageButton createImageButton(Texture up, Texture down) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = getTextureRegionDrawable(up);
        style.imageDown = getTextureRegionDrawable(down);
        style.imageOver = style.imageDown;
        ImageButton button = new ImageButton(style);
        button.align(Align.center);
        return button;
    }

    public static ImageButton createImageButton(TextureRegion up, TextureRegion down) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(up);
        style.imageDown = new TextureRegionDrawable(down);
        style.imageOver = style.imageDown;
        ImageButton button = new ImageButton(style);
        button.align(Align.center);
        return button;
    }

    private static TextureRegionDrawable getTextureRegionDrawable(Texture texture) {
        return new TextureRegionDrawable(new TextureRegion(texture));
    }

}