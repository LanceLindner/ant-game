package main.audio;

import java.util.ArrayList;

public class AudioManager {
	public static ArrayList<MusicContainer> MusicContainers = new ArrayList<MusicContainer>();

	public static double listenerX = 0;
	public static double listenerY = 0;

	public static void update(int newListenerX, int newListenerY) {
		listenerX = newListenerX;
		listenerY = newListenerY;
		for (MusicContainer musicConatiner : MusicContainers) {
			if (musicConatiner.isGlobal() == false) {
				float minVolumeDistance = musicConatiner.getMinVolumeDistance();
				float maxVolumeDistance = musicConatiner.getMaxVolumeDistance();

				float xDistance = (float) (Math.abs(musicConatiner.getX() - listenerX) - maxVolumeDistance);
				float yDistance = (float) (Math.abs(musicConatiner.getY() - listenerY) - maxVolumeDistance);

				if (xDistance < 0)
					xDistance = 0;
				if (yDistance < 0)
					yDistance = 0;

				float pan = panBounds(xDistance / minVolumeDistance);
				float volume = volumeBounds(1 - xDistance / minVolumeDistance / 2 - yDistance / minVolumeDistance / 2);

				if (musicConatiner.isInvertedRange() == true)
					volume = 1 - volume;

				musicConatiner.getMusic().setPan(pan, volume);
			}
		}
	}

	public static float volumeBounds(float volume) {
		if (volume < 0)
			volume = 0;
		if (volume > 1)
			volume = 1;
		return volume;
	}

	public static float panBounds(float pan) {
		if (pan < -1)
			pan = -1;
		if (pan > 1)
			pan = 1;
		return pan;
	}
}
