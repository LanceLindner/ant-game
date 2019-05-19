package AssetManagement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class AssetManagerManager {
	private AssetManager assetManager;
	private FileHandleResolver resolver;

	public AssetManagerManager() {
		assetManager = new AssetManager();
		resolver = new InternalFileHandleResolver();
		loadAll();
		assetManager.finishLoading();
	}

	public void loadAll() {
		load("sounds", Sound.class);
	}

	public void load(String directory, Class<?> classType) {
		FileHandle folder = resolver.resolve("").child("assets/" + directory);
		for (FileHandle asset : folder.list()) {
			assetManager.load(asset.path(), classType);
		}
	}

	/*
	 * The contents of these methods are scaffolding until we implement the
	 * AssetManager
	 */

	public Sound getSound(String name) {
		return assetManager.get("assets/sounds/" + name + ".mp3", Sound.class);
	}

	public Music getMusic(String name) {
		return Gdx.audio.newMusic(Gdx.files.internal("assets/music/" + name + ".mp3"));
	}

	public Texture getTexture(String name) {
		return new Texture("assets/sprites/sprites/" + name + ".png");
	}

	public void dispose() {
		assetManager.dispose();
	}
}
