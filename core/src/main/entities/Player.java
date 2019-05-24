package main.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import main.audio.AudioManager;
import main.floors.Floor;
import main.main.Globals;
import main.main.InputManager;
import main.tiles.Tile;

public class Player extends Entity {
	private static final double INITIAL_VELOCITY = 7.5;
	private double velocityMutliplier;

	public Player(Floor floor, int x, int y) {
		super(floor, x, y);
		spriteSheet = new main.spritesheets.SpriteSheet("player");
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
			moveY(velocityMutliplier * Globals.deltaTime);
			direction = 0;
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			moveX(-velocityMutliplier * Globals.deltaTime);
			direction = 3;
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			moveY(-velocityMutliplier * Globals.deltaTime);
			direction = 2;
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			moveX(velocityMutliplier * Globals.deltaTime);
			direction = 1;
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

	public void moveX(double deltaX) {
		double newX = x + deltaX;
		if (deltaX > 0) {// right
			int edge = (int) Math.round(newX + 0.5);
			if (edge != Math.round(x)) {
				for (int i = (int) Math.round(y - 0.5); i < (Math.round(y + 0.5) + 0.01); ++i) {
					if (floor.isTileValid(edge, i)) {
						Tile newTile = floor.getTile(edge, i);
						if (newTile.getTileType() != null && newTile.getTileType().isSolid() == true) {
							x = newTile.getX() - 0.5 - 0.51;
							return;
						}
					} else {
						x = edge - 0.5 - 0.51;
						return;
					}
				}
			}
		} else {// left
			int edge = (int) Math.round(newX - 0.5);
			if (edge != Math.round(x)) {
				for (int i = (int) Math.round(y - 0.5); i < (Math.round(y + 0.5) + 0.01); ++i) {
					if (floor.isTileValid(edge, i)) {
						Tile newTile = floor.getTile(edge, i);
						if (newTile.getTileType() != null && newTile.getTileType().isSolid() == true) {
							x = newTile.getX() + 0.5 + 0.51;
							return;
						}
					} else {
						x = edge + 0.5 + 0.51;
						return;
					}
				}
			}
		}
		x = newX;
	}

	public void moveY(double deltaY) {
		double newY = y + deltaY;
		if (deltaY > 0) {// up
			int edge = (int) Math.round(newY + 0.5);
			if (edge != Math.round(y)) {
				for (int i = (int) Math.round(x - 0.5); i < (Math.round(x + 0.5) + 0.01); ++i) {
					if (floor.isTileValid(i, edge)) {
						Tile newTile = floor.getTile(i, edge);
						if (newTile.getTileType() != null && newTile.getTileType().isSolid() == true) {
							y = newTile.getY() - 0.5 - 0.51;
							return;
						}
					} else {
						y = edge - 0.5 - 0.51;
						return;
					}
				}
			}
		} else {// down
			int edge = (int) Math.round(newY - 0.5);
			if (edge != Math.round(y)) {
				for (int i = (int) Math.round(x - 0.5); i < (Math.round(x + 0.5) + 0.01); ++i) {
					if (floor.isTileValid(i, edge)) {
						Tile newTile = floor.getTile(i, edge);
						if (newTile.getTileType() != null && newTile.getTileType().isSolid() == true) {
							y = newTile.getY() + 0.5 + 0.51;
							return;
						}
					} else {
						y = edge + 0.5 + 0.51;
						return;
					}
				}
			}
		}
		y = newY;
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
