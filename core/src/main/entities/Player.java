package main.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import main.audio.AudioManager;
import main.floors.Floor;
import main.main.Globals;

public class Player extends Entity {
	private static final double INITIAL_VELOCITY = 10;
	private double velocityMutliplier = 10;

	public Player(Floor floor, int x, int y) {
		super(floor, x, y);
		AudioManager.update(x, y);
		floor.getTile(x, y).removeEntity();
	}

	public int[] getVisibleTileBounds() {
		int[] bounds = new int[4];
		bounds[0] = (int) (x - Globals.TILES_PER_WIDTH / 2);
		bounds[1] = (int) (x - Globals.TILES_PER_WIDTH / 2);
		bounds[2] = (int) (y - Globals.TILES_PER_HEIGHT / 2);
		bounds[3] = (int) (y - Globals.TILES_PER_HEIGHT / 2);
		return bounds;
	}

	@Override
	public void update() {
		boolean changed = false;
		if (Gdx.input.isKeyPressed(Keys.W)) {
			y += velocityMutliplier * Globals.deltaTime;
			changed = true;
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			x -= velocityMutliplier * Globals.deltaTime;
			changed = true;
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			y -= velocityMutliplier * Globals.deltaTime;
			changed = true;
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			x += velocityMutliplier * Globals.deltaTime;
			changed = true;
		}
		if (changed == true) {
			AudioManager.update(x, y);
		}
	}

	public void updateCamera(OrthographicCamera camera) {
		int cameraX = (int) (x * Globals.TILE_SIZE + Globals.TILE_SIZE / 2);
		int cameraY = (int) (y * Globals.TILE_SIZE + Globals.TILE_SIZE / 2);
		camera.position.set(new Vector2(cameraX, cameraY), 0);
		camera.update();

		velocityMutliplier = INITIAL_VELOCITY * camera.zoom;
	}
}
