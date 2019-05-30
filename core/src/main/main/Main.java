package main.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import main.floors.Floor;
import main.floors.Overworld;
import main.persistance.Persistance;
import main.scenemanager.SceneManager;

public class Main extends Game {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private static BitmapFont font;

	private Stage stage;
	private SceneManager sceneManager;

	private Floor floor;

	@Override
	public void create() {
		Persistance.load();

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(Globals.DEFAULT_FONT_SCALE);

		Globals.windowWidth = Gdx.graphics.getWidth();
		Globals.windowHeight = Gdx.graphics.getHeight();
		Globals.windowWidthInTilePixels = Globals.TILES_PER_WIDTH * Globals.TILE_SIZE;
		Globals.windowHeightInTilePixels = Globals.TILES_PER_HEIGHT * Globals.TILE_SIZE;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Globals.windowWidthInTilePixels, Globals.windowHeightInTilePixels);
		camera.update();

		floor = new Overworld();

		Viewport viewport = new FitViewport(Globals.windowWidth, Globals.windowHeight);
		stage = new Stage(viewport);
		sceneManager = new SceneManager(stage, floor);

		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new InputManager(camera, floor));
		multiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.2f, 0.1f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Globals.deltaTime = Gdx.graphics.getDeltaTime();
		Globals.globalTime += Globals.deltaTime;

		floor.render(camera, batch);
		sceneManager.render();
	}

	@Override
	public void dispose() {
		Persistance.save();

		stage.dispose();

		batch.dispose();

		floor.dispose();

		Globals.assetManagerManager.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
		camera.update();

	}
}
