package lowe.mike.snake.util;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
            = new AssetDescriptor<FreeTypeFontGenerator>("TECHNOLIN.ttf", FreeTypeFontGenerator.class);
    private static final AssetDescriptor<Texture> SPLASH_BACKGROUND_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("splash-background.png", Texture.class);
    private static final AssetDescriptor<Texture> BACKGROUND_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("background.png", Texture.class);
    private static final AssetDescriptor<Texture> MEDIUM_RIGHT_ARROW_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("medium-right-arrow.png", Texture.class);
    private static final AssetDescriptor<Texture>
            MEDIUM_RIGHT_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("medium-right-arrow-pressed.png", Texture.class);
    private static final AssetDescriptor<Texture> MEDIUM_LEFT_ARROW_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("medium-left-arrow.png", Texture.class);
    private static final AssetDescriptor<Texture> MEDIUM_LEFT_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("medium-left-arrow-pressed.png", Texture.class);
    private static final AssetDescriptor<Texture> GRID_FRAME_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("grid-frame.png", Texture.class);
    private static final AssetDescriptor<Texture> LARGE_UP_ARROW_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("large-up-arrow.png", Texture.class);
    private static final AssetDescriptor<Texture> LARGE_UP_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("large-up-arrow-pressed.png", Texture.class);
    private static final AssetDescriptor<Texture> LARGE_RIGHT_ARROW_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("large-right-arrow.png", Texture.class);
    private static final AssetDescriptor<Texture> LARGE_RIGHT_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("large-right-arrow-pressed.png", Texture.class);
    private static final AssetDescriptor<Texture> LARGE_DOWN_ARROW_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("large-down-arrow.png", Texture.class);
    private static final AssetDescriptor<Texture> LARGE_DOWN_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("large-down-arrow-pressed.png", Texture.class);
    private static final AssetDescriptor<Texture> LARGE_LEFT_ARROW_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("large-left-arrow.png", Texture.class);
    private static final AssetDescriptor<Texture> LARGE_LEFT_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("large-left-arrow-pressed.png", Texture.class);
    private static final AssetDescriptor<Texture> PAUSE_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("pause.png", Texture.class);
    private static final AssetDescriptor<Texture> PAUSE_PRESSED_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("pause-pressed.png", Texture.class);


    // DONE UP TO HERE
    private static final AssetDescriptor<Texture> SNAKE_BODY_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("snake-body.png", Texture.class);

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

    /**
     * Creates a new {@code Assets} instance.
     */
    public Assets() {
        loadSplashBackgroundTexture();
        loadMainAssets();
    }

    /*
     * Wait until splash background texture is loaded before continuing.
     * This is so we can display the splash screen while the main assets
     * are still being loaded.
     */
    private void loadSplashBackgroundTexture() {
        loadAsset(SPLASH_BACKGROUND_TEXTURE_ASSET_DESCRIPTOR);
        assetManager.finishLoading();
        addSmoothingFilter(getSplashBackgroundTexture());
    }

    private void loadAsset(AssetDescriptor... assetDescriptors) {
        for (AssetDescriptor assetDescriptor : assetDescriptors) {
            assetManager.load(assetDescriptor);
        }
    }

    private static void addSmoothingFilter(Texture... textures) {
        for (Texture texture : textures) {
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    private void loadMainAssets() {
        // need this so we can load in fonts
        assetManager.setLoader(FreeTypeFontGenerator.class,
                new FreeTypeFontGeneratorLoader(new InternalFileHandleResolver()));
        loadAsset(FONT_GENERATOR_ASSET_DESCRIPTOR, BACKGROUND_TEXTURE_ASSET_DESCRIPTOR,
                MEDIUM_RIGHT_ARROW_TEXTURE_ASSET_DESCRIPTOR,
                MEDIUM_RIGHT_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR,
                MEDIUM_LEFT_ARROW_TEXTURE_ASSET_DESCRIPTOR,
                MEDIUM_LEFT_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR,
                GRID_FRAME_TEXTURE_ASSET_DESCRIPTOR,
                LARGE_UP_ARROW_TEXTURE_ASSET_DESCRIPTOR,
                LARGE_UP_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR,
                LARGE_RIGHT_ARROW_TEXTURE_ASSET_DESCRIPTOR,
                LARGE_RIGHT_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR,
                LARGE_DOWN_ARROW_TEXTURE_ASSET_DESCRIPTOR,
                LARGE_DOWN_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR,
                LARGE_LEFT_ARROW_TEXTURE_ASSET_DESCRIPTOR,
                LARGE_LEFT_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR,
                PAUSE_TEXTURE_ASSET_DESCRIPTOR,
                PAUSE_PRESSED_TEXTURE_ASSET_DESCRIPTOR,

                // DONE UP TO HERE
                SNAKE_BODY_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return {@code true} if all loading is finished
     */
    public boolean isFinishedLoading() {
        if (assetManager.update()) {
            loadFonts();
            addSmoothingFilter(getBackgroundTexture(), getMediumRightArrowTexture(),
                    getMediumRightArrowPressedTexture(), getMediumLeftArrowTexture(),
                    getMediumLeftArrowPressedTexture(), getGridFrameTexture(),
                    getLargeUpArrowTexture(), getLargeUpArrowPressedTexture(),
                    getLargeRightArrowTexture(), getLargeRightArrowPressedTexture(),
                    getLargeDownArrowTexture(), getLargeDownArrowPressedTexture(),
                    getLargeLeftArrowTexture(), getLargeLeftArrowPressedTexture(),
                    getPauseTexture(), getPausePressedTexture(),

                    // DONE UP TO HERE
                    getSnakeBodyTexture());
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
        BitmapFont font = fontGenerator.generateFont(parameter);
        return font;
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
    public Texture getSplashBackgroundTexture() {
        return assetManager.get(SPLASH_BACKGROUND_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the background {@link Texture}
     */
    public Texture getBackgroundTexture() {
        return assetManager.get(BACKGROUND_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the medium right arrow {@link Texture}
     */
    public Texture getMediumRightArrowTexture() {
        return assetManager.get(MEDIUM_RIGHT_ARROW_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the medium right arrow pressed {@link Texture}
     */
    public Texture getMediumRightArrowPressedTexture() {
        return assetManager.get(MEDIUM_RIGHT_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the medium left arrow {@link Texture}
     */
    public Texture getMediumLeftArrowTexture() {
        return assetManager.get(MEDIUM_LEFT_ARROW_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the medium left arrow pressed {@link Texture}
     */
    public Texture getMediumLeftArrowPressedTexture() {
        return assetManager.get(MEDIUM_LEFT_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the grid frame {@link Texture}
     */
    public Texture getGridFrameTexture() {
        return assetManager.get(GRID_FRAME_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the large up arrow {@link Texture}
     */
    public Texture getLargeUpArrowTexture() {
        return assetManager.get(LARGE_UP_ARROW_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the large up arrow pressed {@link Texture}
     */
    public Texture getLargeUpArrowPressedTexture() {
        return assetManager.get(LARGE_UP_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the large right arrow {@link Texture}
     */
    public Texture getLargeRightArrowTexture() {
        return assetManager.get(LARGE_RIGHT_ARROW_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the large right arrow pressed {@link Texture}
     */
    public Texture getLargeRightArrowPressedTexture() {
        return assetManager.get(LARGE_RIGHT_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the large down arrow {@link Texture}
     */
    public Texture getLargeDownArrowTexture() {
        return assetManager.get(LARGE_DOWN_ARROW_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the large down arrow pressed {@link Texture}
     */
    public Texture getLargeDownArrowPressedTexture() {
        return assetManager.get(LARGE_DOWN_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the large left arrow {@link Texture}
     */
    public Texture getLargeLeftArrowTexture() {
        return assetManager.get(LARGE_LEFT_ARROW_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the large left arrow pressed {@link Texture}
     */
    public Texture getLargeLeftArrowPressedTexture() {
        return assetManager.get(LARGE_LEFT_ARROW_PRESSED_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the pause {@link Texture}
     */
    public Texture getPauseTexture() {
        return assetManager.get(PAUSE_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return the pause pressed {@link Texture}
     */
    public Texture getPausePressedTexture() {
        return assetManager.get(PAUSE_PRESSED_TEXTURE_ASSET_DESCRIPTOR);
    }

    ///////// DONE UP TO HERE

    /**
     * @return the snake body {@link Texture}
     */
    public Texture getSnakeBodyTexture() {
        return assetManager.get(SNAKE_BODY_TEXTURE_ASSET_DESCRIPTOR);
    }

    /////////////

    /**
     * Disposes the splash background {@link Texture}.
     */
    public void disposeSplashBackgroundTexture() {
        if (assetManager.isLoaded(SPLASH_BACKGROUND_TEXTURE_ASSET_DESCRIPTOR.fileName)) {
            assetManager.unload(SPLASH_BACKGROUND_TEXTURE_ASSET_DESCRIPTOR.fileName);
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