package main.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.world.World;

public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private World world;

	Texture img;

	@Override
	public void create() {
		Persistance.load();

		world = new World();

		batch = new SpriteBatch();
		img = new Texture("assets/sprites/sprites/box.png");
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.2f, 0.1f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);

		world.update();
		world.render(camera, batch);

		batch.end();
	}

	// Called when the window is exited
	// Saves game data and disposes disposable objects

	@Override
	public void dispose() {
		Persistance.save();

		batch.dispose();
		img.dispose();
	}
}
