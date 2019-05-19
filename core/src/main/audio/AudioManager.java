package main.audio;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Sound;

import main.main.Globals;

public class AudioManager {
	private static double soundRange = Globals.TILES_PER_WIDTH * 2;
	private static double zoomModifier = 1;

	public static ArrayList<MusicContainer> musicContainers = new ArrayList<MusicContainer>();

	private static double listenerX = 0;
	private static double listenerY = 0;

	public static void cameraZoomed(double zoom, double maxZoom) {
		zoomModifier = 1 / zoom;
		soundRange = Globals.TILES_PER_WIDTH * 2 * zoom;
	}

	public static void playSound(Sound sound, double soundX, double soundY) {
		double[] distances = getDistance(soundX, soundY);

		int panDirection = getPanDirection(distances[0]);

		float volume = volumeBounds(
				applyZoomModifierToVolume(1 - distances[0] / soundRange / 2 - distances[1] / soundRange / 2));
		float pitch = (float) (Math.random() * .1 + 0.95);
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
		if (musicContainer.getMusicType().isGlobal() == true) {
			musicContainer.getMusic().setPan(0, 1);
		} else {
			double minVolumeDistance = musicContainer.getMusicType().getMinVolumeDistance();
			double maxVolumeDistance = musicContainer.getMusicType().getMaxVolumeDistance();

			double[] distances = getDistance(musicContainer.getX(), musicContainer.getY());

			double xDistanceCorrected = Math.abs(distances[0]) - maxVolumeDistance;
			double yDistanceCorrected = Math.abs(distances[1]) - maxVolumeDistance;

			double[] correctedDistances = new double[] { xDistanceCorrected, yDistanceCorrected };

			if (correctedDistances[0] < 0)
				correctedDistances[0] = 0;
			if (correctedDistances[1] < 0)
				correctedDistances[1] = 0;

			int panDirection = getPanDirection(distances[0]);

			float volume = volumeBounds(applyZoomModifierToVolume(
					1 - correctedDistances[0] / minVolumeDistance / 2 - correctedDistances[1] / minVolumeDistance / 2));
			if (musicContainer.getMusicType().isRangeInverted() == true)
				volume = 1 - volume;

			float pan = panBounds(correctedDistances[0] / minVolumeDistance * panDirection);

			musicContainer.getMusic().setPan(pan, volume);
		}
	}

	private static double[] getDistance(double soundX, double soundY) {
		double distanceX = soundX - listenerX;
		double distanceY = soundY - listenerY;
		return new double[] { distanceX, distanceY };
	}

	private static int getPanDirection(double xDistance) {
		if (xDistance < 0)
			return -1;
		else {
			return 1;
		}
	}

	private static float volumeBounds(double volume) {
		if (volume < 0)
			volume = 0;
		if (volume > 1)
			volume = 1;
		return (float) volume;
	}

	private static float pitchBounds(double pitch) {
		if (pitch < 0.5)
			pitch = 0.5;
		if (pitch > 2)
			pitch = 2;
		return (float) pitch;
	}

	private static float panBounds(double pan) {
		if (pan < -1)
			pan = -1;
		if (pan > 1)
			pan = 1;
		return (float) pan;
	}

	private static float applyZoomModifierToVolume(double volume) {
		return (float) (volume * zoomModifier);
	}
}
