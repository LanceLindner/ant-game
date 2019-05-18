package main.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.floors.Floor;
import main.main.AssetManagerManager;
import main.main.Globals;

public abstract class Entity {
	protected double x;
	protected double y;
	protected Floor floor;

	protected Texture image = AssetManagerManager.getTexture("box");

	public Entity(Floor floor, int x, int y) {
		this.x = x;
		this.y = y;
		this.floor = floor;

		floor.getTile(x, y).addEntity(this);
	}

	public abstract void update();

	public void draw(SpriteBatch batch) {
		int drawX = (int) (x * Globals.TILE_SIZE);
		int drawY = (int) (y * Globals.TILE_SIZE);
		batch.draw(image, drawX, drawY, Globals.TILE_SIZE, Globals.TILE_SIZE);
	}
}
