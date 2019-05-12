package main.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.floors.Floor;
import main.persistance.Persistance;

public class Main extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Floor floor;

	@Override
	public void create() {
		Persistance.load();

		floor = new Floor();

		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.2f, 0.1f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		floor.update();
		floor.draw(camera, batch);

		batch.end();
	}

	@Override
	public void dispose() {
		Persistance.save();

		batch.dispose();

		floor.dispose();
	}
}
