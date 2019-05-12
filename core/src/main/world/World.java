package main.world;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import main.entities.Entity;

public class World extends TiledMap {
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private ArrayList<Entity> entities = new ArrayList<Entity>();

	public World() {
		for (int i = 0; i < 100; ++i) {
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
