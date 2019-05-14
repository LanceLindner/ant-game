package main.audio;

import com.badlogic.gdx.audio.Music;

public class MusicContainer {
	public Music music;
	public double x;
	public double y;
	boolean global;
	public int range;
	public boolean invertedRange;

	public MusicContainer(Music music, boolean global) {
		this(music, 0, 0, global, 100, false, true);
	}

	public MusicContainer(Music music, double x, double y, boolean global, int range, boolean invertedRange,
			boolean shouldLoop) {
		this.music = music;
		this.x = x;
		this.y = y;
		this.global = global;
		this.range = range;
		this.invertedRange = invertedRange;
		if (shouldLoop == true)
			music.setLooping(shouldLoop);
	}

	// this will not be neccessary when we have the assetManager implemented
	public void dispose() {
		music.dispose();
	}
}
