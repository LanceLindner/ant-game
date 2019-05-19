package main.SpriteSheets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import main.main.Globals;

public class SpriteSheet {
	private static final float FRAME_DURATION = 1 / 60f;

	private float stateBegin = 0;
	private Animation<TextureRegion> active;
	private Animation<TextureRegion> idle, walk;

	public TextureRegion lastFrame;

	public SpriteSheet(String name) {
		TextureAtlas atlas = Globals.assetManagerManager.getTextureAtlas();
		idle = new Animation<TextureRegion>(FRAME_DURATION, atlas.findRegions(name + "Idle"), PlayMode.LOOP);
		walk = new Animation<TextureRegion>(FRAME_DURATION, atlas.findRegions(name + "Walk"), PlayMode.LOOP);

		active = walk;
	}
}
