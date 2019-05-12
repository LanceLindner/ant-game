package main.floors;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import main.entities.Entity;

public class Floor {
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private ArrayList<Entity> entities = new ArrayList<Entity>();

	public Floor() {
		tiledMap = new TmxMapLoader().load("assets/floors/test3.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
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
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render(new int[] { 0 });

		// batch.setProjectionMatrix(camera.combined);

		// for (Entity entity : entities) {
		// entity.draw(batch);
		// }
	}

	public void dispose() {
		tiledMap.dispose();
		tiledMapRenderer.dispose();
	}
}
