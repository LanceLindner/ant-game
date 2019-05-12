package main.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import main.floors.Floor;
import main.persistance.Persistance;

public class Main extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private static BitmapFont font;

	private Floor floor;

	@Override
	public void create() {
		Persistance.load();

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(0.2f);

		floor = new Floor();

		Globals.windowWidth = Gdx.graphics.getWidth();
		Globals.windowHeight = Gdx.graphics.getHeight();
		int numberOfGamePixelsX = Globals.windowWidth / (Globals.windowWidth / (10 * 16));
		int numberOfGamePixelsY = Globals.windowHeight / (Globals.windowHeight / (10 * 16));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, numberOfGamePixelsX * 30, numberOfGamePixelsY * 30);
		camera.update();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.2f, 0.1f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		floor.update();

		camera.position.set(new Vector2(0, 0), 0);
		camera.update();

		batch.begin();
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
