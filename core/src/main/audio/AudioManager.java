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
			if (musicConatiner.global == false) {
				float pan = panBounds((float) (Math.abs(musicConatiner.x - listenerX) / musicConatiner.range));
				float volume = volumeBounds(
						(float) (1 - Math.abs(musicConatiner.x - listenerX) / musicConatiner.range / 2
								- Math.abs(musicConatiner.y - listenerY) / musicConatiner.range / 2));
				if (musicConatiner.invertedRange == true)
					volume = 1 - volume;
				musicConatiner.music.setPan(pan, volume);
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
