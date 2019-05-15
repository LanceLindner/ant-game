package main.tiles;

import java.util.HashMap;

import main.audio.MusicType;

public enum TileType {
	WALL1(17, true), WALL2(18, true), WALL3(19, true), WALL4(20, true), SNORE1(33, "snore", 1), AHH1(34, "test", 2);

	private final int id;
	private boolean solid;

	private String musicName;

	private MusicType musicType;

	private TileType(int id, boolean solid) {
		this.id = id;
		this.solid = solid;
	}

	private TileType(int id, String musicName, int musicTypeId) {
		this.id = id;
		this.musicName = musicName;
		this.musicType = MusicType.getMusicTypeById(musicTypeId);
	}

	public int getID() {
		return id;
	}

	public boolean isSolid() {
		return solid;
	}

	public String getMusicName() {
		return musicName;
	}

	public MusicType getMusicType() {
		return musicType;
	}

	private static HashMap<Integer, TileType> tileMap;

	static {
		tileMap = new HashMap<Integer, TileType>();
		for (TileType tileType : TileType.values()) {
			tileMap.put(tileType.id, tileType);
		}
	}

	public static TileType getTileTypeById(int id) {
		return tileMap.get(id);
	}
}
