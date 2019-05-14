package main.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.entities.Entity;
import main.floors.Floor;

public class Tile {
	private Floor floor;
	private int x;
	private int y;
	private TileType tileType;

	private Entity entity;

	private double visibility;

	public Tile(Floor floor, int x, int y, TileType tileType) {
		this.floor = floor;
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

	public void update() {
		if (entity != null) {
			entity.update();
		}
	}

	public void draw(SpriteBatch batch) {
		if (entity != null) {
			entity.draw(batch);
		}
	}
}
