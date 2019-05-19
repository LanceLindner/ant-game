package main.audio;

import java.util.HashMap;

import com.badlogic.gdx.audio.Sound;

import main.main.Globals;

public enum SoundType {// should probably dispose sounds
	test(1, "ant"),

	;

	private int id;
	private Sound sound;

	public Sound getSound() {
		return sound;
	}

	private SoundType(int id, String sound) {
		this.id = id;
		this.sound = Globals.assetManagerManager.getSound(sound);
	}

	private static HashMap<Integer, SoundType> soundMap;

	static {
		soundMap = new HashMap<Integer, SoundType>();
		for (SoundType soundType : SoundType.values()) {
			soundMap.put(soundType.id, soundType);
		}
	}

	public static SoundType getSoundTypeById(int id) {
		return soundMap.get(id);
	}
}
