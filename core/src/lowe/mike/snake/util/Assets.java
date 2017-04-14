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
            = new AssetDescriptor<FreeTypeFontGenerator>("TECHNOLIN.ttf",
            FreeTypeFontGenerator.class);
    private static final AssetDescriptor<Texture> SPLASH_BACKGROUND_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("splash-background.png", Texture.class);
    private static final AssetDescriptor<Texture> BACKGROUND_TEXTURE_ASSET_DESCRIPTOR
            = new AssetDescriptor<Texture>("background.png", Texture.class);

    /*
     * Font properties.
     */
    private static final int TITLE_FONT_SIZE = 62;
    private static final int BUTTON_FONT_SIZE = 36;

    private final AssetManager assetManager = new AssetManager();
    private BitmapFont titleFont;
    private BitmapFont buttonFont;

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
        loadAsset(FONT_GENERATOR_ASSET_DESCRIPTOR, BACKGROUND_TEXTURE_ASSET_DESCRIPTOR);
    }

    /**
     * @return {@code true} if all loading is finished
     */
    public boolean isFinishedLoading() {
        if (assetManager.update()) {
            loadFonts();
            addSmoothingFilter(getBackgroundTexture());
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

        titleFont = loadFont(fontGenerator, parameter, TITLE_FONT_SIZE);
        buttonFont = loadFont(fontGenerator, parameter, BUTTON_FONT_SIZE);

        // finished with font generator so dispose it
        assetManager.unload(FONT_GENERATOR_ASSET_DESCRIPTOR.fileName);
    }

    private BitmapFont loadFont(FreeTypeFontGenerator fontGenerator,
                                FreeTypeFontGenerator.FreeTypeFontParameter parameter,
                                int fontSize) {
        parameter.size = fontSize;
        BitmapFont font = fontGenerator.generateFont(parameter);
        Scaling.scaleFont(font);
        return font;
    }

    /**
     * @return the title {@link BitmapFont}
     */
    public BitmapFont getTitleFont() {
        return titleFont;
    }

    /**
     * @return the button {@link BitmapFont}
     */
    public BitmapFont getButtonFont() {
        return buttonFont;
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
        titleFont.dispose();
        buttonFont.dispose();
    }

}