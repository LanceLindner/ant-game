package main.audio;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Sound;

public class AudioManager {
	private static final double soundRange = 20;
	private static double zoomModifier = 1;

	public static ArrayList<MusicContainer> musicContainers = new ArrayList<MusicContainer>();

	private static double listenerX = 0;
	private static double listenerY = 0;

	public static void setZoomModifier(double zoom, double maxZoom) {
		zoomModifier = 1 / zoom;
	}

	public static void playSound(Sound sound) {
		sound.play();
	}

	public static void playSound(Sound sound, double soundX, double soundY) {
		double[] position = getDistance(soundX, soundY);

		int panDirection;

		if (position[0] < 0)
			panDirection = -1;
		else {
			panDirection = 1;
		}

		float volume = volumeBounds(1 - position[0] / soundRange / 2 - position[1] / soundRange / 2);
		float pitch = (float) (Math.random() * .2 + 0.9);
		float pan = panBounds((soundX - listenerX) / soundRange * panDirection);

		sound.play(volume, pitch, pan);
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

		double[] position = getDistance(musicContainer.getX(), musicContainer.getY());

		double xDistanceCorrected = Math.abs(position[0]) - maxVolumeDistance;
		double yDistanceCorrected = Math.abs(position[1]) - maxVolumeDistance;

		double[] correctedPosition = new double[] { xDistanceCorrected, yDistanceCorrected };

		int panDirection;

		if (position[0] < 0)
			panDirection = -1;
		else {
			panDirection = 1;
		}

		if (correctedPosition[0] < 0)
			correctedPosition[0] = 0;
		if (correctedPosition[1] < 0)
			correctedPosition[1] = 0;

		float volume = volumeBounds(
				1 - correctedPosition[0] / minVolumeDistance / 2 - correctedPosition[1] / minVolumeDistance / 2);
		if (musicContainer.getMusicType().isRangeInverted() == true)
			volume = 1 - volume;

		float pan = panBounds(correctedPosition[0] / minVolumeDistance * panDirection);

		musicContainer.getMusic().setPan(pan, volume);
	}

	private static double[] getDistance(double soundX, double soundY) {
		double distanceX = soundX - listenerX;
		double distanceY = soundY - listenerY;
		return new double[] { distanceX, distanceY };
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
