package main.tiles;

import java.util.HashMap;

import main.audio.MusicContainer;

public enum TileType {
	WALL1(17, true), WALL2(18, true), WALL3(19, true), WALL4(20, true);

	private final int id;
	private boolean solidity;
	private MusicContainer musicContainer;

	private TileType(int id, boolean solidity) {
		this.id = id;
		this.solidity = solidity;
	}

	private TileType(int id, String musicName, int minVolumeDistance, int maxVolumeDistance) {
		this.id = id;
		// musicContainer = new MusicContainer();
	}

	public int getID() {
		return id;
	}

	public boolean isSolid() {
		return solidity;
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
