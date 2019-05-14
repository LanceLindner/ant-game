package main.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import main.main.Globals;

public class Player extends Entity {
	private double velocityMutliplier = 5;

	public Player(int x, int y) {
		super(x, y);
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
	public void move() {
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
	}

	public void updateCamera(OrthographicCamera camera) {
		int cameraX = (int) (x * Globals.TILE_SIZE + Globals.TILE_SIZE / 2);
		int cameraY = (int) (y * Globals.TILE_SIZE + Globals.TILE_SIZE / 2);
		camera.position.set(new Vector2(cameraX, cameraY), 0);
		camera.update();
	}
}
