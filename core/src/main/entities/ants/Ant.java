package main.entities.ants;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.entities.Entity;
import main.entities.brain.Brain;
import main.floors.Floor;
import main.main.Globals;
import main.tiles.Tile;

//will probably be made abstract soon
public class Ant extends Entity {

	private Brain brain;
	private int health;
	private int direction;
	private boolean isAlive;

	private int numberOfInputNeurons = 4;
	private int numberOfHiddenNeurons = 4;
	private int numberOfOutputNeurons = 4;

	private double cooldown;

	public Ant(Floor floor, int x, int y) {
		super(floor, x, y);
		image = new Texture("assets/sprites/sprites/ant.png");
		isAlive = true;
		direction = 1;
		health = 10;

		floor.lightArea(x, y);

		brain = new Brain(numberOfInputNeurons, numberOfHiddenNeurons, numberOfOutputNeurons);

		brain.addAxon(0, 0, 0, 2);
		// brain.addRandomAxons((int) (Math.random() * 10));

		cooldown = 0;
	}

	@Override
	public void draw(SpriteBatch batch) {
		float drawX = (float) (x * Globals.TILE_SIZE);
		float drawY = (float) (y * Globals.TILE_SIZE);

		// drawY += 2 * Globals.TILE_SIZE;
		// System.out.println(direction);
		switch (direction) {
		case 0:

			drawY -= cooldown * Globals.TILE_SIZE;
			break;
		case 1:
			drawX -= cooldown * Globals.TILE_SIZE;
			break;
		case 2:
			drawY += cooldown * Globals.TILE_SIZE;
			break;
		case 3:
			drawX -= cooldown * Globals.TILE_SIZE;
			break;
		default:
			break;

		}

		batch.draw(image, drawX, drawY, Globals.TILE_SIZE, Globals.TILE_SIZE);
	}

	@Override
	public void update() {
		if (cooldown <= 0) {
			Tile tileAhead = getTileAhead();

			int[] inputValues = new int[numberOfInputNeurons];
			inputValues[0] = 0;
			inputValues[1] = 0;
			inputValues[2] = 0;
			inputValues[3] = 0;

			if (isAlive) {

				// We will need to work a couple things out in the future, this right now is
				// just for testing

				/*
				 * Index 0: Whether the tile ahead is not a wall
				 */

				// Will always be equal to 1 because otherwise the if statement wouldn't execute

				if (tileAhead.getTileType() == null || !tileAhead.getTileType().isSolid()) {
					inputValues[0] = 1;
				} else {
					inputValues[0] = 0;
				}

				brain.update(inputValues);

				int[] outputValues = brain.getOutput();

				if (outputValues[0] == 1) {
					moveForward();
				} else {
					// turnRight();
				}

			}
			cooldown = 1;
		}
		cooldown -= Globals.deltaTime;
	}

	// Will return the tile ahead of the direction the ant is facing, defaults to
	// the tile it is on
	private Tile getTileAhead() {
		switch (direction) {
		case 0:
			return floor.getTile((int) x, (int) (y + 1));
		case 1:
			return floor.getTile((int) (x + 1), (int) y);
		case 2:
			return floor.getTile((int) x, (int) (y - 1));
		case 3:
			return floor.getTile((int) (x - 1), (int) y);
		default:
			return floor.getTile((int) x, (int) y);
		}
	}

	private void moveForward() {
		Tile tileAhead = getTileAhead();

		if (tileAhead.getTileType() != null) {
			if (tileAhead.getTileType().isSolid()) {
				isAlive = false;
				return;
			}
		}
		floor.getTile((int) x, (int) y).removeEntity();
		x = tileAhead.getX();
		y = tileAhead.getY();
		floor.getTile((int) x, (int) y).addEntity(this);
		floor.lightArea((int) x, (int) y);
	}

	private void turnLeft() {
		direction = ((direction + 3) % 4);
		// moveForward();
	}

	private void turnRight() {
		direction = (direction + 1) % 4;
		// moveForward();
	}
}
