package main.tiles;

public enum TileType {
	WALL1(17, true), WALL2(18, true), WALL3(19, true), WALL4(20, true);

	private final int id;
	private boolean solidity;

	private TileType(int id, boolean solidity) {
		this.id = id;
		this.solidity = solidity;
	}

	public int getID() {
		return id;
	}

	public boolean isSolid() {
		return solidity;
	}
}
