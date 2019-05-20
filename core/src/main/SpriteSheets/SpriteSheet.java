package main.SpriteSheets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import main.audio.AudioManager;
import main.audio.SoundType;
import main.main.Globals;

public class SpriteSheet {
	private float frameDuration = (float) (1 / 5 + Math.random() / 10 - (1 / 20));

	private Animation<TextureRegion> active;
	private Animation<TextureRegion> idle, walk, die;

	private float stateBegin = 0;
	private TextureRegion lastFrame;

	public SpriteSheet(String name) {
		TextureAtlas atlas = Globals.assetManagerManager.getTextureAtlas();
		idle = new Animation<TextureRegion>(frameDuration, atlas.findRegions(name + "Idle"), PlayMode.LOOP);
		walk = new Animation<TextureRegion>(frameDuration, atlas.findRegions(name + "Walk"), PlayMode.LOOP);
		die = new Animation<TextureRegion>(frameDuration * 5, atlas.findRegions(name + "Die"), PlayMode.NORMAL);

		active = walk;
	}

	public void setToIdle() {
		if (active != idle) {
			active = idle;
			stateBegin = (float) Globals.globalTime;
		}
	}

	public void setToWalk() {
		if (active != walk) {
			active = walk;
			stateBegin = (float) Globals.globalTime;
		}
	}

	public void setToDie() {
		if (active != die) {
			active = die;
			stateBegin = (float) Globals.globalTime;
		}
	}

	public TextureRegion getCurrentFrame(double x, double y) {
		TextureRegion newFrame = active.getKeyFrame((float) Globals.globalTime - stateBegin);
		if (newFrame != lastFrame) {
			lastFrame = newFrame;
			if (active == walk) {
				if (Math.random() < 0.1) {
					int id = 2;
					AudioManager.playSound(SoundType.getSoundTypeById(id).getSound(), x, y);
				}
			}
		}
		return lastFrame;
	}
}
