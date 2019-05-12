package main.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.persistance.Persistance;
import main.world.World;

public class Main extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private World world;

	@Override
	public void create() {
		Persistance.load();

		world = new World();

		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.2f, 0.1f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		world.update();
		world.draw(camera, batch);

		batch.end();
	}

	@Override
	public void dispose() {
		Persistance.save();

		batch.dispose();

		world.dispose();
	}
}
