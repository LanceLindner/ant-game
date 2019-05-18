package main.audio;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

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
		this.sound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/" + sound + ".mp3"));
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
