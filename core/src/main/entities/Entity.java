package main.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.main.Globals;

public abstract class Entity {
	protected double x;
	protected double y;

	protected Texture image = new Texture("assets/sprites/sprites/box.png");

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void update();

	public void draw(SpriteBatch batch) {
		int drawX = (int) (x * Globals.TILE_SIZE);
		int drawY = (int) (y * Globals.TILE_SIZE);
		batch.draw(image, drawX, drawY, Globals.TILE_SIZE, Globals.TILE_SIZE);
	}
}
