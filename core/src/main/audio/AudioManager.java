package main.audio;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
	public static final double soundRange = 20;

	public static ArrayList<MusicContainer> musicContainers = new ArrayList<MusicContainer>();

	private static double listenerX = 0;
	private static double listenerY = 0;

	// to be handled by the AssetManager once implemented
	public static Sound loadSound(String soundName) {
		return Gdx.audio.newSound(Gdx.files.internal("assets/sound/" + soundName + ".mp3"));
	}

	public static void playSound(Sound sound) {
		sound.play();
	}

	public static void playSound(Sound sound, double soundX, double soundY) {
		double xDistance = soundX - listenerX;
		double yDistance = soundY - listenerY;

		int panDirection;

		if (xDistance < 0)
			panDirection = -1;
		else {
			panDirection = 1;
		}

		float volume = volumeBounds(1 - xDistance / soundRange / 2 - yDistance / soundRange / 2);
		float pitch = (float) (Math.random() * .2 + 0.9);
		float pan = panBounds((soundX - listenerX) / soundRange * panDirection);

		sound.play(volume, pitch, pan);
	}

	// to be handled by the AssetManager once implemented
	public static Music loadMusic(String musicName) {
		return Gdx.audio.newMusic(Gdx.files.internal("assets/music/" + musicName + ".mp3"));
	}

	private static void addMusicContainer(MusicContainer musicContainer) {
		musicContainers.add(musicContainer);
	}

	public static void streamMusic(MusicContainer musicContainer) {
		addMusicContainer(musicContainer);
		if (musicContainer.getMusicType().isRandomStart() == true) {
			musicContainer.getMusic().setPosition((float) (Math.random() * 60));
		}
		musicContainer.getMusic().play();
		update(listenerX, listenerY);
	}

	public static void update(double newListenerX, double newListenerY) {
		listenerX = newListenerX;
		listenerY = newListenerY;
		for (MusicContainer musicContainer : musicContainers) {
			if (musicContainer.getMusicType().isGlobal() == false) {
				update(musicContainer);
			}
		}
	}

	private static void update(MusicContainer musicContainer) {
		double minVolumeDistance = musicContainer.getMusicType().getMinVolumeDistance();
		double maxVolumeDistance = musicContainer.getMusicType().getMaxVolumeDistance();

		double xDistance = musicContainer.getX() - listenerX;
		double yDistance = musicContainer.getY() - listenerY;

		double xDistanceCorrected = Math.abs(xDistance) - maxVolumeDistance;
		double yDistanceCorrected = Math.abs(yDistance) - maxVolumeDistance;

		int panDirection;

		if (xDistance < 0)
			panDirection = -1;
		else {
			panDirection = 1;
		}

		if (xDistanceCorrected < 0)
			xDistanceCorrected = 0;
		if (yDistanceCorrected < 0)
			yDistanceCorrected = 0;

		float volume = volumeBounds(
				1 - xDistanceCorrected / minVolumeDistance / 2 - yDistanceCorrected / minVolumeDistance / 2);
		if (musicContainer.getMusicType().isRangeInverted() == true)
			volume = 1 - volume;

		float pan = panBounds(xDistanceCorrected / minVolumeDistance * panDirection);

		musicContainer.getMusic().setPan(pan, volume);
	}

	public static float volumeBounds(double volume) {
		if (volume < 0)
			volume = 0;
		if (volume > 1)
			volume = 1;
		return (float) volume;
	}

	public static float pitchBounds(double pitch) {
		if (pitch < 0.5)
			pitch = 0.5;
		if (pitch > 2)
			pitch = 2;
		return (float) pitch;
	}

	public static float panBounds(double pan) {
		if (pan < -1)
			pan = -1;
		if (pan > 1)
			pan = 1;
		return (float) pan;
	}
}
