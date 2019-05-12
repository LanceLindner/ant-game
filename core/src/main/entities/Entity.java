package main.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.main.Globals;

public abstract class Entity {
	protected int x;
	protected int y;

	protected static Texture image = new Texture("assets/sprites/sprites/box.png");

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update() {
		move();
	}

	public void move() {

	}

	public void draw(SpriteBatch batch) {
		batch.draw(image, x, y, Globals.TILE_SIZE, Globals.TILE_SIZE);
	}
}
