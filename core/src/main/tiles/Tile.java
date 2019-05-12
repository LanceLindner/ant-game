package main.tiles;

import main.entities.Entity;
import main.world.World;

public class Tile {
	private World world;
	private int x;
	private int y;

	private Entity entity;

	private double visibility;

	public Tile() {

	}

	public boolean isThereAnEntityOnThisTile() {
		return entity != null ? true : false;
	}

	public void addEntity(Entity newEntity) {
		entity = newEntity;
	}

	public void removeEntity() {
		entity = null;
	}
}
