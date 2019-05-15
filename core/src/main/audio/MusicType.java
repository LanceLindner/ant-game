package main.audio;

import java.util.HashMap;

public enum MusicType {
	SNORE(1);

	private int id;

	private MusicType(int id) {
		this.id = id;
	}

	private static HashMap<Integer, MusicType> tileMap;

	static {
		tileMap = new HashMap<Integer, MusicType>();
		for (MusicType musicType : MusicType.values()) {
			tileMap.put(musicType.id, musicType);
		}
	}

	public static MusicType getTileTypeById(int id) {
		return tileMap.get(id);
	}
}
