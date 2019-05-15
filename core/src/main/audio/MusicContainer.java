package main.audio;

import com.badlogic.gdx.audio.Music;

public class MusicContainer {
	private Music music;
	private double x;
	private double y;
	private boolean global;
	private int range;
	private int clampRange;
	private boolean invertedRange;

	public Music getMusic() {
		return music;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isGlobal() {
		return global;
	}

	public int getRange() {
		return range;
	}

	public int getClampRange() {
		return clampRange;
	}

	public boolean isInvertedRange() {
		return invertedRange;
	}

	public MusicContainer(Music music, boolean global) {
		this(music, 0, 0, global, 100, 0, false, true);
	}

	public MusicContainer(Music music, double x, double y, boolean global, int range, int clampRange,
			boolean invertedRange, boolean shouldLoop) {
		this.music = music;
		this.x = x;
		this.y = y;
		this.global = global;
		this.range = range;
		this.clampRange = clampRange;
		this.invertedRange = invertedRange;
		if (shouldLoop == true)
			music.setLooping(shouldLoop);
	}

	// this will not be neccessary when we have the assetManager implemented
	public void dispose() {
		music.dispose();
	}
}
