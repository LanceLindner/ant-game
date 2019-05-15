package main.tiles;

import java.util.HashMap;

public enum TileType {
	WALL1(17, true), WALL2(18, true), WALL3(19, true), WALL4(20, true);

	private final int id;
	private boolean solid;

	private String musicName;
	private int minVolumeDistance;
	private int maxVolumeDistance;

	private TileType(int id, boolean solid) {
		this.id = id;
		this.solid = solid;
	}

	private TileType(int id, String musicName, int minVolumeDistance, int maxVolumeDistance) {
		this.id = id;
		this.musicName = musicName;
		this.minVolumeDistance = minVolumeDistance;
		this.maxVolumeDistance = maxVolumeDistance;
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

	public int getMinVolumeDistance() {
		return minVolumeDistance;
	}

	public int getMaxVolumeDistance() {
		return maxVolumeDistance;
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
