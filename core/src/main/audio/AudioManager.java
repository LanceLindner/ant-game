package main.audio;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class AudioManager {
	public static ArrayList<MusicContainer> musicContainers = new ArrayList<MusicContainer>();

	public static Music loadMusic(String musicName) {
		return Gdx.audio.newMusic(Gdx.files.internal("assets/music/" + musicName + ".mp3"));
	}

	private static void addMusicContainer(MusicContainer musicContainer) {
		musicContainers.add(musicContainer);
	}

	public static void streamMusic(MusicContainer musicContainer) {
		musicContainer.getMusic().setPan(0, 0);
		addMusicContainer(musicContainer);
		musicContainer.getMusic().play();

	}

	public static void update(double listenerX, double listenerY) {
		for (MusicContainer musicContainer : musicContainers) {
			if (musicContainer.isGlobal() == false) {
				update(musicContainer, listenerX, listenerY);
			}
		}
	}

	private static void update(MusicContainer musicContainer, double listenerX, double listenerY) {
		double minVolumeDistance = musicContainer.getMinVolumeDistance();
		double maxVolumeDistance = musicContainer.getMaxVolumeDistance();

		double xDistance = Math.abs(musicContainer.getX() - listenerX) - maxVolumeDistance;
		double yDistance = Math.abs(musicContainer.getY() - listenerY) - maxVolumeDistance;

		if (xDistance < 0)
			xDistance = 0;
		if (yDistance < 0)
			yDistance = 0;

		float pan = panBounds(xDistance / minVolumeDistance);
		float volume = volumeBounds(1 - xDistance / minVolumeDistance / 2 - yDistance / minVolumeDistance / 2);

		if (musicContainer.isInvertedRange() == true)
			volume = 1 - volume;

		musicContainer.getMusic().setPan(pan, volume);
	}

	public static float volumeBounds(double volume) {
		return volumeBounds((float) volume);
	}

	public static float volumeBounds(float volume) {
		if (volume < 0)
			volume = 0;
		if (volume > 1)
			volume = 1;
		return volume;
	}

	public static float panBounds(double pan) {
		return panBounds((float) pan);
	}

	public static float panBounds(float pan) {
		if (pan < -1)
			pan = -1;
		if (pan > 1)
			pan = 1;
		return pan;
	}
}
