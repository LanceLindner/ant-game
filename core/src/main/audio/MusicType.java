package main.audio;

import java.util.HashMap;

public enum MusicType {
	LONG_DISTANCE(1, false, 500, 25, false, true, true),
	SHORT_DISTANCE(2, false, 5, 0, false, true, true);

	private int id;

	private boolean global;
	private int minVolumeDistance;
	private int maxVolumeDistance;
	private boolean rangeInverted;
	private boolean shouldLoop;
	private boolean randomStart;

	private MusicType(int id, boolean global, int minVolumeDistance, int maxVolumeDistance, boolean invertedRange,
			boolean shouldLoop, boolean randomStart) {
		this.id = id;
		this.global = global;
		this.minVolumeDistance = minVolumeDistance;
		this.maxVolumeDistance = maxVolumeDistance;
		this.rangeInverted = invertedRange;
		this.shouldLoop = shouldLoop;
		this.randomStart = randomStart;
	}

	public boolean isGlobal() {
		return global;
	}

	public int getMinVolumeDistance() {
		return minVolumeDistance;
	}

	public int getMaxVolumeDistance() {
		return maxVolumeDistance;
	}

	public boolean isRangeInverted() {
		return rangeInverted;
	}

	public boolean isShouldLoop() {
		return shouldLoop;
	}

	public boolean isRandomStart() {
		return randomStart;
	}

	private static HashMap<Integer, MusicType> tileMap;

	static {
		tileMap = new HashMap<Integer, MusicType>();
		for (MusicType musicType : MusicType.values()) {
			tileMap.put(musicType.id, musicType);
		}
	}

	public static MusicType getMusicTypeById(int id) {
		return tileMap.get(id);
	}
}
