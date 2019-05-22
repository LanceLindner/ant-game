package main.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import main.audio.AudioManager;
import main.floors.Floor;
import main.main.Globals;
import main.main.InputManager;

public class Player extends Entity {
	private static final double INITIAL_VELOCITY = 7.5;
	private double velocityMutliplier;

	public Player(Floor floor, int x, int y) {
		super(floor, x, y);
		AudioManager.update(x, y);
		floor.getTile(x, y).removeEntity();
	}

	public int[] getVisibleTileBounds() {
		int[] bounds = new int[4];
		bounds[0] = (int) (x - (double) Globals.TILES_PER_WIDTH / 2 * velocityMutliplier / INITIAL_VELOCITY - 3);
		bounds[1] = (int) (x + (double) Globals.TILES_PER_WIDTH / 2 * velocityMutliplier / INITIAL_VELOCITY + 3);
		bounds[2] = (int) (y - (double) Globals.TILES_PER_HEIGHT / 2 * velocityMutliplier / INITIAL_VELOCITY - 3);
		bounds[3] = (int) (y + (double) Globals.TILES_PER_HEIGHT / 2 * velocityMutliplier / INITIAL_VELOCITY + 3);
		return bounds;
	}

	@Override
	public void update() {
		if (Gdx.input.isKeyPressed(Keys.W)) {
			y += velocityMutliplier * Globals.deltaTime;
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			x -= velocityMutliplier * Globals.deltaTime;
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			y -= velocityMutliplier * Globals.deltaTime;
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			x += velocityMutliplier * Globals.deltaTime;
		}
		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)) {
			if (Gdx.input.isKeyPressed(Keys.EQUALS)) {
				InputManager.zoom(-0.25);
			}
			if (Gdx.input.isKeyPressed(Keys.MINUS)) {
				InputManager.zoom(0.25);
			}
			if (Gdx.input.isKeyPressed(Keys.NUM_0)) {
				InputManager.setZoom(1);
			}
		}

		AudioManager.update(x, y);
	}

	public void updateCameraForPadding(OrthographicCamera camera) {
		int cameraX = (int) ((x + Math.random() - 0.5) * Globals.TILE_SIZE + Globals.TILE_SIZE / 2);
		int cameraY = (int) ((y + Math.random() - 0.5) * Globals.TILE_SIZE + Globals.TILE_SIZE / 2);
		camera.position.set(new Vector2(cameraX, cameraY), 0);
		camera.update();
	}

	public void updateCamera(OrthographicCamera camera) {
		int cameraX = (int) (x * Globals.TILE_SIZE + Globals.TILE_SIZE / 2);
		int cameraY = (int) (y * Globals.TILE_SIZE + Globals.TILE_SIZE / 2);
		camera.position.set(new Vector2(cameraX, cameraY), 0);
		camera.update();

		velocityMutliplier = INITIAL_VELOCITY * camera.zoom;
	}
}
