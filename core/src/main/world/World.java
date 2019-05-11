package main.world;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.entities.Entity;

public class World {
	public ArrayList<Entity> entities = new ArrayList<Entity>();

	public World() {
		for (int i = 0; i < 5000; ++i) {
			entities.add(new Entity());
		}
	}

	public void update() {
		for (Entity entity : entities) {
			entity.update();
		}
	}

	public void draw(OrthographicCamera camera, SpriteBatch batch) {
		for (Entity entity : entities) {
			entity.draw(batch);
		}
	}
}
