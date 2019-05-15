package main.audio;

import com.badlogic.gdx.audio.Music;

public class MusicContainer {
	private Music music;
	private double x;
	private double y;
	private boolean global;
	private int minVolumeDistance;
	private int maxVolumeDistance;
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

	public int getMinVolumeDistance() {
		return minVolumeDistance;
	}

	public int getMaxVolumeDistance() {
		return maxVolumeDistance;
	}

	public boolean isInvertedRange() {
		return invertedRange;
	}

	public MusicContainer(Music music, boolean global) {
		this(music, 0, 0, global, 100, 0, false, true);
	}

	public MusicContainer(Music music, double x, double y, boolean global, int minVolumeDistance, int maxVolumeDistance,
			boolean invertedRange, boolean shouldLoop) {
		this.music = music;
		this.x = x;
		this.y = y;
		this.global = global;
		this.minVolumeDistance = minVolumeDistance;
		this.maxVolumeDistance = maxVolumeDistance;
		this.invertedRange = invertedRange;
		if (shouldLoop == true)
			music.setLooping(shouldLoop);
	}

	// this will not be neccessary when we have the assetManager implemented
	public void dispose() {
		music.dispose();
	}
}
