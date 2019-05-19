package main.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class AssetManagerManager {
	private AssetManager assetManager;

	public AssetManagerManager() {
		assetManager = new AssetManager();
		assetManager.finishLoading();
	}

	/*
	 * The contents of these methods are scaffolding until we implement the
	 * AssetManager
	 */

	public Sound getSound(String name) {
		return Gdx.audio.newSound(Gdx.files.internal("assets/sound/" + name + ".mp3"));
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