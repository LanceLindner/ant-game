package main.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import main.floors.Floor;
import main.floors.Overworld;
import main.persistance.Persistance;

public class Main extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private static BitmapFont font;

	private Stage stage;
	private Stack stack;

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
		new InputManager(camera);

		Viewport viewport = new FitViewport(Globals.windowWidth, Globals.windowHeight, camera);
		viewport.setCamera(camera);

		stage = new Stage(viewport);
		stack = new Stack();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.2f, 0.1f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Globals.deltaTime = Gdx.graphics.getDeltaTime();
		Globals.globalTime += Globals.deltaTime;

		floor.render(camera, batch);
	}

	@Override
	public void dispose() {
		Persistance.save();

		stage.dispose();

		batch.dispose();

		floor.dispose();

		Globals.assetManagerManager.dispose();
	}
}
