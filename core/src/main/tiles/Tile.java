package main.tiles;

import main.entities.Entity;
import main.floors.Floor;

public class Tile {
	private Floor floor;
	private int x;
	private int y;
	private TileType tileType;

	private Entity entity;

	private double visibility;

	public Tile(int x, int y, TileType tileType) {
		this.x = x;
		this.y = y;
		this.tileType = tileType;

	}

	public boolean containsEntity() {
		return (entity != null);
	}

	public void addEntity(Entity newEntity) {
		entity = newEntity;
	}

	public void removeEntity() {
		entity = null;
	}

	public TileType getTileType() {
		return tileType;
	}
}
