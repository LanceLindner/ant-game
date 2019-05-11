package main.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Entity {
	private int x;
	private int y;

	private static Texture img = new Texture("assets/sprites/sprites/box.png");

	public Entity() {
		x = 300;
		y = 300;
	}

	public void update() {
		x += (int) (Math.random() * 10.0 - 5.0);
		y += (int) (Math.random() * 10.0 - 5.0);

	}

	public void draw(SpriteBatch batch) {
		batch.draw(img, x, y);
	}
}
