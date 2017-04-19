package lowe.mike.snake.util;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.utils.Disposable;

/**
 * {@code Assets} provides access to assets, such as {@link Texture}s,
 * used in the <i>Snake</i> game.
 *
 * @author Mike Lowe
 */
public final class Assets implements Disposable {

    /*
     * Describe the assets to load in.
     */
    private static final AssetDescriptor<FreeTypeFontGenerator> FONT_GENERATOR_ASSET_DESCRIPTOR
            = new AssetDescriptor<FreeTypeFontGenerator>("TECHNOLIN.ttf",
            FreeTypeFontGenerator.class);
    private static final AssetDescriptor<Texture> SPLASH_BACKGROUND_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("splash-background.png", Texture.class);
    private static final AssetDescriptor<TextureAtlas> TEXTURE_ATLAS_ASSET_DESCRIPTOR
            = new AssetDescriptor<TextureAtlas>("textures.atlas", TextureAtlas.class);

    /*
     * Font properties.
     */
    private static final int LARGE_FONT_SIZE = 62;
    private static final int MEDIUM_FONT_SIZE = 36;
    private static final int SMALL_FONT_SIZE = 28;

    private final AssetManager assetManager = new AssetManager();
    private BitmapFont largeFont;
    private BitmapFont mediumFont;
    private BitmapFont smallFont;
    private Texture splashBackground;
    private TextureRegion background;
    private TextureRegion mediumRightArrow;
    private TextureRegion mediumRightArrowPressed;
    private TextureRegion mediumLeftArrow;
    private TextureRegion mediumLeftArrowPressed;
    private TextureRegion gridFrame;
    private TextureRegion largeUpArrow;
    private TextureRegion largeUpArrowPressed;
    private TextureRegion largeRightArrow;
    private TextureRegion largeRightArrowPressed;
    private TextureRegion largeDownArrow;
    private TextureRegion largeDownArrowPressed;
    private TextureRegion largeLeftArrow;
    private TextureRegion largeLeftArrowPressed;
    private TextureRegion pause;
    private TextureRegion pausePressed;
    private TextureRegion snakeBody;

    /**
     * Creates a new {@code Assets} instance.
     */
    public Assets() {
        loadSplashBackground();
        loadMainAssets();
    }

