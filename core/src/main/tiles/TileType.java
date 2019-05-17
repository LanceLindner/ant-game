package main.tiles;

import java.util.HashMap;

import main.audio.MusicType;

public enum TileType {
	WALL1(17, true),
	WALL2(18, true),
	WALL3(19, true),
	WALL4(20, true),

	SNORE1(33, "snore", 1),
	AHH1(34, "test", 2),

	SHADOW1(49),
	SHADOW2(50),
	SHADOW3(51),
	SHADOW4(52),
	SHADOW5(53),
	SHADOW6(54),

	GREEN_RESIDUE1(65),
	GREEN_RESIDUE2(66),
	GREEN_RESIDUE3(67),
	GREEN_RESIDUE4(68),

	YELLOW_RESIDUE1(71),
	YELLOW_RESIDUE2(72),
	YELLOW_RESIDUE3(73),
	YELLOW_RESIDUE4(74),

	NULL(87);

	private final int id;
	private boolean solid = false;

	private String musicName;

	private MusicType musicType;

	private TileType(int id) {
		this.id = id;
	}

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
