package main.audio;

import com.badlogic.gdx.audio.Music;

public class MusicContainer {
	private Music music;
	private double x;
	private double y;
	private MusicType musicType;

	public Music getMusic() {
		return music;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public MusicType getMusicType() {
		return musicType;
	}

	public MusicContainer(Music music, double x, double y, MusicType musicType) {
		this.music = music;
		this.x = x;
		this.y = y;
		this.musicType = musicType;
		if (musicType.isShouldLoop() == true)
			music.setLooping(true);
	}

	// this will not be neccessary when we have the assetManager implemented
	public void dispose() {
		music.dispose();
	}
}