    /*
     * Wait until splash background texture is loaded before continuing.
     * This is so we can display the splash screen while the main assets
     * are still being loaded.
     */
    private void loadSplashBackground() {
        assetManager.load(SPLASH_BACKGROUND_ASSET_DESCRIPTOR);
        assetManager.finishLoading();
        splashBackground = assetManager.get(SPLASH_BACKGROUND_ASSET_DESCRIPTOR);
        // apply smoothing filters
        splashBackground.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    private void loadMainAssets() {
        // need this so we can load in fonts
        assetManager.setLoader(FreeTypeFontGenerator.class,
                new FreeTypeFontGeneratorLoader(new InternalFileHandleResolver()));
        assetManager.load(FONT_GENERATOR_ASSET_DESCRIPTOR);
        assetManager.load(TEXTURE_ATLAS_ASSET_DESCRIPTOR);
    }

    /**
     * @return {@code true} if all loading is finished
     */
    public boolean isFinishedLoading() {
        if (assetManager.update()) {
            loadFonts();
            loadTextureRegions();
            return true;
        } else {
            return false;
        }
    }

    private void loadFonts() {
        FreeTypeFontGenerator fontGenerator = assetManager.get(FONT_GENERATOR_ASSET_DESCRIPTOR);

        FreeTypeFontGenerator.FreeTypeFontParameter parameter
                = new FreeTypeFontGenerator.FreeTypeFontParameter();
        // apply smoothing filters
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;

        largeFont = loadFont(fontGenerator, parameter, LARGE_FONT_SIZE);
        mediumFont = loadFont(fontGenerator, parameter, MEDIUM_FONT_SIZE);
        smallFont = loadFont(fontGenerator, parameter, SMALL_FONT_SIZE);

        // finished with font generator so dispose it
        assetManager.unload(FONT_GENERATOR_ASSET_DESCRIPTOR.fileName);
    }

    private BitmapFont loadFont(FreeTypeFontGenerator fontGenerator,
                                FreeTypeFontGenerator.FreeTypeFontParameter parameter,
                                int fontSize) {
        parameter.size = fontSize;
        return fontGenerator.generateFont(parameter);
    }

    private void loadTextureRegions() {
        TextureAtlas textureAtlas = assetManager.get(TEXTURE_ATLAS_ASSET_DESCRIPTOR);
        // apply smoothing filters
        textureAtlas.getTextures().first().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        background = textureAtlas.findRegion("background");
        mediumRightArrow = textureAtlas.findRegion("medium-right-arrow");
        mediumRightArrowPressed = textureAtlas.findRegion("medium-right-arrow-pressed");
        mediumLeftArrow = textureAtlas.findRegion("medium-left-arrow");
        mediumLeftArrowPressed = textureAtlas.findRegion("medium-left-arrow-pressed");
        gridFrame = textureAtlas.findRegion("grid-frame");
        largeUpArrow = textureAtlas.findRegion("large-up-arrow");
        largeUpArrowPressed = textureAtlas.findRegion("large-up-arrow-pressed");
        largeRightArrow = textureAtlas.findRegion("large-right-arrow");
        largeRightArrowPressed = textureAtlas.findRegion("large-right-arrow-pressed");
        largeDownArrow = textureAtlas.findRegion("large-down-arrow");
        largeDownArrowPressed = textureAtlas.findRegion("large-down-arrow-pressed");
        largeLeftArrow = textureAtlas.findRegion("large-left-arrow");
        largeLeftArrowPressed = textureAtlas.findRegion("large-left-arrow-pressed");
        pause = textureAtlas.findRegion("pause");
        pausePressed = textureAtlas.findRegion("pause-pressed");
        snakeBody = textureAtlas.findRegion("snake-body");
    }

    /**
     * @return the large {@link BitmapFont}
     */
    public BitmapFont getLargeFont() {
        return largeFont;
    }

    /**
     * @return the medium {@link BitmapFont}
     */
    public BitmapFont getMediumFont() {
        return mediumFont;
    }

    /**
     * @return the small {@link BitmapFont}
     */
    public BitmapFont getSmallFont() {
        return smallFont;
    }

    /**
     * @return the splash background {@link Texture}
     */
    public Texture getSplashBackground() {
        return splashBackground;
    }

    /**
     * @return the background {@link TextureRegion}
     */
    public TextureRegion getBackground() {
        return background;
    }

    /**
     * @return the medium right arrow {@link TextureRegion}
     */
    public TextureRegion getMediumRightArrow() {
        return mediumRightArrow;
    }

    /**
     * @return the medium right arrow pressed {@link TextureRegion}
     */
    public TextureRegion getMediumRightArrowPressed() {
        return mediumRightArrowPressed;
    }

    /**
     * @return the medium left arrow {@link TextureRegion}
     */
    public TextureRegion getMediumLeftArrow() {
        return mediumLeftArrow;
    }

    /**
     * @return the medium left arrow pressed {@link TextureRegion}
     */
    public TextureRegion getMediumLeftArrowPressed() {
        return mediumLeftArrowPressed;
    }

    /**
     * @return the grid frame {@link TextureRegion}
     */
    public TextureRegion getGridFrame() {
        return gridFrame;
    }

    /**
     * @return the large up arrow {@link TextureRegion}
     */
    public TextureRegion getLargeUpArrow() {
        return largeUpArrow;
    }

    /**
     * @return the large up arrow pressed {@link TextureRegion}
     */
    public TextureRegion getLargeUpArrowPressed() {
        return largeUpArrowPressed;
    }

    /**
     * @return the large right arrow {@link TextureRegion}
     */
    public TextureRegion getLargeRightArrow() {
        return largeRightArrow;
    }

    /**
     * @return the large right arrow pressed {@link TextureRegion}
     */
    public TextureRegion getLargeRightArrowPressed() {
        return largeRightArrowPressed;
    }

    /**
     * @return the large down arrow {@link TextureRegion}
     */
    public TextureRegion getLargeDownArrow() {
        return largeDownArrow;
    }

    /**
     * @return the large down arrow pressed {@link TextureRegion}
     */
    public TextureRegion getLargeDownArrowPressed() {
        return largeDownArrowPressed;
    }

    /**
     * @return the large left arrow {@link TextureRegion}
     */
    public TextureRegion getLargeLeftArrow() {
        return largeLeftArrow;
    }

    /**
     * @return the large left arrow pressed {@link TextureRegion}
     */
    public TextureRegion getLargeLeftArrowPressed() {
        return largeLeftArrowPressed;
    }

    /**
     * @return the pause {@link TextureRegion}
     */
    public TextureRegion getPause() {
        return pause;
    }

    /**
     * @return the pause pressed {@link TextureRegion}
     */
    public TextureRegion getPausePressed() {
        return pausePressed;
    }

    /**
     * @return the snake body {@link TextureRegion}
     */
    public TextureRegion getSnakeBody() {
        return snakeBody;
    }

    /**
     * Disposes the splash background {@link Texture}.
     */
    public void disposeSplashBackground() {
        if (assetManager.isLoaded(SPLASH_BACKGROUND_ASSET_DESCRIPTOR.fileName)) {
            assetManager.unload(SPLASH_BACKGROUND_ASSET_DESCRIPTOR.fileName);
        }
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        largeFont.dispose();
        mediumFont.dispose();
        smallFont.dispose();
    }

}